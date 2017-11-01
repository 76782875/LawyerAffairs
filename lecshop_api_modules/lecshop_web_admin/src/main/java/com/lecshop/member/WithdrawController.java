package com.lecshop.member;

import com.lecshop.total.withdraw.bean.QueryConditions;
import com.lecshop.total.withdraw.bean.Withdraw;
import com.lecshop.total.withdraw.service.WithdrawService;
import com.lecshop.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * Created by dujinkai on 17/7/26.
 * 提现控制器
 */
@Controller
public class WithdrawController {

    /**
     * 注入提现服务接口
     */
    @Autowired
    private WithdrawService withdrawService;

    /**
     * 跳转到查询提现记录页面
     *
     * @return 返回提现记录页面
     */
    @RequestMapping("/toquerywithdraw")
    public ModelAndView toQueryWithdraw() {
        return new ModelAndView("member/withdraws");
    }


    /**
     * 分页查询提现记录
     *
     * @param pageHelper      分页帮助类
     * @param queryConditions 查询条件
     * @return 返回提现记录
     */
    @RequestMapping("/querywithdraws")
    @ResponseBody
    public BaseResponse queryWithdraws(PageHelper<Withdraw> pageHelper, QueryConditions queryConditions) {
        return BaseResponse.build(withdrawService.queryWithdraws(pageHelper, queryConditions));
    }

    /**
     * 审核通过
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/passapply")
    @ResponseBody
    public int passApply(long id) {
        return withdrawService.changeStatus(id, "1");
    }

    /**
     * 拒绝提现申请
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/refuseapply")
    @ResponseBody
    public int refuseApply(long id) {
        return withdrawService.changeStatus(id, "2");
    }


    /**
     * 发放金额
     *
     * @param id 提现id
     * @return 返回提现结果
     */
    @RequestMapping("/releasemoney")
    @ResponseBody
    public WithdrawResponse releaseMoney(long id) {
        return withdrawService.releaseMoney(id);
    }
}
