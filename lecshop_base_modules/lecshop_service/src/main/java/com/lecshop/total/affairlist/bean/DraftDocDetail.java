package com.lecshop.total.affairlist.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 草拟文书详情实体类
 *
 * Created by LecShop on 2017/7/31.
 */
@Data
public class DraftDocDetail {

    /**
     * 主键id
     */
    private long id;

    /**
     * 草拟文书id
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
     * 文书下载地址
     */
    private String docUrl;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    public static DraftDocDetail newInstance(long docId, String type, String desc) {
        DraftDocDetail detail = new DraftDocDetail();
        detail.docId = docId;
        detail.type = type;
        detail.desc = desc;
        return detail;
    }

    public static DraftDocDetail newInstance(long docId, String type, String desc, String docUrl) {
        DraftDocDetail detail = new DraftDocDetail();
        detail.docId = docId;
        detail.type = type;
        detail.desc = desc;
        detail.docUrl = docUrl;
        return detail;
    }
}
