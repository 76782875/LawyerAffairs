package com.lecshop.company.companyauth.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户和角色的关联表的实体类
 *
 * @author sunluyang on 2017/6/7.
 */
@Data
public class UserAndRole {
    /**
     * 用户id
     */
    private long userId;
    /**
     * 角色id
     */
    private long roleId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;
}
