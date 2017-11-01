package com.lecshop.utils;

import com.alibaba.fastjson.annotation.JSONField;
import com.lecshop.total.affairlist.bean.AppointMeet;
import lombok.Data;

/**
 * Created by dujinkai on 17/7/28.
 * 微信公众号推送消息实体
 */
@Data
public class PushMessage {

    /**
     * 推送给用户的openId
     */
    @JSONField(name = "touser")
    private String toUser;

    /**
     * 模版id
     */
    @JSONField(name = "template_id")
    private String templateId = "dejFd_4lObaNhMJNk3JXZROc9zDyOf0Boxk3HZRoXa4";

    /**
     * 内容
     */
    @JSONField(name = "data")
    private PushMessageData data;

    public static PushMessage buildPushMessage(PushMessage pushMessage, String toUser, AppointMeet appointMeet) {
        String keyword1 = appointMeet.getConsultType();
        String keyword2 = appointMeet.getDesc();
        String keyword3 = appointMeet.getMeetTime().toString();
        String remark = appointMeet.getMeetAddress();
        pushMessage.toUser = toUser;
        pushMessage.data = new PushMessageData().buildPushMessageData(keyword1, keyword2, keyword3, remark);
        return pushMessage;
    }
}


