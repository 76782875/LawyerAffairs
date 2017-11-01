package com.lecshop.company.index.service.impl;

import com.lecshop.company.index.bean.IndexInfo;
import com.lecshop.company.index.bean.ToDoAffairs;
import com.lecshop.company.index.service.IndexInfoService;
import com.lecshop.total.affairlist.bean.*;
import com.lecshop.total.affairlist.service.*;
import com.lecshop.total.renewrecord.service.RenewRecordService;
import com.lecshop.total.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 首页信息service实现类
 *
 * @author sunluyang on 2017/7/21.
 */
@Service
public class IndexInfoServiceImpl implements IndexInfoService {
    /**
     * 调试日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(IndexInfoServiceImpl.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RenewRecordService renewRecordService;
    @Autowired
    private DraftDocService draftDocService;
    @Autowired
    private ModifyDocService modifyDocService;
    @Autowired
    private LawyerLetterService lawyerLetterService;
    @Autowired
    private DisputesService disputesService;
    @Autowired
    private ImportantMatterService importantMatterService;
    @Autowired
    private AppointMeetService appointMeetService;

    /**
     * 首页待处理事务
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @param type      类型
     * @return 返回待处理事务
     */
    public IndexInfo toDoAffairs(long companyId, long userId, String type) {
        LOGGER.info("toDoAffairs and companyId:{}\r\n userId:{}\r\n type:{}", companyId, userId, type);
        IndexInfo indexInfo = new IndexInfo();
        List<ToDoAffairs> toDoAffairsList = new ArrayList<>();
        //用户信息
        indexInfo.setUser(userService.queryUserAndCompany(companyId, userId));
        //今日消费
        indexInfo.setConsumptionToday(renewRecordService.queryConsumption(companyId, 0, 0));
        //总消费
        indexInfo.setTotalConsumption(renewRecordService.queryConsumption(companyId, 0, 1));
        userId = "0".equals(type) ? 0 : userId;
        //草拟文书
        List<DraftDoc> draftDocList = draftDocService.queryToDoDraftDoc(companyId, userId);
        if (!CollectionUtils.isEmpty(draftDocList)) {
            draftDocList.forEach(draftDoc ->
                    toDoAffairsList.add(new ToDoAffairs().getDefaultToDoAffairs(draftDoc.getId(), draftDoc.getLawyer().getName(), draftDoc.getStatus(), draftDoc.getCreateTime(), "1")));
        }
        //修改文书
        List<ModifyDoc> modifyDocList = modifyDocService.queryToDoModifyDoc(companyId, userId);
        if (!CollectionUtils.isEmpty(modifyDocList)) {
            modifyDocList.forEach(modifyDoc ->
                    toDoAffairsList.add(new ToDoAffairs().getDefaultToDoAffairs(modifyDoc.getId(), modifyDoc.getLawyer().getName(), modifyDoc.getStatus(), modifyDoc.getCreateTime(), "2")));
        }
        //律师函
        List<LawyerLetter> lawyerLetterList = lawyerLetterService.queryToDoLawyerLetter(companyId, userId);
        if (!CollectionUtils.isEmpty(lawyerLetterList)) {
            lawyerLetterList.forEach(lawyerLetter ->
                    toDoAffairsList.add(new ToDoAffairs().getDefaultToDoAffairs(lawyerLetter.getId(), lawyerLetter.getLawyer().getName(), lawyerLetter.getStatus(), lawyerLetter.getCreateTime(), "3")));
        }

        //纠纷处理
        List<Disputes> disputesList = disputesService.queryToDoDisputes(companyId, userId);
        if (!CollectionUtils.isEmpty(disputesList)) {
            disputesList.forEach(disputes ->
                    toDoAffairsList.add(new ToDoAffairs().getDefaultToDoAffairs(disputes.getId(), disputes.getLawyer().getName(), disputes.getStatus(), disputes.getCreateTime(), "4")));
        }
        //预约会面
        List<AppointMeet> appointMeetList = appointMeetService.queryToDoAppointMeet(companyId, userId);
        if (!CollectionUtils.isEmpty(appointMeetList)) {
            appointMeetList.forEach(appointMeet ->
                    toDoAffairsList.add(ToDoAffairs.buildMeetType(new ToDoAffairs().getDefaultToDoAffairs(appointMeet.getId(), appointMeet.getLawyer().getName(), appointMeet.getStatus(), appointMeet.getCreateTime(), "5"), appointMeet.getMeetType(), appointMeet.getType())));
        }
        //重大事项
        List<ImportantMatter> importantMatterList = importantMatterService.queryToDoImportantMatter(companyId, userId);
        if (!CollectionUtils.isEmpty(importantMatterList)) {
            importantMatterList.forEach(importantMatter ->
                    toDoAffairsList.add(new ToDoAffairs().getDefaultToDoAffairs(importantMatter.getId(), importantMatter.getLawyer().getName(), importantMatter.getStatus(), importantMatter.getCreateTime(), "6")));
        }
        indexInfo.setToDoAffairs(toDoAffairsList);
        Collections.sort(indexInfo.getToDoAffairs());
        return indexInfo;
    }
}
