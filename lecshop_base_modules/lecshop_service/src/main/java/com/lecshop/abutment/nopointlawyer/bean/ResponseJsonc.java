package com.lecshop.abutment.nopointlawyer.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 未指定律师接口返回对象
 *
 * @author sunluyang on 2017/8/3.
 */
@Data
public class ResponseJsonc {
    /**
     * 1：查询成功，0：查询失败
     */
    private int result;
    /**
     * 异常信息
     */
    private String error;
    /**
     * 律师信息列表
     */
    private List<LawyerInfos> a_list = new ArrayList<>();
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
}
