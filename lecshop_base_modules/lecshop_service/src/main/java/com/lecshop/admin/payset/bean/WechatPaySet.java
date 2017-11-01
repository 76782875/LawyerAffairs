package com.lecshop.admin.payset.bean;

import lombok.Data;

/**
 * 支付设置接口-微信支付接口设置实体类
 *
 * @author sunluyang on 2017/5/17.
 */
@Data
public class WechatPaySet {
    /**
     * 公众号appId
     */
    private String appId;
    /**
     * AppSecret
     */
    private String appSecret;
    /**
     * 商户号
     */
    private String merchantNum;
    /**
     * API密钥
     */
    private String apiKey;
    /**
     * 支付回调
     */
    private String payCallback;
    /**
     * 是否启用 1启用 0不启用
     */
    private String isUse;
}
