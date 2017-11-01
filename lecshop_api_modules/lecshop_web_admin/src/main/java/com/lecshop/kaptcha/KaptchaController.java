package com.lecshop.kaptcha;

import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.KaptchaUtil;
import com.lecshop.utils.UnAuth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证码控制器
 */
@Controller
public class KaptchaController extends KaptchaUtil {

    /**
     * 生成验证码
     */
    @RequestMapping("/createkaptcha")
    @UnAuth
    public void createKaptcha(HttpServletResponse response, HttpServletRequest request) throws Exception {
        super.createKaptcha(response, code -> request.getSession().setAttribute(CommonConstant.ADMIN_LOGIN_KAPTCHA_KEY, code));
    }
}
