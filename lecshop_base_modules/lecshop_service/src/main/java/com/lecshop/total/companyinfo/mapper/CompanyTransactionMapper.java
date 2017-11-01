package com.lecshop.total.companyinfo.mapper;

import com.lecshop.total.companyinfo.bean.CompanyTransaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 公司对账数据库接口
 *
 * Created by LecShop on 2017/8/23.
 */
@Repository
public interface CompanyTransactionMapper {

    /**
     * 查询公司
     *
     * @param params 查询参数
     * @return 公司
     */
    List<CompanyTransaction> queryCompany(Map<String, Object> params);

    /**
     * 查询公司总数
     *
     * @param params 查询参数
     * @return 公司总数
     */
    int queryCompanyCount(Map<String, Object> params);
}
