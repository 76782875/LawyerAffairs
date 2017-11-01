package com.lecshop.abutment.nopointlawyer.bean;

import lombok.Data;

/**
 * 未指定律师返回实体类
 *
 * @author sunluyang on 2017/8/3.
 */
@Data
public class SearchBeanc {
    /**
     * 案件类型：案件类型编码
     */
    private String casetype;
    /**
     * 自定义案件类型
     */
    private String casetypefree;
    /**
     * 密钥
     */
    private String key;
    /**
     * 页码，从1开始
     */
    private int pi;
    /**
     * 分页大小
     */
    private int ps;

    public static SearchBeanc getPiAndPs(SearchBeanc searchBeanc) {
        searchBeanc.pi = 1;
        searchBeanc.ps = 10;
        return searchBeanc;
    }
}
