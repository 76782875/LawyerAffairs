package com.lecshop.admin.role.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 权限与角色关联表实体类
 *
 * @author sunluyang on 2017/7/10.
 */
@Data
public class RoleAndAuth {
    /**
     * 主键(权限Id)
     */
    private long id;
    /**
     * 角色ID
     */
    private long roleId;
    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;
}
