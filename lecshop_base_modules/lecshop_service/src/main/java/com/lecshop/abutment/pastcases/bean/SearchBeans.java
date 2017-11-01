package com.lecshop.abutment.pastcases.bean;

import lombok.Data;

/**
 * 过往案例搜索条件
 *
 * @author sunluyang on 2017/8/1.
 */
@Data
public class SearchBeans {
    /**
     * 律师执业证号
     */
    private String id;
    /**
     * 未知
     */
    private String name;
    /**
     * 律师所在律所
     */
    private String institution;
    /**
     * 密钥
     */
    private String key;
}
