package com.lecshop.affairs;

import com.lecshop.total.affairlist.bean.*;
import com.lecshop.total.affairlist.service.*;
import com.lecshop.util.LoginUtils;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by dujinkai on 17/7/31.
 * 事务控制器
 */
@Controller
public class BusinessAffairsController {

    /**
     * 注入律师函服务接口
     */
    @Autowired
    private LawyerLetterService lawyerLetterService;

    /**
     * 注入律师涵详情接口
     */
    @Autowired
    private LawyerLetterDetailService lawyerLetterDetailService;

    /**
     * 注入电话咨询服务接口
     */
    @Autowired
    private TelConsultationService telConsultationService;

    /**
     * 注入修改文书服务接口
     */
    @Autowired
    private ModifyDocService modifyDocService;

    /**
     * 注入修改文书详情接口
     */
    @Autowired
    private ModifyDocDetailService modifyDocDetailService;

    /**
     * 注入草拟文书服务接口
     */
    @Autowired
    private DraftDocService draftDocService;

    /**
     * 注入草拟文书详情服务接口
     */
    @Autowired
    private DraftDocDetailService draftDocDetailService;

    /**
     * 预约会面服务接口
     */
    @Autowired
    private AppointMeetService appointMeetService;

    /**
     * 注入纠纷委托服务接口
     */
    @Autowired
    private DisputesService disputesService;

    /**
     * 注入纠结详情服务接口
     */
    @Autowired
    private DisputeDetailService disputeDetailService;

    /**
     * 注入重大事项详情服务接口
     */
    @Autowired
    private ImportantMatterDetailService importantMatterDetailService;

    /**
     * 注入重大事项服务接口
     */
    @Autowired
    private ImportantMatterService importantMatterService;

    /**
     * 跳转到事务页面
     *
     * @return 返回事务页面
     */
    @RequestMapping("/tobusinessaffairs")
    public ModelAndView toBusinessAffairs() {
        return new ModelAndView("affairs/affairsmenu");
    }


    /**
     * 跳转到律师函页面
     *
     * @return 返回律师函页面
     */
    @RequestMapping("/tolawyerletter")
    public ModelAndView toLawyerLetter() {
        return new ModelAndView("affairs/lawyerletter");
    }

    /**
     * 查询律师涵信息
     *
     * @param pageHelper 分页帮助类
     * @return 返回律师涵信息
     */
    @RequestMapping("/querylawyerletters")
    @ResponseBody
    public BaseResponse queryLawyerLetters(HttpServletRequest request, PageHelper<LawyerLetter> pageHelper) {
        return BaseResponse.build(lawyerLetterService.queryLawyerLetter(pageHelper, "", "", 0, 0, LoginUtils.getInstance().getLawyerId(request)));
    }


    /**
     * 跳转到律师函详情页面
     *
     * @return 返回律师函详情页面
     */
    @RequestMapping("/tolawyerletterdetails")
    public ModelAndView toLawyerLetterDetails(long letterId) {
        return new ModelAndView("affairs/lawyerletterdetail").addObject("letterId", letterId);
    }

    /**
     * 查询律师函详情
     *
     * @param letterId 律师涵id
     * @return 返回律师涵详情
     */
    @RequestMapping("/querylawyerletterdetails")
    @ResponseBody
    public List<LawyerLetterDetail> queryLawyerLetterDetails(long letterId) {
        return lawyerLetterDetailService.queryLawyerLetterDetails(letterId);
    }

    /**
     * 跳转到上传律师函页面
     *
     * @param letterId 律师函id
     * @return 返回上传律师函页面
     */
    @RequestMapping("/touploadlawyerletter")
    public ModelAndView toUploadLawyerLetter(long letterId) {
        return new ModelAndView("affairs/uploadlawyerletter").addObject("letterId", letterId);
    }

