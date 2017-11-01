package com.lecshop.abutment.lawyer.service.impl;

import com.lecshop.abutment.lawyer.bean.LawyerInfo;
import com.lecshop.abutment.lawyer.bean.ResponseJson;
import com.lecshop.abutment.lawyer.bean.SearchBean;
import com.lecshop.abutment.lawyer.mapper.SearchLawyerMapper;
import com.lecshop.abutment.lawyer.service.SearchLawyerService;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.lawyer.service.LawyerService;
import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.InterfaceData;
import com.lecshop.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * 律师搜索接口service实现类
 *
 * @author sunluyang on 2017/7/28.
 */
@Service
public class SearchLawyerServiceImpl implements SearchLawyerService {

    /**
     * 调试日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(SearchLawyerServiceImpl.class);

    /**
     * 搜索律师mapper
     */
    @Autowired
    private SearchLawyerMapper searchLawyerMapper;
    /**
     * 律师service
     */
    @Autowired
    private LawyerService lawyerService;

    /**
     * 页面律师数据
     *
     * @param pageHelper 分页帮助类
     * @param searchBean 搜索条件
     * @return 律师数据
     */
    @Override
    public PageHelper<LawyerInfo> searchLawyer(PageHelper<LawyerInfo> pageHelper, SearchBean searchBean) {
        LOGGER.info("searchLawyer and pageHelper:{}\r\n searchBean:{}", pageHelper, searchBean);
        ResponseJson responseJson = (ResponseJson) InterfaceData.getInterfaceDate(new ResponseJson(), getInterfaceUrl(searchBean.buildPage(pageHelper)));
        pageHelper.getQueryParams(new HashMap<>(), responseJson.getTotalcount());
        List<LawyerInfo> lawyerInfoList = new ArrayList<>();
        if (interfaceIsTrue(responseJson)) {
            responseJson.getA_list().forEach(lawyerInfo -> {
                if (StringUtils.isEmpty(lawyerInfo.getCode())) {
                    lawyerInfoList.add(lawyerInfo);
                } else {
                    Lawyer lawyer = lawyerService.queryLawyerByCode(lawyerInfo.getCode());
                    if (Objects.nonNull(lawyer)) {
                        lawyerInfoList.add(lawyerInfo.getRestructureLawyerList(lawyerInfo, lawyer, searchLawyerMapper.queryAvgScoreByLawyerId(lawyer.getId())));
                    } else {
                        lawyerInfoList.add(lawyerInfo);
                    }
                }

            });
        }
        return pageHelper.setListDates(lawyerInfoList);
    }

    /**
     * 获取调用接口URL
     *
     * @param searchBean 接口参数
     * @return 调用接口URL
     */
    private String getInterfaceUrl(SearchBean searchBean) {
        StringBuilder param = new StringBuilder("");
        if (!StringUtils.isEmpty(searchBean.getKeywords())) {
            param.append("&keywords=").append(searchBean.getKeywords());
        }
        if (!StringUtils.isEmpty(searchBean.getCasetype())) {
            param.append("&casetype=").append(searchBean.getCasetype());
        }
        if (!StringUtils.isEmpty(searchBean.getCasetypefree())) {
            param.append("&casetypefree=").append(searchBean.getCasetypefree());
        }
        LOGGER.info("getInterfaceUrl and searchBean:{}\r\n url:{}", searchBean, CommonConstant.LAWYER_SEARCH_INTERFACE + "?pi=" + searchBean.getPi() + "&ps=" + searchBean.getPs() + param);
        return CommonConstant.LAWYER_SEARCH_INTERFACE + "?pi=" + searchBean.getPi() + "&ps=" + searchBean.getPs() + param;
    }

    /**
     * 接口是否调用成功
     *
     * @param responseJson 接口返回数据
     * @return true 成功 false 失败
     */
    private boolean interfaceIsTrue(ResponseJson responseJson) {
        return responseJson.getResult() == 1 && !CollectionUtils.isEmpty(responseJson.getA_list()) && StringUtils.isEmpty(responseJson.getError());
    }
}
