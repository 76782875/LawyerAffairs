package com.lecshop.member;

import com.lecshop.admin.sms.service.SmsService;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.lawyer.bean.LawyerExternal;
import com.lecshop.total.lawyer.service.LawyerService;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.BaseResponseExternal;
import com.lecshop.utils.PageHelper;
import com.lecshop.utils.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 律师会员控制器
 *
 * @author sunluyang on 2017/7/24.
 */
@Controller
public class LawyerMemberController {
    /**
     * 注入律师service
     */
    @Autowired
    private LawyerService lawyerService;
    /**
     * 注入短信设置service接口
     */
    @Autowired
    private SmsService smsService;

    /**
     * 跳转到律师会员列表
     *
     * @return 律师会员列表
     */
    @RequestMapping("/tolawyermemberlist")
    public ModelAndView toLawyerMemberList() {
        return new ModelAndView("member/lawyermember");
    }

    /**
     * 分页查询律师列表
     *
     * @param pageHelper 分页帮助类
     * @param name       律师姓名
     * @param mobile     手机号码
     * @return 律师列表
     */
    @RequestMapping("/querylawyerlist")
    @ResponseBody
    public BaseResponse queryLawyer(PageHelper<Lawyer> pageHelper, String name, String mobile, String status) {
        return BaseResponse.build(lawyerService.queryLawyer(pageHelper, name, mobile, status));
    }

    /**
     * 对外查询律师接口
     *
     * @param pageHelper 分页帮助类
     * @param key        秘钥
     * @return 律师集合
     */
    @RequestMapping("/lawyerlist")
    @ResponseBody
    @UnAuth
    public BaseResponseExternal externalInterface(PageHelper<LawyerExternal> pageHelper, String key) {
        Map<String, Object> map = lawyerService.externalInterface(pageHelper, key);
        return BaseResponseExternal.build((PageHelper<LawyerExternal>) map.get("pageHelper"), (int) map.get("rescode"));
    }

    /**
     * 删除律师会员
     *
     * @param id 律师会员id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/deletelawyer")
    @ResponseBody
    public int deleteLawyer(long id) {
        return lawyerService.deleteLawyer(id);
    }

    /**
     * 批量删除律师会员
     *
     * @param ids 律师会员id数组
     * @return 成功返回>=1，失败返回0
     */
    @RequestMapping("/batchdeletelawyer")
    @ResponseBody
    public int batchDeletelawyer(long[] ids) {
        return lawyerService.batchDeleteLawyer(ids);
    }

    /**
     * 根据id查询律师会员
     *
     * @param id 律师会员id
     * @return 律师会员
     */
    @RequestMapping("/querylawyerbyid")
    @ResponseBody
    public Lawyer queryLawyerById(long id) {
        return lawyerService.queryLawyerById(id);
    }

    /**
     * 审核律师会员
     *
     * @param lawyer 律师会员
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/auditlawyer")
    @ResponseBody
    public int auditLawyer(@RequestBody Lawyer lawyer) {
        return lawyerService.auditLawyer(lawyer);
    }

    /**
     * 添加律师会员
     *
     * @param lawyer 律师会员
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/addlawyer")
    @ResponseBody
    public int addLawyer(@RequestBody Lawyer lawyer) {
        return lawyerService.addLawyer(lawyer, true);
    }

    /**
     * 修改律师
     *
     * @param lawyer 律师
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/updatelawyer")
    @ResponseBody
    public int updateLawyer(@RequestBody Lawyer lawyer) {
        return lawyerService.updateLawyer(lawyer);
    }

    /**
     * 发送验证码
     *
     * @param phone    手机号
     * @param password 密码
     * @return 返回值
     */
    @RequestMapping("/tosendpassword")
    @ResponseBody
    @UnAuth
    public int toSendPassword(String phone, String password) {
        return smsService.sendMsg(phone, password) ? 1 : 0;
    }
}
