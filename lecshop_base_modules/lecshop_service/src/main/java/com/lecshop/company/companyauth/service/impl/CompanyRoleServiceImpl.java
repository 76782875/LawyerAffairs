package com.lecshop.company.companyauth.service.impl;

import com.lecshop.admin.sms.service.SmsService;
import com.lecshop.company.companyauth.bean.CompanyRole;
import com.lecshop.company.companyauth.bean.CompanyRoleAndAuth;
import com.lecshop.company.companyauth.mapper.CompanyRoleMapper;
import com.lecshop.company.companyauth.service.CompanyRoleService;
import com.lecshop.company.companymenu.bean.CompanyEditMenu;
import com.lecshop.company.companymenu.bean.CompanyMenu;
import com.lecshop.company.companymenu.service.CompanyMenuService;
import com.lecshop.total.user.bean.User;
import com.lecshop.total.user.service.UserService;
import com.lecshop.utils.PageHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 角色Service层
 *
 * @author sunluyang on 2017/6/7.
 */
@Service
public class CompanyRoleServiceImpl implements CompanyRoleService {

    /**
     * 注入公司角色mapper
     */
    @Autowired
    private CompanyRoleMapper companyRoleMapper;
    /**
     * 注入公司菜单service
     */
    @Autowired
    private CompanyMenuService companyMenuService;
    /**
     * 注入用户service
     */
    @Autowired
    private UserService userService;

    /**
     * 注入短信设置service接口
     */
    @Autowired
    private SmsService smsService;

    /**
     * 调试日式
     */
    private Logger Logger = LoggerFactory.getLogger(CompanyRoleServiceImpl.class);

    /**
     * 分页查询所有店铺角色
     *
     * @param pageHelper 分页帮助类
     * @param name       角色名称、空查询所有,不为空按条件查询
     * @param isPaging   是否分页 1需要 0不需要
     * @return 店铺角色分类
     */
    @Override
    public PageHelper<CompanyRole> queryAllCompanyRole(PageHelper<CompanyRole> pageHelper, String name, int isPaging, long companyId) {
        Logger.debug("queryAllCompanyRole and pageHelper : {} \r\n name: {} \r\n isPaging{}", pageHelper, name, isPaging);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("isPaging", isPaging);
        params.put("companyId", companyId);
        return pageHelper.setListDates(companyRoleMapper.queryAllCompanyRole(pageHelper.getQueryParams(params, companyRoleMapper.queryCompanyRoleCount(params))));
    }

    /**
     * 根据公司员工id查询角色菜单
     *
     * @param userId 公司员工id
     * @return 菜单
     */
    @Override
    public List<CompanyEditMenu> companyRoleAuthMenu(long userId) {
        Logger.debug("companyRoleAuthMenu and userId : {}", userId);
        List<CompanyMenu> companyMenuList = companyMenuService.companyMenuCommon(userId, false);
        List<CompanyEditMenu> companyEditMenuList = new ArrayList<>();
        for (CompanyMenu companyMenu : companyMenuList) {
            companyEditMenuList.add(CompanyEditMenu.getCompanyEditMenu(companyMenu.getAuthorityId(), companyMenu.getParentId(), companyMenu.getName()));
            getNum(companyMenu, companyEditMenuList);
        }
        return companyEditMenuList;
    }

    /**
     * 添加角色并关联角色权限
     *
     * @param name    角色名称
     * @param authIds 权限Id
     * @return 返回添加结果-1用户名存在 -2没有权限id >=1添加成功
     */
    @Override
    @Transactional
    public int addCompanyRole(String name, long[] authIds, long companyId) {
        Logger.debug("addCompanyRole and name : {} \r\n authIds: {}\r\n companyId:{}", name, authIds, companyId);
        if (ArrayUtils.isEmpty(authIds)) {
            Logger.error("addCompanyRole error due to authIds is empty");
            return -2;
        }
        if (!Objects.isNull(companyRoleMapper.roleNameCheck(name))) {
            Logger.error("addCompanyRole error due to name is null");
            return -1;
        }
        CompanyRole companyRole = new CompanyRole();
        companyRoleMapper.addCompanyRole(companyRole.getAddCompanyRole(name, companyId));
        List<CompanyRoleAndAuth> list = new ArrayList<>();
        Arrays.stream(authIds).forEach(authId -> {
            list.add(new CompanyRoleAndAuth().getAddCompanyRoleAndAuth(authId, companyRole.getId()));
        });
        return companyRoleMapper.addCompanyRoleAndAuth(list);
    }

    /**
     * 根据角色ID查询该角色拥有对权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID集合
     */
    @Override
    public List<Long> queryAuthIdByRoleId(long roleId) {
        Logger.debug("queryAuthIdByRoleId and roleId:{}", roleId);
        return companyRoleMapper.queryAuthIdByRoleId(roleId);
    }

