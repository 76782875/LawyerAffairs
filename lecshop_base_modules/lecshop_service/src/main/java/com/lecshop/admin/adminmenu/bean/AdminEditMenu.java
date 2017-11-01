package com.lecshop.admin.adminmenu.bean;

import lombok.Data;

/**
 * admin中对菜单编辑对实体类
 */
@Data
public class AdminEditMenu {
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

    public static AdminEditMenu getAdminEditMenu(int id, int pId, String name) {
        AdminEditMenu adminEditMenu = new AdminEditMenu();
        adminEditMenu.id = id;
        adminEditMenu.pId = pId;
        adminEditMenu.name = name;
        if (pId == 0) {
            adminEditMenu.open = true;
        } else {
            adminEditMenu.open = false;
        }
        return adminEditMenu;
    }
}
