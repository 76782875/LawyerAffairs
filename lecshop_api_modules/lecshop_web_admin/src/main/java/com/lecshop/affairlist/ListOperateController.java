package com.lecshop.affairlist;

import com.lecshop.total.affairlist.bean.DraftDocDetail;
import com.lecshop.total.affairlist.bean.LawyerLetterDetail;
import com.lecshop.total.affairlist.bean.ModifyDocDetail;
import com.lecshop.total.affairlist.service.*;
import com.lecshop.utils.FileUpAndDownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 事务列表操作控制器
 *
 * @author sunluyang on 2017/8/9.
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
     * 注入纠纷service
     */
    @Autowired
    private DisputesService disputesService;
    /**
     * 注入重大事项
     */
    @Autowired
    private ImportantMatterService importantMatterService;
    /**
     * 自动注入律师函详情service
     */
    @Autowired
    private LawyerLetterDetailService lawyerLetterDetailService;
    /**
     * 自动注入草拟文书详情service
     */
    @Autowired
    private DraftDocDetailService draftDocDetailService;

    /**
     * 自动注入电话咨询service
     */
    @Autowired
    private TelConsultationService telConsultationService;

    /**
     * 根据文书id查询文书事务交流详情
     *
     * @param docId 文书id
     * @return 文书事务交流详情
     */
    @RequestMapping("/queryModifyDocDetailById")
    @ResponseBody
    public List<ModifyDocDetail> queryModifyDocDetailById(long docId) {
        return modifyDocDetailService.queryModifyDocDetailById(docId);
    }

    /**
     * 根据律师函id查询律师函交流详情
     *
     * @param letterId 律师函id
     * @return 律师函交流详情
     */
    @RequestMapping("/querylawyerletterdetailbyletterid")
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
    @RequestMapping("/querydraftdocdetailbydocid")
    @ResponseBody
    public List<DraftDocDetail> queryDraftDocDetailByDocId(long docId) {
        return draftDocDetailService.queryDraftDocDetailByDocId(docId);
    }

    /**
     * 确认律师函
     *
     * @param letterId 律师函id
     * @param lawyerId 律师id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/confirmlawyerletter")
    @ResponseBody
    public int confirmLawyerLetter(long letterId, long lawyerId) {
        return lawyerLetterService.confirmLawyerLetter(letterId, "3", lawyerId);
    }

    /**
     * 确认草拟文书
     *
     * @param docId    草拟文书id
     * @param lawyerId 律师id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/confirmdraftdoc")
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
    @RequestMapping("/confirmmodifydoc")
    @ResponseBody
    public int confirmModifyDoc(long docId, long lawyerId) {
        return modifyDocService.confirmModifyDoc(docId, "3", lawyerId);
    }


    /**
     * 确认预约会面
     *
     * @param meetId   预约会面id
     * @param lawyerId 律师id
     * @param meetType 1 指定律师 2 没有指定律师
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/confirmappointmeetting")
    @ResponseBody
    public int confirmAppointmentMeetting(long meetId, long lawyerId, String meetType) {
        return appointMeetService.confirmAppointMeet(meetId, "5", lawyerId, meetType);
    }

    /**
     * 确认纠纷服务
     *
     * @param id 纠纷服务id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/confirmdisputes")
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
    @RequestMapping("/confirmimportantmatter")
    @ResponseBody
    public int confirmImportantMatter(long id) {
        return importantMatterService.changeImportantMatterStatus(id, "5");
    }

    /**
     * 确认电话咨询
     *
     * @param telId    电话咨询id
     * @param lawyerId 律师id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/confirmtelconsultation")
    @ResponseBody
    public int confirmTelConsultation(long telId, long lawyerId) {
        return telConsultationService.confirmTelConsultation(telId, "2", lawyerId);
    }

    /**
     * 跳转到纠纷详情页面
     *
     * @param disputeId 纠纷id
     * @return 纠纷详情页面
     */
    @RequestMapping("/todisputesdetail")
    public ModelAndView toDisputesDetail(long disputeId) {
        return new ModelAndView("affairlist/disputesdetail").addObject("disputeId", disputeId);
    }

    /**
     * 文书事务-下载
     *
     * @param name 模板名称
     */
    @RequestMapping("/docdownload")
    public void docDownload(HttpServletResponse response, String name) {
        FileUpAndDownUtils.fileDownloadCommon(response, "upload.properties", name, this);
    }
}
