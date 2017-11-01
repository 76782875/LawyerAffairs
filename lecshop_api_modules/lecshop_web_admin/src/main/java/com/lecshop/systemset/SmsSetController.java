package com.lecshop.systemset;

import com.lecshop.admin.sms.bean.Sms;
import com.lecshop.admin.sms.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 短信接口设置控制器
 *
 * @author sunluyang on 2017/7/11.
 */
@Controller
public class SmsSetController {

    /**
     * 注入短信接口设置实现类
     */
    @Autowired
    private SmsService smsService;

    /**
     * 跳转到短信接口设置页面
     *
     * @return 短信接口设置页面
     */
    @RequestMapping("/tosmsset")
    public ModelAndView toSmsSet() {
        return new ModelAndView("systemset/smsset");
    }

    /**
     * 查询sms设置
     *
     * @return sms设置实体类
     */
    @RequestMapping("/querysmsset")
    @ResponseBody
    public Sms querySmsSet() {
        return smsService.querySmsSet();
    }

    /**
     * 编辑sms接口设置
     *
     * @param sms 短信接口设置实体类
     * @return -1 sms为空 1 编辑成功
     */
    @RequestMapping("/editsmsset")
    @ResponseBody
    public int editSmsSet(@RequestBody Sms sms) {
        return smsService.editSmsSet(sms);
    }
}
