package com.lecshop.reportforms;

import com.lecshop.total.companyinfo.bean.CompanyTransaction;
import com.lecshop.total.companyinfo.service.CompanyTransactionService;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 公司对账控制器
 *
 * Created by LecShop on 2017/8/23.
 */
@Controller
public class CompanyTransactionController {

    /**
     * 自动注入公司对账service
     */
    @Autowired
    private CompanyTransactionService companyTransactionService;

    /**
     * 跳转至公司对账页面
     *
     * @return 公司对账页面
     */
    @RequestMapping("/tocompanytransaction")
    public ModelAndView toCompanyTransaction() {
        return new ModelAndView("reportforms/companytransaction");
    }

    /**
     * 查询公司对账报表
     *
     * @param pageHelper 分页帮助类
     * @param startTime 起始时间
     * @param endTime 终止时间
     * @return 公司对账
     */
    @RequestMapping("/querycompanytransaction")
    @ResponseBody
    public BaseResponse queryCompanyTransaction(PageHelper<CompanyTransaction> pageHelper, String startTime, String endTime) {
        return BaseResponse.build(companyTransactionService.queryCompanyTransaction(pageHelper, startTime, endTime));
    }

}
