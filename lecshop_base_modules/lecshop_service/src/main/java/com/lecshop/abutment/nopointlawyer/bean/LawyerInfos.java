package com.lecshop.abutment.nopointlawyer.bean;

import lombok.Data;


/**
 * 未指定律师接口律师对象
 *
 * @author sunluyang on 2017/8/3.
 */
@Data
public class LawyerInfos {
    /**
     * 律师证号
     */
    private String code;
    /**
     * 律师姓名
     */
    private String name;
    /**
     * 律所
     */
    private String institution;
}
