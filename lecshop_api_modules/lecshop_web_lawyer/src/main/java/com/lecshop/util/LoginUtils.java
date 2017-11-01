package com.lecshop.util;

import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.utils.CommonConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by dujinkai on 17/7/24.
 * 登录工具类
 */
public class LoginUtils {

    private static final LoginUtils INSTANCE = new LoginUtils();

    private LoginUtils() {

    }

    public static LoginUtils getInstance() {
        return INSTANCE;
    }

    /**
     * 获得律师的id
     *
     * @return 返回律师的id
     */
    public long getLawyerId(HttpServletRequest request) {
        return Objects.nonNull(getLawyerFromSession(request)) ? getLawyerFromSession(request).getId() : 0;
    }

    /**
     * 判断律师是否登录
     *
     * @return 登录返回true  未登录返回false
     */
    public boolean isLogin(HttpServletRequest request) {
        return Objects.nonNull(getLawyerFromSession(request));
    }

    /**
     * 将律师信息放入session中
     *
     * @param lawyer 律师信息
     */
    public void putLawlerToSession(HttpServletRequest request, Lawyer lawyer) {
        request.getSession().setAttribute(CommonConstant.LAWYER_SESSION_KEY, lawyer);
    }

    /**
     * 从session中获得律师信息
     *
     * @return 返回律师信息
     */
    private Lawyer getLawyerFromSession(HttpServletRequest request) {
        return (Lawyer) request.getSession().getAttribute(CommonConstant.LAWYER_SESSION_KEY);
    }

}
