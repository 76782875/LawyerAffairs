package com.lecshop.admin.profitset.service.impl;

import com.lecshop.admin.profitset.bean.ProfitSet;
import com.lecshop.admin.profitset.mapper.ProfitSetMapper;
import com.lecshop.admin.profitset.service.ProfitSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 律师收益设置service实现类
 *
 * @author sunluyang on 2017/7/11.
 */
@Service
public class ProfitSetServiceImpl implements ProfitSetService {

    /**
     * 注入律师收益设置mapper
     */
    @Autowired
    private ProfitSetMapper profitSetMapper;

    /**
     * 查询律师收益设置
     *
     * @return 律师收益设置实体类
     */
    @Override
    public ProfitSet queryProfitSet() {
        return profitSetMapper.queryProfitSet();
    }

    /**
     * 根据条件进行更新律师收益设置信息
     *
     * @param type      1 电话咨询 2 预约会面 3 预约会面指定律师 4 提现设置
     * @param profitSet 收益设置实体类
     * @return 编辑返回码
     */
    @Override
    public int editProfitSet(int type, ProfitSet profitSet) {
        Map<String, Object> map = new HashMap<>();
        map.put("param", type);
        map.put("profitSet", profitSet);
        return profitSetMapper.editProfitSet(map);
    }
}
