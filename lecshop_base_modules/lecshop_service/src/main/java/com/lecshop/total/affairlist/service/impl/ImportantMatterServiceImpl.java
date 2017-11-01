package com.lecshop.total.affairlist.service.impl;

import com.lecshop.total.affairlist.bean.ImportantMatter;
import com.lecshop.total.affairlist.mapper.ImportantMatterMapper;
import com.lecshop.total.affairlist.service.ImportantMatterService;
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
 * 重大事项service实现类
 *
 * @author sunluyang on 2017/7/21.
 */
@Service
public class ImportantMatterServiceImpl implements ImportantMatterService {

    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(ImportantMatterServiceImpl.class);
    /**
     * 注入重大事项mapper
     */
    @Autowired
    private ImportantMatterMapper importantMatterMapper;
    /**
     * 注入对账service
     */
    @Autowired
    private TransactionRecordsService transactionRecordsService;

    /**
     * 查询重大事项
     *
     * @param pageHelper  分页帮助类
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @param companyId   公司id
     * @param userId      用户id
     * @param lawyerId    律师id
     * @return 重大事项集合
     */
    @Override
    public PageHelper<ImportantMatter> queryImportantMatter(PageHelper<ImportantMatter> pageHelper, String lawyerName, String companyName, long companyId, long userId, long lawyerId) {
        Map<String, Object> params = new HashMap<>();
        params.put("lawyerName", lawyerName);
        params.put("companyName", companyName);
        params.put("companyId", companyId);
        params.put("userId", userId);
        params.put("lawyerId", lawyerId);
        return pageHelper.setListDates(importantMatterMapper.queryImportantMatter(pageHelper.getQueryParams(params, importantMatterMapper.queryImportantMatterCount(params))));
    }

    /**
     * @param companyId 公司id
     * @param userId    用户id
     * @return
     */
    @Override
    public List<ImportantMatter> queryToDoImportantMatter(long companyId, long userId) {
        logger.debug("queryToDoImportantMatter and companyId :{} \r\n and userId :{}", companyId, userId);
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", companyId);
        map.put("userId", userId);
        return importantMatterMapper.queryToDoImportantMatter(map);
    }

    /**
     * 修改重大事项状态
     *
     * @param id     重大事项id
     * @param status 状态
     * @return 成功返回1，失败返回0
     */
    @Override
    public int changeImportantMatterStatus(long id, String status) {
        logger.debug("changeImportantMatterStatus and id :{} \r\n and status :{}", id, status);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("status", status);
        ImportantMatter importantMatter = importantMatterMapper.queryImportantMatterById(id);
        transactionRecordsService.addTransactionRecords(TransactionRecords.buildForAdd(importantMatter.getLawyerId(), importantMatter.getPrice().divide(new BigDecimal(2)), 8));
        return importantMatterMapper.changeImportantMatterStatus(params);
    }

    /**
     * 为重大事项评分
     *
     * @param id    重大事项id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    @Override
    public int gradeForImportantMatter(long id, int score) {
        logger.debug("gradeForImportantMatter and id :{} \r\n and score :{}", id, score);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("score", score);
        return importantMatterMapper.gradeForImportantMatter(params);
    }

    @Override
    public int confirmImportantMatter(long id, BigDecimal money) {
        logger.debug("confirmImportantMatter and id:{} \r\n money:{}", id, money);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("money", money);
        return importantMatterMapper.confirmImportantMatter(params);
    }

    @Override
    public ImportantMatter queryImportantMatterById(long id) {
        logger.debug("queryImportantMatterById and id:{}", id);
        return importantMatterMapper.queryImportantMatterById(id);
    }

    @Override
    public int addImportantMatter(ImportantMatter importantMatter) {
        logger.debug("addImportantMatter and importantMatter:{}", importantMatter);
        return importantMatterMapper.addImportantMatter(importantMatter);
    }


    /**
     * 查询律师的重大事项订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 重大事项订单数目
     */
    @Override
    public int queryImportantMatterOrderCount(Map<String, Object> params) {
        logger.debug("queryImportantMatterOrderCount and params :{}", params);
        return importantMatterMapper.queryImportantMatterOrderCount(params);
    }

    /**
     * 查询公司发起的的重大事项订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 重大事项订单数目
     */
    @Override
    public int queryImportantMatterOrderCountOfCompany(Map<String, Object> params) {
        logger.debug("queryImportantMatterOrderCountOfCompany and params :{}", params);
        return importantMatterMapper.queryImportantMatterOrderCountOfCompany(params);
    }
}
