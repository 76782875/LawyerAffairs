package com.lecshop.total.affairlist.service.impl;

import com.lecshop.total.affairlist.bean.DisputeDetail;
import com.lecshop.total.affairlist.bean.Disputes;
import com.lecshop.total.affairlist.mapper.DisputesMapper;
import com.lecshop.total.affairlist.service.DisputeDetailService;
import com.lecshop.total.affairlist.service.DisputesService;
import com.lecshop.total.transactionrecords.bean.TransactionRecords;
import com.lecshop.total.transactionrecords.service.TransactionRecordsService;
import com.lecshop.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 纠纷service
 *
 * @author sunluyang on 2017/7/21.
 */
@Service
public class DisputesServiceImpl implements DisputesService {

    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(DisputesServiceImpl.class);

    /**
     * 注入纠纷mapper
     */
    @Autowired
    private DisputesMapper disputesMapper;
    /**
     * 注入纠纷细节service
     */
    @Autowired
    private DisputeDetailService disputeDetailService;
    /**
     * 对账service
     */
    @Autowired
    private TransactionRecordsService transactionRecordsService;

    /**
     * 查询纠纷事务
     *
     * @param pageHelper  分页帮助类
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 纠纷事务集合
     */
    @Override
    public PageHelper<Disputes> queryDisputes(PageHelper<Disputes> pageHelper, String lawyerName, String companyName, long companyId, long userId, long lawyerId) {
        logger.info("queryTelConsultation and pageHelper:{}\r\n lawyerName:{}\r\n companyName:{}\r\n companyId:{}\r\n userId:{}\r\n lawyerId:{}\r\n",
                pageHelper, lawyerName, companyName, companyId, userId, lawyerId);
        Map<String, Object> params = new HashMap<>();
        params.put("lawyerName", lawyerName);
        params.put("companyName", companyName);
        params.put("companyId", companyId);
        params.put("userId", userId);
        params.put("lawyerId", lawyerId);
        return pageHelper.setListDates(disputesMapper.queryDisputes(pageHelper.getQueryParams(params, disputesMapper.queryDisputesCount(params))));
    }

    /**
     * 查询待处理纠纷事务
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return 纠纷事务集合
     */
    @Override
    public List<Disputes> queryToDoDisputes(long companyId, long userId) {
        logger.debug("queryToDoDisputes and companyId :{}\r\n userId:{}", companyId, userId);
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", companyId);
        map.put("userId", userId);
        return disputesMapper.queryToDoDisputes(map);
    }

    /**
     * 添加纠纷
     *
     * @param disputes 纠纷
     * @return 添加返回码
     */
    @Override
    public int addDisputes(Disputes disputes, long companyId, long userId) {
        logger.debug("addDisputes and disputes :{} \r\n companyId :{}\r\n userId:{}", disputes, companyId, userId);
        disputesMapper.addDisputes(disputes.buildDisputes(companyId, userId));
        return disputeDetailService.addDisputeDetail(new DisputeDetail().disputeDetailList(disputes.getId()));
    }

    /**
     * 修改纠纷状态
     *
     * @param id     纠纷id
     * @param status 状态
     * @return 成功返回1，失败返回0
     */
    @Override
    public int changeDisputesStatus(long id, String status) {
        logger.debug("changeDisputesStatus and id :{} \r\n and status :{}", id, status);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("status", status);
        Disputes disputes = disputesMapper.queryDisputesById(id);
        transactionRecordsService.addTransactionRecords(TransactionRecords.buildForAdd(disputes.getLawyerId(), disputes.getPrice().divide(new BigDecimal(2)), 7));
        return disputesMapper.changeDisputesStatus(params);
    }

    /**
     * 为纠纷服务评分
     *
     * @param id    纠纷id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    @Override
    public int gradeForDisputes(long id, int score) {
        logger.debug("gradeForDisputes and id :{} \r\n and score :{}", id, score);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("score", score);
        return disputesMapper.gradeForDisputes(params);
    }

    @Override
    public int confirmDispute(long id, BigDecimal money) {
        logger.debug("confirmDispute and id :{} \r\n money:{}", money);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("money", money);
        return disputesMapper.confirmDispute(params);
    }

    @Override
    public Disputes queryDisputesById(long id) {
        logger.debug("changeDisputesStatus and id :{}", id);
        return disputesMapper.queryDisputesById(id);
    }


    /**
     * 查询律师的纠纷订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 纠纷订单数目
     */
    @Override
    public int queryDisputesOrderCount(Map<String, Object> params) {
        logger.debug("queryDisputesOrderCount and params :{}", params);
        return disputesMapper.queryDisputesOrderCount(params);
    }

    /**
     * 查询公司发起的纠纷订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 纠纷订单数目
     */
    @Override
    public int queryDisputesOrderCountOfCompany(Map<String, Object> params) {
        logger.debug("queryDisputesOrderCountOfCompany and params :{}", params);
        return disputesMapper.queryDisputesOrderCountOfCompany(params);
    }
}
