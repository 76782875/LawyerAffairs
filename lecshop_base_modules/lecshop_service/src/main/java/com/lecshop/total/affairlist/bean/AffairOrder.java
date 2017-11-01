package com.lecshop.total.affairlist.bean;

import lombok.Data;

import java.time.LocalTime;

/**
 * 事务订单实体类
 *
 * @author sunluyang on 2017/8/10.
 */
@Data
public class AffairOrder {
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 订单名称
     */
    private String orderName;
    /**
     * 订单金额
     */
    private String money;
    /**
     * 订单类型：1纠纷 2重大事项
     */
    private String type;

    public AffairOrder buildAffairOrder(String orderNo, String orderName, String money, String type) {
        this.orderNo = orderNo + "-" + LocalTime.now() + "-" + type;
        this.orderName = orderName;
        this.money = money;
        this.type = type;
        return this;
    }
}
