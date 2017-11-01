package com.lecshop.company.pay;

import com.lecshop.admin.payset.bean.AliPaySet;
import com.lecshop.admin.payset.service.PaySetService;
import com.lecshop.total.affairlist.bean.AffairOrder;
import com.lecshop.total.affairlist.bean.Disputes;
import com.lecshop.total.affairlist.bean.ImportantMatter;
import com.lecshop.total.affairlist.service.DisputesService;
import com.lecshop.total.affairlist.service.ImportantMatterService;
import com.lecshop.total.transactionrecords.bean.TransactionRecords;
import com.lecshop.total.transactionrecords.service.TransactionRecordsService;
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
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 纠纷重大事项转账控制器
 *
 * @author sunluyang on 2017/8/10.
 */
@Controller
public class ALiPayForAffairController {

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
    private Logger logger = LoggerFactory.getLogger(ALiPayForAffairController.class);
    /**
     * 支付设置service
     */
    @Autowired
    private PaySetService paySetService;
    /**
     * 纠纷service
     */
    @Autowired
    private DisputesService disputesService;
    /**
     * 重大事项service
     */
    @Autowired
    private ImportantMatterService importantMatterService;
    /**
     * 对账service
     */
    @Autowired
    private TransactionRecordsService transactionRecordsService;

    /**
     * 纠纷重大事项支付
     *
     * @param response 响应
     */
    @RequestMapping("/c_payforaffair")
    public void payForAffair(HttpServletResponse response, long id, String type) {
        logger.info("payForAffair and id:{}\r\n type:{}", id, type);
        //获取订单信息
        AffairOrder affairOrder = checkAffairOrder(type, id);
        if (Objects.isNull(affairOrder)) {
            logger.error("payForAffair fail due to affairOrder is null");
            return;
        }
        // 获取支付用的信息
        String sHtmlText = getALiPayInfo(paySetService.queryPaySet().getAliPaySet(), affairOrder);
        // 设置编码集
        response.setContentType(CONTENT_TYPE);
        // 设置编码格式
        response.setCharacterEncoding(UTF);
        try {
            // 写入
            response.getWriter().write(sHtmlText);
            // 订单日志记录
            logger.debug("给订单号为：【" + affairOrder.getOrderNo() + "】的订单付款成功");
        } catch (IOException e) {
            logger.error("支付请求失败", e);
        }
    }

    private AffairOrder checkAffairOrder(String type, long id) {
        logger.info("checkAffairOrder and id:{}\r\n type:{}", id, type);
        if ("1".equals(type)) {
            Disputes disputes = disputesService.queryDisputesById(id);
            return Objects.nonNull(disputes) && "2".equals(disputes.getStatus()) ?
                    new AffairOrder().buildAffairOrder(String.valueOf(disputes.getId()), disputes.getDesc(), disputes.getPrice().setScale(2, BigDecimal.ROUND_DOWN).toString(), type)
                    : null;
        }
        if ("2".equals(type)) {
            //获取订单信息
            ImportantMatter importantMatter = importantMatterService.queryImportantMatterById(id);
            return Objects.nonNull(importantMatter) && "2".equals(importantMatter.getStatus()) ?
                    new AffairOrder().buildAffairOrder(String.valueOf(importantMatter.getId()), importantMatter.getDesc(), importantMatter.getPrice().setScale(2, BigDecimal.ROUND_DOWN).toString(), type)
                    : null;
        }
        return null;
    }

