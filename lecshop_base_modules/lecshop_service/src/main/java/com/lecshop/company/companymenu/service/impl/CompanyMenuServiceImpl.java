package com.lecshop.company.companymenu.service.impl;

import com.lecshop.company.companymenu.bean.CompanyMenu;
import com.lecshop.company.companymenu.mapper.CompanyMenuMapper;
import com.lecshop.company.companymenu.service.CompanyMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 公司端菜单service实现类
 *
 * @author sunluyang on 2017/6/7.
 */
@Service
public class CompanyMenuServiceImpl implements CompanyMenuService {
    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(CompanyMenuServiceImpl.class);

    /**
     * 菜单mapper
     */
    @Autowired
    private CompanyMenuMapper companyMenuMapper;

    /**
     * 根据管理员id查询菜单
     *
     * @param userId 管理员id
     * @return 管理员菜单实体类 递归返回一级
     */
    @Override
    public List<CompanyMenu> queryCompanyMenu(Long userId) {
        logger.debug("queryAdminMenu and userId:{}", userId);
        return companyMenuCommon(userId, true);
    }

    /**
     * 查询该用户所有菜单
     *
     * @param userId 管理员id
     * @return 菜单实体类
     */
    @Override
    public List<CompanyMenu> queryAllCompanyMenu(Long userId) {
        logger.debug("queryAllCompanyMenu and userId:{}", userId);
        return companyMenuMapper.queryCompanyMenu(userId);
    }

    /**
     * 根据管理员id查询菜单-公共方法
     *
     * @param userId 管理员id
     * @param isMenu 是否是用于后台菜单
     * @return 管理员菜单实体类 递归返回一级
     */
    @Override
    public List<CompanyMenu> companyMenuCommon(Long userId, boolean isMenu) {
        logger.debug("companyMenuCommon and userId:{}\r\n isMenu:{}", userId, isMenu);
        //查询所有该用户菜单
        List<CompanyMenu> adminMenu = queryAllCompanyMenu(userId);
        //返回该用户一级菜单
        List<CompanyMenu> parentList = isMenu ? adminMenu.stream().filter(parentMenu -> parentMenu.getParentId() == 0).collect(Collectors.toList()) :
                adminMenu.stream().filter(parentMenu -> parentMenu.getParentId() == 0 && parentMenu.getAuthorityId() != 2).collect(Collectors.toList());
        //返回该用户二级菜单
        for (CompanyMenu parentMenu : parentList) {
            List<CompanyMenu> list = new ArrayList<>();
            for (CompanyMenu allMenu : adminMenu) {
                if (parentMenu.getAuthorityId() == allMenu.getParentId() && allMenu.getGrade() == 2) {
                    list.add(allMenu);
                    parentMenu.setChildMenu(list);
                }
            }
        }
        //返回该用户三级菜单
        for (CompanyMenu parentMenu : parentList) {
            if (CollectionUtils.isEmpty(parentMenu.getChildMenu())) {
                continue;
            }
            for (CompanyMenu childMenu : parentMenu.getChildMenu()) {
                List<CompanyMenu> list = new ArrayList<>();
                for (CompanyMenu allMenu : adminMenu) {
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
            for (CompanyMenu parentMenu : parentList) {
                if (CollectionUtils.isEmpty(parentMenu.getChildMenu())) {
                    continue;
                }
                for (CompanyMenu childMenu : parentMenu.getChildMenu()) {
                    if (CollectionUtils.isEmpty(childMenu.getChildMenu())) {
                        continue;
                    }
                    for (CompanyMenu thirdMenu : childMenu.getChildMenu()) {
                        List<CompanyMenu> list = new ArrayList<>();
                        for (CompanyMenu allMenu : adminMenu) {
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
