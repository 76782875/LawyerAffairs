package com.lecshop.company.companymenu.mapper;

import com.lecshop.company.companymenu.bean.CompanyMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公司端菜单mapper
 *
 * @author sunluyang on 2017/6/7.
 */
@Repository
public interface CompanyMenuMapper {

    /**
     * 根据管理员id查询菜单
     *
     * @param customerId 管理员id
     * @return 管理员菜单实体类
     */
    List<CompanyMenu> queryCompanyMenu(Long customerId);
}
