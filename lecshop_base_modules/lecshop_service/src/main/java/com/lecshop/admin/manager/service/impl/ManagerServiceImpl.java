package com.lecshop.admin.manager.service.impl;

import com.lecshop.admin.adminmenu.bean.AdminEditMenu;
import com.lecshop.admin.adminmenu.bean.AdminMenu;
import com.lecshop.admin.adminmenu.service.AdminMenuService;
import com.lecshop.admin.manager.bean.Manager;
import com.lecshop.admin.manager.mapper.ManagerMapper;
import com.lecshop.admin.manager.service.ManagerService;
import com.lecshop.admin.role.bean.RoleAndManager;
import com.lecshop.utils.MD5Utils;
import com.lecshop.utils.PageHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 管理员服务实现类
 *
 * @author sunluyang on 2017/7/10.
 */
@Service
public class ManagerServiceImpl implements ManagerService {
    /**
     * 调试日式
     */
    private Logger Logger = LoggerFactory.getLogger(ManagerServiceImpl.class);

    /**
     * 管理员数据库接口
     */
    @Autowired
    private ManagerMapper managerMapper;
    /**
     * 菜单service
     */
    @Autowired
    private AdminMenuService adminMenuService;

    /**
     * 通过用户名查询管理员
     *
     * @param name 用户名
     * @return Manager管理员
     */
    @Override
    public Manager queryManagerByName(String name) {
        Logger.info("queryManagerByName and name:{}", name);
        if (StringUtils.isEmpty(name)) {
            Logger.error("queryManagerByName fail due to name is empty....");
            return null;
        }
        return managerMapper.queryManagerByName(name);
    }

    /**
     * 修改管理员密码
     *
     * @param manager       管理员对象
     * @param oldPassword   原密码
     * @param newPassword   新密码
     * @param reNewPassword 重新输入的密码
     * @return 修改返回码 0 没有登录 -1 输入不能为空 1 修改成功 2 两次输入密码不一致 3 原始密码不正确
     */
    @Override
    public int updateManagerPassWord(Manager manager, String oldPassword, String newPassword, String reNewPassword) {
        if (Objects.isNull(manager)) {
            return 0;
        }
        //判断是否问空
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(reNewPassword)) {
            return -1;
        }
        if (!newPassword.equals(reNewPassword)) {
            return 2;
        }
        // 根据用户名查询管理员信息queryManagerByName
        Manager queryManager = managerMapper.queryManagerByName(manager.getUsername());
        //判断原密码是否正确
        if (!queryManager.isPasswordRight(oldPassword)) {//oldPassword
            return 3;
        }
        manager.setPassword(MD5Utils.getInstance().createMd5(newPassword));
        return managerMapper.updateManagerPassWord(manager);
    }

    /**
     * 查询所有管理员
     *
     * @param pageHelper 分页对象
     * @param name       管理员名称
     * @param id         当前登录管理员id
     * @return 所有管理员
     */
    @Override
    public PageHelper<Manager> queryManagers(PageHelper<Manager> pageHelper, String name, long id) {
        Logger.debug("queryManagers and pageHelper : {} \r\n name: {}\r\n id:{}", pageHelper, name, id);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("id", id);
        return pageHelper.setListDates(managerMapper.queryManagers(pageHelper.getQueryParams(params, managerMapper.queryManagersCount(params))));
    }


    /**
     * 添加管理员
     *
     * @param manager 管理员对象
     * @param roleId  角色id
     * @return 添加返回码 0 失败 1成功
     */
    @Override
    public int addManager(Manager manager, int roleId) {
        Logger.debug("addManager and manager:{}", manager);
        if (!StringUtils.isEmpty(managerMapper.queryManagerByName(manager.getUsername()))) {
            Logger.error("addManager fail due to name is exit....");
            return -1;
        }
        manager.setPassword(MD5Utils.getInstance().createMd5(manager.getPassword()));
        Map<String, Object> map = new HashMap<>();
        managerMapper.addManager(manager);
        map.put("managerId", manager.getId());
        map.put("roleId", roleId);
        return managerMapper.addManagerRole(map);
    }

    /**
     * 根据管理员id查询角色和管理员关联对象
     *
     * @param managerId 管理员id
     * @return 角色和管理员关联对象
     */
    @Override
    public RoleAndManager queryRoleAndManagerByManagerId(long managerId) {
        Logger.debug("queryRoleAndManagerByManagerId and managerId:{}", managerId);
        return managerMapper.queryRoleAndManagerByManagerId(managerId);
    }

    /**
     * 编辑管理员
     *
     * @param roleAndManager 角色管理员实体类
     * @return -1用户名存在,1编辑成功
     */
    @Override
    public int editManager(RoleAndManager roleAndManager) {
        Logger.debug("editManager and roleAndManager:{}", roleAndManager);
        //查询管理员名称是否已存在
        Manager manager = managerMapper.queryManagerByName(roleAndManager.getUserName());
        if (!StringUtils.isEmpty(manager)) {
            if (manager.getId() != roleAndManager.getManagerId()) {
                Logger.error("editManager fail due to name is exit....");
                return -1;
            }
        }
        //修改管理员表中的数据，修改角色与管理员关联表中的角色id
        managerMapper.updateManager(roleAndManager);
        managerMapper.updateRoleAndManager(roleAndManager);
        return 1;
    }

    /**
     * 删除管理员
     *
     * @param managerIds 管理员id
     * @return -1 managerIds为空 1 删除成功
     */
    @Override
    public int deleteManager(long[] managerIds) {
        if (ArrayUtils.isEmpty(managerIds)) {
            Logger.error("deleteManager fail due to managerIds is empty....");
            return -1;
        }
        Logger.debug("deleteManager and managerIds:{}", managerIds);
        //删除管理员表
        managerMapper.deleteManager(managerIds);
        //删除管理员和角色的关联表
        managerMapper.deleteRoleAndManager(managerIds);
        return 1;
    }

    /**
     * 根据用户Id查询角色菜单
     *
     * @param managerId 管理员Id
     * @return 菜单
     */
    @Override
    public List<AdminEditMenu> roleAuthMenu(long managerId) {
        Logger.debug("roleAuthMenu and managerId:{}", managerId);
        List<AdminMenu> adminMenuList = adminMenuService.adminMenuCommon(managerId, false);
        List<AdminEditMenu> adminEditMenuList = new ArrayList<>();
        for (AdminMenu adminMenu : adminMenuList) {
            adminEditMenuList.add(AdminEditMenu.getAdminEditMenu(adminMenu.getAuthorityId(), adminMenu.getParentId(), adminMenu.getName()));
            getNum(adminMenu, adminEditMenuList);
        }
        return adminEditMenuList;
    }

    /**
     * 递归加载菜单
     *
     * @param adminMenu         所有菜单
     * @param adminEditMenuList 生成的树形菜单
     * @return 树形菜单
     */
    private List<AdminEditMenu> getNum(AdminMenu adminMenu, List<AdminEditMenu> adminEditMenuList) {
        if (!CollectionUtils.isEmpty(adminMenu.getChildMenu())) {
            for (AdminMenu adminMenus : adminMenu.getChildMenu()) {
                adminEditMenuList.add(AdminEditMenu.getAdminEditMenu(adminMenus.getAuthorityId(), adminMenus.getParentId(), adminMenus.getName()));
                getNum(adminMenus, adminEditMenuList);
            }
        }
        return adminEditMenuList;
    }
}
