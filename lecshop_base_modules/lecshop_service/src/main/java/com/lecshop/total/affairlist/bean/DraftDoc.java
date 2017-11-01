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
 * 草拟文书
 * <p>
 * Created by LecShop on 2017/7/18.
 */
@Data
public class DraftDoc {

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
     * 文书名称
     */
    private String name;

    /**
     * 0 刚提交 待律师草拟 1 律师草拟文书中  2 待用户确认 3 已完成
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

    public static DraftDoc newInstance(long companyId, long userId, long lawyerId, String name) {
        DraftDoc doc = new DraftDoc();
        doc.companyId = companyId;
        doc.userId = userId;
        doc.lawyerId = lawyerId;
        doc.name = name;
        return doc;
    }
}
