package com.lecshop.total.companyinfo.service;

import com.lecshop.total.companyinfo.bean.CompanyTransaction;
import com.lecshop.utils.PageHelper;

/**
 * 公司对账service
 *
 * Created by LecShop on 2017/8/23.
 */
public interface CompanyTransactionService {

    /**
     * 分页查询公司对账
     *
     * @param pageHelper 分页帮助类
     * @param startTime  起始时间
     * @param endTime    终止时间
     * @return 公司对账
     */
    PageHelper<CompanyTransaction> queryCompanyTransaction(PageHelper<CompanyTransaction> pageHelper, String startTime, String endTime);
}
