package com.lecshop.admin.sms.service.impl;

import com.lecshop.admin.sms.bean.Sms;
import com.lecshop.admin.sms.mapper.SmsMapper;
import com.lecshop.admin.sms.service.SmsService;
import com.lecshop.utils.SendSmsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 短信设置service实现类
 *
 * @author sunluyang on 2017/7/10.
 */
@Service
public class SmsServiceImpl implements SmsService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    /**
     * 注入短信设置mapper
     */
    @Autowired
    private SmsMapper smsMapper;

    /**
     * 查询短信设置
     *
     * @return 短信实体类
     */
    @Override
    public Sms querySmsSet() {
        logger.debug("querySmsSet...");
        return smsMapper.querySmsSet();
    }

    /**
     * 编辑短信设置
     *
     * @param sms 短信设置实体类
     * @return -1实体类为空 1编辑成功
     */
    @Override
    public int editSmsSet(Sms sms) {
        if (Objects.isNull(sms)) {
            logger.error("editSmsSet error due to sms is empty...");
            return -1;
        }
        return smsMapper.editSmsSet(sms);
    }

    /**
     * 发送短信
     *
     * @param phone 电话号码
     * @param param 发送内容
     * @return 返回 TRUE or FALSE
     */
    @Override
    public boolean sendMsg(String phone, String param) {
        return SendSmsUtil.sendSms(smsMapper.querySmsSet().getSmsToSetPhoneAndSendContent(phone, param));
    }
}
