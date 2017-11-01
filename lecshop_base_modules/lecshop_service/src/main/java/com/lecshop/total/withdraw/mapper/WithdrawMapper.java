package com.lecshop.total.withdraw.mapper;

import com.lecshop.total.withdraw.bean.Withdraw;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/7/26.
 * 提现数据库接口
 */
public interface WithdrawMapper {

    /**
     * 添加提现申请
     *
     * @param withdraw 提现
     * @return 成功返回1 失败返回0
     */
    int addWithdraw(Withdraw withdraw);

    /**
     * 查询提现记录总数
     *
     * @param params 查询参数
     * @return 返回提现记录总数
     */
    int queryWithdrawsCount(Map<String, Object> params);

    /**
     * 查询提现记录
     *
     * @param params 查询参数
     * @return 返回提现记录
     */
    List<Withdraw> queryWithdraws(Map<String, Object> params);

    /**
     * 改变提现状态
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int changeStatus(Map<String, Object> params);

    /**
     * 根据id查询提现记录
     *
     * @param id 提现id
     * @return 返回提现记录
     */
    Withdraw queryById(long id);
}
