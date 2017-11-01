package com.lecshop.total.withdrawset.bean;

import lombok.Data;

/**
 * Created by dujinkai on 17/7/25.
 * 律师提现设置表
 */
@Data
public class WithdrawSet {

    /**
     * 主键id
     */
    private long id;

    /**
     * 律师id
     */
    private long lawyerId;

    /**
     * 支付包账户
     */
    private String account;

    /**
     * 支付包实名认证姓名
     */
    private String name;
}
