package com.lecshop.total.user.mapper;

import com.lecshop.total.user.bean.User;

import java.util.List;
import java.util.Map;

/**
 * 公司员工mapper
 *
 * @author sunluyang on 2017/7/14.
 */
public interface UserMapper {

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
     * 根据员工和公司id查询该员工信息以及公司信息
     *
     * @param map 查询条件
     * @return user 集合
     */
    User queryUserAndCompany(Map<String, Object> map);

    /**
     * 分页查询公司会员信息
     *
     * @param params 查询参数
     * @return 公司会员信息集合
     */
    List<User> queryCompanyMemberInfos(Map<String, Object> params);

    /**
     * 查询公司会员信息总记录数
     *
     * @param params 查询参数
     * @return 公司会员信息总记录数
     */
    int queryCompanyMemberInfosCount(Map<String, Object> params);

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
     * @param map 更新状态
     * @return 返回码
     */
    int editStatus(Map<String, Object> map);

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
     * @param params 查询参数
     * @return 公司子账号
     */
    List<User> queryCompanyStaff(Map<String, Object> params);

    /**
     * 查询公司子账号总记录数
     *
     * @param params 查询参数
     * @return 公司子账号总记录数
     */
    int queryCompanyStaffCount(Map<String, Object> params);

    /**
     * 修改用户密码
     *
     * @param user 用户实体类
     * @return 编辑返回码
     */
    int updateUserPassWord(User user);

    /**
     * 修改用户
     *
     * @param user 用户实体类
     * @return 成功返回1，失败返回0
     */
    int updateUser(User user);
}
