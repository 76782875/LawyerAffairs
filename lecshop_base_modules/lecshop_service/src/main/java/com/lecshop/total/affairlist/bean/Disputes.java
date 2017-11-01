package com.lecshop.total.affairlist.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.total.companyinfo.bean.CompanyInfo;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.user.bean.User;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 纠纷实体类
 *
 * @author sunluyang on 2017/7/21.
 */
@Data
public class Disputes {
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
     * 律师id
     */
    private long lawyerId;
    /**
     * 状态 0 待律师确认 1 拒绝 2 律师接单 3 已完成
     */
    private String status;
    /**
     * 纠纷要多少钱
     */
    private BigDecimal price;
    /**
     * 描述
     */
    private String desc;
    /**
     * 咨询类型
     */
    private String consultType;
    /**
     * 评分 1 ～5 分
     */
    private int score;
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

    public Disputes buildDisputes(long companyId, long userId) {
        this.companyId = companyId;
        this.userId = userId;
        this.status = "0";
        return this;
    }
}
