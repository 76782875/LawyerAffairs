package com.lecshop.admin.profitset.service;

import com.lecshop.admin.profitset.bean.ProfitSet;

/**
 * 律师收益设置service接口
 *
 * @author sunluyang on 2017/7/11.
 */
public interface ProfitSetService {

    /**
     * 查询律师收益设置
     *
     * @return 律师收益设置实体类
     */
    ProfitSet queryProfitSet();

    /**
     * 根据条件进行更新律师收益设置信息
     *
     * @param type      1 电话咨询 2 预约会面 3 预约会面指定律师 4 提现设置
     * @param profitSet 收益设置实体类
     * @return 编辑返回码
     */
    int editProfitSet(int type, ProfitSet profitSet);
}
