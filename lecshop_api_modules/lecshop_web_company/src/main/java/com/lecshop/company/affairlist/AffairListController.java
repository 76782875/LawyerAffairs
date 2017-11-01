package com.lecshop.company.affairlist;

import com.lecshop.company.adminutil.CompanyLoginUtils;
import com.lecshop.total.affairlist.bean.*;
import com.lecshop.total.affairlist.service.*;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 事务列表控制器
 *
 * @author sunluyang on 2017/7/11.
 */
@Controller
public class AffairListController {
    /**
     * 注入修改文书事务service
     */
    @Autowired
    private ModifyDocService modifyDocService;
    /**
     * 草拟文书service
     */
    @Autowired
    private DraftDocService draftDocService;
    /**
     * 注入电话咨询service
     */
    @Autowired
    private TelConsultationService telConsultationService;
    /**
     * 注入预约会面service
     */
    @Autowired
    private AppointMeetService appointMeetService;
    /**
     * 注入律师函service
     */
    @Autowired
    private LawyerLetterService lawyerLetterService;
    /**
     * 注入纠纷事务service
     */
    @Autowired
    private DisputesService disputesService;
    /**
     * 注入重大事项service
     */
    @Autowired
    private ImportantMatterService importantMatterService;

    /**
     * 跳转到律师函页面
     *
     * @return 律师函页面
     */
    @RequestMapping("/c_tolawyerletters")
    public ModelAndView toLawyerLetter() {
        return new ModelAndView("affairlist/lawyerletter");
    }

    /**
     * 跳转到修改和草拟文书页面
     *
     * @return 修改和草拟文书页面
     */
    @RequestMapping("/c_tomodifyanddraftdoc")
    public ModelAndView toModifyAndDraftDoc(String url) {
        return new ModelAndView("affairlist/modifyanddraftdoc").addObject("url", url);
    }

    /**
     * 跳转到公司事务-电话咨询页面
     *
     * @return 纠纷事务电话咨询页面
     */
    @RequestMapping("/c_tocompanytelconsultation")
    public ModelAndView toCompanyTelConsultation() {
        return new ModelAndView("affairlist/ctelconsultation");
    }

    /**
     * 跳转到公司事务-预约会面页面
     *
     * @return 预约会面页面
     */
    @RequestMapping("/c_tocompanyappointmeet")
    public ModelAndView toCompanyAppointMeet(String meetType) {
        return new ModelAndView("affairlist/cappointmeet").addObject("meetType", meetType);
    }

    /**
     * 跳转到纠纷事务-电话咨询页面
     *
     * @return 电话咨询页面
     */
    @RequestMapping("/c_todisputestelconsultation")
    public ModelAndView toDisputesTelConsultation() {
        return new ModelAndView("affairlist/dtelconsultation");
    }

    /**
     * 跳转到纠纷事务-预约会面页面
     *
     * @return 预约会面页面
     */
    @RequestMapping("/c_todisputesappointmeet")
    public ModelAndView toDisputesAppointMeet() {
        return new ModelAndView("affairlist/dappointmeet");
    }

    /**
     * 跳转到纠纷事务-纠纷服务页面
     *
     * @return 纠纷服务页面
     */
    @RequestMapping("/c_todisputesservice")
    public ModelAndView toDisputesService() {
        return new ModelAndView("affairlist/ddisputes");
    }

    /**
     * 跳转到重大事项-电话咨询页面
     *
     * @return 电话咨询页面
     */
    @RequestMapping("/c_toimportanttelconsultation")
    public ModelAndView toImportantTelConsultation() {
        return new ModelAndView("affairlist/itelconsultation");
    }

    /**
     * 跳转到重大事项-预约会面页面
     *
     * @return 预约会面页面
     */
    @RequestMapping("/c_toimportantappointmeet")
    public ModelAndView toImportantAppointMeet() {
        return new ModelAndView("affairlist/iappointmeet");
    }

    /**
     * 跳转到重大事项-重大事项页面
     *
     * @return 重大事项页面
     */
    @RequestMapping("/c_toimportantmatter")
    public ModelAndView toImportantMatter() {
        return new ModelAndView("affairlist/iimportant");
    }

