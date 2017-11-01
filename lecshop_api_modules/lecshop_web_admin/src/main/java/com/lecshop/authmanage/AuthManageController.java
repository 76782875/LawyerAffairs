package com.lecshop.authmanage;

import com.lecshop.admin.adminmenu.bean.AdminEditMenu;
import com.lecshop.admin.manager.bean.Manager;
import com.lecshop.admin.manager.service.ManagerService;
import com.lecshop.admin.role.bean.Role;
import com.lecshop.admin.role.bean.RoleAndManager;
import com.lecshop.admin.role.service.RoleService;
import com.lecshop.adminutil.AdminLoginUtils;
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
import java.util.Objects;

/**
 * 权限管理控制器
 *
 * @author sunluyang on 2017/7/10.
 */
@Controller
public class AuthManageController {
    /**
     * 注入管理员实现类
     */
    @Autowired
    private ManagerService managerService;
    /**
     * 注入角色实现类
     */
    @Autowired
    private RoleService roleService;

    /**
     * 跳转角色管理页面
     *
     * @return 管理员列表页面
     */
    @RequestMapping("/torolemanage")
    public ModelAndView toRoleManage() {
        return new ModelAndView("authmanage/rolemanage");
    }

    /**
     * 查询所有角色
     *
     * @param pageHelper 分页帮助类
     * @param roleName   角色名称
     * @param isPaging   是否需要分页 1需要 0不需要
     * @return 返回角色信息
     */
    @RequestMapping("/rolelist")
    @ResponseBody
    public BaseResponse queryAllRole(PageHelper<Role> pageHelper, String roleName, int isPaging) {
        return BaseResponse.build(roleService.queryAllRole(pageHelper, roleName, isPaging));
    }

    /**
     * admin角色权限菜单显示
     *
     * @param request 页面请求
     * @return 角色菜单
     */
    @RequestMapping("/roleauthmenu")
    @ResponseBody
    public List<AdminEditMenu> roleAuthMenu(HttpServletRequest request) {
        return managerService.roleAuthMenu(AdminLoginUtils.getInstance().getManagerFromSession(request).getId());
    }

    /**
     * 添加角色并关联角色权限
     *
     * @param roleName 角色实体类
     * @param authIds  权限Id
     * @return 返回添加结果-1用户名存在 -2没有权限id >=1添加成功
     */
    @RequestMapping("/addroleandauth")
    @ResponseBody
    public int addRoleAndAuth(String roleName, long[] authIds) {
        return roleService.addRole(roleName, authIds);
    }

    /**
     * 根据角色ID查询该角色拥有对权限ID
     *
     * @param roleId 角色ID
     * @return 权限ID集合
     */
    @RequestMapping("/queryauthidbyroleid")
    @ResponseBody
    public List<Long> queryAuthIdByRoleId(long roleId) {
        return roleService.queryAuthIdByRoleId(roleId);
    }

    /**
     * 编辑角色并编辑角色权限
     *
     * @param roleId   角色id
     * @param authIds  权限id
     * @param roleName 角色名称
     * @return 编辑结果 -1用户名存在 -2没有权限id >=1编辑成功
     */
    @RequestMapping("/editroleandauth")
    @ResponseBody
    public int editRoleAndAuth(long roleId, String roleName, long[] authIds) {
        return roleService.editRole(roleId, roleName, authIds);
    }

    /**
     * 删除角色并删除角色权限
     *
     * @param roleIds 角色id
     * @return 删除返回值
     */
    @RequestMapping("/deleteroleandauth")
    @ResponseBody
    public int deleteRoleAndAuth(long[] roleIds) {
        return roleService.deleteRole(roleIds);
    }

    /**
     * 跳转管理员列表页面
     *
     * @return 管理员列表页面
     */
    @RequestMapping("/tomanagerlist")
    public ModelAndView toManagerList() {
        return new ModelAndView("authmanage/managerlist");
    }

    /**
     * 查询所有管理员
     *
     * @param pageHelper 分页帮助类
     * @param name       管理员名称
     * @return 返回管理员信息
     */
    @RequestMapping("/managerlist")
    @ResponseBody
    public BaseResponse queryAllManager(PageHelper<Manager> pageHelper, String name, HttpServletRequest request) {
        return BaseResponse.build(managerService.queryManagers(pageHelper, name, AdminLoginUtils.getInstance().getManagerFromSession(request).getId()));
    }

    /**
     * 添加管理员
     *
     * @param manager 管理员对象
     * @param roleId  角色id
     * @return 添加返回值 0添加失败 1添加成功
     */
    @RequestMapping("/addmanager")
    @ResponseBody
    public int addManager(@RequestBody Manager manager, int roleId) {
        return managerService.addManager(manager, roleId);
    }

    /**
     * 根据管理员id查询角色id
     *
     * @param managerId 管理员id
     * @return 角色id
     */
    @RequestMapping("/queryroleid")
    @ResponseBody
    public RoleAndManager queryRoleIdByManagerId(long managerId) {
        return managerService.queryRoleAndManagerByManagerId(managerId);
    }

    /**
     * 编辑管理员
     *
     * @param roleAndManager 角色管理员实体类
     * @return -1用户名存在,1编辑成功
     */
    @RequestMapping("/editmanager")
    @ResponseBody
    public int editManager(@RequestBody RoleAndManager roleAndManager) {
        return managerService.editManager(roleAndManager);
    }

    /**
     * 删除管理员
     *
     * @param managerIds 管理员id
     * @return -1 managerIds为空 1 删除成功
     */
    @RequestMapping("/deletemanager")
    @ResponseBody
    public int deleteManager(long[] managerIds) {
        return managerService.deleteManager(managerIds);
    }
}
