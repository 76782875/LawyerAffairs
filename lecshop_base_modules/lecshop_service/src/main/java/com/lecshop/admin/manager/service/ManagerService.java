package com.lecshop.admin.manager.service;


import com.lecshop.admin.adminmenu.bean.AdminEditMenu;
import com.lecshop.admin.manager.bean.Manager;
import com.lecshop.admin.role.bean.RoleAndManager;
import com.lecshop.utils.PageHelper;

import java.util.List;

/**
 * 管理员服务接口
 *
 * @author sunluyang on 2017/7/10.
 */
public interface ManagerService {

    /**
     * 根据用户名查询用户信息
     *
     * @param name 用户名
     * @return 返回用户信息
     */
    Manager queryManagerByName(String name);

    /**
     * 修改管理员密码
     *
     * @param oldPassword   原密码
     * @param newPassword   新密码
     * @param reNewPassword 重新输入的密码
     * @return 编辑返回码
     */
    int updateManagerPassWord(Manager manager, String oldPassword, String newPassword, String reNewPassword);


    /**
     * 分页查询管理员列表
     *
     * @param pageHelper 分页对象
     * @param name       管理员名称
     * @param id         当前登录管理员id
     * @return 返回管理员列表
     */
    PageHelper<Manager> queryManagers(PageHelper<Manager> pageHelper, String name, long id);

    /**
     * 添加管理员
     *
     * @param manager 管理员对象
     * @param roleId  角色id
     * @return 添加返回码 0 失败 1成功
     */
    int addManager(Manager manager, int roleId);

    /**
     * 根据管理员id查询角色和管理员关联对象
     *
     * @param managerId 管理员id
     * @return 角色和管理员关联对象
     */
    RoleAndManager queryRoleAndManagerByManagerId(long managerId);

    /**
     * 编辑管理员
     *
     * @param roleAndManager 角色管理员实体类
     * @return -1用户名存在,1编辑成功
     */
    int editManager(RoleAndManager roleAndManager);

    /**
     * 删除管理员
     *
     * @param managerIds 管理员id
     * @return 返回码
     */
    int deleteManager(long[] managerIds);

    /**
     * 根据用户Id查询角色菜单
     *
     * @param managerId 管理员Id
     * @return 菜单
     */
    List<AdminEditMenu> roleAuthMenu(long managerId);
}
