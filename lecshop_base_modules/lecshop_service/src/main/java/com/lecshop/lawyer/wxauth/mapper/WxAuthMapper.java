package com.lecshop.lawyer.wxauth.mapper;

import com.lecshop.lawyer.wxauth.bean.WxAuth;

import java.util.List;

/**
 * Created by dujinkai on 17/7/24.
 * 微信认数据库接口
 */
public interface WxAuthMapper {

    /**
     * 更新openid查询微信认证信息
     *
     * @param openId 微信openid
     * @return 返回微信认证信息
     */
    WxAuth queryByOpenId(String openId);

    /**
     * 添加微信认证
     *
     * @param wxAuth 微信认证
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
