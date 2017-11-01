package com.lecshop.company.fouraffair;

import com.lecshop.total.affairlist.service.AppointMeetService;
import com.lecshop.total.affairlist.service.ModifyDocService;
import com.lecshop.total.affairlist.service.TelConsultationService;
import com.lecshop.total.companyinfo.bean.CompanyInfo;
import com.lecshop.total.companyinfo.service.CompanyInfoService;
import com.lecshop.total.template.service.TemplateDownRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 操作权限检测实体类
 *
 * @author sunluyang on 2017/8/17.
 */
@Component
public class ClickAuthCheck {
    /**
     * 注入修改文书service
     */
    @Autowired
    private ModifyDocService modifyDocService;
    /**
     * 注入公司信息service
     */
    @Autowired
    private CompanyInfoService companyInfoService;
    /**
     * 注入预约会面service
     */
    @Autowired
    private AppointMeetService appointMeetService;
    /**
     * 注入电话咨询service
     */
    @Autowired
    private TelConsultationService telConsultationService;
    /**
     * 注入模板下载service
     */
    @Autowired
    private TemplateDownRecordService templateDownRecordService;

    public boolean checkIsAuthToClick(long companyId, String type) {
        //type 1预约咨询 2公司电话咨询 3纠纷电话咨询 4修改文书 5合同下载 6文书中的电话 7其他((草拟文书/律师函)/重大事项电话咨询/纠纷/重大事项) 8律师函 草拟文书
        CompanyInfo companyInfo = companyInfoService.queryCompanyInfoById(companyId);
        if ("4".equals(companyInfo.getVipType())) {
            return false;
        }
        if ("3".equals(companyInfo.getVipType())) {//vip3 直接TRUE
            return true;
        }
        if ("2".equals(companyInfo.getVipType())) {//vip2 预约咨询不超过5次---不是预约咨询||count<=5) true，否则 false
            return !"1".equals(type) || appointMeetService.queryAppointMeetCountByTime(companyInfo.getStartTime(), companyInfo.getId()) < 5;
        }
        if ("1".equals(companyInfo.getVipType())) {//vip1 无预约咨询---是预约咨询 false， 否则 true
            return !"1".equals(type);
        }
        if ("0".equals(companyInfo.getVipType())) {
            //vip0 2 公司电话咨询(2次) 3 纠纷电话咨询(1次) 4 修改文书(1次) 5 合同下载(1次) 6文书中的电话 7其他(草拟文书/律师函/重大事项电话咨询/纠纷/重大事项)
            if ("2".equals(type)) {//
                return telConsultationService.queryTelConsultationCountByTime(companyInfo.getStartTime(), companyInfo.getId(), "1") < 2;
            }
            if ("3".equals(type)) {
                return telConsultationService.queryTelConsultationCountByTime(companyInfo.getStartTime(), companyInfo.getId(), "2") < 1;
            }
            if ("4".equals(type)) {
                return modifyDocService.queryModifyDocCountByTime(companyInfo.getStartTime(), companyInfo.getId()) < 1;
            }
            if ("5".equals(type)) {
                return templateDownRecordService.queryTemplateDownRecordCountByTime(companyInfo.getStartTime(), companyInfo.getId()) < 1;
            }
            return "8".equals(type) || "6".equals(type);
        }
        return false;
    }
}
