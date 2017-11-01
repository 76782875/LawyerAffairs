package com.lecshop.company.register;

import com.lecshop.admin.upyunset.service.UpyunService;
import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * 注册控制器
 *
 * @author sunluyang on 2017/7/17.
 */
@Controller
public class RegisterController {

    /**
     * 注入注册service
     */
    @Autowired
    private RegisterService registerService;
    /**
     * 自动注入又拍云service
     */
    @Autowired
    private UpyunService upyunService;

    /**
     * 跳转到注册页面
     *
     * @return 注册页面
     */
    @RequestMapping("/c_toregister")
    @UnAuth
    public ModelAndView toRegister() {
        return new ModelAndView("register/register");
    }

    /**
     * 发送注册验证码
     *
     * @param phone 手机号
     * @return 发送返回码
     */
    @RequestMapping("/c_tosendregistercode")
    @ResponseBody
    @UnAuth
    public int toSendRegisterCode(String phone, HttpServletRequest request) {
        String result = registerService.toSendRegisterCode(phone, "");
        if (!"-1".equals(result) && !"0".equals(result)) {
            request.getSession().setAttribute(CommonConstant.COMPANY_REGISTER_KAPTCHA_KEY, result);
            return 1;
        }
        return Integer.parseInt(result);
    }

    /**
     * 公司注册注册
     *
     * @param phone           手机号码
     * @param phoneCode       手机验证码
     * @param password        密码
     * @param rePassword      重新输入密码
     * @param companyName     公司名称
     * @param code            社会信用代码号
     * @param businessLicence 营业执照
     * @return 注册返回码 -1短信验证码错误 -2 手机号不正确 -3两次密码不一致 -4该手机号已被使用 1 成功
     */
    @RequestMapping("/c_register")
    @ResponseBody
    @UnAuth
    public int register(String phone, String phoneCode, String password, String rePassword, String companyName, String code, String businessLicence, HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getSession().getAttribute(CommonConstant.COMPANY_REGISTER_KAPTCHA_KEY))) {
            return -1;
        }
        return registerService.register(phone, phoneCode, password, rePassword, companyName, code, businessLicence, request.getSession().getAttribute(CommonConstant.COMPANY_REGISTER_KAPTCHA_KEY).toString());
    }

    /**
     * 上传图片
     *
     * @return 返回图片在又拍云的地址
     * @throws Exception 异常
     */
    @RequestMapping("/c_uploadtoupyun")
    @ResponseBody
    @UnAuth
    public String uploadToUpYun(MultipartHttpServletRequest request, String name) throws Exception {
        if (StringUtils.isEmpty(name)) {
            name = "image";
        }
        MultipartFile multipartFile = request.getFile(name);
        if (Objects.isNull(multipartFile)) {
            return "";
        }
        return upyunService.uploadToUpYun(multipartFile.getInputStream(), multipartFile.getBytes());
    }
}
