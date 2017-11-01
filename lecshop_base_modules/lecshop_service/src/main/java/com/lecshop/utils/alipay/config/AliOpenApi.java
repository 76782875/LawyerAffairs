package com.lecshop.utils.alipay.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by dujinkai on 17/7/26.
 * 阿里开发平台配置
 */
@Component("aliOpenApi")
@Data
public class AliOpenApi {

    /**
     * 应用id
     */
    @Value("${appId}")
    private String appId;

    /**
     * 私钥
     */
    @Value("${privateKey}")
    private String privateKey;

    /**
     * 阿里公钥
     */
    @Value("${publicKey}")
    private String publicKey;

    /**
     * 加密类型
     */
    @Value("${type}")
    private String type;

    /**
     * 网关
     */
    @Value("${gateWay}")
    private String gateWay;

}
