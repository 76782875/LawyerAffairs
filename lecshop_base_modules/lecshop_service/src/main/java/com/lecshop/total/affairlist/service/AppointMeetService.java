package com.lecshop.total.affairlist.service;

import com.lecshop.abutment.nopointlawyer.bean.SearchBeanc;
import com.lecshop.total.affairlist.bean.AppointMeet;
import com.lecshop.utils.PageHelper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 预约会员service
 *
 * @author sunluyang on 2017/7/15.
 */
public interface AppointMeetService {
    /**
     * 分页查询预约会面
     *
     * @param pageHelper  分页帮助类
     * @param meetType    1 指定律师 2 没有指定律师
     * @param type        类型 1 公司事务 2公司纠纷 3 公司重大事项'
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 预约会面集合
     */
    PageHelper<AppointMeet> queryTelConsultation(PageHelper<AppointMeet> pageHelper, String meetType, String type, String lawyerName, String companyName, long companyId, long userId, long lawyerId);

    /**
     * 查询待处理的预约会面信息
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return 预约会面集合
     */
    List<AppointMeet> queryToDoAppointMeet(long companyId, long userId);

    /**
     * 抢单(预约会面)
     *
     * @param lawyerId 律师id
     * @param id       预约会面id
     * @return 成功返回1 失败返回0
     */
    int grabAppointMeet(long lawyerId, long id);

    /**
     * 确认预约会面
     *
     * @param id       主键id
     * @param status   状态
     * @param lawyerId 律师id
     * @param meetType 1 指定律师 2 没有指定律师
     * @return 成功返回1 失败返回0
     */
    int confirmAppointMeet(long id, String status, long lawyerId, String meetType);

    /**
     * 修改状态
     *
     * @param id     主键id
     * @param status 状态
     * @return 成功返回1 失败返回0
     */
    int changeStatus(long id, String status);

    /**
     * 添加预约会面
     *
     * @param appointMeet 预约会面
     * @return 返回添加码
     */
    int addAppointMeet(AppointMeet appointMeet, SearchBeanc searchBeanc);

    /**
     * 为预约会面评分
     *
     * @param id    会面id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    int gradeForMeeting(long id, int score);

    /**
     * 取消预约会面
     *
     * @param id 预约会面id
     * @return 返回码
     */
    int cancelAppointMeet(long id);

    /**
     * 根据时间查询从时间算起共有多少个预约咨询
     *
     * @param localDateTime 时间
     * @return 总个数
     */
    int queryAppointMeetCountByTime(LocalDateTime localDateTime, long companyId);

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
