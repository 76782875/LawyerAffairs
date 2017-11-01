package com.lecshop.total.lawyer.service;

import com.lecshop.total.lawyer.bean.LawyerProfit;
import com.lecshop.utils.PageHelper;

import java.util.List;
import java.util.Map;

/**
 * 律师收益service
 *
 * Created by LecShop on 2017/8/21.
 */
public interface LawyerProfitService {

    /**
     * 分页查询律师收益
     *
     * @param pageHelper 分页帮助类
     * @param startTime  起始时间
     * @param endTime    终止时间
     * @return  律师收益
     */
    PageHelper<LawyerProfit> queryLawyerProfit(PageHelper<LawyerProfit> pageHelper, String startTime, String endTime);
}
