package com.lecshop.total.affairlist.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 律师函 详情实体类
 *
 * Created by LecShop on 2017/7/19.
 */
@Data
public class LawyerLetterDetail {

    /**
     * 主键id
     */
    private long id;

    /**
     * 律师函id
     */
    private long letterId;

    /**
     * 1 用户 2 律师
     */
    private String type;

    /**
     * 描述
     */
    private String desc;

    /**
     * 文件地址
     */
    private String fileUrl;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    public LawyerLetterDetail() {
    }

    public LawyerLetterDetail(long letterId, String type, String desc, String fileUrl) {
        this.letterId = letterId;
        this.type = type;
        this.desc = desc;
        this.fileUrl = fileUrl;
    }

    public static LawyerLetterDetail newInstance(long letterId, String type, String desc, String fileUrl) {
        return new LawyerLetterDetail(letterId, type, desc, fileUrl);
    }

}
