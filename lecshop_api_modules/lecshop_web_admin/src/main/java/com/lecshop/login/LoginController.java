package com.lecshop.login;

import com.lecshop.admin.adminmenu.service.AdminMenuService;
import com.lecshop.admin.login.LoginService;
import com.lecshop.admin.manager.bean.Manager;
import com.lecshop.admin.manager.service.ManagerService;
import com.lecshop.adminutil.AdminLoginUtils;
import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录控制器
 *
 * @author sunluyang on 2017/7/10.
 */
@Controller
public class LoginController {
    /**
     * 注入登录service
     */
    @Autowired
    private LoginService loginService;
    /**
     * 注入admin菜单service
     */
    @Autowired
    private AdminMenuService adminMenuService;
    /**
     * 注入管理员service
     */
    @Autowired
    private ManagerService managerService;

    /**
     * 登录页面
     *
     * @return 跳转到登录页面
     */
    @RequestMapping("/tologin")
    @UnAuth
    public ModelAndView toLogin() {
        return new ModelAndView("login/login");
    }

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param code     用户输入的验证码
     * @return -1 参数不对  -2 验证码不对 -3 用户名或者密码错误 -4 用户被禁用 0成功
     */
    @RequestMapping("/login")
    @ResponseBody
    @UnAuth
    public int login(String username, String password, String code, HttpServletRequest request) {
        //session中的验证码
        String codeInSession = request.getSession().getAttribute(CommonConstant.ADMIN_LOGIN_KAPTCHA_KEY).toString();
        return loginService.login(username, password, code, codeInSession, manager -> {
            alterLoginSuccess(manager, request);
            // 清除session中的验证码
            request.getSession().removeAttribute(CommonConstant.ADMIN_LOGIN_KAPTCHA_KEY);
        });
    }

    /**
     * 登录成功后跳转到首页
     *
     * @return 首页
     */
    @RequestMapping("/index")
    public ModelAndView loginSuccessToIndex() {
        return new ModelAndView("index/index");
    }

    /**
     * 修改管理员密码
     *
     * @param request       请求
     * @param oldPassword   原密码
     * @param newPassword   新密码
     * @param reNewPassword 重新输入的密码
     * @return 修改返回码 0 没有登录 -1 输入不能为空 1 修改成功 2 两次输入密码不一致 3 原始密码不正确
     */
    @RequestMapping("/updatemanagerpassword")
    @ResponseBody
    @UnAuth
    public int updateManagerPassWord(HttpServletRequest request, String oldPassword, String newPassword, String reNewPassword) {
        return managerService.updateManagerPassWord(AdminLoginUtils.getInstance().getManagerFromSession(request), oldPassword, newPassword, reNewPassword);
    }

    /**
     * 登录成功后处理的事
     *
     * @param manager 管理员
     */
    private void alterLoginSuccess(Manager manager, HttpServletRequest request) {
        // 将用户信息放入session中
        AdminLoginUtils.getInstance().putManagerToSession(request, manager);
        //将admin后台菜单添加到session中
        request.getSession().setAttribute("adminMenu", adminMenuService.queryAdminMenu(manager.getId()));
        request.getSession().setAttribute(CommonConstant.ADMIN_MENU_FIRSTMENUS, 1);
    }
}