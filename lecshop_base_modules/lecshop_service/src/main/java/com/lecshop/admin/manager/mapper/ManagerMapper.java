package com.lecshop.admin.manager.mapper;


import com.lecshop.admin.manager.bean.Manager;
import com.lecshop.admin.role.bean.RoleAndManager;

import java.util.List;
import java.util.Map;

/**
 * 管理员的数据库接口
 *
 * @author sunluyang on 2017/7/10.
 */
public interface ManagerMapper {

    /**
     * 根据用户名查询用户信息
     *
     * @param name 用户名
     * @return 返回用户信息
     */
    Manager queryManagerByName(String name);

    /**
     * 修改管理员密码
     *
     * @param manager 管理员实体类
     * @return 编辑返回码
     */
    int updateManagerPassWord(Manager manager);

    /**
     * 查询管理员
     *
     * @param params 查询参数
     * @return 返回管理员列表
     */
    List<Manager> queryManagers(Map<String, Object> params);

    /**
     * 查询管理员总记录数
     *
     * @param params 参数
     * @return 返回管理员总记录数
     */
    int queryManagersCount(Map<String, Object> params);


    /**
     * 添加管理员
     *
     * @param manager 管理员对象
     * @return 添加返回码
     */
    int addManager(Manager manager);

    /**
     * 添加管理员权限表
     *
     * @param map 管理员id,角色id集合
     * @return 添加返回码
     */
    int addManagerRole(Map<String, Object> map);

    /**
     * 根据管理员id查询角色和管理员关联对象
     *
     * @param managerId 管理员id
     * @return 角色和管理员关联对象
     */
    RoleAndManager queryRoleAndManagerByManagerId(long managerId);

    /**
     * 编辑管理员表
     *
     * @param roleAndManager 角色和管理员关联对象
     * @return 编辑返回码
     */
    int updateManager(RoleAndManager roleAndManager);

    /**
     * 编辑管理员和角色关联表
     *
     * @param roleAndManager 角色和管理员关联对象
     * @return 编辑返回码
     */
    int updateRoleAndManager(RoleAndManager roleAndManager);

    /**
     * 删除管理员-删除管理员表中数据
     *
     * @param idList 管理员id
     * @return 返回码
     */
    int deleteManager(long[] idList);

    /**
     * 删除管理员-删除角色和管理员关联表中数据
     *
     * @param idList 管理员id
     * @return 返回码
     */
    int deleteRoleAndManager(long[] idList);
}
