package com.lecshop.admin.adminmenu.service.impl;

import com.lecshop.admin.adminmenu.bean.AdminMenu;
import com.lecshop.admin.adminmenu.mapper.AdminMenuMapper;
import com.lecshop.admin.adminmenu.service.AdminMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * admin端菜单service实现类层
 *
 * @author sunluyang on 2017/7/10.
 */
@Service
public class AdminMenuServiceImpl implements AdminMenuService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(AdminMenuServiceImpl.class);

    /**
     * 菜单mapper
     */
    @Autowired
    private AdminMenuMapper adminMenuMapper;

    /**
     * 根据管理员id查询菜单
     *
     * @param managerId 管理员id
     * @return 管理员菜单实体类 递归返回一级
     */
    @Override
    public List<AdminMenu> queryAdminMenu(Long managerId) {
        logger.info("queryAdminMenu and managerId:{}", managerId);
        return adminMenuCommon(managerId, true);
    }

    /**
     * 查询该用户所有菜单
     *
     * @param managerId 管理员id
     * @return 菜单实体类
     */
    @Override
    public List<AdminMenu> queryAllAdminMenu(Long managerId) {
        logger.info("queryAllAdminMenu and managerId:{}", managerId);
        return adminMenuMapper.queryAdminMenu(managerId);
    }

    /**
     * 根据管理员id查询菜单-公共方法
     *
     * @param managerId 管理员id
     * @param isMenu    是否是用于后台菜单
     * @return 管理员菜单实体类 递归返回一级
     */
    @Override
    public List<AdminMenu> adminMenuCommon(Long managerId, boolean isMenu) {
        logger.info("queryAdminMenu and managerId:{}\r\n isMenu:{}", managerId, isMenu);
        //查询所有该用户菜单
        List<AdminMenu> adminMenu = queryAllAdminMenu(managerId);
        //返回该用户一级菜单
        List<AdminMenu> parentList = isMenu ? adminMenu.stream().filter(parentMenu -> parentMenu.getParentId() == 0).collect(Collectors.toList()) :
                adminMenu.stream().filter(parentMenu -> parentMenu.getParentId() == 0 && parentMenu.getAuthorityId() != 7).collect(Collectors.toList());
        //返回该用户二级菜单
        for (AdminMenu parentMenu : parentList) {
            List<AdminMenu> list = new ArrayList<>();
            for (AdminMenu allMenu : adminMenu) {
                if (parentMenu.getAuthorityId() == allMenu.getParentId() && allMenu.getGrade() == 2) {
                    list.add(allMenu);
                    parentMenu.setChildMenu(list);
                }
            }
        }
        //返回该用户三级菜单
        for (AdminMenu parentMenu : parentList) {
            if (CollectionUtils.isEmpty(parentMenu.getChildMenu())) {
                continue;
            }
            for (AdminMenu childMenu : parentMenu.getChildMenu()) {
                List<AdminMenu> list = new ArrayList<>();
                for (AdminMenu allMenu : adminMenu) {
                    if (childMenu.getAuthorityId() == allMenu.getParentId() && allMenu.getGrade() == 3) {
                        list.add(allMenu);
                        childMenu.setChildMenu(list);
                    }
                }
            }
        }
        //如果是用于菜单则直接返回，否则将四级菜单遍历出来
        if (isMenu) {
            return parentList;
        } else {
            //返回该用户四级菜单
            for (AdminMenu parentMenu : parentList) {
                if (CollectionUtils.isEmpty(parentMenu.getChildMenu())) {
                    continue;
                }
                for (AdminMenu childMenu : parentMenu.getChildMenu()) {
                    if (CollectionUtils.isEmpty(childMenu.getChildMenu())) {
                        continue;
                    }
                    for (AdminMenu thirdMenu : childMenu.getChildMenu()) {
                        List<AdminMenu> list = new ArrayList<>();
                        for (AdminMenu allMenu : adminMenu) {
                            if (thirdMenu.getAuthorityId() == allMenu.getParentId() && allMenu.getGrade() == 4) {
                                list.add(allMenu);
                                thirdMenu.setChildMenu(list);
                            }
                        }
                    }
                }
            }
            return parentList;
        }
    }
}
