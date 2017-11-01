package com.lecshop.admin.sms.mapper;

import com.lecshop.admin.sms.bean.Sms;
import org.springframework.stereotype.Repository;

/**
 * 短信接口设置mapper层
 *
 * @author sunluyang on 2017/7/10.
 */
@Repository
public interface SmsMapper {

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
     * @return 编辑返回码
     */
    int editSmsSet(Sms sms);
}
