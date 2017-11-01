package com.lecshop.company.register;

/**
 * 注册service接口
 *
 * @author sunluyang on 2017/7/17.
 */
public interface RegisterService {
    /**
     * 发送注册验证码
     *
     * @param phone 手机号码
     * @return 发送短信返回码 -1 手机号码非法  0发送失败
     */
    String toSendRegisterCode(String phone, String code);

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
     * @return 注册返回码
     */
    int register(String phone, String phoneCode, String password, String rePassword, String companyName, String code, String businessLicence, String codeInSession);
}
