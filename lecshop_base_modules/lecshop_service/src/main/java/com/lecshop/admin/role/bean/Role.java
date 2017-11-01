package com.lecshop.admin.role.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 角色实体类
 *
 * @author sunluyang on 2017/7/10.
 */
@Data
public class Role {
    /**
     * 主键id
     */
    private long id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 删除标志 0未删除 1 删除
     */
    private String delFlag;
    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;
}
