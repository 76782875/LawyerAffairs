package com.lecshop.total.wxsetting.service.impl;

import com.lecshop.total.wxsetting.bean.WxSetting;
import com.lecshop.total.wxsetting.mapper.WxSettingMapper;
import com.lecshop.total.wxsetting.service.WxSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dujinkai on 17/7/24.
 * 微信服务接口实现
 */
@Service
public class WxSettingServiceImpl implements WxSettingService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(WxSettingServiceImpl.class);

    /**
     * 查询微信设置
     */
    @Autowired
    private WxSettingMapper wxSettingMapper;

    @Override
    public WxSetting queryWxSetting() {
        logger.debug("queryWxSetting....");
        return wxSettingMapper.queryWxSetting();
    }

    /**
     * 修改微信设置
     *
     * @param wxSetting 微信设置
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateWxSetting(WxSetting wxSetting) {
        logger.debug("updateWxSetting and wxSetting :{}", wxSetting);
        return wxSettingMapper.updateWxSetting(wxSetting);
    }
}
