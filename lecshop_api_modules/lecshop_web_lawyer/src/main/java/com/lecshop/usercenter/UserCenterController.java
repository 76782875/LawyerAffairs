package com.lecshop.usercenter;

import com.alibaba.fastjson.JSON;
import com.lecshop.admin.profitset.service.ProfitSetService;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.lawyer.service.LawyerService;
import com.lecshop.total.withdraw.bean.QueryConditions;
import com.lecshop.total.withdraw.bean.Withdraw;
import com.lecshop.total.withdraw.service.WithdrawService;
import com.lecshop.total.withdrawset.bean.WithdrawSet;
import com.lecshop.total.withdrawset.service.WithdrawSetService;
import com.lecshop.total.wxsetting.service.WxSettingService;
import com.lecshop.util.LoginUtils;
import com.lecshop.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dujinkai on 17/7/25.
 * 个人中心控制器
 */
@Controller
public class UserCenterController {

    /**
     * 注入律师服务接口
     */
    @Autowired
    private LawyerService lawyerService;

    /**
     * 注入提现设置实体
     */
    @Autowired
    private WithdrawSetService withdrawSetService;

    /**
     * 注入律师收益设置
     */
    @Autowired
    private ProfitSetService profitSetService;

    /**
     * 注入提现服务接口
     */
    @Autowired
    private WithdrawService withdrawService;

    @Autowired
    private WxSettingService wxSettingService;

    /**
     * 跳转到个人中心
     */
    @RequestMapping("/tocenter")
    public ModelAndView toCenter() {
        return new ModelAndView("center/usercenter");
    }


    /**
     * 跳转到个人信息
     *
     * @return 返回个人信息页面
     */
    @RequestMapping("/touserinfo")
    public ModelAndView toUserInfo() {
        return new ModelAndView("center/userinfo");
    }

    /**
     * 更新律师信息
     *
     * @param lawyer 律师信息
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/updatelawyer")
    @ResponseBody
    public int updateLawyer(HttpServletRequest request, Lawyer lawyer) {
        lawyer.setId(LoginUtils.getInstance().getLawyerId(request));
        return lawyerService.updateLawyerForCenter(lawyer);
    }

    /**
     * 修改律师名称
     *
     * @return 跳转到修改律师名称页面
     */
    @RequestMapping("/toupdatename")
    public ModelAndView toUpdateName() {
        return new ModelAndView("center/updatename");
    }

    /**
     * 修改律师qq
     *
     * @return 返回律师qq页面
     */
    @RequestMapping("/toupdateqq")
    public ModelAndView toUpdateQQ() {
        return new ModelAndView("center/updateqq");
    }

    /**
     * 修改律师邮箱
     *
     * @return 返回律师邮箱页面
     */
    @RequestMapping("/toupdateemail")
    public ModelAndView toUpdateEmail() {
        return new ModelAndView("center/updateemail");
    }

    /**
     * 去修改律所
     *
     * @return 返回修改律所页面
     */
    @RequestMapping("/toupdatelawyerplace")
    public ModelAndView toUpdateLawyerPlace() {
        return new ModelAndView("center/updatelawyerplace");
    }


    /**
     * 去修改地址
     *
     * @return 返回修改地址页面
     */
    @RequestMapping("/toupdateaddress")
    public ModelAndView toUpdateAddress() {
        return new ModelAndView("center/updateaddress");
    }

    /**
     * 跳转到提现设置页面
     *
     * @return 返回提现设置页面
     */
    @RequestMapping("/towithdrawsetting")
    public ModelAndView toWithdrawSetting(HttpServletRequest request) {
        return new ModelAndView("center/withdrawset").addObject("withdrawset", withdrawSetService.queryByLawyerId(LoginUtils.getInstance().getLawyerId(request)));
    }

    /**
     * 更新提现设置
     *
     * @param withdrawSet 提现设置
     * @return 成功返回 1 失败返回0
     */
    @RequestMapping("/updatewithdrawset")
    @ResponseBody
    public int updateWithdrawSet(HttpServletRequest request, WithdrawSet withdrawSet) {
        withdrawSet.setLawyerId(LoginUtils.getInstance().getLawyerId(request));
        return withdrawSetService.updateWithdrawSet(withdrawSet);
    }

    /**
     * 跳转到提现页面
     *
     * @return 返回提现页面
     */
    @RequestMapping("/towithdraw")
    public ModelAndView toWithdraw(HttpServletRequest request) {
        return new ModelAndView("center/withdraw").addObject("withdrawset", withdrawSetService.queryByLawyerId(LoginUtils.getInstance().getLawyerId(request))).addObject("profitSet", profitSetService.queryProfitSet());
    }

    /**
     * 申请提现
     *
     * @param withdraw 提现实体
     * @return 成功返回1 失败返回0 -1:当前日期不能提现 -2:未配置提现账户 -3:参数错误 -4:账户余额不足
     */
    @RequestMapping("/applywithdraw")
    @ResponseBody
    public int applyWithdraw(HttpServletRequest request, Withdraw withdraw) {
        return withdrawService.addWithdraw(withdraw.setDetailValuesFroApply(LoginUtils.getInstance().getLawyerId(request)));
    }

    /**
     * 跳转到提现记录页面
     *
     * @return 返回提现记录页面
     */
    @RequestMapping("/towithdrawlists")
    public ModelAndView toWithdrawLists() {
        return new ModelAndView("center/withdrawlists");
    }

    /**
     * 查询律师的提现记录
     *
     * @param pageHelper 分页帮助类
     * @return 返回律师提现记录
     */
    @RequestMapping("/querywithdraws")
    @ResponseBody
    public BaseResponse queryWithdraws(HttpServletRequest request, PageHelper<Withdraw> pageHelper) {
        QueryConditions queryConditions = new QueryConditions();
        queryConditions.setLawyerId(LoginUtils.getInstance().getLawyerId(request));
        return BaseResponse.build(withdrawService.queryWithdraws(pageHelper, queryConditions));
    }

}
