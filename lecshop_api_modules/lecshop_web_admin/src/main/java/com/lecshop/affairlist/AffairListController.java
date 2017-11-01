package com.lecshop.affairlist;

import com.lecshop.total.affairlist.bean.*;
import com.lecshop.total.affairlist.service.*;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 事务列表控制器
 *
 * @author sunluyang on 2017/7/11.
 */
@Controller
public class AffairListController {
    /**
     * 注入草拟文书service
     */
    @Autowired
    private DraftDocService draftDocService;
    /**
     * 注入纠纷service
     */
    @Autowired
    private DisputesService disputesService;
    /**
     * 注入修改文书事务service
     */
    @Autowired
    private ModifyDocService modifyDocService;
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
     * 注入重大事项
     */
    @Autowired
    private ImportantMatterService importantMatterService;
    /**
     * 注入电话咨询service
     */
    @Autowired
    private TelConsultationService telConsultationService;

    /**
     * 跳转到律师函页面
     *
     * @return 律师函页面
     */
    @RequestMapping("/tolawyerletter")
    public ModelAndView toLawyerLetter() {
        return new ModelAndView("affairlist/lawyerletter");
    }

    /**
     * 跳转到修改和草拟文书页面
     *
     * @return 修改和草拟文书页面
     */
    @RequestMapping("/tomodifyanddraftdoc")
    public ModelAndView toModifyAndDraftDoc(String url) {
        return new ModelAndView("affairlist/modifyanddraftdoc").addObject("url", url);
    }

    /**
     * 跳转到公司事务-电话咨询页面
     *
     * @return 纠纷事务电话咨询页面
     */
    @RequestMapping("/tocompanytelconsultation")
    public ModelAndView toCompanyTelConsultation() {
        return new ModelAndView("affairlist/ctelconsultation");
    }

    /**
     * 跳转到公司事务-预约会面页面
     *
     * @return 预约会面页面
     */
    @RequestMapping("/tocompanyappointmeet")
    public ModelAndView toCompanyAppointMeet(String meetType) {
        return new ModelAndView("affairlist/cappointmeet").addObject("meetType", meetType);
    }

    /**
     * 跳转到纠纷事务-电话咨询页面
     *
     * @return 电话咨询页面
     */
    @RequestMapping("/todisputestelconsultation")
    public ModelAndView toDisputesTelConsultation() {
        return new ModelAndView("affairlist/dtelconsultation");
    }

    /**
     * 跳转到纠纷事务-预约会面页面
     *
     * @return 预约会面页面
     */
    @RequestMapping("/todisputesappointmeet")
    public ModelAndView toDisputesAppointMeet() {
        return new ModelAndView("affairlist/dappointmeet");
    }

    /**
     * 跳转到纠纷事务-纠纷服务页面
     *
     * @return 纠纷服务页面
     */
    @RequestMapping("/todisputesservice")
    public ModelAndView toDisputesService() {
        return new ModelAndView("affairlist/ddisputes");
    }

    /**
     * 跳转到重大事项-电话咨询页面
     *
     * @return 电话咨询页面
     */
    @RequestMapping("/toimportanttelconsultation")
    public ModelAndView toImportantTelConsultation() {
        return new ModelAndView("affairlist/itelconsultation");
    }

    /**
     * 跳转到重大事项-预约会面页面
     *
     * @return 预约会面页面
     */
    @RequestMapping("/toimportantappointmeet")
    public ModelAndView toImportantAppointMeet() {
        return new ModelAndView("affairlist/iappointmeet");
    }

    /**
     * 跳转到重大事项-重大事项页面
     *
     * @return 重大事项页面
     */
    @RequestMapping("/toimportantmatter")
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
    @RequestMapping("/querylawyerletter")
    @ResponseBody
    public BaseResponse queryLawyerLetter(PageHelper<LawyerLetter> pageHelper, String lawyerName, String companyName) {
        return BaseResponse.build(lawyerLetterService.queryLawyerLetter(pageHelper, companyName, lawyerName, 0, 0, 0));
    }

    /**
     * 分页查询修改文书
     *
     * @param pageHelper 分页帮助类
     * @return 返回修改文书集合
     */
    @RequestMapping("/querymodifydoc")
    @ResponseBody
    public BaseResponse queryModifyDoc(PageHelper<ModifyDoc> pageHelper, String lawyerName, String companyName) {
        return BaseResponse.build(modifyDocService.queryModifyDoc(pageHelper, lawyerName, companyName, 0, 0, 0));
    }

    /**
     * 分页查询草拟文书
     *
     * @param pageHelper 分页帮助类
     * @return 返回修改文书集合
     */
    @RequestMapping("/querydraftdoc")
    @ResponseBody
    public BaseResponse queryDraftDoc(PageHelper<DraftDoc> pageHelper, String lawyerName, String companyName) {
        return BaseResponse.build(draftDocService.queryDraftDoc(pageHelper, lawyerName, companyName, 0, 0, 0));
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
    @RequestMapping("/querytelconsultation")
    @ResponseBody
    public BaseResponse queryTelConsultation(PageHelper<TelConsultation> pageHelper, String type, String lawyerName, String companyName) {
        return BaseResponse.build(telConsultationService.queryTelConsultation(pageHelper, type, lawyerName, companyName, 0, 0, 0));
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
    @RequestMapping("/queryappointmeet")
    @ResponseBody
    public BaseResponse queryAppointMeet(PageHelper<AppointMeet> pageHelper, String meetType, String type, String lawyerName, String companyName) {
        return BaseResponse.build(appointMeetService.queryTelConsultation(pageHelper, meetType, type, lawyerName, companyName, 0, 0, 0));
    }

    /**
     * 分页查询纠纷服务
     *
     * @param pageHelper  分页帮助类
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 纠纷服务集合
     */
    @RequestMapping("/querydisputes")
    @ResponseBody
    public BaseResponse queryDisputes(PageHelper<Disputes> pageHelper, String lawyerName, String companyName) {
        return BaseResponse.build(disputesService.queryDisputes(pageHelper, lawyerName, companyName, 0, 0, 0));
    }

    /**
     * 分页查询重大事项
     *
     * @param pageHelper  分页帮助类
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 重大事项集合
     */
    @RequestMapping("/queryimportantmatter")
    @ResponseBody
    public BaseResponse queryImportantMatter(PageHelper<ImportantMatter> pageHelper, String lawyerName, String companyName) {
        return BaseResponse.build(importantMatterService.queryImportantMatter(pageHelper, lawyerName, companyName, 0, 0, 0));
    }

}
