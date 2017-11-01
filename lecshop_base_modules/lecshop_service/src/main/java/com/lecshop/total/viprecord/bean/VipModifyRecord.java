package com.lecshop.total.viprecord.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 公司VIP修改记录
 *
 * @author sunluyang on 2017/8/22.
 */
@Data
public class VipModifyRecord {
    /**
     *
     */
    private long id;
    /**
     * 管理员id
     */
    private long managerId;
    /**
     * 公司id
     */
    private long companyId;
    /**
     * 修改前的VIP 0 1 2 3
     */
    private String oldVip;
    /**
     * 修改前的VIP 0 1 2 3
     */
    private String newVip;
    /**
     * 说明
     */
    private String remark;
    /**
     * VIP开始时间
     */
    private LocalDateTime startTime;
    /**
     * VIP结束时间
     */
    private LocalDateTime endTime;
    /**
     * 0 平台操作 1用户充值
     */
    private String type;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 管理员名称
     */
    private String managerName;
    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    public VipModifyRecord buildVipModifyRecordForPay(long companyId, String newVip, String remark, LocalDateTime startTime, LocalDateTime endTime) {
        this.newVip = newVip;
        this.remark = remark;
        this.companyId = companyId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = "1";
        return this;
    }

    public VipModifyRecord buildVipModifyRecordForAdmin(long companyId, String oldVip, LocalDateTime startTime, LocalDateTime endTime) {
        this.oldVip = oldVip;
        this.companyId = companyId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = "0";
        return this;
    }
}
