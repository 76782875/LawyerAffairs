package com.lecshop.total.affairlist.mapper;

import com.lecshop.total.affairlist.bean.TelConsultation;

import java.util.List;
import java.util.Map;

/**
 * 电话咨询mapper
 *
 * @author sunluyang on 2017/7/15.
 */
public interface TelConsultationMapper {

    /**
     * 按照条件查询电话咨询条数
     *
     * @param map 查询条件
     * @return 返回条数
     */
    int queryTelConsultationCount(Map<String, Object> map);

    /**
     * 按照条件查询电话咨询
     *
     * @param map 查询条件
     * @return 返回查询集合
     */
    List<TelConsultation> queryTelConsultation(Map<String, Object> map);

    /**
     * 为电话咨询评分
     *
     * @param params 电话咨询id及分数
     * @return 成功返回1，失败返回0
     */
    int gradeForTelConsultation(Map<String, Object> params);

    /**
     * 添加电话咨询
     *
     * @param telConsultation 电话咨询实体类
     * @return 添加返回码
     */
    int addTelConsultation(TelConsultation telConsultation);

    /**
     * 改变状态
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int changeStatus(Map<String, Object> params);

    /**
     * 根据时间和公司id查询电话咨询数量
     *
     * @param map 查询条件
     * @return 总数
     */
    int queryTelConsultationCountByTime(Map<String, Object> map);


    /**
     * 查询律师的电话咨询订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 律师的电话咨询订单数目
     */
    int queryTelConsultationOrderCount(Map<String, Object> params);

    /**
     * 查询公司发起的电话咨询订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 公司发起的电话咨询订单数目
     */
    int queryTelConsultationOrderCountOfCompany(Map<String, Object> params);
}
