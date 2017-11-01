package com.lecshop.adminutil;

import com.lecshop.admin.manager.bean.Manager;
import com.lecshop.utils.CommonConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 登录帮助类 主要是获得登录信息 和判断用户是否登录
 *
 * @author sunluyang on 2017/7/10.
 */
public class AdminLoginUtils {

    private static final AdminLoginUtils ADMIN_LOGIN_UTILS = new AdminLoginUtils();

    private AdminLoginUtils() {
    }

    public static AdminLoginUtils getInstance() {
        return ADMIN_LOGIN_UTILS;
    }

    /**
     * 将用户信息放入session中
     *
     * @param manager 管理员对象
     */
    public void putManagerToSession(HttpServletRequest request, Manager manager) {
        request.getSession().setAttribute(CommonConstant.ADMIN_LOGIN_SESSION_KEY, manager);
    }

    /**
     * 从session中获得管理员信息
     *
     * @return 返回管理员信息
     */
    public Manager getManagerFromSession(HttpServletRequest request) {
        return Objects.nonNull(request.getSession().getAttribute(CommonConstant.ADMIN_LOGIN_SESSION_KEY)) ? (Manager) request.getSession().getAttribute(CommonConstant.ADMIN_LOGIN_SESSION_KEY) : null;
    }

    /**
     * 判断是否登录
     *
     * @return 登录返回true  未登录返回false
     */
    public boolean isLogin(HttpServletRequest request) {
        return Objects.nonNull(getManagerFromSession(request));
    }

}
