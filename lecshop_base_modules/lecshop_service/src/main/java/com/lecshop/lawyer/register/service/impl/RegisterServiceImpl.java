package com.lecshop.lawyer.register.service.impl;

import com.lecshop.admin.sms.service.SmsService;
import com.lecshop.lawyer.register.service.RegisterService;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.lawyer.service.LawyerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * Created by dujinkai on 17/7/24.
 * 律师注册服务接口实现
 */
@Service("lawyerRegister")
public class RegisterServiceImpl implements RegisterService {

    /**
     * 注入短信service
     */
    @Autowired
    private SmsService smsService;

    /**
     * 注入律师服务接口
     */
    @Autowired
    private LawyerService lawyerService;

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);

    @Override
    public String toSendValidateCode(String mobile, boolean validateMobile) {
        logger.debug("toSendValidateCode and mobile:{}", mobile);

        // 如果需要校验手机号码 并且 手机号码存在 直接返回
        if (validateMobile && lawyerService.hasMobileExsit(mobile)) {
            logger.error("toSendValidateCode fail due to mobile is exist");
            return "-1";
        }

        // 随机生成验证码
        String code = String.valueOf((int) (Math.random() * 1000000));

        // 发送短信
        return smsService.sendMsg(mobile, code) ? code : "0";
    }

    @Override
    public int registerLawyer(Lawyer lawyer, String code, String codeInSession, String mobileInSession) {
        logger.debug("registerLawyer and lawyer:{},code:{}", lawyer, code);

        if (Objects.isNull(lawyer) || StringUtils.isEmpty(code)) {

            logger.error("registerLawyer fail due to params is empty....");

            return -3;
        }

        // 验证手机号码和验证码
        if (!lawyer.getMobile().equals(mobileInSession) || !code.equals(codeInSession)) {
            logger.error("registerLawyer fail due to code is error....");
            return -2;
        }

        // 添加律师
        return lawyerService.addLawyer(lawyer, false);
    }

    @Override
    public int updatePassword(String mobile, String code, String password, String codeInSession, String mobileInSession) {

        logger.debug("updatePassword and mobile:{} \r\n code {}", mobile, code);

        if (StringUtils.isEmpty(mobile) || StringUtils.isEmpty(code) || StringUtils.isEmpty(password)) {
            logger.error("updatePassword fail due to params is null....");
            return -3;
        }

        // 验证手机号码和验证码
        if (!mobile.equals(mobileInSession) || !code.equals(codeInSession)) {
            logger.error("updatePassword fail due to  mobile or code is wrong");
            return -2;
        }

        return lawyerService.updatePassword(mobile, password);
    }
}
