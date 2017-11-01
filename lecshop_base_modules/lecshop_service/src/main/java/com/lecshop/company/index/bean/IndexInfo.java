package com.lecshop.company.index.bean;

import com.lecshop.total.user.bean.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 首页所有信息实体类
 *
 * @author sunluyang on 2017/7/21.
 */
@Data
public class IndexInfo {

    /**
     * 用户信息
     */
    private User user;
    /**
     * 今日消费
     */
    private BigDecimal consumptionToday;
    /**
     * 总消费
     */
    private BigDecimal totalConsumption;

    /**
     * 要处理的事务集合
     */
    private List<ToDoAffairs> toDoAffairs;
}
