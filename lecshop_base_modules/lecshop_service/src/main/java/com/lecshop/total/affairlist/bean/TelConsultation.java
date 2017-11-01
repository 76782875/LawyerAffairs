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
 * 电话咨询实体类
 *
 * @author sunluyang on 2017/7/11.
 */
@Data
public class TelConsultation {
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
     * 类型 1 公司事务 2公司纠纷 3 公司重大事项
     */
    private String type;
    /**
     * 评分 1 ～5
     */
    private int score;
    /**
     * 0 待用户确认 1律师催促 2已完成
     */
    private String status;
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

    /**
     * 构建电话咨询
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @param lawyerId  律师id
     * @param type      类型 1 公司事务 2公司纠纷 3 公司重大事项
     * @return 电话咨询实体类
     */
    public static TelConsultation buildTelConsultation(long companyId, long userId, long lawyerId, String type) {
        TelConsultation telConsultation = new TelConsultation();
        telConsultation.type = type;
        telConsultation.status = "0";
        telConsultation.userId = userId;
        telConsultation.lawyerId = lawyerId;
        telConsultation.companyId = companyId;
        return telConsultation;
    }
}