    /**
     * 纠纷重大事项支付成功后后台回调接口
     */
    @RequestMapping("/c_alipaysuccessforaffair")
    @UnAuth
    public void aLiPaySuccessForAffair(HttpServletRequest request, HttpServletResponse response) {
        logger.info("aLiPaySuccessForAffair...");
        //查询支付方式信息
        AliPaySet aliPaySet = paySetService.queryPaySet().getAliPaySet();
        // 设置商户号
        AlipayConfig.partner = aliPaySet.getPid();
        // 设置商户秘钥
        AlipayConfig.key = aliPaySet.getKey();
        //设置收款账号
        AlipayConfig.seller_email = aliPaySet.getPayee();
        // 获取支付宝POST过来反馈信息
        Map<?, ?> requestParams = request.getParameterMap();
        Map<String, String> param = new HashMap<>();
        requestParams.forEach((key, value) -> {
            String name = (String) key;
            String[] values = (String[]) requestParams.get(name);
            StringBuilder valueStr = new StringBuilder();
            for (int i = 0; i < values.length; i++) {
                if (i == values.length - 1) {
                    valueStr.append(values[i]);
                } else {
                    valueStr.append(values[i]).append(",");
                }
            }
            param.put(name, valueStr.toString());

        });
        // 获取支付宝的通知返回参数
        try {
            // 支付宝交易号
            String orderNo = new String(request.getParameter(OUT_TRADE_NO).getBytes(ISO_8859_1), UTF);
            // 交易状态
            String orderStatus = new String(request.getParameter(TRADE_STATUS).getBytes(ISO_8859_1), UTF);
            if (AlipayNotify.verify(param)) {
                //查看订单状态并修改状态
                checkAndUpdateOrderStatus(orderStatus, orderNo);
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
     * 纠纷重大事项支付成功后前台跳转页面
     */
    @RequestMapping("/c_paysuccesstopageforaffair")
    public ModelAndView paySuccessToPageForAffair(String type) {
        String url = "1".equals(type) ? "c_todisputesservice.htm" : "c_toimportantmatter.htm";
        return new ModelAndView(new RedirectView(url));
    }

    /**
     * 支付宝支付时-获取支付需要的信息
     */
    private String getALiPayInfo(AliPaySet aliPaySet, AffairOrder affairOrder) {
        // 合作者身份(PID)
        AlipayConfig.partner = aliPaySet.getPid();
        // 安全校验码(Key)
        AlipayConfig.key = aliPaySet.getKey();
        // 收款账号
        AlipayConfig.seller_email = aliPaySet.getPayee();
        // 支付成功回调地址
        String notifyUrl = aliPaySet.getBackCallbackUrl() + "company/c_alipaysuccessforaffair.htm";
        // 页面回调地址
        String returnUrl = aliPaySet.getBackCallbackUrl() + "company/c_paysuccesstopageforaffair.htm?type=" + affairOrder.getType();
        // 商户订单号
        String orderNo = affairOrder.getOrderNo();
        // 订单名称
        String orderName = affairOrder.getOrderName();
        // 订单金额
        String orderPrice = affairOrder.getMoney();
        // 订单描述
        String orderDesc = "咨询费用";
        // 防钓鱼时间戳
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
    private void checkAndUpdateOrderStatus(String tradeStatus, String orderNo) {
        logger.info("checkAndUpdateOrderStatus and tradeStatus:{}\r\n orderNo:{}", tradeStatus, orderNo);
        String type = orderNo.split("-")[2];
        orderNo = orderNo.split("-")[0];
        Disputes disputes = new Disputes();
        ImportantMatter importantMatter = new ImportantMatter();
        if ("1".equals(type)) {
            disputes = disputesService.queryDisputesById(Long.parseLong(orderNo));
            if (Objects.isNull(disputes)) {
                logger.error("checkAndUpdateOrderStatus is fail due to disputes is null");
                return;
            }
        }
        if ("2".equals(type)) {
            //通过订单号查询订单
            importantMatter = importantMatterService.queryImportantMatterById(Long.parseLong(orderNo));
            if (Objects.isNull(importantMatter)) {
                logger.error("checkAndUpdateOrderStatus is fail due to importantMatter is null");
                return;
            }
        }
        //修改订单状态 支付宝返回支付成功
        if ("TRADE_FINISHED".equals(tradeStatus) || "TRADE_SUCCESS".equals(tradeStatus)) {
            updateStatus(type, orderNo, importantMatter, disputes);
        }
    }

    /**
     * 更新纠纷、重大事项状态并添加律师收益记录
     *
     * @param type            1 纠纷 2 重大事项
     * @param orderNo         订单id
     * @param importantMatter 重大事项实体类
     * @param disputes        纠纷实体类
     */
    private void updateStatus(String type, String orderNo, ImportantMatter importantMatter, Disputes disputes) {
        logger.info("checkAndUpdateOrderStatus and type:{}\r\n orderNo:{}\r\n orderNo:{}\r\n importantMatter\r\n disputes", type, orderNo, importantMatter, disputes);
        if ("1".equals(type) && "2".equals(disputes.getStatus())) {
            disputesService.changeDisputesStatus(Long.parseLong(orderNo), "3");
            transactionRecordsService.addTransactionRecords(TransactionRecords.buildForAdd(disputes.getLawyerId(), disputes.getPrice().divide(new BigDecimal(2)), 7));
        }
        if ("2".equals(type) && "2".equals(importantMatter.getStatus())) {
            importantMatterService.changeImportantMatterStatus(Long.parseLong(orderNo), "3");
            transactionRecordsService.addTransactionRecords(TransactionRecords.buildForAdd(importantMatter.getLawyerId(), importantMatter.getPrice().divide(new BigDecimal(2)), 8));
        }
    }
}
