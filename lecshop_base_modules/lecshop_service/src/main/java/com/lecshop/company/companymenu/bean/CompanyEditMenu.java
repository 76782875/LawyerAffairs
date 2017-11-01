package com.lecshop.company.companymenu.bean;

import lombok.Data;

/**
 * 公司中对菜单编辑对实体类
 *
 * @author sunluyang on 2017/6/7.
 */
@Data
public class CompanyEditMenu {
    /**
     * 菜单Id
     */
    private int id;
    /**
     * 父级Id
     */
    private int pId;
    /**
     *
     */
    private String name;
    /**
     * 前端需要对字段
     */
    private boolean open = false;

    public static CompanyEditMenu getCompanyEditMenu(int id, int pId, String name) {
        CompanyEditMenu companyEditMenu = new CompanyEditMenu();
        companyEditMenu.id = id;
        companyEditMenu.pId = pId;
        companyEditMenu.name = name;
        companyEditMenu.open = pId == 0;
        return companyEditMenu;
    }
}
