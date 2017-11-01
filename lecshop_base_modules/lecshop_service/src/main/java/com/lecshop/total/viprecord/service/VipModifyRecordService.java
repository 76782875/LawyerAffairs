package com.lecshop.total.viprecord.service;

import com.lecshop.total.viprecord.bean.VipModifyRecord;
import com.lecshop.utils.PageHelper;

import java.time.LocalDateTime;

/**
 * VIP修改记录service
 *
 * @author sunluyang on 2017/8/22.
 */
public interface VipModifyRecordService {
    /**
     * 添加VIP修改记录
     *
     * @param vipModifyRecord VIP修改记录实体类
     * @return 添加返回码
     */
    int addVipModifyRecord(VipModifyRecord vipModifyRecord);

    /**
     * 根据时间查询VIP修改记录
     *
     * @param time 时间
     * @return 查询VIP修改记录根据时间
     */
    VipModifyRecord queryVipModifyRecordByTime(LocalDateTime time, long companyId);

    /**
     * 根据时间查询VIP修改记录
     *
     * @param time 时间
     * @return 查询VIP修改记录根据时间
     */
    VipModifyRecord queryVipModifyRecordByTimeForLogin(LocalDateTime time, long companyId);

    /**
     * 分页查询VIP修改记录
     *
     * @param pageHelper  分页帮助类
     * @param companyName 公司名称
     * @return 公司会员信息
     */
    PageHelper<VipModifyRecord> queryVipModifyRecord(PageHelper<VipModifyRecord> pageHelper, String companyName);
}
