package com.lecshop.company.pay;

import com.lecshop.admin.payset.bean.AliPaySet;
import com.lecshop.admin.payset.service.PaySetService;
import com.lecshop.company.adminutil.CompanyLoginUtils;
import com.lecshop.total.companyinfo.bean.CompanyInfo;
import com.lecshop.total.companyinfo.service.CompanyInfoService;
import com.lecshop.total.renewrecord.bean.RenewRecord;
import com.lecshop.total.renewrecord.service.RenewRecordService;
import com.lecshop.total.viprecord.bean.VipModifyRecord;
import com.lecshop.total.viprecord.service.VipModifyRecordService;
import com.lecshop.utils.UnAuth;
import com.lecshop.utils.alipay.config.AlipayConfig;
import com.lecshop.utils.alipay.util.AlipayNotify;
import com.lecshop.utils.alipay.util.AlipaySubmit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 支付与继续支付控制器
 *
 * @author sunluyang on 2017/7/19.
 */
@Controller
public class ALiPayController {
    private static final String FAIL = "fail";
    private static final String UTF = "utf-8";
    private static final String SUCCESS = "success";
    private static final String ISO_8859_1 = "ISO-8859-1";
    private static final String OUT_TRADE_NO = "out_trade_no";
    private static final String TRADE_STATUS = "trade_status";
    private static final String CONTENT_TYPE = "text/html;charset=UTF-8";
    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(ALiPayController.class);
    /**
     * 支付设置service
     */
    @Autowired
    private PaySetService paySetService;
    /**
     * 续费记录service
     */
    @Autowired
    private RenewRecordService renewRecordService;
    /**
     * 公司信息service
     */
    @Autowired
    private CompanyInfoService companyInfoService;
    /**
     * VIP修改记录service
     */
    @Autowired
    private VipModifyRecordService vipModifyRecordService;

    /**
     * 支付宝支付接口
     *
     * @param request     request请求
     * @param response    响应
     * @param renewRecord 续费订单
     */
    @RequestMapping("/c_pay")
    public void pay(HttpServletRequest request, HttpServletResponse response, RenewRecord renewRecord) {
        //获取订单信息
        renewRecord.getDefaultRenewRecord(CompanyLoginUtils.getInstance().getUserIdFromSession(request), CompanyLoginUtils.getInstance().getCompanyIdFromSession(request));
        //订单中商品名称
        String orderName = renewRecord.getOrderName();
        logger.info("pay and renewRecord:{}", renewRecord);
        if (renewRecordService.addRenewRecord(renewRecord) != 1) {
            logger.error("pay fail due to addRenewRecord is not success");
            return;
        }
        // 获取支付用的信息
        String sHtmlText = getALiPayInfo(paySetService.queryPaySet().getAliPaySet(), renewRecord, orderName);
        // 设置编码集
        response.setContentType(CONTENT_TYPE);
        // 设置编码格式
        response.setCharacterEncoding(UTF);
        try {
            // 写入
            response.getWriter().write(sHtmlText);
            // 订单日志记录
            logger.debug("给订单号为：【" + renewRecord.getOrderNo() + "】的订单付款成功");
        } catch (IOException e) {
            logger.error("支付请求失败", e);
        }
    }

    /**
     * 支付宝继续支付接口
     */
    @RequestMapping("/c_goonpay")
    public void goOnPay(HttpServletResponse response, String orderNo) {
        logger.info("goOnPay and orderNo:{}", orderNo);
        //获取订单信息
        RenewRecord renewRecord = renewRecordService.queryRenewRecordByOrderNo(orderNo);
        if (Objects.isNull(renewRecord)) {
            logger.error("goOnPay fail due to renewRecord is null");
            return;
        }
        if (!"0".equals(renewRecord.getStatus())) {
            logger.error("goOnPay fail due to renewRecord status is not equals 0");
            return;
        }
        //订单中商品名称
        String orderName = renewRecord.getOrderName();
        // 获取支付用的信息
        String sHtmlText = getALiPayInfo(paySetService.queryPaySet().getAliPaySet(), renewRecord, orderName);
        // 设置编码集
        response.setContentType(CONTENT_TYPE);
        // 设置编码格式
        response.setCharacterEncoding(UTF);
        try {
            // 写入
            response.getWriter().write(sHtmlText);
            // 订单日志记录
            logger.debug("给订单号为：【" + renewRecord.getOrderNo() + "】的订单付款成功");
        } catch (IOException e) {
            logger.error("支付请求失败", e);
        }
    }

