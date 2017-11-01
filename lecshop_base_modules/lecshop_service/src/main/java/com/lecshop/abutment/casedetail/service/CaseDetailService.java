package com.lecshop.abutment.casedetail.service;

import com.lecshop.abutment.casedetail.bean.ResponseJsonz;

/**
 * 案例细节service实现类
 *
 * @author sunluyang on 2017/8/2.
 */
public interface CaseDetailService {
    /**
     * 查询案例细节
     *
     * @param id 案例id
     * @return 案例细节实体类
     */
    ResponseJsonz queryCaseDetail(String id);
}
