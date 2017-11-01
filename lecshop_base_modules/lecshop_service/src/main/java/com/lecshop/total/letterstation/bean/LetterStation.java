package com.lecshop.total.letterstation.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 站内信实体类
 *
 * @author sunluyang on 2017/7/27.
 */
@Data
public class LetterStation {
    /**
     * 主键id
     */
    private long id;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 状态 0 未读 1 已读
     */
    private String status;
    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;
    /**
     * 是否删除，0未删除，1删除
     */
    private String delFlag;
}
