package com.lecshop.company.index.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 待处理事项实体类
 *
 * @author sunluyang on 2017/7/21.
 */
@Data
public class ToDoAffairs implements Comparable<ToDoAffairs> {
    /**
     * 主键id
     */
    private long id;
    /**
     * 律师名称
     */
    private String lawyerName;
    /**
     * 状态
     */
    private String status;
    /**
     * 类型
     */
    private String type;

    /**
     * 事务类型
     */
    private String affairType;
    /**
     * 预约会面类型
     */
    private String meetType;

    /**
     * 时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime time;

    public ToDoAffairs getDefaultToDoAffairs(long id, String lawyerName, String status, LocalDateTime time, String type) {
        this.id = id;
        this.lawyerName = lawyerName;
        this.status = status;
        this.type = type;
        this.time = time;
        return this;
    }

    public static ToDoAffairs buildMeetType(ToDoAffairs toDoAffairs, String meetType, String affairType) {
        toDoAffairs.meetType = meetType;
        toDoAffairs.affairType = affairType;
        return toDoAffairs;
    }

    /**
     * 按时间排序
     *
     * @param toDoAffairs 待处理事务
     * @return 相等返回0，小于返回<0，大于返回>0
     */
    @Override
    public int compareTo(ToDoAffairs toDoAffairs) {
        return toDoAffairs.getTime().compareTo(this.getTime());
    }
}
