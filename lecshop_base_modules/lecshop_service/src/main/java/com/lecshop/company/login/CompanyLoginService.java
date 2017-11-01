package com.lecshop.company.login;

import com.lecshop.total.user.bean.User;

import java.util.function.Consumer;

/**
 * 公司员工登录service接口
 *
 * @author sunluyang on 2017/7/14.
 */
public interface CompanyLoginService {
    /**
     * 登录接口
     *
     * @param mobileOrCompanyName 手机号码或公司名称
     * @param password            密码
     * @param code                用户输入的验证码
     * @param codeInSession       session中的验证码
     * @param consumer            回调接口
     * @return 0 输入不能为空  1 登录成功 2 验证码不正确 3 无该账号 4 账号被禁用 5 用户名或密码错误
     */
    int login(String mobileOrCompanyName, String password, String code, String codeInSession, Consumer<User> consumer);

    /**
     * 忘记密码接口
     *
     * @param phone         手机号码
     * @param code          用户输入的验证码
     * @param password      密码
     * @param rePassword    重新输入验证码
     * @param codeInSession session中的验证码
     * @return 返回码
     */
    int forgetPassword(String phone, String code, String password, String rePassword, String codeInSession);
}
