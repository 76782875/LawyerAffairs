package com.lecshop.admin.payset.mapper;

import com.lecshop.admin.payset.bean.PaySet;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 支付接口设置mapper层
 *
 * @author sunluyang on 2017/5/17.
 */
@Repository
public interface PaySetMapper {
    /**
     * 查询所有支付接口设置
     *
     * @return 返回PaySet集合
     */
    List<PaySet> queryPaySet();

    /**
     * 根据codeType删除支付接口设置
     *
     * @param codeType 1支付宝 2微信 3银联
     * @return 返回删除行数
     */
    int deletePaySet(String codeType);

    /**
     * 批量插入支付接口设置
     *
     * @param list PaySet集合
     * @return 返回插入行数
     */
    int addPaySet(List<PaySet> list);
}