    /**
     * 支付宝支付成功
     */
    @RequestMapping("/c_alipaysuccess")
    @UnAuth
    public void aLiPaySuccess(HttpServletRequest request, HttpServletResponse response) {
        logger.info("aLiPaySuccess success");
        //查询支付方式信息
        AliPaySet aliPaySet = paySetService.queryPaySet().getAliPaySet();
        // 设置商户号
        AlipayConfig.partner = aliPaySet.getPid();
        // 设置商户秘钥
        AlipayConfig.key = aliPaySet.getKey();
        //设置收款账号
        AlipayConfig.seller_email = aliPaySet.getPayee();
        // 获取支付宝POST过来反馈信息
        Map<String, String> params = new HashMap<>();
        Map<?, ?> requestParams = request.getParameterMap();
        for (Object o : requestParams.keySet()) {
            String name = (String) o;
            String[] values = (String[]) requestParams.get(name);
            StringBuilder valueStr = new StringBuilder();
            for (int i = 0; i < values.length; i++) {
                if (i == values.length - 1) {
                    valueStr.append(values[i]);
                } else {
                    valueStr.append(values[i]);
                    valueStr.append(",");
                }
            }
            params.put(name, valueStr.toString());
        }
        // 获取支付宝的通知返回参数
        try {
            // 支付宝交易号
            String orderNo = new String(request.getParameter(OUT_TRADE_NO).getBytes(ISO_8859_1), UTF);
            // 交易状态
            String tradeStatus = new String(request.getParameter(TRADE_STATUS).getBytes(ISO_8859_1), UTF);
            if (AlipayNotify.verify(params)) {
                //查看订单状态并修改状态
                checkOrderStatus(tradeStatus, orderNo);
                //返回验证成功
                sendResultInfo(response, SUCCESS);
            } else {
                //返回验证成功失败
                sendResultInfo(response, FAIL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 支付宝-支付成功后前台跳转页面
     */
    @RequestMapping("/c_paysuccesstopage")
    public ModelAndView paySuccessToPage() {
        return new ModelAndView(new RedirectView("c_torenewrecord.htm?firstMenus=9&secondMenus=35&thirdMenus=37"));
    }

    /**
     * 支付宝支付时-获取支付需要的信息
     */
    private String getALiPayInfo(AliPaySet aliPaySet, RenewRecord renewRecord, String orderName) {
        //合作者身份(PID)
        AlipayConfig.partner = aliPaySet.getPid();
        //安全校验码(Key)
        AlipayConfig.key = aliPaySet.getKey();
        //收款账号
        AlipayConfig.seller_email = aliPaySet.getPayee();
        // 支付成功回调地址
        String notifyUrl = aliPaySet.getBackCallbackUrl() + "company/c_alipaysuccess.htm";
        // 页面回调地址
        String returnUrl = aliPaySet.getBackCallbackUrl() + "company/c_paysuccesstopage.htm";
        // 商户订单号
        String orderNo = renewRecord.getOrderNo();
        //订单金额
        String orderPrice = String.valueOf(renewRecord.getRenewMoney());
        // 订单描述
        String orderDesc = "会员续费";
        //防钓鱼时间戳
        String showUrl = "";
        // 防钓鱼时间戳
        String antiPhishingKey = "";
        // 客户端的IP地址
        String exterInvokeIp = "";
        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", "1");
        sParaTemp.put("notify_url", notifyUrl);
        sParaTemp.put("return_url", returnUrl);
        sParaTemp.put("out_trade_no", orderNo);
        sParaTemp.put("subject", orderName);
        sParaTemp.put("total_fee", orderPrice);
        sParaTemp.put("body", orderDesc);
        sParaTemp.put("show_url", showUrl);
        sParaTemp.put("anti_phishing_key", antiPhishingKey);
        sParaTemp.put("exter_invoke_ip", exterInvokeIp);
        return AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
    }

    /**
     * 支付宝-发送结果信息
     */
    private void sendResultInfo(HttpServletResponse response, String msg) throws IOException {
        PrintWriter out = response.getWriter();
        out.println(msg);
        out.flush();
        out.close();
    }

    /**
     * 支付宝-订单检测返回结果并修改订单状态
     */
    private void checkOrderStatus(String tradeStatus, String orderNo) {
        logger.info("checkOrderStatus and tradeStatus:{}\r\n orderNo:{}", tradeStatus, orderNo);
        //通过订单号查询订单
        RenewRecord renewRecord = renewRecordService.queryRenewRecordByOrderNo(orderNo);
        if (Objects.isNull(renewRecord)) {
            logger.error("checkOrderStatus is error due to renewRecord is null");
            return;
        }
        //修改订单状态 支付宝返回支付成功
        if ("TRADE_FINISHED".equals(tradeStatus) || "TRADE_SUCCESS".equals(tradeStatus)) {
            if ("0".equals(renewRecord.getStatus())) {
                //1.修改订单状态
                renewRecordService.editRenewRecordByOrderNo(orderNo);
                //2.修改VIP等级
                CompanyInfo companyInfo = companyInfoService.queryCompanyInfoById(renewRecord.getCompanyId());
                if ("0".equals(companyInfo.getVipType()) || "4".equals(companyInfo.getVipType())) {
                    companyInfoService.updateCompanyInfoForVip(companyInfo.buildVip(renewRecord.getType(), LocalDateTime.now()));
                }
                //查询是否存在充值记录
                VipModifyRecord vipModifyRecord = vipModifyRecordService.queryVipModifyRecordByTime(LocalDateTime.now(), companyInfo.getId());
                LocalDateTime startTime = Objects.isNull(vipModifyRecord) ? LocalDateTime.now() : vipModifyRecord.getEndTime().plusSeconds(1);
                LocalDateTime endTime = startTime.plusYears(1);
                //VIP记录表插入数据
                vipModifyRecordService.addVipModifyRecord(new VipModifyRecord().buildVipModifyRecordForPay(companyInfo.getId(), renewRecord.getType(), renewRecord.getOrderName(), startTime, endTime));
            }
        }
    }
}
