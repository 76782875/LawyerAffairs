package com.lecshop.company.companymenu.service;


import com.lecshop.company.companymenu.bean.CompanyMenu;

import java.util.List;

/**
 * 公司端菜单service
 *
 * @author sunluyang on 2017/6/7.
 */
public interface CompanyMenuService {
    /**
     * 根据管理员id查询菜单
     *
     * @param customerId 会员id
     * @return 管理员菜单实体类
     */
    List<CompanyMenu> queryCompanyMenu(Long customerId);

    /**
     * 根据管理员id所有菜单
     *
     * @param customerId 会员id
     * @return 管理员菜单实体类
     */
    List<CompanyMenu> queryAllCompanyMenu(Long customerId);

    /**
     * 根据管理员id查询菜单-公共方法
     *
     * @param customerId 会员id
     * @param isMenu     是否是用于后台菜单
     * @return 管理员菜单实体类 递归返回一级
     */
    List<CompanyMenu> companyMenuCommon(Long customerId, boolean isMenu);
}
