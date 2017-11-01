package com.lecshop.total.renewrecord.service.impl;

import com.lecshop.total.renewrecord.bean.RenewRecord;
import com.lecshop.total.renewrecord.mapper.RenewRecordMapper;
import com.lecshop.total.renewrecord.service.RenewRecordService;
import com.lecshop.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 续费记录service实现类
 * <p>
 * Created by LecShop on 2017/7/13.
 */
@Service
public class RenewRecordServiceImpl implements RenewRecordService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(RenewRecordServiceImpl.class);

    /**
     * 自动注入续费记录数据库接口
     */
    @Autowired
    private RenewRecordMapper renewRecordMapper;

    /**
     * 分页查询续费记录
     *
     * @param pageHelper 分页帮助类
     * @param name       公司名称
     * @param orderNo    订单号
     * @return 续费记录
     */
    @Override
    public PageHelper<RenewRecord> queryRenewRecordList(PageHelper<RenewRecord> pageHelper, String name, String orderNo) {
        logger.debug("queryRenewRecordList and pageHelper :{} \r\n name:{}\r\n orderNo:{} ", pageHelper, name, orderNo);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("orderNo", orderNo);
        return pageHelper.setListDates(renewRecordMapper.queryRenewRecordList(pageHelper.getQueryParams(params, renewRecordMapper.queryRenewRecordCount(params))));
    }

    /**
     * 根据订单号查询订单状态
     *
     * @param orderNo 订单编号
     * @return 续费记录
     */
    @Override
    public RenewRecord queryRenewRecordByOrderNo(String orderNo) {
        logger.debug("queryRenewRecordByOrderNo and orderNo :{}", orderNo);
        return renewRecordMapper.queryRenewRecordByOrderNo(orderNo);
    }

    /**
     * 根据订单号编辑订单状态
     *
     * @param orderNo 订单号
     * @return 编辑返回码
     */
    @Override
    public int editRenewRecordByOrderNo(String orderNo) {
        logger.debug("editRenewRecordByOrderNo and orderNo :{}", orderNo);
        return renewRecordMapper.editRenewRecordByOrderNo(orderNo);
    }

    /**
     * 添加续费订单
     *
     * @param renewRecord 续费实体类
     * @return 添加返回码
     */
    @Override
    public int addRenewRecord(RenewRecord renewRecord) {
        logger.debug("addRenewRecord and renewRecord :{}", renewRecord);
        return renewRecordMapper.addRenewRecord(renewRecord);
    }

    /**
     * 分页查询公司会员续费记录
     *
     * @param userId     用户id
     * @param companyId  公司id
     * @param pageHelper 分页帮助类
     * @return 公司会员续费记录
     */
    @Override
    public PageHelper<RenewRecord> queryYourselfRenewRecord(PageHelper<RenewRecord> pageHelper, long userId, long companyId) {
        logger.debug("queryMemberRenewRecord and pageHelper :{} \r\n and userId :{} \r\n and companyId :{}", pageHelper, userId, companyId);
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        params.put("companyId", companyId);
        return pageHelper.setListDates(renewRecordMapper.queryYourselfRenewRecord(pageHelper.getQueryParams(params, renewRecordMapper.queryYourselfRenewRecordCount(params))));
    }

    /**
     * 查询消费情况
     *
     * @param companyId    公司id
     * @param userId       用户id
     * @param todayOrTotal 0 今天 1 总共
     * @return 返回消费金额
     */
    @Override
    public BigDecimal queryConsumption(long companyId, long userId, int todayOrTotal) {
        logger.debug("queryConsumption and companyId:{}\r\n userId:{}\r\ntodayOrTotal:{}", companyId, userId, todayOrTotal);
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", companyId);
        map.put("userId", userId);
        map.put("todayOrTotal", todayOrTotal);
        return renewRecordMapper.queryConsumption(map);
    }

    /**
     * 根据公司id查询续费总金额
     *
     * @param params 公司id、起始时间及终止时间
     * @return 续费总金额
     */
    @Override
    public BigDecimal querySumMoneyByCompanyId(Map<String, Object> params) {
        logger.debug("querySumMoneyByCompanyId and params :{}", params);
        return renewRecordMapper.querySumMoneyByCompanyId(params);
    }
}
