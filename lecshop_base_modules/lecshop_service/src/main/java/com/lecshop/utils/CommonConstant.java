package com.lecshop.utils;

/**
 * 公共常量类
 *
 * @author sunluyang on 2017/7/10.
 */
public interface CommonConstant {

    /**
     * 开始页
     */
    String START_ROW_NUM = "startRowNum";

    /**
     * 每页大小
     */
    String PAGE_SIZE = "pageSize";

    /**
     * admin端session放置验证码的key
     */
    String ADMIN_LOGIN_KAPTCHA_KEY = "ADMIN_LOGIN_KAPTCHA_KEY";
    /**
     * 公司登录验证码
     */
    String COMPANY_LOGIN_KAPTCHA_KEY = "COMPANY_LOGIN_KAPTCHA_KEY";

    /**
     * 登录成功后把用户放入session中的key
     */
    String ADMIN_LOGIN_SESSION_KEY = "ADMIN_LOGIN_SESSION_KEY";

    /**
     * 公司登录成功后用户信息放入到session中的key
     */
    String COMPANY_LOGIN_SESSION_KEY = "COMPANY_LOGIN_SESSION_KEY";

    /**
     * 公司注册码放入到session中的key
     */
    String COMPANY_REGISTER_KAPTCHA_KEY = "COMPANY_REGISTER_KAPTCHA_KEY";

    /**
     * 公司忘记密码放入到session中的key
     */
    String COMPANY_FORGET_PASSWORD_KAPTCHA_KEY = "COMPANY_FORGET_PASSWORD_KAPTCHA_KEY";

    /**
     * admin一级菜单id
     */
    String ADMIN_MENU_FIRSTMENUS = "firstMenus";

    /**
     * admin二级菜单id
     */
    String ADMIN_MENU_SECONDMENUS = "secondMenus";

    /**
     * admin三级菜单id
     */
    String ADMIN_MENU_THIRDMENUS = "thirdMenus";

    /**
     * 公司端一级菜单id
     */
    String COMPANY_MENU_FIRSTMENUS = "firstMenus";

    /**
     * 公司端二级菜单id
     */
    String COMPANY_MENU_SECONDMENUS = "secondMenus";

    /**
     * 公司端三级菜单id
     */
    String COMPANY_MENU_THIRDMENUS = "thirdMenus";

    /**
     * 管理员启用
     */
    String MANAGER_USE = "0";

    /**
     * 律师登录后session的key
     */
    String LAWYER_SESSION_KEY = "LAWYER_SESSION_KEY";

    /**
     * 律师注册验证码key
     */
    String LAWYER_REGISTER_KAPTCHA_KEY = "LAWYER_REGISTER_KAPTCHA_KEY";

    /**
     * 律师注册的时候的手机号码
     */
    String LAWYER_REGISTER_MOBILE_KEY = "LAWYER_REGISTER_MOBILE_KEY";


    /**
     * 律师找回密码验证码key
     */
    String LAWYER_FOREGTPASSWORD_KAPTCHA_KEY = "LAWYER_FOREGTPASSWORD_KAPTCHA_KEY";

    /**
     * 律师找回密码的时候的手机号码
     */
    String LAWYER_FOREGTPASSWORD_MOBILE_KEY = "LAWYER_FOREGTPASSWORD_MOBILE_KEY";

    /**
     * 微信认证的openId
     */
    String LAWYER_WX_OPENID = "LAWYER_WX_OPENID";

    String ENCODE = "UTF-8";

    String LAWYER_SEARCH_INTERFACE = "http://183.207.184.125:8081/lawyer/rest/search";

    String PAST_CASES_INTERFACE = "http://183.207.184.125:8081/lawyer/rest/lawyer";

    String PAST_CASES_DETAIL_INTERFACE = "http://183.207.184.125:8081/lawyer/rest/judgment/";

    String APPOINT_LAWYER_INTERFACE = "http://183.207.184.125:8081/lawyer/rest/lawyersearch";
}
