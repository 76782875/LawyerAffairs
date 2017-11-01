package com.lecshop.admin.manager.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import com.lecshop.utils.MD5Utils;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * 管理员实体
 *
 * @author sunluyang on 2017/7/10.
 */
@Data
public class Manager {

    /**
     * 主键id
     */
    private long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否启用
     * 0 启用 1禁用 默认0启用
     */
    private String isUse = CommonConstant.MANAGER_USE;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 删除标识 0未删除 1删除
     */
    private String delFlag;

    /**
     * 判断密码是否正确
     *
     * @param password 用户输入的密码
     * @return 正确返回true  不正确返回false
     */
    @JsonIgnore
    public boolean isPasswordRight(String password) {
        return !StringUtils.isEmpty(password) && MD5Utils.getInstance().createMd5(password).equals(this.password);
    }

    /**
     * 清除密码
     */
    public void clearPassword() {
        this.password = "***********";
    }
}

