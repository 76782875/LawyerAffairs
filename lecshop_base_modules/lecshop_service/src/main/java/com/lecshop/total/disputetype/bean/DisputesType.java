package com.lecshop.total.disputetype.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 公司纠纷类型实体类
 *
 * Created by LecShop on 2017/7/11.
 */
@Data
public class DisputesType {

    /**
     * 主键id
     */
    private long id;

    /**
     * 纠纷类型名称
     */
    private String name;

    /**
     * 排序
     */
    private int sort;

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
     * 父级id 如果是第一级  则为0
     */
    private long parentId;

    /**
     * 级别 1 表示第一级 2表示第二级
     */
    private int grade;

    /**
     * 对接code
     */
    private String code;

}
