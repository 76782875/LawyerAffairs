package com.lecshop.utils;

import com.lecshop.admin.sms.bean.Sms;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 短信发送工具类
 *
 * @author sunluyang on 2017/7/12.
 */
public class SendSmsUtil {

    /**
     * 调试日志
     */
    private static final Logger logger = LoggerFactory.getLogger(SendSmsUtil.class);

    /**
     * 发送短信
     *
     * @param sms 短信配置实体类
     * @return true or false
     */
    public static boolean sendSms(Sms sms) {
        if (Objects.isNull(sms)) {
            logger.error("sendSms is error due to sms is null");
            return false;
        }
        try {
            TaobaoClient client = new DefaultTaobaoClient(sms.getUrl(), sms.getKey(), sms.getSecret());
            AlibabaAliqinFcSmsNumSendResponse response = client.execute(buildSendParam(sms));
            logger.info("sendSms and response:{}", response);
            return true;
        } catch (ApiException e) {
            logger.error("sendSms is error e:{}", e);
            return false;
        }
    }

    /**
     * 构建发送短信实体类
     *
     * @param sms 短信配置实体类
     * @return 短信实体类
     */
    private static AlibabaAliqinFcSmsNumSendRequest buildSendParam(Sms sms) {
        logger.debug("buildSendParam and sms:{}", sms);
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setExtend("123456");
        request.setSmsType("normal");
        request.setSmsFreeSignName(sms.getSign());
        request.setSmsParamString("{\"number\":\"" + sms.getSendContent() + "\",\"product\":\"\"}");
        request.setRecNum(sms.getPhone());
        request.setSmsTemplateCode(sms.getTemplateId());
        return request;
    }
}
