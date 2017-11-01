package com.lecshop.company.companyauth.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 公司角色和权限的关联表实体类
 *
 * @author sunluyang on 2017/6/7.
 */
@Data
public class CompanyRoleAndAuth {
    /**
     * 主键id
     */
    private long id;
    /**
     * 权限Id
     */
    private long authId;
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

    public CompanyRoleAndAuth getAddCompanyRoleAndAuth(long authId, long roleId) {
        this.authId = authId;
        this.roleId = roleId;
        return this;
    }
}
