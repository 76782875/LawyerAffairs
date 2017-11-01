package com.lecshop.register;

import com.lecshop.lawyer.register.service.RegisterService;
import com.lecshop.lawyer.wxauth.bean.WxAuth;
import com.lecshop.lawyer.wxauth.service.WxAuthService;
import com.lecshop.total.lawyer.bean.Lawyer;
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
 * 律师注册控制器
 */
@Controller
public class RegisterController {

    /**
     * 注入注册服务接口
     */
    @Resource(name = "lawyerRegister")
    private RegisterService registerService;

    /**
     * 注入微信认证服务接口
     */
    @Autowired
    private WxAuthService wxAuthService;

    /**
     * 发送短信验证码
     *
     * @return 成功返回1 失败返回0 -1:手机号码已存在
     */
    @RequestMapping("/sendvalidcode")
    @ResponseBody
    @UnAuth
    public int sendValidCode(HttpServletRequest request, String mobile) {
        String result = registerService.toSendValidateCode(mobile, true);

        // 短信发送失败
        if ("0".equals(result) || "-1".equals(result)) {
            return Integer.parseInt(result);
        }

        // 将验证码放入session中
        request.getSession().setAttribute(CommonConstant.LAWYER_REGISTER_KAPTCHA_KEY, result);

        // 将手机号码放入session中
        request.getSession().setAttribute(CommonConstant.LAWYER_REGISTER_MOBILE_KEY, mobile);

        return 1;
    }

    /**
     * 律师注册
     *
     * @param lawyer       律师信息
     * @param validateCode 验证码
     * @return -3:参数错误  -2:验证码错误   -1:手机号码存在 0:失败 1:成功
     */
    @RequestMapping("/register")
    @ResponseBody
    @UnAuth
    public int register(HttpServletRequest request, Lawyer lawyer, String validateCode) {

        // 注册律师
        int reslut = registerService.registerLawyer(lawyer.setDefaultValuesForAdd(), validateCode, (String) request.getSession().getAttribute(CommonConstant.LAWYER_REGISTER_KAPTCHA_KEY), (String) request.getSession().getAttribute(CommonConstant.LAWYER_REGISTER_MOBILE_KEY));

        // 注册成功
        if (reslut == 1) {

            // 用户微信的openid
            String openId = (String) request.getSession().getAttribute(CommonConstant.LAWYER_WX_OPENID);

            // 更新微信认证和律师的关联表
            if (!StringUtils.isEmpty(openId)) {
                wxAuthService.addWxAuth(WxAuth.build(openId, lawyer.getId()));
                request.getSession().removeAttribute(CommonConstant.LAWYER_WX_OPENID);
            }
            clearRegisterSession(request);
        }

        return reslut;
    }


    /**
     * 发送短信验证码_忘记密码
     *
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/forget_sendvalidcode")
    @ResponseBody
    @UnAuth
    public int forgetSendValidCode(HttpServletRequest request, String mobile) {

        // 发送验证码
        String result = registerService.toSendValidateCode(mobile, false);

        // 短信发送失败
        if ("0".equals(result)) {
            return 0;
        }

        // 将验证码放入session中
        request.getSession().setAttribute(CommonConstant.LAWYER_FOREGTPASSWORD_KAPTCHA_KEY, result);

        // 将手机号码放入session中
        request.getSession().setAttribute(CommonConstant.LAWYER_FOREGTPASSWORD_MOBILE_KEY, mobile);

        return 1;
    }

    /**
     * 找回密码
     *
     * @param mobile 手机号码
     * @param code   验证码
     * @return -3:参数错误  -2:验证码错误  0:失败 1:成功
     */
    @RequestMapping("/updatepassword")
    @ResponseBody
    @UnAuth
    public int updatePassword(HttpServletRequest request, String mobile, String code, String password) {
        int result = registerService.updatePassword(mobile, code, password, (String) request.getSession().getAttribute(CommonConstant.LAWYER_FOREGTPASSWORD_KAPTCHA_KEY), (String) request.getSession().getAttribute(CommonConstant.LAWYER_FOREGTPASSWORD_MOBILE_KEY));
        if (result == 1) {
            cleaerUpdatePasswordSession(request);
        }
        return result;
    }


    /**
     * 跳转到找回密码页面
     *
     * @return 返回找回密码页面
     */
    @RequestMapping("/toretrievepassword")
    @UnAuth
    public ModelAndView toRetrievePassword() {
        return new ModelAndView("login/retrievePassword");
    }

    /**
     * 跳转到注册页面
     *
     * @return 返回注册页面
     */
    @RequestMapping("/toregister")
    @UnAuth
    public ModelAndView toRegister() {
        return new ModelAndView("register/register");
    }


    /**
     * 清除注册中放置的session
     */
    private void clearRegisterSession(HttpServletRequest request) {
        request.getSession().removeAttribute(CommonConstant.LAWYER_REGISTER_KAPTCHA_KEY);
        request.getSession().removeAttribute(CommonConstant.LAWYER_REGISTER_MOBILE_KEY);
    }

    /**
     * 清除找回密码放置的session
     */
    private void cleaerUpdatePasswordSession(HttpServletRequest request) {
        request.getSession().removeAttribute(CommonConstant.LAWYER_FOREGTPASSWORD_MOBILE_KEY);
        request.getSession().removeAttribute(CommonConstant.LAWYER_FOREGTPASSWORD_KAPTCHA_KEY);
    }
}
