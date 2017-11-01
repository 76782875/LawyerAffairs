package com.lecshop.lawyer.login.service.impl;

import com.lecshop.lawyer.login.service.LoginService;
import com.lecshop.lawyer.wxauth.bean.WxAuth;
import com.lecshop.lawyer.wxauth.service.WxAuthService;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.lawyer.service.LawyerService;
import com.lecshop.total.wxsetting.service.WxSettingService;
import com.lecshop.utils.WxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Created by dujinkai on 17/7/24.
 * 登录服务接口
 */
@Service("lawyerLogin")
public class LoginServiceImpl implements LoginService {

    /**
     * 注入律师服务接口
     */
    @Autowired
    private LawyerService lawyerService;

    /**
     * 注入微信服务接口
     */
    @Autowired
    private WxSettingService wxSettingService;

    /**
     * 微信认证服务接口
     */
    @Autowired
    private WxAuthService wxAuthService;

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public int login(String mobile, String password, Consumer<Lawyer> successConsumer, Consumer<Lawyer> bindConsumer) {

        Lawyer lawyer = lawyerService.queryByMobile(mobile);

        if (Objects.isNull(lawyer)) {
            logger.error("login fail due to lawyer is null...");
            return -1;
        }

        //  校验密码
        if (!lawyer.validatePassword(password)) {
            logger.error("login fail due to password is wrong...");
            return -1;
        }

        // 用户和微信用户绑定
        if (Objects.nonNull(bindConsumer)) {
            bindConsumer.accept(lawyer);
        }

        // 校验状态
        if (!lawyer.validateStatus()) {
            logger.error("login fail due to  lawyer stats is wrong and lawyer:{}", lawyer);
            return Integer.parseInt(lawyer.getStatus());
        }

        // 清除密码
        lawyer.clearPassword();

        if (Objects.nonNull(successConsumer)) {
            successConsumer.accept(lawyer);
        }

        return 200;
    }

    @Override
    public int wxLogin(String code, Consumer<String> consumer, Consumer<Lawyer> lawyerConsumer) {

        logger.debug("wxLogin and code:{}", code);

        if (StringUtils.isEmpty(code)) {
            logger.error("wxLogin fail due to code is null...");
            return -1;
        }

        // 获得用户微信的openId
        String openId = WxUtils.getInstance().getOpenId(code, wxSettingService.queryWxSetting());

        if (StringUtils.isEmpty(openId)) {
            logger.error("wxLogin fail due to get openId fail....");
            return -1;
        }

        // 根据openId 查询律师信息
        WxAuth wxAuth = wxAuthService.queryByOpenId(openId);

        // 如果微信还没有绑定律师 则直接返回
        if (Objects.isNull(wxAuth)) {
            logger.error("weixin has not lawyer exist....");

            if (Objects.nonNull(consumer)) {
                consumer.accept(openId);
            }

            return -2;
        }

        // 查出绑定的律师信息
        Lawyer lawyer = lawyerService.queryLawyerById(wxAuth.getLawyerId());

        // 律师状态正常 返回成功
        if ("0".equals(lawyer.getStatus())) {
            // 将律师信息放入session中
            if (Objects.nonNull(lawyerConsumer)) {
                lawyerConsumer.accept(lawyer);
            }
            return 1;
        } else if ("1".equals(lawyer.getStatus())) {
            // 律师禁用
            return -3;
        } else if ("2".equals(lawyer.getStatus())) {
            // 待审核
            return -4;
        } else {
            // 审核未通过
            return -5;
        }

    }

}
