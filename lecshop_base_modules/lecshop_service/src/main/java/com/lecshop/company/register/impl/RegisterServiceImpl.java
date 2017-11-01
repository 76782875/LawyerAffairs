package com.lecshop.company.register.impl;

import com.lecshop.admin.sms.service.SmsService;
import com.lecshop.company.companyauth.service.CompanyRoleService;
import com.lecshop.utils.PhoneValidUtils;
import com.lecshop.total.companyinfo.bean.CompanyInfo;
import com.lecshop.total.companyinfo.service.CompanyInfoService;
import com.lecshop.company.register.RegisterService;
import com.lecshop.total.user.bean.User;
import com.lecshop.total.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


/**
 * 注册service实现类
 *
 * @author sunluyang on 2017/7/17.
 */
@Service
public class RegisterServiceImpl implements RegisterService {
    /**
     * 调试日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(RegisterServiceImpl.class);
    /**
     * 注入短信service
     */
    @Autowired
    private SmsService smsService;
    /**
     * 注入公司信息service
     */
    @Autowired
    private CompanyInfoService companyInfoService;
    /**
     * 注入员工service
     */
    @Autowired
    private UserService userService;
    /**
     * 注入公司角色service
     */
    @Autowired
    private CompanyRoleService companyRoleService;

    /**
     * 发送注册验证码
     *
     * @param phone 手机号码
     * @return 发送短信返回码 -1 手机号码非法  0发送失败
     */
    @Override
    public String toSendRegisterCode(String phone, String code) {
        LOGGER.info("toSendRegisterCode and phone:{}\r\n code:{}", phone, code);
        if (!PhoneValidUtils.toValid(phone)) {
            LOGGER.error("toSendRegisterCode fail due phone valid is false");
            return "-1";
        }
        code = String.valueOf((int) (Math.random() * 1000000));
        return smsService.sendMsg(phone, code) ? code : "0";
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
     * @param codeInSession   session中的手机验证码
     * @return 注册返回码 -1短信验证码错误 -2 手机号不正确 -3 两次密码不一致 -4该手机号已被使用 1 成功
     */
    @Override
    public int register(String phone, String phoneCode, String password, String rePassword, String companyName, String code, String businessLicence, String codeInSession) {
        LOGGER.info("register");
        if (!codeInSession.equals(phoneCode)) {
            return -1;
        }
        if (!PhoneValidUtils.toValid(phone)) {
            return -2;
        }
        if (!password.equals(rePassword)) {
            return -3;
        }

        if (!Objects.isNull(userService.queryUserByMobile(phone))) {
            return -4;
        }
        if (!Objects.isNull(userService.queryUserByName(companyName))) {
            return -5;
        }
        CompanyInfo companyInfo = new CompanyInfo().getRegisterCompanyInfo(companyName, code, businessLicence);
        companyInfoService.addCompanyInfo(companyInfo);
        User user = new User().getDefaultUser(companyName, password, phone, companyInfo.getId(), "0", "0");
        userService.addCompanyStaff(user);
        return companyRoleService.addUserRole(user.getId(), 1);
    }
}
