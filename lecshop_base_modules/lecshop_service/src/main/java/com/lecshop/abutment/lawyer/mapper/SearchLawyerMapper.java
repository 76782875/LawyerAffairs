package com.lecshop.abutment.lawyer.mapper;

import org.springframework.stereotype.Repository;

/**
 * 律师搜索接口组装数据mapper
 *
 * @author sunluyang on 2017/7/28.
 */
@Repository
public interface SearchLawyerMapper {

    /**
     * 根据律师id计算所有事务平均评分
     *
     * @param lawyerId 律师id
     * @return 平均评分
     */
    int queryAvgScoreByLawyerId(long lawyerId);
}
