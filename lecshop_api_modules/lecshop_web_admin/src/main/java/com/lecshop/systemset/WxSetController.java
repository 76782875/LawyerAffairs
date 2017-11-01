package com.lecshop.systemset;

import com.lecshop.total.wxsetting.bean.WxSetting;
import com.lecshop.total.wxsetting.service.WxSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 微信设置控制器
 *
 * Created by LecShop on 2017/7/28.
 */
@Controller
public class WxSetController {

    /**
     * 自动注入微信设置service
     */
    @Autowired
    private WxSettingService wxSettingService;

    /**
     * 跳转至微信设置页面
     *
     * @return 微信设置页面
     */
    @RequestMapping("/towxsetting")
    public ModelAndView toWxsetting() {
        return new ModelAndView("systemset/wxsetting");
    }

    /**
     * 查询微信设置详情
     *
     * @return 微信设置详情
     */
    @RequestMapping("/querywxsetting")
    @ResponseBody
    public WxSetting queryWxsetting() {
        return wxSettingService.queryWxSetting();
    }

    /**
     * 修改微信设置
     *
     * @param wxSetting 微信设置
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/editwxsetting")
    @ResponseBody
    public int updateWxSetting(@RequestBody WxSetting wxSetting) {
        return wxSettingService.updateWxSetting(wxSetting);
    }
}
