package com.lecshop.lawyer.login.service;

import com.lecshop.total.lawyer.bean.Lawyer;

import java.util.function.Consumer;

/**
 * Created by dujinkai on 17/7/24.
 * 登录服务接口
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param mobile          手机号码
     * @param password        密码
     * @param successConsumer 登录成功回调
     * @param bindConsumer    绑定成功回调
     * @return 200 成功 -1 用户名或者密码错误 1 禁用 2 待审核  3 审核不通过
     */
    int login(String mobile, String password, Consumer<Lawyer> successConsumer, Consumer<Lawyer> bindConsumer);

    /**
     * 微信登录
     *
     * @param code           微信认证后回调返回的唯一code
     * @param consumer       openId 的回调
     * @param lawyerConsumer 会员的回调
     * @return -1:微信认证失败 -2:微信还没绑定律师 1:成功 -3:律师禁用  -4:律师待审核  -5:审核未通过
     */
    int wxLogin(String code, Consumer<String> consumer, Consumer<Lawyer> lawyerConsumer);
}
