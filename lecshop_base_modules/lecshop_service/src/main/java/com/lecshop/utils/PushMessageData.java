package com.lecshop.utils;

import lombok.Data;

/**
 * Created by dujinkai on 17/7/31.
 * 微信公众号推送的内容
 */
@Data
public class PushMessageData {

    /**
     * 标题
     */
    private PushMessageContent first;

    /**
     * 业务类型
     */
    private PushMessageContent keyword1;

    /**
     * 咨询问题
     */
    private PushMessageContent keyword2;

    /**
     * 咨询时间
     */
    private PushMessageContent keyword3;

    /**
     * 描述
     */
    private PushMessageContent remark;

    public PushMessageData buildPushMessageData(String keyword1, String keyword2, String keyword3, String remark) {
        this.first = new PushMessageContent().buildPushMessageContent("#173177", "预约会面");
        this.keyword1 = new PushMessageContent().buildPushMessageContent("#173177", keyword1);
        this.keyword2 = new PushMessageContent().buildPushMessageContent("#173177", keyword2);
        this.keyword3 = new PushMessageContent().buildPushMessageContent("#173177", keyword3);
        this.remark = new PushMessageContent().buildPushMessageContent("#173177", "会面地点："+remark);
        return this;
    }

}
