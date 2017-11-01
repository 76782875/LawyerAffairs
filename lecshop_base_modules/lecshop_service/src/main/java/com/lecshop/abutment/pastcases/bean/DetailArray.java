package com.lecshop.abutment.pastcases.bean;

import lombok.Data;

import java.util.List;

/**
 * 律师相关法律文书信息列表
 *
 * @author sunluyang on 2017/8/1.
 */
@Data
public class DetailArray {
    /**
     * 处理过的所有案件列表
     */
    private List<Judgment> allJudgments;
    /**
     * 最近两年的案件列表
     */
    private List<Judgment> recent2yearJudgments;
}
