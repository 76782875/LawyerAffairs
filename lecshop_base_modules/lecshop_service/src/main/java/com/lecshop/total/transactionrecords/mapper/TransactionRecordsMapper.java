package com.lecshop.total.transactionrecords.mapper;

import com.lecshop.total.transactionrecords.bean.TransactionRecords;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 对账报表数据库接口
 * <p>
 * Created by LecShop on 2017/7/12.
 */
@Repository
public interface TransactionRecordsMapper {

    /**
     * 分页查询对账报表
     *
     * @param params 律师名称
     * @return 对账报表集合
     */
    List<TransactionRecords> queryTransactionRecordsList(Map<String, Object> params);

    /**
     * 查询对账报表总记录数
     *
     * @param params 律师名称
     * @return 对账报表总记录数
     */
    int queryTransactionRecordsCount(Map<String, Object> params);

    /**
     * 查询律师所有的收入记录
     *
     * @param lawyerId 律师id
     * @return 返回律师所有的收入记录
     */
    List<TransactionRecords> queryLawyerAllIncome(long lawyerId);

    /**
     * 查询律师所有的支出记录
     *
     * @param lawyerId 律师id
     * @return 返回律师所有的支出记录
     */
    List<TransactionRecords> queryLawyerAllOutcome(long lawyerId);

    /**
     * 查询律师当天的所有收入记录
     *
     * @param lawyerId 律师id
     * @return 返回律师当天的所有收入记录
     */
    List<TransactionRecords> queryLawyerCurrentDayAllIncome(long lawyerId);

    /**
     * 新增交易记录
     *
     * @param transactionRecords 交易记录
     * @return 成功返回1 失败返回0
     */
    int addTransactionRecords(TransactionRecords transactionRecords);

    /**
     * 根据id删除交易记录
     *
     * @param id 主键id
     * @return 成功返回1 失败返回0
     */
    int deleteById(long id);

    /**
     * 查询律师总收入
     *
     * @param params 律师id、起始时间及终止时间
     * @return 律师总收入
     */
    BigDecimal queryLawyerTotalIncome(Map<String, Object> params);
}
