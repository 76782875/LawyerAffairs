package com.lecshop.lawyer.wxauth.service.impl;

import com.lecshop.lawyer.wxauth.bean.WxAuth;
import com.lecshop.lawyer.wxauth.mapper.WxAuthMapper;
import com.lecshop.lawyer.wxauth.service.WxAuthService;
import com.lecshop.total.wxsetting.mapper.WxSettingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * Created by dujinkai on 17/7/24.
 * 微信认证服务接口实现
 */
@Service
public class WxAuthServiceImpl implements WxAuthService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(WxAuthServiceImpl.class);

    /**
     * 微信认为数据库接口
     */
    @Autowired
    private WxAuthMapper wxAuthMapper;

    @Override
    public WxAuth queryByOpenId(String openId) {
        logger.debug("queryByOpenId and openId:{}", openId);
        return wxAuthMapper.queryByOpenId(openId);
    }

    @Override
    public int addWxAuth(WxAuth wxAuth) {

        logger.debug("addWxAuth and wxAuth:{}", wxAuth);

        if (Objects.isNull(wxAuth)) {
            logger.error("addWxAuth fail due to params is null...");
            return 0;
        }
        return wxAuthMapper.addWxAuth(wxAuth);
    }

    @Override
    public List<String> queryOpenIdsByLawyerList(List<Long> lawyerIds) {
        return wxAuthMapper.queryOpenIdsByLawyerList(lawyerIds);
    }
}
