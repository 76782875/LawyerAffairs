package com.lecshop.utils;

import lombok.Data;

/**
 * Created by dujinkai on 17/7/28.
 * 微信公众号推送消息的内容实体
 */
@Data
public class PushMessageContent {

    /**
     * 值
     */
    private String value;

    /**
     * 颜色
     */
    private String color;

    public PushMessageContent buildPushMessageContent(String color, String value) {
        this.color = color;
        this.value = value;
        return this;
    }
}



