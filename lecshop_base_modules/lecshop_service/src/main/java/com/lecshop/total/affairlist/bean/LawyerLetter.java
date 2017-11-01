package com.lecshop.total.affairlist.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.total.companyinfo.bean.CompanyInfo;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.user.bean.User;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 律师函实体类
 * <p>
 * Created by LecShop on 2017/7/19.
 */
@Data
public class LawyerLetter {

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
     * 收件人电话
     */
    private String mobile;

    /**
     * 收件人地址
     */
    private String address;
    /**
     * 状态
     */
    private String status;
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

    public static LawyerLetter newInstance(long companyId, long userId, long lawyerId, String mobile, String address) {
        LawyerLetter letter = new LawyerLetter();
        letter.companyId = companyId;
        letter.userId = userId;
        letter.lawyerId = lawyerId;
        letter.mobile = mobile;
        letter.address = address;
        return letter;
    }

}
