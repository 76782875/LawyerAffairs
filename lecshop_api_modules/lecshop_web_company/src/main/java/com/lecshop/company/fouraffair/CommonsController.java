package com.lecshop.company.fouraffair;

import com.lecshop.abutment.casedetail.bean.ResponseJsonz;
import com.lecshop.abutment.casedetail.service.CaseDetailService;
import com.lecshop.abutment.conversation.bean.CallList;
import com.lecshop.abutment.conversation.service.ConversationService;
import com.lecshop.abutment.lawyer.bean.LawyerInfo;
import com.lecshop.abutment.lawyer.bean.SearchBean;
import com.lecshop.abutment.lawyer.service.SearchLawyerService;
import com.lecshop.abutment.nopointlawyer.bean.SearchBeanc;
import com.lecshop.abutment.pastcases.bean.ResponseJsons;
import com.lecshop.abutment.pastcases.bean.SearchBeans;
import com.lecshop.abutment.pastcases.service.PastCasesService;
import com.lecshop.company.adminutil.CompanyLoginUtils;
import com.lecshop.total.affairlist.bean.AppointMeet;
import com.lecshop.total.affairlist.service.AppointMeetService;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import com.lecshop.utils.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 事务中公用的接口
 *
 * @author sunluyang on 2017/8/16.
 */
@Controller
public class CommonsController {
    /**
     * 注入事务权限检测实体类
     */
    @Autowired
    private ClickAuthCheck clickAuthCheck;
    /**
     * 注入过往案例service
     */
    @Autowired
    private PastCasesService pastCasesService;
    /**
     * 注入案例细节service
     */
    @Autowired
    private CaseDetailService caseDetailService;
    /**
     * 注入预约会面service
     */
    @Autowired
    private AppointMeetService appointMeetService;
    /**
     * 注入律师搜索service
     */
    @Autowired
    private SearchLawyerService searchLawyerService;
    /**
     * 注入通话service
     */
    @Autowired
    private ConversationService conversationService;


    /**
     * 分页查询律师
     *
     * @param pageHelper 分页帮助类
     * @param searchBean 搜索条件
     * @return 律师集合
     */
    @RequestMapping("/c_querysearchlawyer")
    @ResponseBody
    @UnAuth
    public BaseResponse querySearchLawyer(PageHelper<LawyerInfo> pageHelper, SearchBean searchBean) {
        return BaseResponse.build(searchLawyerService.searchLawyer(pageHelper, searchBean));
    }

    /**
     * 添加预约会面
     *
     * @param appointMeet 预约会面实体类
     * @return 添加返回码
     */
    @RequestMapping("/c_addappointmeet")
    @ResponseBody
    @UnAuth
    public int addAppointMeet(@RequestBody AppointMeet appointMeet, SearchBeanc searchBeanc, HttpServletRequest request) {
        if (!clickAuthCheck.checkIsAuthToClick(getCompanyIdFromSession(request), "1")) {
            return -8;
        }
        return appointMeetService.addAppointMeet(appointMeet.getCompanyIdAndUserId(getCompanyIdFromSession(request), getUserIdFromSession(request)), SearchBeanc.getPiAndPs(searchBeanc));
    }

    /**
     * 发起通话
     *
     * @param call     被叫
     * @param lawyerId 律师id
     * @param request  request请求
     * @return 返回码
     */
    @RequestMapping("/c_initiatecall")
    @ResponseBody
    @UnAuth
    public int initiateCall(String call, String lawyerId, String type, HttpServletRequest request) {
        if (!clickAuthCheck.checkIsAuthToClick(getCompanyIdFromSession(request), getType(type))) {
            return -8;
        }
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
        String mobile = CompanyLoginUtils.getInstance().getUserFromSession(request).getMobile();
        return conversationService.initiateCall(mobile, call, getUserIdFromSession(request), getCompanyIdFromSession(request), lawyerId, type, basePath);
    }

    /**
     * 接收发起通话回调
     *
     * @param callList 回调参数
     * @return 返回码
     */
    @RequestMapping("/c_receivecallbackurl")
    @ResponseBody
    @UnAuth
    public int receiveCallBackUrl(CallList callList) {
        return conversationService.receiveCallBackUrl(callList);
    }

    /**
     * 跳转到过往案例页面
     *
     * @return 过往案例页面
     */
    @RequestMapping("/c_topastcases")
    @UnAuth
    public ModelAndView toPastCases(String code, int type, String name, String institution) throws UnsupportedEncodingException {
        return new ModelAndView("threeaffair/pastcases")
                .addObject("code", code).addObject("name", name)
                .addObject("institution", institution).addObject("type", type);
    }

    /**
     * 查询过往案例
     */
    @RequestMapping("/c_querypastcases")
    @ResponseBody
    @UnAuth
    public ResponseJsons queryPastCases(SearchBeans searchBeans) {
        return pastCasesService.queryPastCases(searchBeans);
    }

    /**
     * 查询案例细节
     *
     * @param id 案例id
     */
    @RequestMapping("/c_querycasedetail")
    @ResponseBody
    @UnAuth
    public ResponseJsonz queryCaseDetail(String id) {
        return caseDetailService.queryCaseDetail(id);
    }

    private long getUserIdFromSession(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getUserIdFromSession(request);
    }

    private long getCompanyIdFromSession(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getCompanyIdFromSession(request);
    }

    private String getType(String type) {
        switch (type) {
            case "11":
            case "12":
                return "6";
            case "1":
                return "2";
            case "2":
                return "3";
            case "3":
                return "7";
            default:
                return "7";
        }
    }
}
