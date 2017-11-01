package com.lecshop.abutment.lawyer.service;

import com.lecshop.abutment.lawyer.bean.LawyerInfo;
import com.lecshop.abutment.lawyer.bean.SearchBean;
import com.lecshop.utils.PageHelper;

/**
 * 律师搜索接口service
 *
 * @author sunluyang on 2017/7/28.
 */
public interface SearchLawyerService {
    /**
     * 页面律师数据
     *
     * @param pageHelper 分页帮助类
     * @param searchBean 搜索条件
     * @return 律师数据
     */
    PageHelper searchLawyer(PageHelper<LawyerInfo> pageHelper, SearchBean searchBean);
}
