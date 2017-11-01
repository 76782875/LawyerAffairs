package com.lecshop.member;

import com.lecshop.total.renewrecord.bean.RenewRecord;
import com.lecshop.total.renewrecord.service.RenewRecordService;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 会员续费记录控制器
 *
 * Created by LecShop on 2017/7/13.
 */
@Controller
public class RenewRecordController {

    /**
     * 自动注入续费记录service
     */
    @Autowired
    private RenewRecordService renewRecordService;

    /**
     * 跳转至续费记录页面
     *
     * @return 续费记录页面
     */
    @RequestMapping("/torenewreocrd")
    public ModelAndView toRenewRecord() {
        return new ModelAndView("member/renewrecord");
    }

    /**
     * 分页查询续费记录
     *
     * @param pageHelper 分页帮助类
     * @param name 公司名称
     * @param orderNo    订单号
     * @return 续费记录
     */
    @RequestMapping("/queryrenewrecordlist")
    @ResponseBody
    public BaseResponse queryRenewRecordList(PageHelper<RenewRecord> pageHelper, String name, String orderNo) {
        return BaseResponse.build(renewRecordService.queryRenewRecordList(pageHelper, name, orderNo));
    }
}
