package com.lecshop.total.renewrecord.service;

import com.lecshop.total.renewrecord.bean.RenewRecord;
import com.lecshop.utils.PageHelper;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 续费记录service
 * <p>
 * Created by LecShop on 2017/7/13.
 */
public interface RenewRecordService {

    /**
     * 分页查询续费记录
     *
     * @param pageHelper 分页帮助类
     * @param name       公司名称
     * @param orderNo    订单号
     * @return 续费记录
     */
    PageHelper<RenewRecord> queryRenewRecordList(PageHelper<RenewRecord> pageHelper, String name, String orderNo);

    /**
     * 根据订单号查询订单状态
     *
     * @param orderNo 订单编号
     * @return 续费记录
     */
    RenewRecord queryRenewRecordByOrderNo(String orderNo);

    /**
     * 根据订单号编辑订单状态
     *
     * @param orderNo 订单号
     * @return 编辑返回码
     */
    int editRenewRecordByOrderNo(String orderNo);

    /**
     * 添加续费订单
     *
     * @param renewRecord 续费实体类
     * @return 添加返回码
     */
    int addRenewRecord(RenewRecord renewRecord);

    /**
     * 分页查询公司会员续费记录
     *
     * @param userId 用户id
     * @param companyId 公司id
     * @param pageHelper 分页帮助类
     * @return 公司会员续费记录
     */
    PageHelper<RenewRecord> queryYourselfRenewRecord(PageHelper<RenewRecord> pageHelper, long userId, long companyId);

    /**
     * 查询消费情况
     *
     * @param companyId    公司id
     * @param userId       用户id
     * @param todayOrTotal 0 今天 1 总共
     * @return 返回消费金额
     */
    BigDecimal queryConsumption(long companyId, long userId, int todayOrTotal);

    /**
     * 根据公司id查询续费总金额
     *
     * @param params 公司id、起始时间及终止时间
     * @return 续费总金额
     */
    BigDecimal querySumMoneyByCompanyId(Map<String, Object> params);
}
