package com.lecshop.admin.login;

import com.lecshop.admin.manager.bean.Manager;

import java.util.function.Consumer;

/**
 * 登录接口
 */
public interface LoginService {

    /**
     * 登录接口
     *
     * @param username      用户名
     * @param password      密码
     * @param code          用户输入的验证码
     * @param codeInSession session中的验证码
     * @param consumer      回调接口
     * @return -1 参数不对  -2 验证码不对 -3 用户名或者密码错误 0成功
     */
    int login(String username, String password, String code, String codeInSession, Consumer<Manager> consumer);
}