    /**
     * 添加律师涵详情
     *
     * @param lawyerLetterDetail 律师涵详情
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/addlawyerletterdetail")
    @ResponseBody
    public int addLawyerLetterDetail(LawyerLetterDetail lawyerLetterDetail) {
        lawyerLetterDetail.setType("2");
        lawyerLetterDetailService.addLawyerLetterDetail(lawyerLetterDetail);
        return lawyerLetterService.changeLawyerLetterStatus(lawyerLetterDetail.getLetterId(), "2");
    }

    /**
     * 律师确认接受律师涵
     *
     * @param letterId 律师涵id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/confirmletter")
    @ResponseBody
    public int confirmLetter(long letterId) {
        return lawyerLetterService.changeLawyerLetterStatus(letterId, "1");
    }

    /**
     * 跳转到公司电话咨询
     *
     * @return 返回公司电话咨询页面
     */
    @RequestMapping("/tocompanytel")
    public ModelAndView toCompanyTel() {
        return new ModelAndView("affairs/companytel");
    }

    /**
     * 查询公司电话咨询
     *
     * @param pageHelper 分页帮助类
     * @return 返回公司电话咨询页面
     */
    @RequestMapping("/querycompanytels")
    @ResponseBody
    public BaseResponse queryCompanyTels(PageHelper<TelConsultation> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(telConsultationService.queryTelConsultation(pageHelper, "1", "", "", 0, 0, LoginUtils.getInstance().getLawyerId(request)));
    }


    /**
     * 跳转到公司纠纷电话咨询
     *
     * @return 返回公司纠纷电话咨询页面
     */
    @RequestMapping("/todisputetel")
    public ModelAndView toDisputeTel() {
        return new ModelAndView("affairs/disputetel");
    }

    /**
     * 查询公司纠纷电话咨询
     *
     * @param pageHelper 分页帮助类
     * @return 返回公司纠纷电话咨询页面
     */
    @RequestMapping("/querydisputetels")
    @ResponseBody
    public BaseResponse queryDisputeTels(PageHelper<TelConsultation> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(telConsultationService.queryTelConsultation(pageHelper, "2", "", "", 0, 0, LoginUtils.getInstance().getLawyerId(request)));
    }


    /**
     * 跳转到公司重大事项电话咨询
     *
     * @return 返回公司重大事项电话咨询页面
     */
    @RequestMapping("/toeventtel")
    public ModelAndView toEventTel() {
        return new ModelAndView("affairs/eventtel");
    }

    /**
     * 查询公司重大事项电话咨询
     *
     * @param pageHelper 分页帮助类
     * @return 返回公司重大事项电话咨询页面
     */
    @RequestMapping("/queryeventtels")
    @ResponseBody
    public BaseResponse queryEventTels(PageHelper<TelConsultation> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(telConsultationService.queryTelConsultation(pageHelper, "3", "", "", 0, 0, LoginUtils.getInstance().getLawyerId(request)));
    }

    /**
     * 跳转到修改文书页面
     *
     * @return 返回修改文书页面
     */
    @RequestMapping("/tomodifydoc")
    public ModelAndView toModifyDoc() {
        return new ModelAndView("affairs/modifydoc");
    }

    /**
     * 查询修改文书列表
     *
     * @param pageHelper 分页帮助类
     * @return 返回修改文书列表
     */
    @RequestMapping("/querymodifydocs")
    @ResponseBody
    public BaseResponse queryModifyDocs(PageHelper<ModifyDoc> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(modifyDocService.queryModifyDoc(pageHelper, "", "", 0, 0, LoginUtils.getInstance().getLawyerId(request)));
    }

    /**
     * 跳转到文书详情
     *
     * @param docId 文书id
     * @return 返回文书详情
     */
    @RequestMapping("/tomodifydocdetails")
    public ModelAndView toModifyDocDetails(long docId) {
        return new ModelAndView("affairs/modifydocdetail").addObject("docId", docId);
    }

    /**
     * 查询文书详情
     *
     * @param docId 文书id
     * @return 返回文书详细记录
     */
    @RequestMapping("/querymodifydocdetails")
    @ResponseBody
    public List<ModifyDocDetail> queryModifyDocDetails(long docId) {
        return modifyDocDetailService.queryModifyDocDetailById(docId);
    }

