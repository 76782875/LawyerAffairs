package com.lecshop.admin.role.mapper;

import com.lecshop.admin.role.bean.Role;
import com.lecshop.admin.role.bean.RoleAndAuth;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色mapper层
 */
@Repository
public interface RoleMapper {

    /**
     * 查询所有角色
     *
     * @param params 空查询所有,不为空按条件查询
     * @return 角色集合
     */
    List<Role> queryAllRole(Map<String, Object> params);

    /**
     * 查询所有角色条数
     *
     * @param params 查询条件
     * @return 条数
     */
    int queryRoleCount(Map<String, Object> params);

    /**
     * 添加角色
     *
     * @param role 角色实体类
     * @return 返回添加主键ID
     */
    int addRole(Role role);

    /**
     * 添加角色权限关联
     *
     * @param list 角色ID
     * @return 返回添加结果
     */
    int addRoleAndAuth(List<RoleAndAuth> list);

    /**
     * 根据角色ID查询该角色拥有对权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID集合
     */
    List<Long> queryAuthIdByRoleId(long roleId);

    /**
     * 根据角色id删除该角色下的所有权限
     *
     * @param idList 角色id
     * @return 返回删除结果
     */
    int deleteAllAuthByRoleId(long[] idList);

    /**
     * 编辑角色名称
     *
     * @param role 角色实体类
     * @return 编辑返回值
     */
    int editRoleName(Role role);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return 删除返回值
     */
    int deleteRole(long[] roleId);

    /**
     * 用于校验角色名称是否重复
     *
     * @param roleName 角色名称
     * @return 返回查询结果
     */
    Role roleNameCheck(String roleName);
}
