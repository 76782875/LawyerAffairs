package com.lecshop.abutment.casedetail.bean;

import lombok.Data;

/**
 * 案例细节接口返回实体类
 *
 * @author sunluyang on 2017/8/2.
 */
@Data
public class ResponseJsonz {
    /**
     * 1：查询成功，0：查询失败
     */
    private int result;
    /**
     * 异常信息
     */
    private String error;
    /**
     * 案例细节
     */
    private CaseDetail a_detail;
}
