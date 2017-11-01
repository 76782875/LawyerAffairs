package com.lecshop.company.authmanage;

import com.lecshop.company.adminutil.CompanyLoginUtils;
import com.lecshop.company.companyauth.bean.CompanyRole;
import com.lecshop.company.companyauth.service.CompanyRoleService;
import com.lecshop.company.companymenu.bean.CompanyEditMenu;
import com.lecshop.total.user.bean.User;
import com.lecshop.total.user.service.impl.UserServiceImpl;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 公司权限管理控制器
 *
 * @author sunluyang on 2017/7/18.
 */
@Controller
public class AuthManageController {

    /**
     * 注入公司角色service
     */
    @Autowired
    private CompanyRoleService companyRoleService;
    /**
     * 注入用户service
     */
    @Autowired
    private UserServiceImpl userService;

    /**
     * 跳转到角色管理页面
     *
     * @return 角色管理页面
     */
    @RequestMapping("/c_torolemanage")
    public ModelAndView toRoleManage() {
        return new ModelAndView("authmanage/rolelist");
    }

    /**
     * 跳转到子账号页面
     *
     * @return 子账号页面
     */
    @RequestMapping("/c_tostaffmanage")
    public ModelAndView toStaffManage() {
        return new ModelAndView("authmanage/stafflist");
    }

    /**
     * 分页查询角色列表
     *
     * @param request    request请求
     * @param isPaging   是否分页 1需要 0不需要
     * @param name       角色名称、空查询所有,不为空按条件查询
     * @param pageHelper 分页帮助类
     * @return 角色集合
     */
    @RequestMapping("/c_queryrolelist")
    @ResponseBody
    public BaseResponse queryRoleList(int isPaging, String name, PageHelper<CompanyRole> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(companyRoleService.queryAllCompanyRole(pageHelper, name, isPaging, CompanyLoginUtils.getInstance().getCompanyIdFromSession(request)));
    }

    /**
     * 查询公司角色拥有的权限菜单
     *
     * @param request 页面请求
     * @return 角色菜单
     */
    @RequestMapping("/c_roleauthmenu")
    @ResponseBody
    public List<CompanyEditMenu> roleAuthMenu(HttpServletRequest request) {
        return companyRoleService.companyRoleAuthMenu(CompanyLoginUtils.getInstance().getUserIdFromSession(request));
    }

    /**
     * 添加角色
     *
     * @param name    角色名称
     * @param authIds 角色id
     * @param request 请求对象
     * @return 添加返回码
     */
    @RequestMapping("/c_addrole")
    @ResponseBody
    public int addCompanyRole(String name, long[] authIds, HttpServletRequest request) {
        return companyRoleService.addCompanyRole(name, authIds, CompanyLoginUtils.getInstance().getCompanyIdFromSession(request));
    }

    /**
     * 根据角色ID查询该角色拥有对权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID集合
     */
    @RequestMapping("/c_queryauthid")
    @ResponseBody
    public List<Long> queryAuthIdByRoleId(long roleId) {
        return companyRoleService.queryAuthIdByRoleId(roleId);
    }

    /**
     * 编辑角色
     *
     * @param roleId  角色id
     * @param authIds 权限id
     * @param name    角色名称
     * @return 编辑结果 0 用户名存在 -1角色名称为空 -2 没有权限id >=1编辑成功
     */
    @RequestMapping("/c_editrole")
    @ResponseBody
    public int editCompanyRole(long roleId, String name, long[] authIds, HttpServletRequest request) {
        return companyRoleService.editCompanyRole(roleId, name, authIds, CompanyLoginUtils.getInstance().getCompanyIdFromSession(request));
    }

    /**
     * 删除角色
     *
     * @param roleIds 角色id
     * @return 删除返回值
     */
    @RequestMapping("/c_deleterole")
    @ResponseBody
    public int deleteRole(long[] roleIds, HttpServletRequest request) {
        return companyRoleService.deleteCompanyRole(roleIds, CompanyLoginUtils.getInstance().getCompanyIdFromSession(request));
    }

    /**
     * 查询所有员工
     *
     * @param pageHelper 分页帮助类
     * @param name       员工
     * @return 返回管理员信息
     */
    @RequestMapping("/c_querystaff")
    @ResponseBody
    public BaseResponse queryCompanyStaff(PageHelper<User> pageHelper, String name, HttpServletRequest request) {
        return BaseResponse.build(userService.queryCompanyStaff(pageHelper, name, CompanyLoginUtils.getInstance().getUserIdFromSession(request), CompanyLoginUtils.getInstance().getCompanyIdFromSession(request)));
    }

    /**
     * 查询公司角色
     *
     * @return 公司角色集合
     */
    @RequestMapping("/c_querycompanyrole")
    @ResponseBody
    public List<CompanyRole> queryCompanyRole(HttpServletRequest request) {
        return companyRoleService.queryCompanyRoleByCompanyId(CompanyLoginUtils.getInstance().getCompanyIdFromSession(request));
    }

    /**
     * 添加公司员工
     *
     * @param user 角色和会员关联的实体类
     * @return 添加返回码
     */
    @RequestMapping("/c_addcompanystaff")
    @ResponseBody
    public int addCompanyStaff(@RequestBody User user, long roleId, HttpServletRequest request) {
        return companyRoleService.addCompanyStaff(user.getCompanyIdAndParentId(CompanyLoginUtils.getInstance().getCompanyIdFromSession(request), CompanyLoginUtils.getInstance().getUserIdFromSession(request)), roleId);
    }

    /**
     * 删除公司员工
     *
     * @param userId 员工id数组
     * @return 删除返回码
     */
    @RequestMapping("/c_deletecompanystaff")
    @ResponseBody
    public int deleteCompanyStaff(long[] userId) {
        return companyRoleService.deleteCompanyStaff(userId);
    }

    /**
     * 编辑公司员工
     *
     * @param status 员工实体类
     * @return 编辑返回码
     */
    @RequestMapping("/c_editcompanystaff")
    @ResponseBody
    public int editCompanyStaff(String status, long roleId, long userId) {
        return companyRoleService.editCompanyStaff(status, roleId, userId);
    }
}
