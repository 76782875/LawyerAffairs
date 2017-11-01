package com.lecshop.total.wxsetting.mapper;

import com.lecshop.total.wxsetting.bean.WxSetting;

/**
 * Created by dujinkai on 17/7/24.
 * 微信设置数据库接口
 */
public interface WxSettingMapper {

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
