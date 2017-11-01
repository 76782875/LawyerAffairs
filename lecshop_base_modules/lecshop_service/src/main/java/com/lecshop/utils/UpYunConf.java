package com.lecshop.utils;

import lombok.Data;

/**
 * 又拍云配置
 *
 * @author sunluyang on 2017/7/10.
 */

@Data
public class UpYunConf {

    /**
     * 服务名称
     */
    private String nameSpace;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 访问地址
     */
    private String address;

    private UpYunConf() {

    }

    private UpYunConf(String nameSpace, String userName, String password, String address) {
        this.nameSpace = nameSpace;
        this.userName = userName;
        this.password = password;
        this.address = address;
    }

    /**
     * 构造实体
     *
     * @param nameSpace 服务名称
     * @param userName  用户名
     * @param password  密码
     * @param address   访问地址
     * @return 返回又拍云设置实体
     */
    public static UpYunConf buildUpYunConf(String nameSpace, String userName, String password, String address) {
        return new UpYunConf(nameSpace, userName, password, address);
    }
}
