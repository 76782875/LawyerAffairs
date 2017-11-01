package com.lecshop.total.affairlist.service.impl;

import com.lecshop.abutment.nopointlawyer.bean.SearchBeanc;
import com.lecshop.abutment.nopointlawyer.service.NoPointLawyerService;
import com.lecshop.admin.profitset.service.ProfitSetService;
import com.lecshop.total.affairlist.bean.AppointMeet;
import com.lecshop.total.affairlist.mapper.AppointMeetMapper;
import com.lecshop.total.affairlist.service.AppointMeetService;
import com.lecshop.total.transactionrecords.bean.TransactionRecords;
import com.lecshop.total.transactionrecords.service.TransactionRecordsService;
import com.lecshop.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 预约会员service实现类
 *
 * @author sunluyang on 2017/7/15.
 */
@Service
public class AppoinMeetServiceImpl implements AppointMeetService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(AppoinMeetServiceImpl.class);
    /**
     * 注入预约会面mapper
     */
    @Autowired
    private AppointMeetMapper appointMeetMapper;
    /**
     * 注入非指定律师service
     */
    @Autowired
    private NoPointLawyerService noPointLawyerService;
    /**
     * 自动注入对账报表service
     */
    @Autowired
    private TransactionRecordsService transactionRecordsService;
    /**
     * 自动注入律师收益设置service
     */
    @Autowired
    private ProfitSetService profitSetService;

    /**
     * 分页查询预约会面
     *
     * @param pageHelper  分页帮助类
     * @param meetType    1 指定律师 2 没有指定律师
     * @param type        类型 1 公司事务 2公司纠纷 3 公司重大事项'
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 预约会面集合
     */
    @Override
    public PageHelper<AppointMeet> queryTelConsultation(PageHelper<AppointMeet> pageHelper, String meetType, String type, String lawyerName, String companyName, long companyId, long userId, long lawyerId) {
        logger.info("queryTelConsultation and pageHelper:{}\r\n type:{}\r\n lawyerName:{}\r\n companyName:{}\r\n companyId:{}\r\n userId:{}\r\n lawyerId:{}\r\n",
                pageHelper, type, lawyerName, companyName, companyId, userId, lawyerId);
        Map<String, Object> params = new HashMap<>();
        params.put("meetType", meetType);
        params.put("type", type);
        params.put("lawyerName", lawyerName);
        params.put("companyName", companyName);
        params.put("companyId", companyId);
        params.put("userId", userId);
        params.put("lawyerId", lawyerId);
        return pageHelper.setListDates(appointMeetMapper.queryAppointMeet(pageHelper.getQueryParams(params, appointMeetMapper.queryAppointMeetCount(params))));
    }

    /**
     * 查询待处理的预约会面信息
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return 预约会面集合
     */
    @Override
    public List<AppointMeet> queryToDoAppointMeet(long companyId, long userId) {
        logger.debug("queryToDoAppointMeet and companyId:{} \r\n userId:{}", companyId, userId);
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", companyId);
        map.put("userId", userId);
        return appointMeetMapper.queryToDoAppointMeet(map);
    }

    @Override
    public int grabAppointMeet(long lawyerId, long id) {
        logger.debug("grabAppointMeet and lawyerId:{} \r\n id:{}", lawyerId, id);
        Map<String, Object> params = new HashMap<>();
        params.put("lawyerId", lawyerId);
        params.put("id", id);
        return appointMeetMapper.grabAppointMeet(params);
    }

    /**
     * 确认预约会面
     *
     * @param id       主键id
     * @param status   状态
     * @param lawyerId 律师id
     * @param meetType 1 指定律师 2 没有指定律师
     * @return 成功返回1 失败返回0
     */
    @Override
    public int confirmAppointMeet(long id, String status, long lawyerId, String meetType) {
        logger.debug("confirmAppointMeet and id :{} \r\n and status :{} \r\n and lawyerId :{} \r\n and meetType", id, status, lawyerId, meetType);
        int incomeType = 0;
        int incomeCount = 0;
        if (meetType != null && !"".equals(meetType)) {
            if ("1".equals(meetType)) {
                incomeType = 2;
                incomeCount = 2;
            } else {
                incomeType = 3;
                incomeCount = 3;
            }
        }
        transactionRecordsService.addTransactionRecords(TransactionRecords.buildForAdd(lawyerId, profitSetService.queryProfitSet().buildMoney(incomeCount), incomeType));
        return changeStatus(id, status);
    }

    /**
     * 修改状态
     *
     * @param id     主键id
     * @param status 状态
     * @return 成功返回1 失败返回0
     */
    @Override
    public int changeStatus(long id, String status) {
        logger.debug("changeStatus id:{} \r\n status:{}", id, status);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("status", status);
        return appointMeetMapper.changeStatus(params);
    }

    /**
     * 添加预约会面
     *
     * @param appointMeet 预约会面
     * @return 添加返回码
     */
    @Override
    public int addAppointMeet(AppointMeet appointMeet, SearchBeanc searchBeanc) {
        logger.debug("addAppointMeet and appointMeet:{} \r\n searchBeanc:{}", appointMeet, searchBeanc);
        noPointLawyerService.pushMessage(appointMeet, searchBeanc);
        return appointMeetMapper.addAppointMeet(appointMeet.buildIsPointForMeet(appointMeet.getLawyerId()));
    }

    /**
     * 为预约会面评分
     *
     * @param id    会面id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    @Override
    public int gradeForMeeting(long id, int score) {
        logger.debug("gradeForMeeting and id :{} \r\n and score :{}", id, score);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("score", score);
        return appointMeetMapper.gradeForMeeting(params);
    }

    @Override
    public int cancelAppointMeet(long id) {
        logger.debug("cancelAppointMeet and id:{}", id);
        AppointMeet appointMeet = appointMeetMapper.queryAppointMeetById(id);
        if (Objects.isNull(appointMeet)) {
            return 0;
        }
        if (appointMeet.getCreateTime().plusHours(2).isBefore(LocalDateTime.now())) {
            return -1;
        }
        return changeStatus(id, "3");
    }

    @Override
    public int queryAppointMeetCountByTime(LocalDateTime localDateTime, long companyId) {
        logger.debug("cancelAppointMeet and localDateTime:{}\r\n companyId:{}", localDateTime, companyId);
        Map<String, Object> map = new HashMap<>();
        map.put("time", localDateTime);
        map.put("companyId", companyId);
        return appointMeetMapper.queryAppointMeetCountByTime(map);
    }

    /**
     * 查询律师的预约会面订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 律师的预约会面订单数目
     */
    @Override
    public int queryMeetingOrderCount(Map<String, Object> params) {
        logger.debug("queryMeetingOrderCount and params :{}", params);
        return appointMeetMapper.queryMeetingOrderCount(params);
    }

    /**
     * 查询公司发起的预约会面订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 公司发起的预约会面订单数目
     */
    @Override
    public int queryMeetingOrderCountOfCompany(Map<String, Object> params) {
        logger.debug("queryMeetingOrderCountOfCompany and params :{}", params);
        return appointMeetMapper.queryMeetingOrderCountOfCompany(params);
    }

}
