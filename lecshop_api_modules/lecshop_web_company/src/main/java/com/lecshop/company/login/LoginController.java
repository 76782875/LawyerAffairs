package com.lecshop.company.login;

import com.lecshop.total.companyinfo.bean.CompanyInfo;
import com.lecshop.total.companyinfo.service.CompanyInfoService;
import com.lecshop.total.user.service.UserService;
import com.lecshop.total.viprecord.bean.VipModifyRecord;
import com.lecshop.total.viprecord.service.VipModifyRecordService;
import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.UnAuth;
import com.lecshop.company.adminutil.CompanyLoginUtils;
import com.lecshop.company.companymenu.service.CompanyMenuService;
import com.lecshop.company.register.RegisterService;
import com.lecshop.total.user.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 公司端登录控制器
 *
 * @author sunluyang on 2017/7/14.
 */
@Controller
public class LoginController {
    /**
     * 注入登录service
     */
    @Autowired
    private CompanyLoginService companyLoginService;
    /**
     * 注入公司菜单service
     */
    @Autowired
    private CompanyMenuService companyMenuService;
    /**
     * 注入注册service
     */
    @Autowired
    private RegisterService registerService;
    /**
     * 注入用户service
     */
    @Autowired
    private UserService userService;
    /**
     * 注入公司信息service
     */
    @Autowired
    private CompanyInfoService companyInfoService;
    /**
     * 注入VIP修改记录service
     */
    @Autowired
    private VipModifyRecordService vipModifyRecordService;

    /**
     * 跳转到登录页面
     *
     * @return 登录页面
     */
    @RequestMapping("/c_tologin")
    @UnAuth
    public ModelAndView toLogin() {
        return new ModelAndView("login/login");
    }

    /**
     * 公司员工登录
     *
     * @param mobileOrCompanyName 公司名称或者手机号码
     * @param password            密码
     * @param code                验证码
     * @return 0 输入不能为空  1 登录成功 2 验证码不正确 3 无该账号 4 账号被禁用 5 用户名或密码错误
     */
    @RequestMapping("/c_login")
    @ResponseBody
    @UnAuth
    public int login(String mobileOrCompanyName, String password, String code, HttpServletRequest request) {
        //session中的验证码
        String codeInSession = request.getSession().getAttribute(CommonConstant.COMPANY_LOGIN_KAPTCHA_KEY).toString();
        return companyLoginService.login(mobileOrCompanyName, password, code, codeInSession, user -> {
            alterLoginSuccess(user, request);
            // 清除session中的验证码
            request.getSession().removeAttribute(CommonConstant.COMPANY_LOGIN_KAPTCHA_KEY);
        });
    }

    /**
     * 发送忘记密码验证码
     *
     * @param phone 手机号
     * @return 发送返回码
     */
    @RequestMapping("/c_tosendforgetcode")
    @ResponseBody
    @UnAuth
    public int toSendRegisterCode(String phone, HttpServletRequest request) {
        String result = registerService.toSendRegisterCode(phone, "");
        if (!"-1".equals(result) && !"0".equals(result)) {
            request.getSession().setAttribute(CommonConstant.COMPANY_FORGET_PASSWORD_KAPTCHA_KEY, result);
            return 1;
        }
        return Integer.parseInt(result);
    }

    /**
     * 忘记密码
     *
     * @param phone      手机号码
     * @param code       用户输入的验证码
     * @param password   密码
     * @param rePassword 重新输入验证码
     * @param request    request请求
     * @return 返回码 -1 两次密码不一致 -2 验证码错误 -3 手机号码不正确 -4 不存在该用户 1成功
     */
    @RequestMapping("/c_forgetpassword")
    @ResponseBody
    @UnAuth
    public int forgetPassword(String phone, String code, String password, String rePassword, HttpServletRequest request) {
        if (StringUtils.isEmpty(request.getSession().getAttribute(CommonConstant.COMPANY_FORGET_PASSWORD_KAPTCHA_KEY))) {
            return -2;
        }
        return companyLoginService.forgetPassword(phone, code, password, rePassword, request.getSession().getAttribute(CommonConstant.COMPANY_FORGET_PASSWORD_KAPTCHA_KEY).toString());
    }

    /**
     * 修改密码
     *
     * @param request       请求
     * @param oldPassword   原密码
     * @param newPassword   新密码
     * @param reNewPassword 重新输入的密码
     * @return 修改返回码 0 没有登录 -1 输入不能为空 1 修改成功 2 两次输入密码不一致 3 原始密码不正确
     */
    @RequestMapping("/c_updatepassword")
    @ResponseBody
    @UnAuth
    public int updatePassword(HttpServletRequest request, String oldPassword, String newPassword, String reNewPassword) {
        return userService.updateUserPassWord(CompanyLoginUtils.getInstance().getUserFromSession(request), oldPassword, newPassword, reNewPassword);
    }

    /**
     * 登录成功后做的操作
     *
     * @param user    公司员工对象
     * @param request request请求
     */
    private void alterLoginSuccess(User user, HttpServletRequest request) {
        // 将用户信息放入session中
        CompanyLoginUtils.getInstance().putUserToSession(request, user);
        //检测会员等级
        checkVip(request);
        //将admin后台菜单添加到session中
        request.getSession().setAttribute("companyMenu", companyMenuService.queryCompanyMenu(user.getId()));
        request.getSession().setAttribute(CommonConstant.COMPANY_MENU_FIRSTMENUS, 1);
    }

    /**
     * 检测会员等级和修改VIP等级
     *
     * @param request request请求
     */
    private void checkVip(HttpServletRequest request) {
        long companyId = CompanyLoginUtils.getInstance().getCompanyIdFromSession(request);
        CompanyInfo companyInfo = companyInfoService.queryCompanyInfoById(companyId);
        VipModifyRecord vipModifyRecord = vipModifyRecordService.queryVipModifyRecordByTimeForLogin(LocalDateTime.now(), companyId);
        if (Objects.nonNull(vipModifyRecord) && !vipModifyRecord.getNewVip().equals(companyInfo.getVipType())) {
            companyInfoService.updateCompanyInfoForVip(companyInfo.buildVip(vipModifyRecord.getNewVip(), vipModifyRecord.getStartTime()));
        }
        if (Objects.isNull(vipModifyRecord) && !companyInfo.getVipType().equals("0") && !companyInfo.getVipType().equals("4") && LocalDateTime.now().isAfter(companyInfo.getEndTime())) {
            companyInfoService.updateCompanyInfoForVip(companyInfo.buildVip("4", LocalDateTime.now()));
        }
    }
}
