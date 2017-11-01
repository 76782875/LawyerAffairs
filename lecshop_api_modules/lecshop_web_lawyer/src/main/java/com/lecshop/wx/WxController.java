package com.lecshop.wx;

import com.lecshop.lawyer.login.service.LoginService;
import com.lecshop.util.LoginUtils;
import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.UnAuth;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by dujinkai on 17/7/24.
 * 微信控制器
 */
@Controller
public class WxController {

    /**
     * 调试日志
     */
    private Logger logger = Logger.getLogger(WxController.class);

    /**
     * 注入登录过滤器
     */
    @Resource(name = "lawyerLogin")
    private LoginService loginService;

    /**
     * 微信认证回调
     *
     * @return 认证后返回首页
     */
    @RequestMapping("wxcallback")
    @UnAuth
    public ModelAndView wxCallBack(HttpServletRequest request) {

        // 微信登录
        int result = loginService.wxLogin(request.getParameter("code"), s -> request.getSession().setAttribute(CommonConstant.LAWYER_WX_OPENID, s),
                lawyer -> LoginUtils.getInstance().putLawlerToSession(request, lawyer));

        if (result == -2) {
            // 跳转到账号绑定页面
            return new ModelAndView("login/login");
        } else if (result == -3) {
            // 跳转到禁用页面
            return new ModelAndView("login/tip").addObject("msg", "律师已被禁用,请与管理员联系");
        } else if (result == -4) {
            // 跳转到待审核页面
            return new ModelAndView("login/tip").addObject("msg", "律师正在审核中");
        } else if (result == -5) {
            // 跳转到审核失败页面
            return new ModelAndView("login/tip").addObject("msg", "律师审核未通过,请与管理员联系");
        } else if (result == 1) {
            return new ModelAndView("index/index");
        } else {
            // 跳转到系统错误页面
            return new ModelAndView("common/error");
        }
    }
}
