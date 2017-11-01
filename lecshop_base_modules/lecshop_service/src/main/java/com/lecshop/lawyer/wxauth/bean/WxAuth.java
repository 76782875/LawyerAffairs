package com.lecshop.lawyer.wxauth.bean;

import lombok.Data;

/**
 * Created by dujinkai on 17/7/24.
 * 微信认证和律师账号的关联实体
 */
@Data
public class WxAuth {

    /**
     * 主键id
     */
    private long id;

    /**
     * 微信openid
     */
    private String wxOpenId;

    /**
     * 律师id
     */
    private long lawyerId;

    /**
     * 构造微信认证实体
     *
     * @param wxOpenId 微信openid
     * @param lawyerId 律师id
     * @return 返回微信认证实体
     */
    public static WxAuth build(String wxOpenId, long lawyerId) {
        WxAuth wxAuth = new WxAuth();
        wxAuth.wxOpenId = wxOpenId;
        wxAuth.lawyerId = lawyerId;
        return wxAuth;
    }
}
