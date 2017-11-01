package com.lecshop.total.template.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.total.templatetype.bean.TemplateType;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 合同模版实体类
 * <p>
 * Created by LecShop on 2017/7/11.
 */
@Data
public class Template {

    /**
     * 主键id
     */
    private long id;

    /**
     * 合同模板名称
     */
    private String name;

    /**
     * 合同模版类型id  对应ls_templage_type 中的id
     */
    private long typeId;

    /**
     * 合同模版地址
     */
    private String url;

    /**
     * 删除标记 0 未删除 1 删除
     */
    private String delFlag;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 合同模板所属的合同模板类型
     */
    private TemplateType templateType;

    public Template toGetTemplate(String name, long typeId, String url) {
        this.name = name;
        this.typeId = typeId;
        this.url = url;
        return this;
    }

}
