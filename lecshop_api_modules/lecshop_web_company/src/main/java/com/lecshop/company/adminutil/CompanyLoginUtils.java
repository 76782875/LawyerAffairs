package com.lecshop.company.adminutil;

import com.lecshop.utils.CommonConstant;
import com.lecshop.total.user.bean.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 登录帮助类 主要是获得登录信息 和判断用户是否登录
 *
 * @author sunluyang on 2017/7/10.
 */
public class CompanyLoginUtils {

    private static final CompanyLoginUtils COMPANY_LOGIN_UTILS = new CompanyLoginUtils();

    private CompanyLoginUtils() {
    }

    public static CompanyLoginUtils getInstance() {
        return COMPANY_LOGIN_UTILS;
    }

    /**
     * 将用户信息放入session中
     *
     * @param user 用户对象
     */
    public void putUserToSession(HttpServletRequest request, User user) {
        request.getSession().setAttribute(CommonConstant.COMPANY_LOGIN_SESSION_KEY, user);
    }

    /**
     * 从session中获得管理员信息
     *
     * @return 返回管理员信息
     */
    public User getUserFromSession(HttpServletRequest request) {
        return Objects.nonNull(request.getSession().getAttribute(CommonConstant.COMPANY_LOGIN_SESSION_KEY)) ? (User) request.getSession().getAttribute(CommonConstant.COMPANY_LOGIN_SESSION_KEY) : null;
    }

    /**
     * 从session获取公司id
     *
     * @return 公司id
     */
    public long getCompanyIdFromSession(HttpServletRequest request) {
        return getUserFromSession(request).getCompanyId();
    }

    /**
     * 从session获取公司id
     *
     * @return 公司id
     */
    public long getUserIdFromSession(HttpServletRequest request) {
        return getUserFromSession(request).getId();
    }

    /**
     * 判断是否登录
     *
     * @return 登录返回true  未登录返回false
     */
    public boolean isLogin(HttpServletRequest request) {
        return Objects.nonNull(getUserFromSession(request));
    }

}
