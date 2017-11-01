package com.lecshop.company.companyauth.service;

import com.lecshop.company.companyauth.bean.CompanyRole;
import com.lecshop.company.companymenu.bean.CompanyEditMenu;
import com.lecshop.total.user.bean.User;
import com.lecshop.utils.PageHelper;

import java.util.List;


/**
 * 角色Service层接口
 *
 * @author sunluyang on 2017/6/7.
 */
public interface CompanyRoleService {
    /**
     * 查询所有角色
     *
     * @param name     空查询所有,不为空按条件查询
     * @param isPaging 是否分页 1需要 0不需要
     * @return 角色集合
     */
    PageHelper<CompanyRole> queryAllCompanyRole(PageHelper<CompanyRole> pageHelper, String name, int isPaging, long userId);

    /**
     * 根据用户Id查询角色菜单
     *
     * @param customerId 会员Id
     * @return 菜单
     */
    List<CompanyEditMenu> companyRoleAuthMenu(long customerId);

    /**
     * 添加角色并关联角色权限
     *
     * @param name      角色名称
     * @param authIds   权限Id
     * @param companyId 店铺id
     * @return 返回添加结果-1用户名存在 -2没有权限id >=1添加成功
     */
    int addCompanyRole(String name, long[] authIds, long companyId);

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
     * @param roleId    角色id
     * @param authIds   权限id
     * @param name      角色名称
     * @param companyId 公司id
     * @return 编辑结果 -1用户名存在 -2没有权限id >=1编辑成功
     */
    int editCompanyRole(long roleId, String name, long[] authIds, long companyId);

    /**
     * 删除角色
     *
     * @param roleId    角色id
     * @param companyId 公司id
     * @return 删除返回值
     */
    int deleteCompanyRole(long[] roleId, long companyId);

    /**
     * 根据公司id查询公司角色
     *
     * @param companyId 公司id
     * @return 公司角色集合
     */
    List<CompanyRole> queryCompanyRoleByCompanyId(long companyId);

    /**
     * 添加员工进行添加角色和会员关联表
     *
     * @param user 实体类
     * @return 添加返回码
     */
    int addCompanyStaff(User user, long roleId);

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
    int deleteCompanyStaff(long[] userIds);

    /**
     * 编辑员工-编辑员工关联的角色id
     *
     * @return 编辑返回码
     */
    int editCompanyStaff(String status, long roleId, long userId);
}
