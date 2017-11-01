package com.lecshop.total.transactionrecords.service.impl;

import com.lecshop.total.transactionrecords.bean.TransactionRecords;
import com.lecshop.total.transactionrecords.mapper.TransactionRecordsMapper;
import com.lecshop.total.transactionrecords.service.TransactionRecordsService;
import com.lecshop.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * 对账报表service实现类
 * <p>
 * Created by LecShop on 2017/7/12.
 */
@Service
public class TransactionRecordsServiceImpl implements TransactionRecordsService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(TransactionRecordsServiceImpl.class);

    /**
     * 自动注入对账报表
     */
    @Autowired
    private TransactionRecordsMapper transactionRecordsMapper;

    /**
     * 分页查询对账报表
     *
     * @param pageHelper 分页帮助类
     * @param name       律师姓名
     * @return 对账报表
     */
    @Override
    public PageHelper<TransactionRecords> queryTransactionRecordsList(PageHelper<TransactionRecords> pageHelper, String name) {
        logger.debug("queryTransactionRecordsList and pageHelper :{} and name :{}", pageHelper, name);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return pageHelper.setListDates(transactionRecordsMapper.queryTransactionRecordsList(pageHelper.getQueryParams(params, transactionRecordsMapper.queryTransactionRecordsCount(params))));
    }

    @Override
    public BigDecimal queryLawyerAllIncome(long lawyerId) {
        logger.debug("queryLawyerAllIncome and lawyerId:{}", lawyerId);

        List<TransactionRecords> transactionRecordsList = transactionRecordsMapper.queryLawyerAllIncome(lawyerId);

        if (CollectionUtils.isEmpty(transactionRecordsList)) {
            return new BigDecimal(0);
        }
        return getCountReduce(transactionRecordsList.stream(), TransactionRecords::getMoney);
    }

    @Override
    public BigDecimal queryLawyerBalance(long lawyerId) {
        logger.debug("queryLawyerBalance and lawyerId:{}", lawyerId);
        return queryLawyerAllIncome(lawyerId).subtract(queryLawyerAllOutcome(lawyerId));
    }

    @Override
    public BigDecimal queryLawyerCurrentDayIncome(long lawyerId) {
        logger.debug("queryLawyerCurrentDayIncome and lawyerId:{}", lawyerId);

        List<TransactionRecords> transactionRecordsList = transactionRecordsMapper.queryLawyerCurrentDayAllIncome(lawyerId);

        if (CollectionUtils.isEmpty(transactionRecordsList)) {
            return new BigDecimal(0);
        }
        return getCountReduce(transactionRecordsList.stream(), TransactionRecords::getMoney);
    }


    @Override
    public long addTransactionRecords(TransactionRecords transactionRecords) {
        logger.debug("addTransactionRecords and transactionRecords:{}", transactionRecords);

        if (Objects.isNull(transactionRecords)) {
            logger.error("addTransactionRecords fail due to params is null...");
            return 0;
        }

        transactionRecordsMapper.addTransactionRecords(transactionRecords);

        return transactionRecords.getId();
    }

    @Override
    public int deleteById(long id) {
        logger.debug("deleteById and id :{}", id);
        return transactionRecordsMapper.deleteById(id);
    }

    /**
     * 获得律师所有的支出
     *
     * @param lawyerId 律师id
     * @return 返回律师所有的支出
     */
    private BigDecimal queryLawyerAllOutcome(long lawyerId) {
        List<TransactionRecords> transactionRecordsList = transactionRecordsMapper.queryLawyerAllOutcome(lawyerId);
        if (CollectionUtils.isEmpty(transactionRecordsList)) {
            return new BigDecimal(0);
        }
        return getCountReduce(transactionRecordsList.stream(), TransactionRecords::getMoney);
    }

    /**
     * 获得集合的加法总数
     *
     * @param stream 集合
     * @param mapper 转化器
     * @param <T>
     * @return 返回集合加法的总数
     */
    private <T> BigDecimal getCountReduce(Stream<T> stream, Function<T, BigDecimal> mapper) {
        return stream.map(mapper).reduce(new BigDecimal(0), BigDecimal::add);
    }

    /**
     * 查询律师总收入
     *
     * @param params 律师id、起始时间及终止时间
     * @return 律师总收入
     */
    @Override
    public BigDecimal queryLawyerTotalIncome(Map<String, Object> params) {
        logger.debug("queryLawyerTotalIncome and params :{}", params);
        return transactionRecordsMapper.queryLawyerTotalIncome(params);
    }
}
