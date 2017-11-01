package com.lecshop.admin.profitset.mapper;

import com.lecshop.admin.profitset.bean.ProfitSet;

import java.util.Map;

/**
 * 律师收益设置数据库层
 *
 * @author sunluyang on 2017/7/11.
 */
public interface ProfitSetMapper {

    /**
     * 查询律师收益设置
     *
     * @return 律师收益设置实体类
     */
    ProfitSet queryProfitSet();

    /**
     * 根据条件进行更新律师收益设置信息
     *
     * @param map 更新条件和要更新的内容
     * @return 编辑返回码
     */
    int editProfitSet(Map<String, Object> map);
}
