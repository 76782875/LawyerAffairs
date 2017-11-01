package com.lecshop.admin.role.service;


import com.lecshop.admin.role.bean.Role;
import com.lecshop.utils.PageHelper;

import java.util.List;

/**
 * 角色Service层接口
 */
public interface RoleService {
    /**
     * 查询所有角色
     *
     * @param roleName 空查询所有,不为空按条件查询
     * @param isPaging 是否分页
     * @return 角色集合
     */
    PageHelper queryAllRole(PageHelper<Role> pageHelper, String roleName, int isPaging);

    /**
     * 添加角色并关联角色权限
     *
     * @param roleName 角色实体类
     * @param authIds  权限Id
     * @return 返回添加结果-1用户名存在 -2没有权限id >=1添加成功
     */
    int addRole(String roleName, long[] authIds);

    /**
     * 根据角色ID查询该角色拥有对权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID集合
     */
    List<Long> queryAuthIdByRoleId(long roleId);

    /**
     * 编辑角色
     *
     * @param roleId   角色id
     * @param authIds  权限id
     * @param roleName 角色名称
     * @return 编辑结果 -1用户名存在 -2没有权限id >=1编辑成功
     */
    int editRole(long roleId, String roleName, long[] authIds);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return 删除返回值
     */
    int deleteRole(long[] roleId);
}
