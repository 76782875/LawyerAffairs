package com.lecshop.total.companyinfo.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 公司对账实体类
 *
 * Created by LecShop on 2017/8/23.
 */
@Data
public class CompanyTransaction {

    /**
     * 公司id
     */
    private long id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 公司发起事务总数
     */
    private int affairCount;

    /**
     * 公司续费金额
     */
    private BigDecimal money;

}
