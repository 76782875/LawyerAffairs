package com.lecshop.total.template.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 合同模板下载记录
 *
 * @author sunluyang on 2017/8/17.
 */
@Data
public class TemplateDownRecord {
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
     * 模板id
     */
    private long templateId;
    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    public TemplateDownRecord buildTemplateDownRecord(long companyId, long userId, long templateId) {
        this.companyId = companyId;
        this.userId = userId;
        this.templateId = templateId;
        return this;
    }
}
