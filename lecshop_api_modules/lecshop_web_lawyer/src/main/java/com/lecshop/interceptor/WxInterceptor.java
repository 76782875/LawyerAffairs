package com.lecshop.interceptor;

import com.lecshop.total.wxsetting.bean.WxSetting;
import com.lecshop.total.wxsetting.service.WxSettingService;
import com.lecshop.util.LoginUtils;
import com.lecshop.utils.UnAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 微信拦截器
 */
public class WxInterceptor extends HandlerInterceptorAdapter {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(WxInterceptor.class);

    /**
     * url 认证url
     */
    private static final String WXURL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=";

    /**
     * 注入微信服务接口
     */
    @Autowired
    private WxSettingService wxSettingService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 如果不需要权限 则直接返回
        if (!isNeedFilter(handler)) {
            logger.debug(" not need  authority");
            return true;
        }

        // 判断有没有登录过如果登录过 不需要拦截(目前只能微信登录,登录过了就说明微信认证过了)
        if (LoginUtils.getInstance().isLogin(request)) {
            logger.debug("lawyer has login.....");
            return true;
        } else {
            // 调用微信接口 进行微信认证
            logger.debug("lawyer has nologin and begin to wx auth ....");
            response.sendRedirect(getUrl());
        }

        return true;
    }


    /**
     * 判断是否需要权限
     */
    private boolean isNeedFilter(Object handler) {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        return handlerMethod.getMethod().getAnnotation(UnAuth.class) == null;
    }


    /**
     * 获得微信认证的url
     *
     * @return 返回微信认证的url
     */
    private String getUrl() {
        WxSetting wxSetting = wxSettingService.queryWxSetting();
        StringBuilder sb = new StringBuilder(WXURL);
        sb.append(wxSetting.getAppId());
        sb.append("&redirect_uri=").append(wxSetting.getCallBackUrl()).append("&response_type=code&scope=snsapi_base&state=#wechat_redirect");
        return sb.toString();
    }
}
