package com.lecshop.admin.adminmenu.service;


import com.lecshop.admin.adminmenu.bean.AdminMenu;

import java.util.List;

/**
 * admin端菜单service层
 *
 * @author sunluyang on 2017/7/10.
 */
public interface AdminMenuService {

    /**
     * 根据管理员id查询菜单
     *
     * @param ManagerId 管理员id
     * @return 管理员菜单实体类
     */
    List<AdminMenu> queryAdminMenu(Long ManagerId);

    /**
     * 根据管理员id所有菜单
     *
     * @param ManagerId 管理员id
     * @return 管理员菜单实体类
     */
    List<AdminMenu> queryAllAdminMenu(Long ManagerId);

    /**
     * 根据管理员id查询菜单-公共方法
     *
     * @param ManagerId 管理员id
     * @param isMenu    是否是用于后台菜单
     * @return 管理员菜单实体类 递归返回一级
     */
    List<AdminMenu> adminMenuCommon(Long ManagerId, boolean isMenu);
}
