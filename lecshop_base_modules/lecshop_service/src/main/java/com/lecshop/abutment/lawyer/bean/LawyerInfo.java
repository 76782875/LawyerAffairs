package com.lecshop.abutment.lawyer.bean;

import com.lecshop.total.lawyer.bean.Lawyer;
import lombok.Data;

/**
 * 接口查询到律师信息
 *
 * @author sunluyang on 2017/7/27.
 */
@Data
public class LawyerInfo {
    /**
     * 律师执业证号
     */
    private String code;
    /**
     * 姓名
     */
    private String name;
    /**
     * 律所
     */
    private String institution;
    /**
     * 经验对象
     */
    private Experience experience;
    /**
     * 满足该搜索条件的所有案件id
     */
    private String[] aboutJudgmentIds;
    /**
     * 评分
     */
    private int score;
    /**
     * 律师头像
     */
    private String photo;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 律师id
     */
    private long lawyerId;

    /**
     * 获取重构后的对象
     *
     * @param lawyer 律师实体类
     * @return 对象
     */
    public LawyerInfo getRestructureLawyerList(LawyerInfo lawyerInfo, Lawyer lawyer, int score) {
        lawyerInfo.score = score;
        lawyerInfo.photo = lawyer.getPic();
        lawyerInfo.lawyerId = lawyer.getId();
        lawyerInfo.mobile = lawyer.getMobile();
        return lawyerInfo;
    }
}
