package com.lecshop.total.transactionrecords.service;

import com.lecshop.total.transactionrecords.bean.TransactionRecords;
import com.lecshop.utils.PageHelper;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 对账报表service
 * <p>
 * Created by LecShop on 2017/7/12.
 */
public interface TransactionRecordsService {

    /**
     * 分页查询对账报表
     *
     * @param pageHelper 分页帮助类
     * @param name       律师姓名
     * @return 对账报表
     */
    PageHelper<TransactionRecords> queryTransactionRecordsList(PageHelper<TransactionRecords> pageHelper, String name);

    /**
     * 查询律师总的收入
     *
     * @param lawyerId 律师id
     * @return 返回律师总的收入
     */
    BigDecimal queryLawyerAllIncome(long lawyerId);

    /**
     * 查询律师账户余额
     *
     * @param lawyerId 律师id
     * @return 返回律师的账户余额
     */
    BigDecimal queryLawyerBalance(long lawyerId);

    /**
     * 查询律师当天的总收入
     *
     * @param lawyerId 律师id
     * @return 返回律师当天的总收入
     */
    BigDecimal queryLawyerCurrentDayIncome(long lawyerId);

    /**
     * 新增律师交易记录
     *
     * @param transactionRecords 交易记录
     * @return 返回主键id
     */
    long addTransactionRecords(TransactionRecords transactionRecords);

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
