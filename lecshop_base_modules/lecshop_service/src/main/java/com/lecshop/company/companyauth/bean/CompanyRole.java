package com.lecshop.company.companyauth.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 公司角色实体类
 *
 * @author sunluyang on 2017/6/7.
 */
@Data
public class CompanyRole {
    /**
     * 主键id
     */
    private long id;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 公司id
     */
    private long companyId;
    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 构建实体类
     *
     * @param name      角色名称
     * @param companyId 公司id
     * @return 公司角色
     */
    public CompanyRole getAddCompanyRole(String name, long companyId) {
        this.name = name;
        this.companyId = companyId;
        return this;
    }

    @JsonIgnore
    public CompanyRole getCompanyRole(long id, String name, long companyId) {
        this.id = id;
        this.name = name;
        this.companyId = companyId;
        return this;
    }
}
