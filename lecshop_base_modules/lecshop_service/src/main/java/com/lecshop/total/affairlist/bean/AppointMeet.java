package com.lecshop.total.affairlist.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import com.lecshop.total.companyinfo.bean.CompanyInfo;
import com.lecshop.total.user.bean.User;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 预约会面实体类
 *
 * @author sunluyang on 2017/7/11.
 */
@Data
public class AppointMeet {
    /**
     * 主键id
     */
    private long id;
    /**
     * 公司id
     */
    private long companyId;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 律师id 默认 -1 不属于任何律师
     */
    private long lawyerId;
    /**
     * 类型 1 公司事务 2公司纠纷 3 公司重大事项
     */
    private String type;
    /**
     * 1 指定律师 2 没有指定律师
     */
    private String meetType;
    /**
     * 状态 0 待律师确认 1 拒绝 2 律师接单 3 已完成
     */
    private String status;
    /**
     * 会面时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime meetTime;
    /**
     * 会面地点
     */
    private String meetAddress;
    /**
     * 评分 1 ～5 分
     */
    private int score;

    /**
     * 描述
     */
    private String desc;

    /**
     * 咨询类型
     */
    private String consultType;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;
    /**
     * 公司员工实体类
     */
    private User user;
    /**
     * 公司信息实体类
     */
    private CompanyInfo companyInfo;
    /**
     * 律师实体类
     */
    private Lawyer lawyer;

    public AppointMeet getCompanyIdAndUserId(long companyId, long userId) {
        this.companyId = companyId;
        this.userId = userId;
        return this;
    }

    public AppointMeet buildIsPointForMeet(long lawyerId) {
        this.lawyerId = lawyerId;
        this.meetType = lawyerId != -1 ? "1" : "2";
        this.status = "0";
        return this;
    }
}
