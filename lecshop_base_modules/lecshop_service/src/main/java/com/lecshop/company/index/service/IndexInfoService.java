package com.lecshop.company.index.service;


import com.lecshop.company.index.bean.IndexInfo;

/**
 * 首页待处理师傅service
 *
 * @author sunluyang on 2017/7/21.
 */
public interface IndexInfoService {
    /**
     * 首页待处理事务
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @param type      类型
     * @return 返回待处理事务
     */
    IndexInfo toDoAffairs(long companyId, long userId, String type);
}
