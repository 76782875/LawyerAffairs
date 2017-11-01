package com.lecshop.total.wxsetting.service;

import com.lecshop.total.wxsetting.bean.WxSetting;

/**
 * Created by dujinkai on 17/7/24.
 * 微信服务接口
 */
public interface WxSettingService {

    /**
     * 查询微信设置
     *
     * @return 返回微信设置
     */
    WxSetting queryWxSetting();

    /**
     * 修改微信设置
     *
     * @param wxSetting 微信设置
     * @return 成功返回1，失败返回0
     */
    int updateWxSetting(WxSetting wxSetting);
}
