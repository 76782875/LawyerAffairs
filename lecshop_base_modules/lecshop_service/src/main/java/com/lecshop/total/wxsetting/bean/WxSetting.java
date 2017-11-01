package com.lecshop.total.wxsetting.bean;

import lombok.Data;

/**
 * Created by dujinkai on 17/7/24.
 * 微信设置实体
 */
@Data
public class WxSetting {

    /**
     * 主键id
     */
    private long id;

    /**
     * 微信的appid
     */
    private String appId;

    /**
     * 微信的appSecret
     */
    private String appSecret;

    /**
     * 登录的时候 微信认证回调的地址
     */
    private String callBackUrl;

}
