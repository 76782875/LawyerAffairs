package com.lecshop.total.viprecord.mapper;

import com.lecshop.total.viprecord.bean.VipModifyRecord;

import java.util.List;
import java.util.Map;

/**
 * vip修改记录mapper
 *
 * @author sunluyang on 2017/8/22.
 */
public interface VipModifyRecordMapper {
    /**
     * 添加VIP修改记录
     *
     * @param vipModifyRecord VIP修改记录
     * @return VIP修改记录
     */
    int addVipModifyRecord(VipModifyRecord vipModifyRecord);

    /**
     * 根据时间查询VIP修改记录
     *
     * @param map 时间/公司id
     * @return VIP修改记录
     */
    VipModifyRecord queryVipModifyRecordByTime(Map<String, Object> map);

    /**
     * 根据时间查询VIP修改记录
     *
     * @param map 时间/公司id
     * @return VIP修改记录
     */
    VipModifyRecord queryVipModifyRecordByTimeForLogin(Map<String, Object> map);

    /**
     * 分页查询VIP修改记录
     *
     * @param params 查询参数
     * @return VIP修改记录
     */
    List<VipModifyRecord> queryVipModifyRecord(Map<String, Object> params);

    /**
     * 查询VIP修改记录总记录数
     *
     * @param params 查询参数
     * @return VIP修改记录总记录数
     */
    int queryVipModifyRecordCount(Map<String, Object> params);
}