    /**
     * 修改文书状态
     *
     * @param docId 文书id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/updatemodifydocstatus")
    @ResponseBody
    public int updateModifyDocStatus(long docId) {
        return modifyDocService.changeModifyDocStatus(docId, "1");
    }

    /**
     * 跳转到上传文书页面
     *
     * @param docId 文书id
     * @return 返回上传律师函页面
     */
    @RequestMapping("/touploadmodifydoc")
    public ModelAndView toUploadModifyDoc(long docId) {
        return new ModelAndView("affairs/uploadmodifydoc").addObject("docId", docId);
    }

    /**
     * 添加修改文书详情
     *
     * @param modifyDocDetail 修改文件详情
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/addmodifydocdetail")
    @ResponseBody
    public int addModifyDocDetail(ModifyDocDetail modifyDocDetail) {
        modifyDocDetail.setType("2");
        modifyDocDetailService.addModifyDocDetail(modifyDocDetail);
        return modifyDocService.changeModifyDocStatus(modifyDocDetail.getDocId(), "2");
    }

    /**
     * 跳转到草拟文书页面
     *
     * @return 返回草拟文书页面
     */
    @RequestMapping("/todraftdoc")
    public ModelAndView toDraftDoc() {
        return new ModelAndView("affairs/draftdoc");
    }

    /**
     * 查询草拟文书列表
     *
     * @param pageHelper 分页帮助类
     * @return 返回草拟文书列表
     */
    @RequestMapping("/querydraftdocs")
    @ResponseBody
    public BaseResponse queryDraftDocs(PageHelper<DraftDoc> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(draftDocService.queryDraftDoc(pageHelper, "", "", 0, 0, LoginUtils.getInstance().getLawyerId(request)));
    }

    /**
     * 确认草拟文书
     *
     * @param docId 文书id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/confirmdraftdoc")
    @ResponseBody
    public int confirmDraftDoc(long docId) {
        return draftDocService.updateDraftDocStatus(docId, "1");
    }

    /**
     * 跳转到草拟文书详情
     *
     * @param docId 文书id
     * @return 返回草拟文书详情页
     */
    @RequestMapping("/todraftdocdetails")
    public ModelAndView toDraftDocDetails(long docId) {
        return new ModelAndView("affairs/draftdocdetail").addObject("docId", docId);
    }

    /**
     * 查询草拟文书详情
     *
     * @param docId 文书id
     * @return 返回草拟文书详情
     */
    @RequestMapping("/querydraftdocdetails")
    @ResponseBody
    public List<DraftDocDetail> queryDraftDocDetails(long docId) {
        return draftDocDetailService.queryDraftDocDetailByDocId(docId);
    }


    /**
     * 跳转到草拟文书上传文书页面
     *
     * @param docId 文书id
     * @return 返回上传页面
     */
    @RequestMapping("/touploaddraftdoc")
    public ModelAndView toUploadDraftDoc(long docId) {
        return new ModelAndView("affairs/uploaddraftdoc").addObject("docId", docId);
    }

    /**
     * 新增草拟文书详情
     *
     * @param draftDocDetail 草拟文书详情
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/adddaraftdetail")
    @ResponseBody
    public int addDaraftDetail(DraftDocDetail draftDocDetail) {
        draftDocDetail.setType("2");
        draftDocDetailService.addDraftDocDetail(draftDocDetail);
        return draftDocService.updateDraftDocStatus(draftDocDetail.getDocId(), "2");
    }

    /**
     * 跳转到公司预约会面页面
     *
     * @return 返回公司预约会面页面
     */
    @RequestMapping("/tocompanymeet")
    public ModelAndView toCompanyMeet() {
        return new ModelAndView("affairs/companymeet");
    }


    /**
     * 查询公司预约会面
     *
     * @param pageHelper 分页帮助类
     * @return 返回公司预约会面
     */
    @RequestMapping("/querycompanymeets")
    @ResponseBody
    public BaseResponse queryCompanyMeets(PageHelper<AppointMeet> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(appointMeetService.queryTelConsultation(pageHelper, "1", "1", "", "", 0, 0, LoginUtils.getInstance().getLawyerId(request)));
    }

