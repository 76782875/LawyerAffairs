package com.lecshop.total.user.service.impl;

import com.lecshop.utils.MD5Utils;
import com.lecshop.utils.PageHelper;
import com.lecshop.total.user.bean.User;
import com.lecshop.total.user.mapper.UserMapper;
import com.lecshop.total.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 公司员工service实现类
 *
 * @author sunluyang on 2017/7/14.
 */
@Service
public class UserServiceImpl implements UserService {

    /**
     * 调试日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * 注入公司员工mapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据手机号码查询用户
     *
     * @param mobile 手机号码
     * @return 公司用户信息
     */
    @Override
    public User queryUserByMobile(String mobile) {
        LOGGER.info("queryUserByMobile and mobile:{}", mobile);
        return userMapper.queryUserByMobile(mobile);
    }

    /**
     * 根据登录名称查询用户
     *
     * @param name 登录名称
     * @return 用户信息
     */
    @Override
    public User queryUserByName(String name) {
        LOGGER.info("queryUserByName and name:{}", name);
        return userMapper.queryUserByName(name);
    }

    /**
     * 根据用户id和公司id查询该员工信息以及公司信息
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return user 集合
     */
    @Override
    public User queryUserAndCompany(long companyId, long userId) {
        LOGGER.info("queryUserAndCompany and companyId:{}\r\n userId:{}", companyId, userId);
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", companyId);
        map.put("userId", userId);
        return userMapper.queryUserAndCompany(map).clearPasswords();
    }

    /**
     * 分页查询公司会员信息
     *
     * @param pageHelper 分页帮助类
     * @param name       公司名称
     * @param userName   用户名
     * @param mobile     会员手机
     * @return 公司会员信息
     */
    @Override
    public PageHelper<User> queryCompanyMemberInfos(PageHelper<User> pageHelper, String name, String userName, String mobile) {
        LOGGER.info("queryCompanyMemberInfos and pageHelper :{} \r\n and name :{} \r\n and nickName :{} \r\n mobile :{}", pageHelper, name, userName, mobile);
        Map<String, Object> params = new HashMap<>();
        params.put("companyName", name);
        params.put("userName", userName);
        params.put("mobile", mobile);
        return pageHelper.setListDates(userMapper.queryCompanyMemberInfos(pageHelper.getQueryParams(params, userMapper.queryCompanyMemberInfosCount(params))));
    }

    /**
     * 删除公司会员
     *
     * @param id 公司会员id
     * @return 成功返回1，失败返回0
     */
    @Override
    public int deleteCompanyMember(long id) {
        LOGGER.info("deleteCompanyMember and id :{}", id);
        return userMapper.deleteCompanyMember(id);
    }

    /**
     * 批量删除公司会员
     *
     * @param ids 公司会员id数组
     * @return 成功返回>=1，失败返回0
     */
    @Override
    public int batchDeleteCompanyMember(long[] ids) {
        LOGGER.info("batchDeleteCompanyMember and ids:{}", ids);
        return userMapper.batchDeleteCompanyMember(ids);
    }

    /**
     * 根据手机号码更新密码
     *
     * @param mobile   手机号码
     * @param password 密码
     * @return 返回码
     */
    @Override
    public int editPasswordByMobile(String mobile, String password) {
        LOGGER.info("editPasswordByMobile");
        return userMapper.editPasswordByMobile(mobile, password);
    }

    @Override
    public int editStatus(long userId, String status) {
        LOGGER.info("editStatus and userId:{}\r\n status:{}", userId, status);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("status", status);
        return userMapper.editStatus(map);
    }

    /**
     * 添加员工-注册
     *
     * @param user 公司员工实体类
     * @return 添加返回码
     */
    @Override
    public int addCompanyStaff(User user) {
        LOGGER.info("addCompanyStaff and user:{}", user);
        return userMapper.addCompanyStaff(user);
    }

    /**
     * 分页查询公司子账号
     *
     * @param pageHelper 分页帮助类
     * @param name       登录名称
     * @param parentId   主账号id
     * @param companyId  公司id
     * @return 公司子账号
     */
    @Override
    public PageHelper<User> queryCompanyStaff(PageHelper<User> pageHelper, String name, long parentId, long companyId) {
        LOGGER.info("queryCompanyStaff and pageHelper:{}\r\n name:{}\r\n parentId:{}\r\n companyId:{}", pageHelper, name, parentId, companyId);
        Map<String, Object> param = new HashMap<>();
        param.put("name", name);
        param.put("parentId", parentId);
        param.put("companyId", companyId);
        return pageHelper.setListDates(userMapper.queryCompanyStaff(pageHelper.getQueryParams(param, userMapper.queryCompanyStaffCount(param))));
    }

    /**
     * 修改用户密码
     *
     * @param user
     * @param oldPassword   原密码
     * @param newPassword   新密码
     * @param reNewPassword 重新输入的密码
     * @return 编辑返回码
     */
    @Override
    public int updateUserPassWord(User user, String oldPassword, String newPassword, String reNewPassword) {
        LOGGER.info("updateUserPassWord");
        if (Objects.isNull(user)) {
            return 0;
        }
        //判断是否问空
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(reNewPassword)) {
            return -1;
        }
        if (!newPassword.equals(reNewPassword)) {
            return 2;
        }
        // 根据用户手机号码查询用户信息
        User queryUser = userMapper.queryUserByMobile(user.getMobile());
        //判断原密码是否正确
        if (!queryUser.isPasswordRight(oldPassword)) {//oldPassword
            return 3;
        }
        user.setPassword(MD5Utils.getInstance().createMd5(newPassword));
        return userMapper.updateUserPassWord(user);
    }

    /**
     * 修改用户
     *
     * @param user 用户实体类
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateUser(User user) {
        LOGGER.debug("updateUser and user :{}", user);
        return userMapper.updateUser(user);
    }
}
