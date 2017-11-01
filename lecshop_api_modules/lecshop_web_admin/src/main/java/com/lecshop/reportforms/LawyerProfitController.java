package com.lecshop.reportforms;

import com.lecshop.total.lawyer.bean.LawyerProfit;
import com.lecshop.total.lawyer.service.LawyerProfitService;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 律师收益控制器
 *
 * Created by LecShop on 2017/8/18.
 */
@Controller
public class LawyerProfitController {

    /**
     * 自动注入律师收益service
     */
    @Autowired
    private LawyerProfitService lawyerProfitService;

    /**
     * 跳转至律师收益页面
     *
     * @return 律师收益页面
     */
    @RequestMapping("/tolawyerprofit")
    public ModelAndView toLawyerProfit() {
        return new ModelAndView("reportforms/lawyerprofit");
    }

    /**
     * 查询律师收益
     *
     * @param pageHelper 分页帮助类
     * @param startTime 起始时间
     * @param endTime 终止时间
     * @return 返回律师收益
     */
    @RequestMapping("/querylawyerprofit")
    @ResponseBody
    public BaseResponse queryLawyerProfit(PageHelper<LawyerProfit> pageHelper, String startTime, String endTime) {
        return BaseResponse.build(lawyerProfitService.queryLawyerProfit(pageHelper, startTime, endTime));
    }
}
