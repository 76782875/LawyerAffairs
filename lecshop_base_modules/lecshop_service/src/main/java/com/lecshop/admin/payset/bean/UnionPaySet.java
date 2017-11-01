package com.lecshop.admin.payset.bean;

import lombok.Data;

/**
 * 支付设置接口-银联支付接口设置实体类
 *
 * @author sunluyang on 2017/5/17.
 */
@Data
public class UnionPaySet {
    /**
     * 商户号
     */
    private String merchantNum;
    /**
     * API-KEY
     */
    private String apiKey;
    /**
     * 前台回调地址
     */
    private String beforeCallbackUrl;
    /**
     * 后台回调地址
     */
    private String backCallbackUrl;
    /**
     * 是否启用 1启用 0不启用
     */
    private String isUse;

}
