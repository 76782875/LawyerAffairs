package com.lecshop.utils;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.lecshop.total.withdraw.bean.Withdraw;
import com.lecshop.total.withdrawset.bean.WithdrawSet;
import com.lecshop.utils.alipay.config.AliOpenApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dujinkai on 17/7/26.
 * 提现工具类
 */
public class WithdrawUtils {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(WithdrawUtils.class);

    private static WithdrawUtils INSTANCE = new WithdrawUtils();

    public static WithdrawUtils getInstance() {
        return INSTANCE;
    }

    private WithdrawUtils() {
    }


    /**
     * 提现
     *
     * @param aliOpenApi  阿里开放api的实体
     * @param withdraw    提现实体
     * @param withdrawSet 提现设置实体
     * @return 返回阿里的返回吗
     * @throws Exception 系统异常
     */
    public WithdrawResponse withdraw(AliOpenApi aliOpenApi, Withdraw withdraw, WithdrawSet withdrawSet) {

        logger.debug("withdraw and aliOpenApi:{} \r\n ");

        AlipayClient alipayClient = new DefaultAlipayClient(aliOpenApi.getGateWay(), aliOpenApi.getAppId(), aliOpenApi.getPrivateKey(), "json", "UTF-8", aliOpenApi.getPublicKey(), aliOpenApi.getType());
        AlipayFundTransToaccountTransferRequest request = new AlipayFundTransToaccountTransferRequest();
        request.setBizContent("{" +
                "\"out_biz_no\":\"" + withdraw.getTradeNo() + "\"," +
                "\"payee_type\":\"ALIPAY_LOGONID\"," +
                "\"payee_account\":\"" + withdrawSet.getAccount() + "\"," +
                "\"amount\":\"0.1\"," +
                "\"payer_show_name\":\"律师弟弟平台\"," +
                "\"payee_real_name\":\"" + withdrawSet.getName() + "\"," +
                "\"remark\":\"账户提现\"" +
                "  }");
        try {
            AlipayFundTransToaccountTransferResponse response = alipayClient.execute(request);
            return WithdrawResponse.build(response.getCode(), response.getSubCode());
        } catch (Exception e) {

        }

        return WithdrawResponse.buildSystemError();
    }
}