    /**
     * 编辑角色
     *
     * @param roleId  角色id
     * @param authIds 权限id
     * @param name    角色名称
     * @return 编辑结果 0 用户名存在 -1角色名称为空 -2 没有权限id >=1编辑成功
     */
    @Override
    @Transactional
    public int editCompanyRole(long roleId, String name, long[] authIds, long companyId) {
        Logger.debug("editCompanyRole and roleId : {} \r\n authIds: {}\r\n roleName:{}\r\n storeId:{}", roleId, authIds, name, companyId);
        if (ArrayUtils.isEmpty(authIds)) {
            Logger.error("editRole error due to authIds is empty");
            return -2;
        }
        if (StringUtils.isEmpty(name)) {
            return -1;
        }
        //如果角色名称为空则不进行角色表更新操作
        CompanyRole roleCheck = companyRoleMapper.roleNameCheck(name);
        if (!Objects.isNull(roleCheck) && roleCheck.getId() != roleId) {
            Logger.error("editRole error name is exit");
            return 0;
        }
        companyRoleMapper.editRoleName(new CompanyRole().getCompanyRole(roleId, name, companyId));
        long[] array = {roleId};
        companyRoleMapper.deleteAllAuthByRoleId(array);
        List<CompanyRoleAndAuth> list = new ArrayList<>();
        Arrays.stream(authIds).forEach(authId -> list.add(new CompanyRoleAndAuth().getAddCompanyRoleAndAuth(authId, roleId)));
        return companyRoleMapper.addCompanyRoleAndAuth(list);
    }

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return 删除返回值
     */
    @Override
    public int deleteCompanyRole(long[] roleId, long companyId) {
        Logger.debug("deleteCompanyRole and roleId:{}\r\n companyId", roleId, companyId);
        if (ArrayUtils.isEmpty(roleId)) {
            Logger.error("deleteRole error roleId is empty");
            return -1;
        }
        //删除角色权限关联表中数据
        companyRoleMapper.deleteAllAuthByRoleId(roleId);
        //删除角色
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", companyId);
        map.put("idArrays", roleId);
        return companyRoleMapper.deleteCompanyRole(map);
    }

    /**
     * 查询公司所有角色
     *
     * @param companyId 店铺id
     * @return 公司所有角色
     */
    @Override
    public List<CompanyRole> queryCompanyRoleByCompanyId(long companyId) {
        Logger.debug("queryCompanyRoleByCompanyId and companyId:{}", companyId);
        return companyRoleMapper.queryCompanyRoleByCompanyId(companyId);
    }

    /**
     * 添加公司员工
     *
     * @param user 实体类
     * @return -1 手机号码已存在 -2 用户名已存在
     */
    @Override
    public int addCompanyStaff(User user, long roleId) {
        Logger.debug("addCompanyStaff and user:{}\r\n roleId", user, roleId);
        if (!Objects.isNull(userService.queryUserByMobile(user.getMobile()))) {
            return -1;
        }
        if (!Objects.isNull(userService.queryUserByName(user.getName()))) {
            return -2;
        }
        String password = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
        smsService.sendMsg(user.getMobile(), password);
        userService.addCompanyStaff(user.getUserPassword(password));
        return addUserRole(user.getId(), roleId);
    }

    /**
     * 添加用户权限
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 添加返回码
     */
    @Override
    public int addUserRole(long userId, long roleId) {
        Logger.debug("addUserRole and userId:{}\r\n roleId:{}", userId, roleId);
        return companyRoleMapper.addUserRole(userId, roleId);
    }

    /**
     * 批量删除员工的角色关联数据
     *
     * @param userIds 员工id
     * @return 删除返回码
     */
    @Override
    public int deleteCompanyStaff(long[] userIds) {
        Logger.debug("deleteCompanyStaff and userIds:{}", userIds);
        companyRoleMapper.deleteUserRole(userIds);
        return userService.batchDeleteCompanyMember(userIds);
    }

    /**
     * 编辑员工-编辑员工关联的角色id
     *
     * @param status 实体类
     * @return 编辑返回码
     */
    @Override
    public int editCompanyStaff(String status, long roleId, long userId) {
        Logger.debug("editCompanyStaff and userId:{}\r\n roleId:{}\r\n status:{}", userId, roleId, status);
        userService.editStatus(userId, status);
        return companyRoleMapper.editUserRole(roleId, userId);
    }


    /**
     * 递归加载菜单
     *
     * @param companyMenu         所有菜单
     * @param companyEditMenuList 生成的树形菜单
     * @return 树形菜单
     */
    private List<CompanyEditMenu> getNum(CompanyMenu companyMenu, List<CompanyEditMenu> companyEditMenuList) {
        if (!CollectionUtils.isEmpty(companyMenu.getChildMenu())) {
            for (CompanyMenu companyMenus : companyMenu.getChildMenu()) {
                companyEditMenuList.add(CompanyEditMenu.getCompanyEditMenu(companyMenus.getAuthorityId(), companyMenus.getParentId(), companyMenus.getName()));
                getNum(companyMenus, companyEditMenuList);
            }
        }
        return companyEditMenuList;
    }
}
