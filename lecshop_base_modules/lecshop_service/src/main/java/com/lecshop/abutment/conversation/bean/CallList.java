package com.lecshop.abutment.conversation.bean;

import lombok.Data;

/**
 * 通话清单实体类
 *
 * @author sunluyang on 2017/8/2.
 */
@Data
public class CallList {
    /**
     * 唯一话单编号
     */
    private String call_id;
    /**
     * 用户ID(userId-company-lawyerId)
     */
    private String uid;
    /**
     * 主叫号码
     */
    private String phone;
    /**
     * 被叫号码
     */
    private String call;
    /**
     * 通话开始时间
     */
    private String start_time;
    /**
     * 通话结束时间
     */
    private String end_time;
    /**
     * 通话持续时间（秒）
     */
    private String last_time;
    /**
     * 本次通话费用（元）
     */
    private String fee;
    /**
     * 主被叫接通状态
     * 0：主叫未接通
     * 1：主叫正常接通、被叫未接通
     * 2：主被叫都正常接通
     */
    private String endtype;
    /**
     * 商户备注信息，通话清单中原样返回(返回值为1公司事务2纠纷事务3重大事项)
     */
    private String ext;
    /**
     * 录音下载地址（保存1个月）
     */
    private String recordurl;
}
