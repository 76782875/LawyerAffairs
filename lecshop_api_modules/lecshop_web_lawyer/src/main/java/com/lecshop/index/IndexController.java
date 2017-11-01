package com.lecshop.index;

import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.lawyer.service.LawyerService;
import com.lecshop.total.transactionrecords.service.TransactionRecordsService;
import com.lecshop.util.LoginUtils;
import com.lecshop.utils.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dujinkai on 17/7/24.
 * 首页控制器
 */
@Controller
public class IndexController {

    /**
     * 注入律师服务接口
     */
    @Autowired
    private LawyerService lawyerService;

    /**
     * 注入交易流水
     */
    @Autowired
    private TransactionRecordsService transactionRecordsService;

    /**
     * 首页
     *
     * @return 返回首页
     */
    @RequestMapping("/index")
    public ModelAndView toIndex() {
        return new ModelAndView("index/index");
    }

    /**
     * 跳转到提示页面
     *
     * @param msg 提示信息
     * @return 返回提示页面
     */
    @RequestMapping("/totip")
    @UnAuth
    public ModelAndView toTip(String msg) {
        return new ModelAndView("login/tip").addObject("msg", msg);
    }

    /**
     * 查询律师信息
     *
     * @return 返回律师信息
     */
    @RequestMapping("/querylawyer")
    @ResponseBody
    public Lawyer queryLawyer(HttpServletRequest request) {
        return lawyerService.queryLawyerById(LoginUtils.getInstance().getLawyerId(request)).clearPassword()
                .setLawyerAllIncome(transactionRecordsService.queryLawyerAllIncome(LoginUtils.getInstance().getLawyerId(request)))
                .setLawyerTodayIncome(transactionRecordsService.queryLawyerCurrentDayIncome(LoginUtils.getInstance().getLawyerId(request)))
                .setLawyerBalance(transactionRecordsService.queryLawyerBalance(LoginUtils.getInstance().getLawyerId(request)));
    }
}
