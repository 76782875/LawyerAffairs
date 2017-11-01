package com.lecshop.admin.role.bean;

import lombok.Data;

/**
 * 角色和管理员关联表的实体类
 */
@Data
public class RoleAndManager {
    /**
     * 管理员id
     */
    private long managerId;
    /**
     * 角色id
     */
    private long roleId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 是否启用
     */
    private String isUse;
}
