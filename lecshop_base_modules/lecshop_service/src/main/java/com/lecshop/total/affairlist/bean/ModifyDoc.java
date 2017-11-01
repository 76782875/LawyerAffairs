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
 * 文书事务实体类
 *
 * @author sunluyang on 2017/7/11.
 */
@Data
public class ModifyDoc {
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
     * 0 刚提交 待律师修改 1 律师修改文书中  2 待用户确认 3 已完成
     */
    private String status;
    /**
     * 文书的下载地址
     */
    private String docUrl;
    /**
     * 描述
     */
    private String desc;
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

    public static ModifyDoc newInstance(long companyId, long userId, long lawyerId, String name, String desc, String docUrl) {
        ModifyDoc modifyDoc = new ModifyDoc();
        modifyDoc.companyId = companyId;
        modifyDoc.userId = userId;
        modifyDoc.lawyerId = lawyerId;
        modifyDoc.name = name;
        modifyDoc.desc = desc;
        modifyDoc.docUrl = docUrl;
        return modifyDoc;
    }
}
