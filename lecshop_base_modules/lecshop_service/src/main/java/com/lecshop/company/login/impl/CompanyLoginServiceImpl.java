package com.lecshop.company.login.impl;

import com.lecshop.utils.MD5Utils;
import com.lecshop.utils.PhoneValidUtils;
import com.lecshop.company.login.CompanyLoginService;
import com.lecshop.total.user.bean.User;
import com.lecshop.total.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 公司员工登录service实现类
 *
 * @author sunluyang on 2017/7/14.
 */
@Service
public class CompanyLoginServiceImpl implements CompanyLoginService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CompanyLoginServiceImpl.class);

    /**
     * 注入公司员工service
     */
    @Autowired
    private UserService userService;

    /**
     * @param mobileOrCompanyName 手机号码或公司名称
     * @param password            密码
     * @param code                用户输入的验证码
     * @param codeInSession       session中的验证码
     * @param consumer            回调接口
     * @return 0 输入不能为空  1 登录成功 2 验证码不正确 3 无该账号 4 账号被禁用 5 用户名或密码错误
     */
    @Override
    public int login(String mobileOrCompanyName, String password, String code, String codeInSession, Consumer<User> consumer) {
        LOGGER.info("login");
        if (StringUtils.isEmpty(mobileOrCompanyName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(code)) {
            return 0;
        }
        if (!codeInSession.equals(code)) {
            return 2;
        }
        User user;
        if (PhoneValidUtils.toValid(mobileOrCompanyName)) {
            user = userService.queryUserByMobile(mobileOrCompanyName);
        } else {
            user = userService.queryUserByName(mobileOrCompanyName);
        }
        if (Objects.isNull(user)) {
            return 3;
        }
        if ("1".equals(user.getStatus())) {
            return 4;
        }
        if (!user.isPasswordRight(password)) {
            return 5;
        }
        // 登录成功 进行回调
        user.clearPassword();
        consumer.accept(user);
        return 1;
    }

    /**
     * 忘记密码
     *
     * @param phone         手机号码
     * @param code          用户输入的验证码
     * @param password      密码
     * @param rePassword    重新输入验证码
     * @param codeInSession session中的验证码
     * @return 返回码 -1 两次密码不一致-2验证码不正确 -3手机号码不合法 -4不存在该用户
     */
    public int forgetPassword(String phone, String code, String password, String rePassword, String codeInSession) {
        LOGGER.info("forgetPassword");
        if (!password.equals(rePassword)) {
            return -1;
        }
        if (!code.equals(codeInSession)) {
            return -2;
        }
        if (!PhoneValidUtils.toValid(phone)) {
            return -3;
        }
        if (Objects.isNull(userService.queryUserByMobile(phone))) {
            return -4;
        }
        return userService.editPasswordByMobile(phone, MD5Utils.getInstance().createMd5(password));
    }
}