    /**
     * 分页查询律师函信息
     *
     * @param pageHelper  分页帮助类
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 律师函信息
     */
    @RequestMapping("/c_querylawyerletter")
    @ResponseBody
    public BaseResponse queryLawyerLetter(HttpServletRequest request, PageHelper<LawyerLetter> pageHelper, String lawyerName, String companyName) {
        return BaseResponse.build(lawyerLetterService.queryLawyerLetter(pageHelper, companyName, lawyerName, getCompanyId(request), getUserId(request), 0));
    }

    /**
     * 分页查询修改文书
     *
     * @param pageHelper 分页帮助类
     * @return 返回修改文书集合
     */
    @RequestMapping("/c_querymodifydoc")
    @ResponseBody
    public BaseResponse queryModifyDoc(HttpServletRequest request, PageHelper<ModifyDoc> pageHelper, String lawyerName, String companyName) {
        return BaseResponse.build(modifyDocService.queryModifyDoc(pageHelper, lawyerName, companyName, getCompanyId(request), getUserId(request), 0));
    }

    /**
     * 分页查询草拟文书
     *
     * @param pageHelper 分页帮助类
     * @return 返回修改文书集合
     */
    @RequestMapping("/c_querydraftdoc")
    @ResponseBody
    public BaseResponse queryDraftDoc(HttpServletRequest request, PageHelper<DraftDoc> pageHelper, String lawyerName, String companyName) {
        return BaseResponse.build(draftDocService.queryDraftDoc(pageHelper, lawyerName, companyName, getCompanyId(request), getUserId(request), 0));
    }

    /**
     * 分页查询电话咨询
     *
     * @param pageHelper  分页帮助类
     * @param type        类型 1 公司事务 2公司纠纷 3 公司重大事项
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 电话咨询集合
     */
    @RequestMapping("/c_querytelconsultation")
    @ResponseBody
    public BaseResponse queryTelConsultation(HttpServletRequest request, PageHelper<TelConsultation> pageHelper, String type, String lawyerName, String companyName) {
        return BaseResponse.build(telConsultationService.queryTelConsultation(pageHelper, type, lawyerName, companyName, getCompanyId(request), getUserId(request), 0));
    }

    /**
     * 分页查询预约会面
     *
     * @param meetType    1 指定律师 2 没有指定律师
     * @param type        类型 1 公司事务 2公司纠纷 3 公司重大事项
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 预约会面集合
     */
    @RequestMapping("/c_queryappointmeet")
    @ResponseBody
    public BaseResponse queryAppointMeet(HttpServletRequest request, PageHelper<AppointMeet> pageHelper, String meetType, String type, String lawyerName, String companyName) {
        return BaseResponse.build(appointMeetService.queryTelConsultation(pageHelper, meetType, type, lawyerName, companyName, getCompanyId(request), getUserId(request), 0));
    }

    /**
     * 分页查询纠纷服务
     *
     * @param pageHelper  分页帮助类
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 纠纷服务集合
     */
    @RequestMapping("/c_querydisputes")
    @ResponseBody
    public BaseResponse queryDisputes(HttpServletRequest request, PageHelper<Disputes> pageHelper, String lawyerName, String companyName) {
        return BaseResponse.build(disputesService.queryDisputes(pageHelper, lawyerName, companyName, getCompanyId(request), getUserId(request), 0));
    }

    /**
     * 分页查询重大事项
     *
     * @param pageHelper  分页帮助类
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 重大事项集合
     */
    @RequestMapping("/c_queryimportantmatter")
    @ResponseBody
    public BaseResponse queryImportantMatter(HttpServletRequest request, PageHelper<ImportantMatter> pageHelper, String lawyerName, String companyName) {
        return BaseResponse.build(importantMatterService.queryImportantMatter(pageHelper, lawyerName, companyName, getCompanyId(request), getUserId(request), 0));
    }

    private long getCompanyId(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getCompanyIdFromSession(request);
    }

    private long getUserId(HttpServletRequest request) {
        return "0".equals(CompanyLoginUtils.getInstance().getUserFromSession(request).getType()) ? 0 : CompanyLoginUtils.getInstance().getUserIdFromSession(request);
    }
}
