package com.lecshop.total.lawyer.bean;

import lombok.Data;

/**
 * 对外接口律师实体类
 *
 * @author sunluyang on 2017/7/27.
 */
@Data
public class LawyerExternal {
    /**
     * 主键id
     */
    private long id;

    /**
     * 律师姓名
     */
    private String name;
    /**
     * 律师手机号码
     */
    private String mobile;
    /**
     * 执业证号
     */
    private String code;
    /**
     * 律师类型 1 自己的律师 2 外面的律师
     */
    private String type;
    /**
     * 律师所在地址
     */
    private String lawyersPlace;
    /**
     * 是否禁用搜索 0 启用 1 禁用
     */
    private String searchForbid;
}
