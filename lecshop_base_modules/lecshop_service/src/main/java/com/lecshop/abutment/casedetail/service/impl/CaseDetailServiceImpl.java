package com.lecshop.abutment.casedetail.service.impl;

import com.lecshop.abutment.casedetail.bean.ResponseJsonz;
import com.lecshop.abutment.casedetail.service.CaseDetailService;
import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.InterfaceData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 案例细节service实现类
 *
 * @author sunluyang on 2017/8/2.
 */
@Service
public class CaseDetailServiceImpl implements CaseDetailService {

    /**
     * 调试日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(CaseDetailServiceImpl.class);

    /**
     * 查询案例细节
     *
     * @param id 案例id
     * @return 案例细节实体类
     */
    @Override
    public ResponseJsonz queryCaseDetail(String id) {
        LOGGER.info("queryCaseDetail and id:{}", id);
        return (ResponseJsonz) InterfaceData.getInterfaceDate(new ResponseJsonz(), getInterfaceUrl(id));
    }

    /**
     * 获取调用接口URL
     *
     * @return 调用接口URL
     */
    private String getInterfaceUrl(String id) {
        LOGGER.info("getInterfaceUrl and url:{}", CommonConstant.PAST_CASES_DETAIL_INTERFACE + "/" + id);
        return CommonConstant.PAST_CASES_DETAIL_INTERFACE + "/" + id;
    }
}
