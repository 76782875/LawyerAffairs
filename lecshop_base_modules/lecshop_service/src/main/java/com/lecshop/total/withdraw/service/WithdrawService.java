package com.lecshop.total.withdraw.service;

import com.lecshop.total.withdraw.bean.QueryConditions;
import com.lecshop.total.withdraw.bean.Withdraw;
import com.lecshop.utils.PageHelper;
import com.lecshop.utils.WithdrawResponse;

/**
 * Created by dujinkai on 17/7/26.
 * 提现服务接口
 */
public interface WithdrawService {

    /**
     * 添加提现申请
     *
     * @param withdraw 提现实体
     * @return 成功返回1 失败返回0 -1:当前日期不能提现 -2:未配置提现账户 -3:参数错误 -4:账户余额不足
     */
    int addWithdraw(Withdraw withdraw);

    /**
     * 分页查询提现记录
     *
     * @param pageHelper      分页帮助类
     * @param queryConditions 查询条件
     * @return 返回提现记录
     */
    PageHelper<Withdraw> queryWithdraws(PageHelper<Withdraw> pageHelper, QueryConditions queryConditions);


    /**
     * 改变提现的状态
     *
     * @param id     主键id
     * @param status 状态
     * @return 成功返回1 失败返回0
     */
    int changeStatus(long id, String status);

    /**
     * 发送金额
     *
     * @return 返回提现结果
     */
    WithdrawResponse releaseMoney(long id);
}
