package com.lecshop.company.interceptor;

import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.UnAuth;
import com.lecshop.company.adminutil.CompanyLoginUtils;
import com.lecshop.company.companymenu.bean.CompanyMenu;
import com.lecshop.company.companymenu.service.CompanyMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 权限拦截器
 *
 * @author sunluyang on 2017/7/10.
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);

    /**
     * 注入公司菜单service
     */
    @Autowired
    private CompanyMenuService companyMenuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求中获取菜单一二三级放置在session中
        sessionMenu(request);
        // 如果不需要权限 则直接返回
        if (!isNeedFilter(handler)) {
            logger.debug("is not need  authority");
            return true;
        }
        // 判断是否具有权限 没有选择 则直接返回没有权限提醒页面
        if (!hasAuth(request)) {
            logger.error("url is not access:{}", request.getServletPath());
            try {
                response.sendRedirect("noauth.jsp");
            } catch (IOException e) {
                logger.error("error:{}", e);
            }
            return false;
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
     * 判断用户是否有权限
     */
    private boolean hasAuth(HttpServletRequest request) {
        // 查询当前用户所有的权限
        List<CompanyMenu> companyMenus = companyMenuService.queryAllCompanyMenu(CompanyLoginUtils.getInstance().getUserFromSession(request).getId());
        // 用户没有权限 直接返回false
        if (CollectionUtils.isEmpty(companyMenus)) {
            return false;
        }
        for (CompanyMenu companyMenu : companyMenus) {
            if (StringUtils.isEmpty(companyMenu.getUrl())) {
                continue;
            }
            if (request.getServletPath().contains(companyMenu.getUrl())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 设置Session里当前的菜单选项
     */
    private void sessionMenu(HttpServletRequest request) {
        if (request.getParameter(CommonConstant.COMPANY_MENU_FIRSTMENUS) != null) {
            request.getSession().setAttribute(CommonConstant.COMPANY_MENU_FIRSTMENUS, request.getParameter(CommonConstant.COMPANY_MENU_FIRSTMENUS));
        }
        if (request.getParameter(CommonConstant.COMPANY_MENU_SECONDMENUS) != null) {
            request.getSession().setAttribute(CommonConstant.COMPANY_MENU_SECONDMENUS, request.getParameter(CommonConstant.COMPANY_MENU_SECONDMENUS));
        }
        if (request.getParameter(CommonConstant.COMPANY_MENU_THIRDMENUS) != null) {
            request.getSession().setAttribute(CommonConstant.COMPANY_MENU_THIRDMENUS, request.getParameter(CommonConstant.COMPANY_MENU_THIRDMENUS));
        }
    }

}
