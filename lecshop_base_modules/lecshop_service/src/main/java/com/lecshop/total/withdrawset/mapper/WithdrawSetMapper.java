package com.lecshop.total.withdrawset.mapper;

import com.lecshop.total.withdrawset.bean.WithdrawSet;

/**
 * Created by dujinkai on 17/7/25.
 * 律师提现设置数据库接口
 */
public interface WithdrawSetMapper {

    /**
     * 根据律师id查询律师提现设置
     *
     * @param lawyerId 律师id
     * @return 返回律师的提现设置
     */
    WithdrawSet queryByLawyerId(long lawyerId);

    /**
     * 根据律师id删除提现设置
     *
     * @param lawyerId 律师id
     * @return 成功返回1 失败返回0
     */
    int deleteByLawyerId(long lawyerId);

    /**
     * 新增律师提现设置
     *
     * @param withdrawSet 提现设置
     * @return 成功返回1 失败返回0
     */
    int addWithdrawSet(WithdrawSet withdrawSet);
}
