package com.lecshop.company.affairlist;

import com.lecshop.total.affairlist.bean.*;
import com.lecshop.total.affairlist.service.*;
import com.lecshop.utils.FileUpAndDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 事务列表操作控制器
 *
 * @author sunluyang on 2017/8/7.
 */
@Controller
public class ListOperateController {
    /**
     * 注入修改文书事务service
     */
    @Autowired
    private ModifyDocService modifyDocService;
    /**
     * 注入修改文书事务交流详情service
     */
    @Autowired
    private ModifyDocDetailService modifyDocDetailService;
    /**
     * 注入草拟文书service
     */
    @Autowired
    private DraftDocService draftDocService;
    /**
     * 自动注入草拟文书详情service
     */
    @Autowired
    private DraftDocDetailService draftDocDetailService;
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
     * 自动注入律师函详情service
     */
    @Autowired
    private LawyerLetterDetailService lawyerLetterDetailService;
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
     * 注入纠纷细节service
     */
    @Autowired
    private DisputeDetailService disputeDetailService;
    /**
     * 注入重大事项service
     */
    @Autowired
    private ImportantMatterDetailService importantMatterDetailService;

    /**
     * 根据文书id查询文书事务交流详情
     *
     * @param docId 文书id
     * @return 文书事务交流详情
     */
    @RequestMapping("/c_queryModifyDocDetailById")
    @ResponseBody
    public List<ModifyDocDetail> queryModifyDocDetailById(long docId) {
        return modifyDocDetailService.queryModifyDocDetailById(docId);
    }

    /**
     * 根据律师函id查询交流详情
     *
     * @param letterId 律师函id
     * @return 交流详情
     */
    @RequestMapping("/c_querylawyerletterdetailbyid")
    @ResponseBody
    public List<LawyerLetterDetail> queryLawyerLetterDetailById(long letterId) {
        return lawyerLetterDetailService.queryLawyerLetterDetails(letterId);
    }

    /**
     * 根据草拟文书id查询草拟文书详情
     *
     * @param docId 草拟文书id
     * @return 草拟文书详情
     */
    @RequestMapping("/c_querydraftdocdetailbydocid")
    @ResponseBody
    public List<DraftDocDetail> queryDraftDocDetailByDocId(long docId) {
        return draftDocDetailService.queryDraftDocDetailByDocId(docId);
    }

    /**
     * 确认草拟文书
     *
     * @param docId    草拟文书id
     * @param lawyerId 律师id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_confirmdraftdoc")
    @ResponseBody
    public int confirmDraftDoc(long docId, long lawyerId) {
        return draftDocService.confirmDraftDoc(docId, "3", lawyerId);
    }

    /**
     * 确认修改文书
     *
     * @param docId    文书id
     * @param lawyerId 律师id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_confirmmodifydoc")
    @ResponseBody
    public int confirmModifyDoc(long docId, long lawyerId) {
        return modifyDocService.confirmModifyDoc(docId, "3", lawyerId);
    }

    /**
     * 确认律师函
     *
     * @param letterId 律师函id
     * @param lawyerId 律师id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_confirmlawyerletter")
    @ResponseBody
    public int confirmLawyerLetter(long letterId, long lawyerId) {
        return lawyerLetterService.confirmLawyerLetter(letterId, "3", lawyerId);
    }

    /**
     * 为律师函评分
     *
     * @param letterId 律师函id
     * @param score    分数
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_gradeforlawyerletter")
    @ResponseBody
    public int gradeForLawyerLetter(long letterId, int score) {
        return lawyerLetterService.gradeForLawyerLetter(letterId, score);
    }

    /**
     * 为草拟文书评分
     *
     * @param docId 草拟文书id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_gradefordraftdoc")
    @ResponseBody
    public int gradeForDraftDoc(long docId, int score) {
        return draftDocService.gradeForDraftDoc(docId, score);
    }

    /**
     * 为修改文书评分
     *
     * @param docId 文书id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_gradeformodifydoc")
    @ResponseBody
    public int gradeForModifyDoc(long docId, int score) {
        return modifyDocService.gradeForModifyDoc(docId, score);
    }

    /**
     * 为电话咨询评分
     *
     * @param id    电话咨询id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_gradefortelconsultation")
    @ResponseBody
    public int gradeForTelConsultation(long id, int score) {
        return telConsultationService.gradeForTelConsultation(id, score);
    }

    /**
     * 律师函交流
     *
     * @param file           文件
     * @param lawyerLetterId 律师函id
     * @param desc           描述
     * @return 成功返回1，失败返回0，文件上传失败返回-1
     */
    @RequestMapping("/c_lawyerlettercommunion")
    @ResponseBody
    public int lawyerLetterCommunion(@RequestParam(value = "fileUpload", required = false) CommonsMultipartFile file, long lawyerLetterId, String desc) {
        return lawyerLetterDetailService.lawyerLetterCommunion(file, lawyerLetterId, desc);
    }

    /**
     * 草拟文书交流
     *
     * @param file  文件
     * @param docId 草拟文书id
     * @param desc  描述
     * @return 成功返回1，失败返回0，文件上传失败返回-1
     */
    @RequestMapping("/c_draftdoccommunion")
    @ResponseBody
    public int draftDocCommunion(@RequestParam(value = "fileUpload", required = false) CommonsMultipartFile file, long docId, String desc) {
        return draftDocDetailService.draftDocCommunion(file, docId, desc);
    }

    /**
     * 修改文书交流
     *
     * @param file  文件
     * @param docId 草拟文书id
     * @param desc  描述
     * @return 成功返回1，失败返回0，文件上传失败返回-1
     */
    @RequestMapping("/c_modifydoccommunion")
    @ResponseBody
    public int modifyDocCommunion(@RequestParam(value = "fileUpload", required = false) CommonsMultipartFile file, long docId, String desc) {
        return modifyDocDetailService.modifyDocCommunion(file, docId, desc);
    }

