package com.lecshop.total.companyinfo.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.total.user.bean.User;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 公司信息实体类
 *
 * @author sunluyang on 2017/7/14.
 */
@Data
public class CompanyInfo {
    /**
     * 主键id
     */
    private long id;
    /**
     * 公司名称
     */
    private String name;
    /**
     * 社会信用号
     */
    private String code;
    /**
     * 公司行业
     */
    private String industry;
    /**
     * 公司地址
     */
    private String address;
    /**
     * 营业执照地址
     */
    private String businessLicence;
    /**
     * 传真
     */
    private String fax;
    /**
     * 总消费
     */
    private BigDecimal totalConsumption;
    /**
     * 有效期结束时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime startTime;
    /**
     * 有效期结束时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime endTime;
    /**
     * 会员等级 0 1 2 3
     */
    private String vipType;
    /**
     * 用户信息
     */
    private User user;

    @JsonIgnore
    public CompanyInfo getDefaultTotalConsumption() {
        this.totalConsumption = new BigDecimal(0);
        return this;
    }

    public CompanyInfo getRegisterCompanyInfo(String name, String code, String businessLicence) {
        this.name = name;
        this.code = code;
        this.businessLicence = businessLicence;
        this.totalConsumption = new BigDecimal(0);
        return this;
    }

    public CompanyInfo getPersonInfo(long id, long userId, String name, String address, String fax, String businessLicence) {
        this.id = id;
        this.fax = fax;
        this.name = name;
        this.address = address;
        this.businessLicence = businessLicence;
        this.user = new User();
        user.setId(userId);
        user.setStatus("0");
        return this;
    }

    public CompanyInfo buildVip(String vipType, LocalDateTime startTime) {
        this.vipType = vipType;
        this.startTime = startTime;
        this.endTime = this.startTime.plusYears(1);
        return this;
    }
}
