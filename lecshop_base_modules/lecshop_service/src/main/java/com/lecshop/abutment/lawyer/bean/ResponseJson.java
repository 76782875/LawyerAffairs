package com.lecshop.abutment.lawyer.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 律师查询接口返回数据
 *
 * @author sunluyang on 2017/7/27.
 */
@Data
public class ResponseJson {
    /**
     * 1：查询成功，0：查询失败
     */
    private int result;
    /**
     * 异常信息
     */
    private String error;
    /**
     * 当前页码
     */
    private int pi;
    /**
     * 分页大小
     */
    private int ps;
    /**
     * 结果总数
     */
    private int totalcount;
    /**
     * 律师信息列表
     */
    private List<LawyerInfo> a_list = new ArrayList<>();
}
