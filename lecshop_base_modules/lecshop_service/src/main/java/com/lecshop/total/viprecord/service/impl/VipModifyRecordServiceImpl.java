package com.lecshop.total.viprecord.service.impl;

import com.lecshop.total.viprecord.bean.VipModifyRecord;
import com.lecshop.total.viprecord.mapper.VipModifyRecordMapper;
import com.lecshop.total.viprecord.service.VipModifyRecordService;
import com.lecshop.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * VIP修改记录service
 *
 * @author sunluyang on 2017/8/22.
 */
@Service
public class VipModifyRecordServiceImpl implements VipModifyRecordService {

    /**
     * 调试日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(VipModifyRecordServiceImpl.class);
    /**
     * 注入VIP修改记录mapper
     */
    @Autowired
    private VipModifyRecordMapper vipModifyRecordMapper;

    /**
     * 添加VIP修改记录
     *
     * @param vipModifyRecord VIP修改记录实体类
     * @return 添加返回码
     */
    @Override
    public int addVipModifyRecord(VipModifyRecord vipModifyRecord) {
        LOGGER.info("addVipModifyRecord and vipModifyRecord:{}", vipModifyRecord);
        return vipModifyRecordMapper.addVipModifyRecord(vipModifyRecord);
    }

    /**
     * 根据时间查询VIP修改记录
     *
     * @param time 时间
     * @return 查询VIP修改记录根据时间
     */
    @Override
    public VipModifyRecord queryVipModifyRecordByTime(LocalDateTime time, long companyId) {
        LOGGER.info("queryVipModifyRecordByTime and time:{}\r\n companyId:{}", time, companyId);
        Map<String, Object> map = new HashMap<>();
        map.put("time", time);
        map.put("companyId", companyId);
        return vipModifyRecordMapper.queryVipModifyRecordByTime(map);
    }

    /**
     * 根据时间查询VIP修改记录
     *
     * @param time 时间
     * @return 查询VIP修改记录根据时间
     */
    @Override
    public VipModifyRecord queryVipModifyRecordByTimeForLogin(LocalDateTime time, long companyId) {
        LOGGER.info("queryVipModifyRecordByTimeForLogin and time:{}\r\n companyId:{}", time, companyId);
        Map<String, Object> map = new HashMap<>();
        map.put("time", time);
        map.put("companyId", companyId);
        return vipModifyRecordMapper.queryVipModifyRecordByTimeForLogin(map);
    }

    /**
     * 分页查询VIP修改记录
     *
     * @param pageHelper  分页帮助类
     * @param companyName 公司名称
     * @return 公司会员信息
     */
    @Override
    public PageHelper<VipModifyRecord> queryVipModifyRecord(PageHelper<VipModifyRecord> pageHelper, String companyName) {
        LOGGER.info("queryVipModifyRecord and pageHelper:{}\r\n companyName:{}", pageHelper, companyName);
        Map<String, Object> params = new HashMap<>();
        params.put("companyName", companyName);
        return pageHelper.setListDates(vipModifyRecordMapper.queryVipModifyRecord(pageHelper.getQueryParams(params, vipModifyRecordMapper.queryVipModifyRecordCount(params))));
    }
}
