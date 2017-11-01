package com.lecshop.admin.role.service.impl;

import com.lecshop.admin.role.bean.Role;
import com.lecshop.admin.role.bean.RoleAndAuth;
import com.lecshop.admin.role.mapper.RoleMapper;
import com.lecshop.admin.role.service.RoleService;
import com.lecshop.utils.PageHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 角色Service层
 */
@Service
public class RoleServiceImpl implements RoleService {

    /**
     * 调试日式
     */
    private Logger Logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    /**
     * 注入角色mapper
     */
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询所有角色
     *
     * @param roleName 空查询所有,不为空按条件查询
     * @return 角色集合
     */
    @Override
    public PageHelper<Role> queryAllRole(PageHelper<Role> pageHelper, String roleName, int isPaging) {
        Logger.debug("queryAllRole and pageHelper : {} \r\n name: {} \r\n isPaging{}", pageHelper, roleName, isPaging);
        Map<String, Object> params = new HashMap<>();
        params.put("roleName", roleName);
        params.put("isPaging", isPaging);
        return pageHelper.setListDates(roleMapper.queryAllRole(pageHelper.getQueryParams(params, roleMapper.queryRoleCount(params))));
    }

    /**
     * 添加角色并关联角色权限
     *
     * @param roleName 角色实体类
     * @param authIds  权限Id
     * @return 返回添加结果-1用户名存在 -2没有权限id >=1添加成功
     */
    @Override
    public int addRole(String roleName, long[] authIds) {
        Logger.debug("addRole and roleName : {} \r\n authIds: {}", roleName, authIds);
        if (ArrayUtils.isEmpty(authIds)) {
            Logger.error("addRole error due to authIds is empty");
            return -2;
        }
        if (!Objects.isNull(roleMapper.roleNameCheck(roleName))) {
            Logger.error("addRole error due to roleName is null");
            return -1;
        }
        Role role = new Role();
        role.setRoleName(roleName);
        roleMapper.addRole(role);
        List<RoleAndAuth> list = new ArrayList();
        Arrays.stream(authIds).forEach(authId -> {
            RoleAndAuth roleAndAuth = new RoleAndAuth();
            roleAndAuth.setRoleId(role.getId());
            roleAndAuth.setId(authId);
            list.add(roleAndAuth);
        });
        return roleMapper.addRoleAndAuth(list);
    }

    /**
     * 根据角色ID查询该角色拥有对权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID集合
     */
    @Override
    public List<Long> queryAuthIdByRoleId(long roleId) {
        Logger.debug("queryAuthIdByRoleId...");
        return roleMapper.queryAuthIdByRoleId(roleId);
    }

    /**
     * 编辑角色
     *
     * @param roleId   角色id
     * @param authIds  权限id
     * @param roleName 角色名称
     * @return 编辑结果 -1用户名存在 -2没有权限id >=1编辑成功
     */
    @Override
    public int editRole(long roleId, String roleName, long[] authIds) {
        Logger.debug("editRole and roleId : {} \r\n authIds: {}\r\n roleName:{}", roleId, authIds, roleName);
        if (ArrayUtils.isEmpty(authIds)) {
            return -2;
        }
        long[] array = {roleId};
        roleMapper.deleteAllAuthByRoleId(array);
        List<RoleAndAuth> list = new ArrayList<>();
        Arrays.stream(authIds).forEach(authId -> {
            RoleAndAuth roleAndAuth = new RoleAndAuth();
            roleAndAuth.setRoleId(roleId);
            roleAndAuth.setId(authId);
            list.add(roleAndAuth);
        });
        //如果角色名称为空则不进行角色表更新操作
        if (!StringUtils.isEmpty(roleName)) {
            Role roleCheck = roleMapper.roleNameCheck(roleName);
            if (!Objects.isNull(roleCheck) && roleCheck.getId() != roleId) {
                Logger.error("editRole error roleName is exit");
                return -1;
            }
            Role role = new Role();
            role.setId(roleId);
            role.setRoleName(roleName);
            roleMapper.editRoleName(role);
        }
        return roleMapper.addRoleAndAuth(list);
    }

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return 删除返回值
     */
    @Override
    public int deleteRole(long[] roleId) {
        Logger.debug("deleteRole and roleId:{}", roleId);
        if (ArrayUtils.isEmpty(roleId)) {
            Logger.error("deleteRole error roleId is empty");
            return -1;
        }
        //删除角色权限关联表中数据
        roleMapper.deleteAllAuthByRoleId(roleId);
        //删除角色
        return roleMapper.deleteRole(roleId);
    }
}
