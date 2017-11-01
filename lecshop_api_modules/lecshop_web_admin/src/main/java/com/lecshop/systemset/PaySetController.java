package com.lecshop.systemset;

import com.lecshop.admin.payset.bean.PaySetCommon;
import com.lecshop.admin.payset.service.PaySetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 支付设置接口控制器
 *
 * @author sunluyang on 2017/7/11.
 */
@Controller
public class PaySetController {

    /**
     * 注入支付接口设置实现类
     */
    @Autowired
    private PaySetService paySetService;

    /**
     * 跳转到支付接口设置页面
     *
     * @return 支付接口设置页面
     */
    @RequestMapping("/topayset")
    public ModelAndView toPaySet() {
        return new ModelAndView("systemset/payset");
    }

    /**
     * 查询支付接口设置
     *
     * @return PaySetCommon
     */
    @RequestMapping("/querypayset")
    @ResponseBody
    public PaySetCommon queryPaySet() {
        return paySetService.queryPaySet();
    }

    /**
     * 编辑支付接口设置
     *
     * @param paySetCommon 实体类参数
     * @param codeType     支付设置类型 1 支付宝 2 微信 3 银联
     * @return -1编辑出错 >=1成功
     */
    @RequestMapping("/editpayset")
    @ResponseBody
    public int editPaySet(@RequestBody PaySetCommon paySetCommon, String codeType) {
        return paySetService.editPaySet(paySetCommon, codeType);
    }
}