    /**
     * 确认公司预约会面
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/confirmcompanymeet")
    @ResponseBody
    public int confirmCompanyMeet(long id) {
        return appointMeetService.changeStatus(id, "2");
    }

    /**
     * 拒绝公司预约会面
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/refusecompanymeet")
    @ResponseBody
    public int refuseCompanyMeet(long id) {
        return appointMeetService.changeStatus(id, "1");
    }

    /**
     * 跳转到纠纷预约会面页面
     *
     * @return 返回纠纷预约会面页面
     */
    @RequestMapping("/todisputemeet")
    public ModelAndView toDisputeMeet() {
        return new ModelAndView("affairs/disputemeet");
    }

    /**
     * 查询公司纠纷预约会面
     *
     * @param pageHelper 分页帮助类
     * @return 返回公司纠纷预约会面
     */
    @RequestMapping("/querydisputemeets")
    @ResponseBody
    public BaseResponse queryDisputeMeets(PageHelper<AppointMeet> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(appointMeetService.queryTelConsultation(pageHelper, "1", "2", "", "", 0, 0, LoginUtils.getInstance().getLawyerId(request)));
    }

    /**
     * 确认公司纠纷预约会面
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/confirmdisputemeet")
    @ResponseBody
    public int confirmDisputeMeet(long id) {
        return appointMeetService.changeStatus(id, "2");
    }

    /**
     * 拒绝公司纠纷预约会面
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/refusedisputemeet")
    @ResponseBody
    public int refuseDisputeMeet(long id) {
        return appointMeetService.changeStatus(id, "1");
    }


    /**
     * 跳转到重大事项预约会面页面
     *
     * @return 返回重大事项预约会面页面
     */
    @RequestMapping("/toeventmeet")
    public ModelAndView toEventMeet() {
        return new ModelAndView("affairs/eventmeet");
    }


    /**
     * 查询公司重大事项预约会面
     *
     * @param pageHelper 分页帮助类
     * @return 返回公司重大事项预约会面
     */
    @RequestMapping("/queryeventmeets")
    @ResponseBody
    public BaseResponse queryEventMeets(PageHelper<AppointMeet> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(appointMeetService.queryTelConsultation(pageHelper, "1", "3", "", "", 0, 0, LoginUtils.getInstance().getLawyerId(request)));
    }

    /**
     * 确认公司重大事项预约会面
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/confirmeventmeet")
    @ResponseBody
    public int confirmEventMeet(long id) {
        return appointMeetService.changeStatus(id, "2");
    }

    /**
     * 拒绝公司重大事项预约会面
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/refuseeventmeet")
    @ResponseBody
    public int refuseEventMeet(long id) {
        return appointMeetService.changeStatus(id, "1");
    }


    /**
     * 跳转到公司预约会面页面(抢单)
     *
     * @return 返回公司预约会面页面(抢单)
     */
    @RequestMapping("/tograbmeet")
    public ModelAndView toGrabMeet() {
        return new ModelAndView("affairs/grabmeet");
    }


    /**
     * 查询公司预约会面(抢单)
     *
     * @param pageHelper 分页帮助类
     * @return 返回公司预约会面(抢单)
     */
    @RequestMapping("/querygrabmeets")
    @ResponseBody
    public BaseResponse queryGrabMeets(PageHelper<AppointMeet> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(appointMeetService.queryTelConsultation(pageHelper, "2", "1", "", "", 0, 0, LoginUtils.getInstance().getLawyerId(request)));
    }


    /**
     * 跳转到纠纷委托页面
     *
     * @return 返回纠纷委托页面
     */
    @RequestMapping("/todispute")
    public ModelAndView toDispute() {
        return new ModelAndView("affairs/dispute");
    }

