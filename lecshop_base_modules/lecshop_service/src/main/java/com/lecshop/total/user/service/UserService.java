package com.lecshop.total.user.service;

import com.lecshop.utils.PageHelper;
import com.lecshop.total.user.bean.User;

import java.util.List;
import java.util.Map;

/**
 * 公司员工service
 *
 * @author sunluyang on 2017/7/14.
 */
public interface UserService {

    /**
     * 根据手机号码查询用户
     *
     * @param mobile 手机号码
     * @return 用户信息
     */
    User queryUserByMobile(String mobile);

    /**
     * 根据登录名称查询用户
     *
     * @param name 登录名称
     * @return 用户信息
     */
    User queryUserByName(String name);

    /**
     * 根据用户id和公司id查询该员工信息以及公司信息
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return user 集合
     */
    User queryUserAndCompany(long companyId, long userId);

    /**
     * 分页查询公司会员信息
     *
     * @param pageHelper 分页帮助类
     * @param name       公司名称
     * @param nickName   会员昵称
     * @param mobile     会员手机
     * @return 公司会员信息
     */
    PageHelper<User> queryCompanyMemberInfos(PageHelper<User> pageHelper, String name, String nickName, String mobile);

    /**
     * 删除公司会员
     *
     * @param id 公司会员id
     * @return 成功返回1，失败返回0
     */
    int deleteCompanyMember(long id);

    /**
     * 批量删除公司会员
     *
     * @param ids 公司会员id数组
     * @return 成功返回>=1，失败返回0
     */
    int batchDeleteCompanyMember(long[] ids);

    /**
     * 根据手机号码更新密码
     *
     * @param mobile   手机号码
     * @param password 密码
     * @return 返回码
     */
    int editPasswordByMobile(String mobile, String password);

    /**
     * 修改状态
     *
     * @param userId 用户id
     * @param status 状态
     * @return 返回码
     */
    int editStatus(long userId, String status);

    /**
     * 添加公司员工
     *
     * @param user 公司员工
     * @return 添加返回码
     */
    int addCompanyStaff(User user);

    /**
     * 分页查询公司子账号
     *
     * @param pageHelper 分页帮助类
     * @param name       登录名称
     * @param parentId   主账号id
     * @param companyId  公司id
     * @return 公司子账号
     */
    PageHelper<User> queryCompanyStaff(PageHelper<User> pageHelper, String name, long parentId, long companyId);

    /**
     * 修改用户密码
     *
     * @param oldPassword   原密码
     * @param newPassword   新密码
     * @param reNewPassword 重新输入的密码
     * @return 编辑返回码
     */
    int updateUserPassWord(User user, String oldPassword, String newPassword, String reNewPassword);

    /**
     * 修改用户
     *
     * @param user 用户实体类
     * @return 成功返回1，失败返回0
     */
    int updateUser(User user);
}
