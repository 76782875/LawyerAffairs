package com.lecshop.abutment.pastcases.bean;

import lombok.Data;

/**
 * 过往案例接口返回JSON
 *
 * @author sunluyang on 2017/8/1.
 */
@Data
public class ResponseJsons {
    /**
     * 1：查询成功，0：查询失败
     */
    private int reslut;
    /**
     * 异常信息
     */
    private String error;
    /**
     * 律师相关法律文书信息列表
     */
    private DetailArray a_detail;
}
