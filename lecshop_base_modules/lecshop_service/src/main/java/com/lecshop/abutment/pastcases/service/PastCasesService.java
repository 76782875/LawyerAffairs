package com.lecshop.abutment.pastcases.service;

import com.lecshop.abutment.pastcases.bean.ResponseJsons;
import com.lecshop.abutment.pastcases.bean.SearchBeans;

/**
 * 过往案例service接口
 *
 * @author sunluyang on 2017/8/1.
 */
public interface PastCasesService {
    /**
     * 获取调用接口查询过往案例
     *
     * @return 过往案例
     */
    ResponseJsons queryPastCases(SearchBeans searchBeans);
}
