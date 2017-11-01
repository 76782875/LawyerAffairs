package com.lecshop.login;

import com.lecshop.lawyer.login.service.LoginService;
import com.lecshop.lawyer.wxauth.bean.WxAuth;
import com.lecshop.lawyer.wxauth.service.WxAuthService;
import com.lecshop.util.LoginUtils;
import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by dujinkai on 17/7/24.
 * 律师端登录控制器
 */
@Controller
public class LoginController {

    /**
     * 注入登录过滤器
     */
    @Resource(name = "lawyerLogin")
    private LoginService loginService;

    /**
     * 注入微信认证服务接口
     */
    @Autowired
    private WxAuthService wxAuthService;

    /**
     * 律师登录
     *
     * @param mobile   手机
     * @param password 密码
     * @return 200 成功 -1 用户名或者密码错误 1 禁用 2 待审核  3 审核不通过
     */
    @RequestMapping("/login")
    @ResponseBody
    @UnAuth
    public int login(HttpServletRequest request, String mobile, String password) {
        return loginService.login(mobile, password, lawyer1 -> LoginUtils.getInstance().putLawlerToSession(request, lawyer1), lawyer -> {
            // 用户微信的openid
            String openId = (String) request.getSession().getAttribute(CommonConstant.LAWYER_WX_OPENID);

            // 更新微信认证和律师的关联表
            if (!StringUtils.isEmpty(openId)) {
                wxAuthService.addWxAuth(WxAuth.build(openId, lawyer.getId()));
                request.getSession().removeAttribute(CommonConstant.LAWYER_WX_OPENID);
            }
        });
    }

    /**
     * 跳转到登录页面
     *
     * @return 返回登录页面
     */
    @UnAuth
    @RequestMapping("/tologin")
    public ModelAndView toLogin() {
        return new ModelAndView("login/login");
    }
}
