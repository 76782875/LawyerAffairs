package com.lecshop.total.lawyer.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 律师收益实体类
 *
 * Created by LecShop on 2017/8/21.
 */
@Data
public class LawyerProfit {

    /**
     * 律师id
     */
    private long id;

    /**
     * 订单数目
     */
    private int doneOrder;

    /**
     * 律师收益
     */
    private BigDecimal money;

    /**
     * 律师姓名
     */
    private String name;

    public LawyerProfit() {
    }

    public LawyerProfit(long id, int doneOrder, BigDecimal money, String name) {
        this.id = id;
        this.doneOrder = doneOrder;
        this.money = money;
        this.name = name;
    }

    public static LawyerProfit newInstance(long id, int doneOrder, BigDecimal money, String name) {
        return new LawyerProfit(id, doneOrder, money, name);
    }
}
