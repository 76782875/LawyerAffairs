package com.lecshop.company.renew;

import com.lecshop.company.adminutil.CompanyLoginUtils;
import com.lecshop.total.renewrecord.bean.RenewRecord;
import com.lecshop.total.renewrecord.service.RenewRecordService;
import com.lecshop.total.user.bean.User;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 公司会员续费记录控制器
 *
 * @author sunluyang on 2017/7/14.
 */
@Controller
public class RenewController {

    /**
     * 自动注入公司会员续费记录service
     */
    @Autowired
    private RenewRecordService renewRecordService;

    /**
     * 跳转到续费页面
     *
     * @return 续费页面
     */
    @RequestMapping("/c_torenew")
    public ModelAndView toRenew() {
        return new ModelAndView("renew/renew");
    }

    /**
     * 跳转至续费记录页面
     *
     * @return 续费记录页面
     */
    @RequestMapping("/c_torenewrecord")
    public ModelAndView toRenewRecord() {
        return new ModelAndView("renew/renewrecord");
    }

    /**
     * 分页查询公司会员续费记录
     *
     * @param request    request请求
     * @param pageHelper 分页帮助类
     * @return 公司会员续费记录
     */
    @RequestMapping("/c_queryrenewrecord")
    @ResponseBody
    public BaseResponse queryRenewRecord(HttpServletRequest request, PageHelper<RenewRecord> pageHelper) {
        return BaseResponse.build(renewRecordService.queryYourselfRenewRecord(pageHelper, getUserIdFormSession(request), getCompanyIdFormSession(request)));
    }

    private long getUserIdFormSession(HttpServletRequest request) {
        return "0".equals(CompanyLoginUtils.getInstance().getUserFromSession(request).getType()) ? 0 : CompanyLoginUtils.getInstance().getUserIdFromSession(request);
    }

    private long getCompanyIdFormSession(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getCompanyIdFromSession(request);
    }
}
