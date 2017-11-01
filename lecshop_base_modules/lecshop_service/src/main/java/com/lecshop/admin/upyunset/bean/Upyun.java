package com.lecshop.admin.upyunset.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import com.lecshop.utils.UpYunConf;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 又拍云实体类
 *
 * Created by LecShop on 2017/7/13.
 */
@Data
public class Upyun {

    /**
     * 主键id
     */
    private long id;

    /**
     * 空间名
     */
    private String nameSpace;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 空间地址
     */
    private String address;

    /**
     * 修改时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime modifyTime;

    /**
     * 获得又拍云的配置
     *
     * @return 返回又拍云配置
     */
    @JsonIgnore
    public UpYunConf getUpYunConf() {
        return UpYunConf.buildUpYunConf(this.nameSpace, this.username, this.password, this.address);
    }
}