    /**
     * 查询纠纷委托
     *
     * @param pageHelper 分页帮助类
     * @return 返回纠纷委托
     */
    @RequestMapping("/querydisputes")
    @ResponseBody
    public BaseResponse queryDisputes(PageHelper<Disputes> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(disputesService.queryDisputes(pageHelper, "", "", 0, 0, LoginUtils.getInstance().getLawyerId(request)));
    }

    /**
     * 拒绝委托纠纷
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/refusedispute")
    @ResponseBody
    public int refuseDispute(long id) {
        return disputesService.changeDisputesStatus(id, "1");
    }

    /**
     * 确认委托
     *
     * @param id    主键id
     * @param money 金额
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/confirmdispute")
    @ResponseBody
    public int confirmDispute(long id, BigDecimal money) {
        return disputesService.confirmDispute(id, money);
    }

    /**
     * 跳转到纠纷详情页
     *
     * @param disputeId 纠纷id
     * @return 返回纠纷详情
     */
    @RequestMapping("/todisputedetail")
    public ModelAndView toDisputeDetail(long disputeId) {
        return new ModelAndView("affairs/disputedetail").addObject("disputeId", disputeId);
    }

    /**
     * 跳转到编辑纠纷页面 (只有上传)
     *
     * @param disputeId 纠纷id
     * @param code      纠纷详情code
     * @return 返回编辑纠纷页面
     */
    @RequestMapping("/toeditdipusteone")
    public ModelAndView toEditDipusteOne(long disputeId, String code) {
        return new ModelAndView("affairs/editdisputeone").addObject("disputeId", disputeId).addObject("code", code);
    }


    /**
     * 跳转到编辑纠纷页面 (只有描述)
     *
     * @param disputeId 纠纷id
     * @param code      纠纷详情code
     * @param tip       提示
     * @return 返回编辑纠纷页面
     */
    @RequestMapping("/toeditdisputetwo")
    public ModelAndView toEditDisputeTwo(long disputeId, String code, String tip) {
        return new ModelAndView("affairs/editdisputetwo").addObject("disputeId", disputeId).addObject("code", code).addObject("tip", tip);
    }

    /**
     * 跳转到编辑纠纷页面 (只有时间)
     *
     * @param disputeId 纠纷id
     * @param code      纠纷详情code
     * @return 返回编辑纠纷页面
     */
    @RequestMapping("/toeditdisputethree")
    public ModelAndView toEditDisputeThree(long disputeId, String code) {
        return new ModelAndView("affairs/editdisputethree").addObject("disputeId", disputeId).addObject("code", code);
    }

    /**
     * 根据纠纷id和code查询纠纷详情
     *
     * @param disputeId 纠纷id
     * @param code      纠纷code
     * @return 返回纠纷详情
     */
    @RequestMapping("/querybydisputeidandcode")
    @ResponseBody
    public DisputeDetail queryByDisputeIdAndCode(long disputeId, String code) {
        return disputeDetailService.queryByDisputeIdAndCode(disputeId, code);
    }

    /**
     * 更新纠纷详情
     *
     * @param disputeDetail 纠纷详情
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/updatedisputedetail")
    @ResponseBody
    public int updateDisputeDetail(DisputeDetail disputeDetail) {
        return disputeDetailService.updateDisputeDetail(disputeDetail);
    }

    /**
     * 跳转到重大事项委托页面
     *
     * @return 返回重大事项委托页面
     */
    @RequestMapping("/toimportantmatter")
    public ModelAndView toImportantMatter() {
        return new ModelAndView("affairs/event");
    }


    /**
     * 查询重大事项委托
     *
     * @param pageHelper 分页帮助类
     * @return 返回重大事项委托
     */
    @RequestMapping("/queryimportantmatters")
    @ResponseBody
    public BaseResponse queryImportantMatters(PageHelper<ImportantMatter> pageHelper, HttpServletRequest request) {
        return BaseResponse.build(importantMatterService.queryImportantMatter(pageHelper, "", "", 0, 0, LoginUtils.getInstance().getLawyerId(request)));
    }