    /**
     * 确认预约会面
     *
     * @param meetId   会面id
     * @param lawyerId 律师id
     * @param meetType 1 指定律师 2 没有指定律师
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_confirmappointmeetting")
    @ResponseBody
    public int confirmAppointMeet(long meetId, long lawyerId, String meetType) {
        return appointMeetService.confirmAppointMeet(meetId, "5", lawyerId, meetType);
    }

    /**
     * 确认纠纷服务
     *
     * @param id 纠纷服务id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_confirmdisputes")
    @ResponseBody
    public int confirmDisputes(long id) {
        return disputesService.changeDisputesStatus(id, "5");
    }

    /**
     * 确认重大事项
     *
     * @param id 重大事项id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_confirmimportantmatter")
    @ResponseBody
    public int confirmImportant(long id) {
        return importantMatterService.changeImportantMatterStatus(id, "5");
    }

    /**
     * 确认电话咨询
     *
     * @param telId    电话咨询id
     * @param lawyerId 律师id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_confirmtelconsultation")
    @ResponseBody
    public int confirmTelConsultation(long telId, long lawyerId) {
        return telConsultationService.confirmTelConsultation(telId, "2", lawyerId);
    }

    /**
     * 为预约会面评分
     *
     * @param id    会面id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_gradeforappointmeeting")
    @ResponseBody
    public int gradeForAppointMeeting(long id, int score) {
        return appointMeetService.gradeForMeeting(id, score);
    }

    /**
     * 为纠纷服务评分
     *
     * @param id    纠纷服务id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_gradefordisputes")
    @ResponseBody
    public int gradeForDisputes(long id, int score) {
        return disputesService.gradeForDisputes(id, score);
    }

    /**
     * 为重大事项评分
     *
     * @param id    重大事项id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_gradeforimportantmatter")
    @ResponseBody
    public int gradeForImportantMatter(long id, int score) {
        return importantMatterService.gradeForImportantMatter(id, score);
    }

    /**
     * 跳转到纠纷详情页面
     *
     * @param disputeId 纠纷id
     * @return 纠纷详情页面
     */
    @RequestMapping("/c_todisputesdetail")
    public ModelAndView toDisputesDetail(long disputeId) {
        return new ModelAndView("affairlist/disputesdetail").addObject("disputeId", disputeId);
    }

    /**
     * 根据纠纷id查询纠纷详情
     *
     * @param disputesId 纠纷id
     * @return 纠纷详情
     */
    @RequestMapping("c_querydisputesdetails")
    @ResponseBody
    public List<DisputeDetail> queryDisputesDetails(long disputesId) {
        return disputeDetailService.queryByDisputeId(disputesId);
    }

    /**
     * 纠纷详情页面的下载
     *
     * @param name 下载文件的名称
     */
    @RequestMapping("/c_disputesdetaildownload")
    public void disputesDetailDownload(HttpServletResponse response, String name) {
        FileUpAndDownUtils.fileDownloadCommon(response, "upload.properties", name, this);
    }

    /**
     * 纠纷详情页面的上传
     *
     * @param file 文件
     * @return 添加返回码
     */
    @RequestMapping("/c_disputesdetailupload")
    @ResponseBody
    public int disputesDetailUpload(@RequestParam("fileUpload") CommonsMultipartFile file, DisputeDetail disputeDetail) {
        if (FileUpAndDownUtils.fileUploadCommon(file, "upload.properties", this) == 1) {
            return disputeDetailService.updateDisputeDetail(disputeDetail.buildDisputesForCompanyUpdate(file.getOriginalFilename()));
        }
        return -1;
    }

    /**
     * 重大事项详情页修改
     *
     * @param file 文件
     * @return 添加返回码
     */
    @RequestMapping("/c_importantmatterdetaildesc")
    @ResponseBody
    public int importantMatterDetailDesc(@RequestParam("fileUpload") CommonsMultipartFile file, ImportantMatterDetail importantMatterDetail) {
        if (!StringUtils.isEmpty(file.getOriginalFilename())) {
            FileUpAndDownUtils.fileUploadCommon(file, "upload.properties", this);
            importantMatterDetail.setUrl(file.getOriginalFilename());
        }
        return importantMatterDetailService.updateImportantMatterDetail(importantMatterDetail);

    }

    /**
     * 预约会面取消订单
     *
     * @param id 预约会面id
     * @return 取消返回码
     */
    @RequestMapping("/c_cancelappointmeet")
    @ResponseBody
    public int cancelAppointMeet(long id) {
        return appointMeetService.cancelAppointMeet(id);
    }

    /**
     * 文书事务-下载
     *
     * @param name 模板名称
     */
    @RequestMapping("/c_docdownload")
    public void docDownload(HttpServletResponse response, String name) {
        FileUpAndDownUtils.fileDownloadCommon(response, "upload.properties", name, this);
    }

    /**
     * 跳转到重大事项详情页面
     */
    @RequestMapping("/c_toimportantmatterdetail")
    @ResponseBody
    public ModelAndView toImportantMatterDetail(long id) {
        return new ModelAndView("affairlist/importantmatterdetail").addObject("id", id);
    }

    /**
     * 查询重大事项详情
     *
     * @param matterId 重大事项id
     * @return 返回重大事项详情
     */
    @RequestMapping("/c_queryimportantmatterdetails")
    @ResponseBody
    public List<ImportantMatterDetail> queryImportantMatterDetails(long matterId) {
        return importantMatterDetailService.queryImportantMatterDetails(matterId);
    }
}
