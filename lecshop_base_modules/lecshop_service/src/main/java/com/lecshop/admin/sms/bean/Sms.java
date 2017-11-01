package com.lecshop.admin.sms.bean;

import lombok.Data;

/**
 * 短信接口实体类
 *
 * @author sunluyang on 2017/7/10.
 */
@Data
public class Sms {
    /**
     * 主键id
     */
    private long id;
    /**
     * AppKey(名称)
     */
    private String key;
    /**
     * AppSecret(SMTP密码)
     */
    private String secret;
    /**
     * URL链接
     */
    private String url;
    /**
     * 短信签名(网关)
     */
    private String sign;
    /**
     * 模板id
     */
    private String templateId;

    /**
     * 发送的手机号
     */
    private String phone;

    /**
     * 发送内容
     */
    private String sendContent;

    public Sms getSmsToSetPhoneAndSendContent(String phone, String sendContent) {
        this.setPhone(phone);
        this.setSendContent(sendContent);
        return this;
    }
}
