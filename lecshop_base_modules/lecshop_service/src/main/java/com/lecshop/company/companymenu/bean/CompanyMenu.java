package com.lecshop.company.companymenu.bean;

import lombok.Data;

import java.util.List;

/**
 * 公司端菜单实体类
 *
 * @author sunluyang on 2017/6/7.
 */
@Data
public class CompanyMenu {

    /**
     * 角色id
     */
    private int roleId;
    /**
     * 权限id
     */
    private int authorityId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单URL
     */
    private String url;
    /**
     * 父级id 如果是第一级  则为0
     */
    private int parentId;
    /**
     * 级别 1 表示第一级 2表示第二级
     */
    private int grade;
    /**
     * 排序
     */
    private int sort;

    /**
     * 子菜单
     */
    private List<CompanyMenu> childMenu;
}
