package com.lecshop.abutment.casedetail.bean;

import lombok.Data;

/**
 * @author sunluyang on 2017/8/2.
 */
@Data
public class CaseDetail {
    /**
     * 裁判文书id
     */
    private String id;
    /**
     * 裁判文书标题
     */
    private String title;
    /**
     *
     */
    private String time;
    /**
     * 审判法庭
     */
    private String court;
    /**
     * 裁判文书编号
     */
    private String number;
    /**
     * 裁判文书详情
     */
    private String mainBody;
}
