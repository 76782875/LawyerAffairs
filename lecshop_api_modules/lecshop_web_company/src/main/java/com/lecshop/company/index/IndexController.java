package com.lecshop.company.index;

import com.lecshop.company.adminutil.CompanyLoginUtils;
import com.lecshop.company.index.bean.IndexInfo;
import com.lecshop.company.index.service.IndexInfoService;
import com.lecshop.total.companyinfo.bean.CompanyInfo;
import com.lecshop.total.companyinfo.service.CompanyInfoService;
import com.lecshop.total.letterstation.bean.LetterStation;
import com.lecshop.total.letterstation.service.LetterStationService;
import com.lecshop.total.renewrecord.service.RenewRecordService;
import com.lecshop.total.user.bean.User;
import com.lecshop.total.user.service.UserService;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import com.lecshop.utils.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 首页控制器
 *
 * @author sunluyang on 2017/7/24.
 */
@Controller
public class IndexController {
    /**
     * 注入首页信息service
     */
    @Autowired
    private IndexInfoService indexInfoService;
    /**
     * 注入站内信service
     */
    @Autowired
    private LetterStationService letterStationService;
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
     * 登录成功后跳转到首页
     *
     * @return 首页
     */
    @RequestMapping("/c_index")
    public ModelAndView loginSuccessToIndex() {
        return new ModelAndView("index/index");
    }

    /**
     * 查询首页信息
     *
     * @param request request请求
     * @return 首页信息
     */
    @RequestMapping("c_queryindexinfo")
    @ResponseBody
    @UnAuth
    public IndexInfo queryIndexInfo(HttpServletRequest request) {
        return indexInfoService.toDoAffairs(getCompanyIdFormSession(request), getUserIdFormSession(request), getUserType(request));
    }

    /**
     * 查询未读站内信数目
     *
     * @param request request请求
     * @return 未读站内信数目
     */
    @RequestMapping("/c_queryunreadletterstationcount")
    @ResponseBody
    @UnAuth
    public int queryUnreadLetterStation(HttpServletRequest request) {
        return letterStationService.queryLetterStationUnreadCount(getUserIdFormSession(request));
    }

    /**
     * 查询站内信
     *
     * @return 站内信
     */
    @RequestMapping("/c_queryletterstation")
    @ResponseBody
    @UnAuth
    public BaseResponse queryLetterStation(HttpServletRequest request, PageHelper<LetterStation> pageHelper) {
        return BaseResponse.build(letterStationService.queryLetterStation(pageHelper, getUserIdFormSession(request)));
    }

    /**
     * 删除站内信
     *
     * @param id 站内信id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_deleteletterstation")
    @ResponseBody
    @UnAuth
    public int deleteLetterStation(long id) {
        return letterStationService.deleteLetterStation(id);
    }

    /**
     * 将站内信设为已读
     *
     * @param id 站内信id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_setletterstationasreaded")
    @ResponseBody
    @UnAuth
    public int setLetterStationAsReaded(long id) {
        return letterStationService.readLetterStation(id);
    }

    /**
     * 查询员工及公司信息
     *
     * @param request request请求
     * @return 员工及公司信息
     */
    @RequestMapping("/c_personcentre")
    @ResponseBody
    @UnAuth
    public User showPersonCentre(HttpServletRequest request) {
        return userService.queryUserAndCompany(getCompanyIdFormSession(request), getUserIdFormSession(request));
    }

    /**
     * 保存个人中心信息
     *
     * @param request request请求
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_savepersoncentre")
    @ResponseBody
    @UnAuth
    public int savePersonCentre(HttpServletRequest request, String name, String mobile, String address, String fax, String businessLicence) {
        if (getUserFormSession(request).getType().equals("0")) {
            companyInfoService.updateCompanyInfoForPersonCentre(
                    new CompanyInfo().getPersonInfo(getCompanyIdFormSession(request), getUserIdFormSession(request), name, address, fax, businessLicence)
            );
        }
        return userService.updateUser(new User().getInstance(getUserIdFormSession(request), name, mobile));
    }

    private long getUserIdFormSession(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getUserIdFromSession(request);
    }

    private long getCompanyIdFormSession(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getCompanyIdFromSession(request);
    }

    private User getUserFormSession(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getUserFromSession(request);
    }

    private String getUserType(HttpServletRequest request) {
        return getUserFormSession(request).getType();
    }
}
