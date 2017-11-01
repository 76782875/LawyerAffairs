package com.lecshop.admin.payset.service;


import com.lecshop.admin.payset.bean.PaySetCommon;

/**
 * 支付接口设置service层
 *
 * @author sunluyang on 2017/5/17.
 */
public interface PaySetService {
    /**
     * 查询所有支付接口设置
     *
     * @return 返回PaySetCommon
     */
    PaySetCommon queryPaySet();

    /**
     * 编辑支付接口设置
     *
     * @return -1编辑出错 1成功
     */
    int editPaySet(PaySetCommon paySetCommon, String codeType);
}
