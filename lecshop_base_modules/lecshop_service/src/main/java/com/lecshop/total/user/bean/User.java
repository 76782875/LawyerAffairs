package com.lecshop.total.user.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import com.lecshop.utils.MD5Utils;
import com.lecshop.total.companyinfo.bean.CompanyInfo;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * 公司员工实体类
 *
 * @author sunluyang on 2017/7/14.
 */
@Data
public class User {
    /**
     * 主键id
     */
    private long id;
    /**
     * 登录名称
     */
    private String name;
    /**
     * 登录密码
     */
    private String password;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 账号状态 0 启用 1 禁用
     */
    private String status;
    /**
     * 0 主账号 1 子账号
     */
    private String type;
    /**
     * 作为子账号的时候归属的主账号id
     */
    private long parentId;
    /**
     * 公司id
     */
    private long companyId;
    /**
     * 0 未删除 1 删除
     */
    private String delFlag;
    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;
    /**
     * 公司信息
     */
    private CompanyInfo companyInfo;

    /**
     * 角色名称
     */
    private String roleName;

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

    /**
     * 清除密码
     */
    public User clearPasswords() {
        this.password = "***********";
        return this;
    }

    public User getDefaultUser(String name, String password, String mobile, long companyId, String type, String status) {
        this.name = name;
        this.password = MD5Utils.getInstance().createMd5(password);
        this.mobile = mobile;
        this.companyId = companyId;
        this.type = type;
        this.status = status;
        return this;
    }

    public User getCompanyIdAndParentId(long companyId, long parentId) {
        this.companyId = companyId;
        this.parentId = parentId;
        this.type = "1";
        return this;
    }

    public User getUserPassword(String password) {
        this.password = MD5Utils.getInstance().createMd5(password);
        return this;
    }

    public User getInstance(long id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        return this;
    }
}
