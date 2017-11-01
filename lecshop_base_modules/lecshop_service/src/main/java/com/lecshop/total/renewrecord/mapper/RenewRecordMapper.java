package com.lecshop.total.renewrecord.mapper;

import com.lecshop.total.renewrecord.bean.RenewRecord;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 续费记录数据库接口
 * <p>
 * Created by LecShop on 2017/7/13.
 */
@Repository
public interface RenewRecordMapper {

    /**
     * 分页查询续费记录
     *
     * @param params 查询参数
     * @return 续费记录集合
     */
    List<RenewRecord> queryRenewRecordList(Map<String, Object> params);

    /**
     * 查询续费记录总记录数
     *
     * @param params 公司名称
     * @return 续费记录集合
     */
    int queryRenewRecordCount(Map<String, Object> params);

    /**
     * 根据订单号查询订单
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
     * 查询会员续费记录集合
     * @param params 查询参数
     * @return 会员续费记录集合
     */
    List<RenewRecord> queryYourselfRenewRecord(Map<String, Object> params);

    /**
     * 查询会员续费记录总记录数
     *
     * @param params 查询参数
     * @return 会员续费记录总记录数
     */
    int queryYourselfRenewRecordCount(Map<String, Object> params);

    /**
     * 查询消费情况
     *
     * @param map 查询条件
     * @return 返回值
     */
    BigDecimal queryConsumption(Map<String, Object> map);

    /**
     * 根据公司id查询续费总金额
     *
     * @param params 公司id、起始时间及终止时间
     * @return 续费总金额
     */
    BigDecimal querySumMoneyByCompanyId(Map<String, Object> params);
}
