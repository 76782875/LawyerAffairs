package com.lecshop.total.lawyer.mapper;

import com.lecshop.total.lawyer.bean.LawyerProfit;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 律师收益数据库接口
 *
 * Created by LecShop on 2017/8/22.
 */
@Repository
public interface LawyerProfitMapper {

    /**
     * 查询律师收益
     *
     * @param params 查询参数
     * @return 律师收益
     */
    List<LawyerProfit> queryLawyerProfit(Map<String, Object> params);

    /**
     * 查询律师总数
     *
     * @param params 查询参数
     * @return 律师总数
     */
    int queryLawyerCount(Map<String, Object> params);
}
