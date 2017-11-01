package com.lecshop.total.affairlist.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 文书事务交流详情
 *
 * @author sunluyang on 2017/7/11.
 */
@Data
public class ModifyDocDetail {
    /**
     * 主键id
     */
    private long id;
    /**
     * 文书id
     */
    private long docId;
    /**
     * 1 用户 2 律师
     */
    private String type;
    /**
     * 描述
     */
    private String desc;
    /**
     * 文书的下载地址
     */
    private String docUrl;
    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    public ModifyDocDetail() {
    }

    public ModifyDocDetail(long docId, String type, String desc, String docUrl) {
        this.docId = docId;
        this.type = type;
        this.desc = desc;
        this.docUrl = docUrl;
    }

    public static ModifyDocDetail newInstance(long docId, String type, String desc, String docUrl) {
        return new ModifyDocDetail(docId, type, desc, docUrl);
    }
}
