package com.lecshop.total.affairlist.service.impl;

import com.lecshop.admin.profitset.service.ProfitSetService;
import com.lecshop.total.affairlist.bean.TelConsultation;
import com.lecshop.total.affairlist.mapper.TelConsultationMapper;
import com.lecshop.total.affairlist.service.TelConsultationService;
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

/**
 * 电话咨询service实现类
 *
 * @author sunluyang on 2017/7/15.
 */
@Service
public class TelConsultationServiceImpl implements TelConsultationService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(TelConsultationServiceImpl.class);

    /**
     * 注入电话咨询mapper
     */
    @Autowired
    private TelConsultationMapper telConsultationMapper;

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
     * 分页查询电话咨询
     *
     * @param pageHelper  分页帮助类
     * @param type        类型 1 公司事务 2公司纠纷 3 公司重大事项
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 返回电话咨询集合
     */
    public PageHelper<TelConsultation> queryTelConsultation(PageHelper<TelConsultation> pageHelper, String type, String lawyerName, String companyName, long companyId, long userId, long lawyerId) {
        logger.info("queryTelConsultation and pageHelper:{}\r\n type:{}\r\n lawyerName:{}\r\n companyName:{}\r\n companyId:{}\r\n userId:{}\r\n lawyerId:{}\r\n",
                pageHelper, type, lawyerName, companyName, companyId, userId, lawyerId);
        Map<String, Object> params = new HashMap<>();
        params.put("type", type);
        params.put("lawyerName", lawyerName);
        params.put("companyName", companyName);
        params.put("companyId", companyId);
        params.put("userId", userId);
        params.put("lawyerId", lawyerId);
        logger.debug("queryTelConsultation and params:{}", params);

        return pageHelper.setListDates(telConsultationMapper.queryTelConsultation(pageHelper.getQueryParams(params, telConsultationMapper.queryTelConsultationCount(params))));
    }

    /**
     * 为电话咨询评分
     *
     * @param id    电话咨询id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    @Override
    public int gradeForTelConsultation(long id, int score) {
        logger.debug("gradeForTelConsultation and id :{} \r\n and score :{}", id, score);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("score", score);
        return telConsultationMapper.gradeForTelConsultation(params);
    }

    /**
     * 添加电话咨询
     *
     * @param telConsultation 电话咨询实体类
     * @return 添加返回码
     */
    @Override
    public int addTelConsultation(TelConsultation telConsultation) {
        logger.debug("addTelConsultation and telConsultation :{}", telConsultation);
        return telConsultationMapper.addTelConsultation(telConsultation);
    }

    @Override
    public int changeStatus(long id, String status) {
        logger.debug("changeStatus and id :{} \r\n status:{}", id, status);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("status", status);
        return telConsultationMapper.changeStatus(params);
    }

    /**
     * 确认电话咨询
     *
     * @param id       电话咨询id
     * @param status   电话咨询状态
     * @param lawyerId 律师id
     * @return 成功返回1，失败0
     */
    @Override
    public int confirmTelConsultation(long id, String status, long lawyerId) {
        logger.debug("confirmTelConsultation and id :{} \r\n and status :{} \r\n lawyerId", id, status, lawyerId);
        transactionRecordsService.addTransactionRecords(TransactionRecords.buildForAdd(lawyerId, profitSetService.queryProfitSet().buildMoney(1), 1));
        return this.changeStatus(id, status);
    }

    /**
     * 根据时间和公司id 查询电话咨询总数
     *
     * @param localDateTime 时间
     * @param companyId     公司id
     * @return 总数
     */
    @Override
    public int queryTelConsultationCountByTime(LocalDateTime localDateTime, long companyId, String type) {
        logger.debug("confirmTelConsultation and localDateTime :{} \r\n and companyId :{} \r\n type", localDateTime, companyId, type);
        Map<String, Object> params = new HashMap<>();
        params.put("time", localDateTime);
        params.put("companyId", companyId);
        params.put("type", type);
        return telConsultationMapper.queryTelConsultationCountByTime(params);
    }


    /**
     * 查询律师的电话咨询订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 律师的电话咨询订单数目
     */
    @Override
    public int queryTelConsultationOrderCount(Map<String, Object> params) {
        logger.debug("queryTelConsultationOrderCount and params :{}", params);
        return telConsultationMapper.queryTelConsultationOrderCount(params);
    }

    /**
     * 查询公司发起的电话咨询订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 公司发起的电话咨询订单数目
     */
    @Override
    public int queryTelConsultationOrderCountOfCompany(Map<String, Object> params) {
        logger.debug("queryTelConsultationOrderCountOfCompany and params :{}", params);
        return telConsultationMapper.queryTelConsultationOrderCountOfCompany(params);
    }
}
