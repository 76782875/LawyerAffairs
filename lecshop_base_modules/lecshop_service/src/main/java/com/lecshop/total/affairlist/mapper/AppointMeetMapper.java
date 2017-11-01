package com.lecshop.total.affairlist.mapper;

import com.lecshop.total.affairlist.bean.AppointMeet;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 预约会员mapper
 *
 * @author sunluyang on 2017/7/15.
 */
@Repository
public interface AppointMeetMapper {

    /**
     * 根据提交查询预约会面
     *
     * @param map 查询条件
     * @return 预约会面集合
     */
    List<AppointMeet> queryAppointMeet(Map<String, Object> map);

    /**
     * 根据条件查询预约会面条数
     *
     * @param map 查询条件
     * @return 返回条数
     */
    int queryAppointMeetCount(Map<String, Object> map);

    /**
     * 查询待处理的预约会面信息
     *
     * @param map 查询条件
     * @return 1
     */
    List<AppointMeet> queryToDoAppointMeet(Map<String, Object> map);

    /**
     * 抢单
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int grabAppointMeet(Map<String, Object> params);

    /**
     * 改变状态
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int changeStatus(Map<String, Object> params);

    /**
     * 添加预约会面
     *
     * @param appointMeet 预约会面
     * @return 返回添加码
     */
    int addAppointMeet(AppointMeet appointMeet);

    /**
     * 为预约会面评分
     *
     * @param params 会面id及分数
     * @return 成功返回1，失败返回0
     */
    int gradeForMeeting(Map<String, Object> params);

    /**
     * 根据预约咨询id查询预约信息
     *
     * @param id 预约咨询id
     * @return 预约咨询信息
     */
    AppointMeet queryAppointMeetById(long id);

    /**
     * 根据时间和公司id查询从时间算起共有多少个预约咨询
     *
     * @param map 查询条件
     * @return 总个数
     */
    int queryAppointMeetCountByTime(Map<String, Object> map);

    /**
     * 查询律师的预约会面订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 律师的预约会面订单数目
     */
    int queryMeetingOrderCount(Map<String, Object> params);

    /**
     * 查询公司发起的预约会面订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 公司发起的预约会面订单数目
     */
    int queryMeetingOrderCountOfCompany(Map<String, Object> params);

}
