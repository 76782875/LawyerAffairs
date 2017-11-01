package com.lecshop.company.companyauth.mapper;


import com.lecshop.company.companyauth.bean.CompanyRole;
import com.lecshop.company.companyauth.bean.CompanyRoleAndAuth;
import com.lecshop.company.companyauth.bean.UserAndRole;

import java.util.List;
import java.util.Map;

/**
 * 角色mapper层
 *
 * @author sunluyang on 2017/6/7.
 */
public interface CompanyRoleMapper {

    /**
     * 查询所有角色条数
     *
     * @param params 查询条件
     * @return 条数
     */
    int queryCompanyRoleCount(Map<String, Object> params);

    /**
     * 查询所有角色
     *
     * @param params 空查询所有,不为空按条件查询
     * @return 角色集合
     */
    List<CompanyRole> queryAllCompanyRole(Map<String, Object> params);

    /**
     * 用于校验角色名称是否重复
     *
     * @param name 角色名称
     * @return 返回查询结果
     */
    CompanyRole roleNameCheck(String name);

    /**
     * 添加角色
     *
     * @param companyRole 角色实体类
     * @return 返回添加主键ID
     */
    int addCompanyRole(CompanyRole companyRole);

    /**
     * 添加角色权限关联
     *
     * @param list 角色ID
     * @return 返回添加结果
     */
    int addCompanyRoleAndAuth(List<CompanyRoleAndAuth> list);

    /**
     * 根据角色ID查询该角色拥有对权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID集合
     */
    List<Long> queryAuthIdByRoleId(long roleId);

    /**
     * 编辑角色名称
     *
     * @param storeRole 角色实体类
     * @return 编辑返回值
     */
    int editRoleName(CompanyRole storeRole);

    /**
     * 根据角色id删除该角色下的所有权限
     *
     * @param idList 角色id
     * @return 返回删除结果
     */
    int deleteAllAuthByRoleId(long[] idList);

    /**
     * 删除角色
     *
     * @param map 角色id,店铺id
     * @return 删除返回值
     */
    int deleteCompanyRole(Map<String, Object> map);

    /**
     * 根据公司id查询角色
     *
     * @param companyId 店铺id
     * @return 公司角色集合
     */
    List<CompanyRole> queryCompanyRoleByCompanyId(long companyId);

    /**
     * 用户和角色的关联表
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 添加返回码
     */
    int addUserRole(long userId, long roleId);

    /**
     * 批量删除员工的角色关联数据
     *
     * @param userIds 员工id
     * @return 删除返回码
     */
    int deleteUserRole(long[] userIds);

    /**
     * 编辑员工-编辑员工关联的角色id
     *
     * @param roleId 实体类
     * @return 编辑返回码
     */
    int editUserRole(long roleId, long userId);
}
