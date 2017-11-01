package com.lecshop.utils;

import org.springframework.util.StringUtils;

/**
 * @author sunluyang on 2017/7/17.
 */
public class PhoneValidUtils {

    /**
     * 校验手机号码
     *
     * @param phone 手机号码
     * @return TRUE 校验通过手机号码合法 FALSE 反之
     */
    public static boolean toValid(String phone) {
        return !StringUtils.isEmpty(phone) && phone.matches("^((13[0-9])|(14[5|7])|(15([0-9]))|(18[0-3,5-9])|(177))\\d{8}$");
    }
}
