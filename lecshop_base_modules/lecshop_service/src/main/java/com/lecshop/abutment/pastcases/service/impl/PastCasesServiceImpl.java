package com.lecshop.abutment.pastcases.service.impl;


import com.lecshop.abutment.pastcases.bean.ResponseJsons;
import com.lecshop.abutment.pastcases.bean.SearchBeans;
import com.lecshop.abutment.pastcases.service.PastCasesService;
import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.InterfaceData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 过往案例service实现类
 *
 * @author sunluyang on 2017/8/1.
 */
@Service
public class PastCasesServiceImpl implements PastCasesService {

    /**
     * 调试日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(PastCasesServiceImpl.class);

    /**
     * 查询过往案例
     *
     * @param searchBeans 查询条件
     * @return 结果集
     */
    @Override
    public ResponseJsons queryPastCases(SearchBeans searchBeans) {
        LOGGER.info("queryPastCases and searchBeans:{}", searchBeans);
        return (ResponseJsons) InterfaceData.getInterfaceDate(new ResponseJsons(), getInterfaceUrl(searchBeans));
    }

    /**
     * 获取调用接口URL
     *
     * @param searchBeans 接口参数
     * @return 调用接口URL
     */
    private String getInterfaceUrl(SearchBeans searchBeans) {
        StringBuilder stringBuilder = new StringBuilder(CommonConstant.PAST_CASES_INTERFACE + "/");
        if (StringUtils.isEmpty(searchBeans.getId()) || "null".equals(searchBeans.getId())) {
            stringBuilder.append("null").append("/judgment").append("?name=")
                    .append(searchBeans.getName()).append("&institution=").append(searchBeans.getInstitution());
        } else {
            stringBuilder.append(searchBeans.getId()).append("/judgment");
        }
        LOGGER.info("getInterfaceUrl and searchBeans:{}\r\n url:{}", searchBeans, stringBuilder.toString());
        return stringBuilder.toString();
    }
}
