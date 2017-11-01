package com.lecshop.admin.sms.service;

import com.lecshop.admin.sms.bean.Sms;

/**
 * 短信设置service接口
 *
 * @author sunluyang on 2017/7/10.
 */
public interface SmsService {
    /**
     * 查询短信设置
     *
     * @return 短信实体类
     */
    Sms querySmsSet();

    /**
     * 编辑短信设置
     *
     * @param sms 短信设置实体类
     * @return -1实体类为空 1编辑成功
     */
    int editSmsSet(Sms sms);

    /**
     * 发送短信
     *
     * @param phone   电话号码
     * @param content 发送的内容
     * @return 返回 TRUE or FALSE
     */
    boolean sendMsg(String phone, String content);
}
