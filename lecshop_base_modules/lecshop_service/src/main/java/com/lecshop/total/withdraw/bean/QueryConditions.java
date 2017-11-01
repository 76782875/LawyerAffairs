package com.lecshop.total.withdraw.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dujinkai on 17/7/26.
 * 提现记录查询条件
 */
@Data
public class QueryConditions {

    /**
     * 律师id
     */
    private long lawyerId;

    /**
     * 律师名称
     */
    private String name;

    /**
     * 状态  状态 0 申请  1 审核通过 2 拒绝 3 已打款
     */
    private String status;

    /**
     * 交易流水号
     */
    private String tradeNo;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 获得查询的map
     *
     * @return 返回查询的map
     */
    public Map<String, Object> getQueryMap() {
        Map<String, Object> params = new HashMap<>();
        params.put("lawyerId", lawyerId);
        params.put("name", name);
        params.put("status", status);
        params.put("tradeNo", tradeNo);
        params.put("mobile", mobile);
        return params;
    }
}
