package com.lecshop.lawyer.wxauth.service;

import com.lecshop.lawyer.wxauth.bean.WxAuth;

import java.util.List;

/**
 * Created by dujinkai on 17/7/24.
 * 微信认证服务接口
 */
public interface WxAuthService {

    /**
     * 根据微信openId 查询微信认证
     *
     * @param openId 微信openid
     * @return 返回微信认证实体
     */
    WxAuth queryByOpenId(String openId);

    /**
     * 新增微信认证
     *
     * @param wxAuth 微信认证实体
     * @return 成功返回1 失败返回0
     */
    int addWxAuth(WxAuth wxAuth);

    /**
     * 根据律师id集合查询律师openId集合
     *
     * @param lawyerIds 律师id集合
     * @return 返回openId集合
     */
    List<String> queryOpenIdsByLawyerList(List<Long> lawyerIds);
}
