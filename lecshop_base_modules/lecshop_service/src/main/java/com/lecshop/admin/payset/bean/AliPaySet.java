package com.lecshop.admin.payset.bean;

import lombok.Data;

/**
 * 支付设置接口-支付宝支付接口设置实体类
 *
 * @author sunluyang on 2017/5/17.
 */
@Data
public class AliPaySet {
    /**
     * 合作者身份(PID)
     */
    private String pid;
    /**
     * 安全校验码(Key)
     */
    private String key;
    /**
     * 收款账号
     */
    private String payee;
    /**
     * 前台回调地址
     */
    private String beforeCallbackUrl;
    /**
     * 后台回调地址
     */
    private String backCallbackUrl;
    /**
     * 手机支付回调
     */
    private String mobilePayCallbackUrl;
    /**
     * 是否启用 1启用 0不启用
     */
    private String isUse;
}
