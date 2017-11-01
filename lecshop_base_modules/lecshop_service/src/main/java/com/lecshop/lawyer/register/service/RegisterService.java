package com.lecshop.lawyer.register.service;

import com.lecshop.total.lawyer.bean.Lawyer;

/**
 * Created by dujinkai on 17/7/24.
 * 律师注册接口
 */
public interface RegisterService {

    /**
     * 发送手机验证码
     *
     * @param mobile         手机号码
     * @param validateMobile 是否需要校验验证吗
     * @return 返回手机发送的验证码  -1:手机号码已经存在
     */
    String toSendValidateCode(String mobile, boolean validateMobile);

    /**
     * 注册律师
     *
     * @param lawyer          律师信息
     * @param code            律师输入的手机验证码
     * @param codeInSession   在session中的验证码
     * @param mobileInSession 在session中的手机号码
     * @return -3:参数错误  -2:验证码错误   -1:手机号码存在 0:失败 1:成功
     */
    int registerLawyer(Lawyer lawyer, String code, String codeInSession, String mobileInSession);

    /**
     * 找回密码
     *
     * @param mobile          手机号码
     * @param code            验证码
     * @param password        密码
     * @param codeInSession   session中的验证码
     * @param mobileInSession session中的手机号码
     * @return -3:参数错误  -2:验证码错误  0:失败 1:成功
     */
    int updatePassword(String mobile, String code, String password, String codeInSession, String mobileInSession);
}