    /**
     * 跳转到重大事项编辑页面
     *
     * @param eventId 重大事项id
     * @return 返回重大事项编辑页面
     */
    @RequestMapping("/tocustomizeevent")
    public ModelAndView toCustomizeEvent(long eventId) {
        return new ModelAndView("affairs/customizeevent").addObject("eventId", eventId);
    }

    /**
     * 新增重大事项模版
     *
     * @param importantMatterDetails 重大事项模版
     * @return 返回1 成功
     */
    @RequestMapping("/addimportmattertemplates")
    @ResponseBody
    public int addImportMatterTemplates(@RequestBody List<ImportantMatterDetail> importantMatterDetails) {
        importantMatterDetailService.addImportantMatterDetailTemplates(importantMatterDetails);
        return 1;
    }

    /**
     * 律师确认重大事项委托
     *
     * @param id    主键id
     * @param money 金额
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/confirmevent")
    @ResponseBody
    public int confirmEvent(long id, BigDecimal money) {
        return importantMatterService.confirmImportantMatter(id, money);
    }

    /**
     * 律师拒绝重大事项委托
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/refuseevent")
    @ResponseBody
    public int refuseEvent(long id) {
        return importantMatterService.changeImportantMatterStatus(id, "1");
    }

    /**
     * 挑战到重大事项详情页面
     *
     * @param matterId 重大事项id
     * @return 返回重大事项详情页面
     */
    @RequestMapping("/toimportantmatterdetail")
    public ModelAndView toImportantMatterDetail(long matterId) {
        return new ModelAndView("affairs/eventdetail").addObject("matterId", matterId);
    }

    /**
     * 查询重大事项详情
     *
     * @param matterId 重大事项id
     * @return 返回重大事项详情
     */
    @RequestMapping("/queryimportantmatterdetails")
    @ResponseBody
    public List<ImportantMatterDetail> queryImportantMatterDetails(long matterId) {
        return importantMatterDetailService.queryImportantMatterDetails(matterId);
    }

    /**
     * 跳转到编辑重大事项详情页面
     *
     * @param matterId 重大事项id
     * @param detailId 详情id
     * @return 返回重大事项详情页面
     */
    @RequestMapping("/toeditmatterdetail")
    public ModelAndView toEditMatterDetail(long matterId, long detailId) {
        return new ModelAndView("affairs/editeventdetail").addObject("matterId", matterId).addObject("detailId", detailId);
    }

    /**
     * 根系重大事项详情id查询重大事项详情
     *
     * @param detailId 重大事项详情id
     * @return 返回重大事项详情
     */
    @RequestMapping("/queryimportantmatterdetailbyid")
    @ResponseBody
    public ImportantMatterDetail queryImportantMatterDetailById(long detailId) {
        return importantMatterDetailService.queryById(detailId);
    }

    /**
     * 更新重大事项详情
     *
     * @param importantMatterDetail 更新重大事项详情
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/updateimportantmatterdetail")
    @ResponseBody
    public int updateImportantMatterDetail(ImportantMatterDetail importantMatterDetail) {
        return importantMatterDetailService.updateImportantMatterDetail(importantMatterDetail);
    }

    /**
     * 设置纠纷委托状态为已完结
     *
     * @param id 主键id
     * @return 成功返回1 失败返回 0
     */
    @RequestMapping("/setdisputeend")
    @ResponseBody
    public int setDisputeEnd(long id) {
        return disputesService.changeDisputesStatus(id, "4");
    }

    /**
     * 设置重大事项已完结
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/seteventend")
    @ResponseBody
    public int setEventEnd(long id) {
        return importantMatterService.changeImportantMatterStatus(id, "5");
    }

    /**
     * 电话咨询律师催单
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/remindertelconstatus")
    @ResponseBody
    public int reminderTelConStatus(long id) {
        return telConsultationService.changeStatus(id, "1");
    }

    /**
     * 预约会面律师催单
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/remindermeet")
    @ResponseBody
    public int reminderMeet(long id) {
        return appointMeetService.changeStatus(id, "4");
    }
}
