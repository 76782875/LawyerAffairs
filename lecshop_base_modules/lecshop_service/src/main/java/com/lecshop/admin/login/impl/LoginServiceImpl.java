package com.lecshop.admin.login.impl;

import com.lecshop.admin.login.LoginService;
import com.lecshop.admin.manager.bean.Manager;
import com.lecshop.admin.manager.service.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 登录服务接口
 *
 * @author sunluyang on 2017/7/10.
 */
@Service
public class LoginServiceImpl implements LoginService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    /**
     * 注入管理员服务接口
     */
    @Autowired
    private ManagerService managerService;

    /**
     * admin登录
     *
     * @param username      用户名
     * @param password      密码
     * @param code          用户输入的验证码
     * @param codeInSession session中的验证码
     * @param consumer      回调接口
     * @return -1 参数不对  -2 验证码不对 -3 用户名或者密码错误 -4 用户被禁用 0成功
     */
    @Override
    public int login(String username, String password, String code, String codeInSession, Consumer<Manager> consumer) {
        logger.debug("admin login and username:{} \r\n password:{} \r\n code:{}", username, "******", code);
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            logger.error("admin login fail due to params is error.....");
            return -1;
        }
        // 如果验证码不对 则返回
        if (!code.equals(codeInSession)) {
            logger.error("admin login fail due to code is wrong....");
            return -2;
        }
        // 根据用户名查询管理员信息
        Manager manager = managerService.queryManagerByName(username);
        // 用户不存在 直接返回
        if (Objects.isNull(manager)) {
            logger.error("admin login fail due to manager is not exist....");
            return -3;
        }
        //该用户被禁用
        if (manager.getIsUse().equals("1")) {
            logger.error("admin login fail due to manager is not disable....");
            return -4;
        }
        // 判断密码是否正确  如果不正确 则返回
        if (!manager.isPasswordRight(password)) {
            logger.error("admin login fail due password is wrong....");
            return -3;
        }
        // 登录成功 进行回调
        manager.clearPassword();
        consumer.accept(manager);
        return 0;
    }
}
