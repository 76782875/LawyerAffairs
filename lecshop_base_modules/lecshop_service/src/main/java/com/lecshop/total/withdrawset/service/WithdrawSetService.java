package com.lecshop.total.withdrawset.service;

import com.lecshop.total.withdrawset.bean.WithdrawSet;

/**
 * Created by dujinkai on 17/7/25.
 * 律师提现服务接口
 */
public interface WithdrawSetService {

    /**
     * 根据律师id查询律师提现设置
     *
     * @param lawyerId 律师id
     * @return 返回律师的提现设置
     */
    WithdrawSet queryByLawyerId(long lawyerId);

    /**
     * 更新提现设置
     *
     * @param withdrawSet 提现设置
     * @return 成功返回1 失败返回0
     */
    int updateWithdrawSet(WithdrawSet withdrawSet);
}
