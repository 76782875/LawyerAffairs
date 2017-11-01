package com.lecshop.total.affairlist.service;

import com.lecshop.total.affairlist.bean.TelConsultation;
import com.lecshop.utils.PageHelper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 电话咨询service
 *
 * @author sunluyang on 2017/7/15.
 */
public interface TelConsultationService {
    /**
     * 分页查询电话咨询
     *
     * @param pageHelper  分页帮助类
     * @param type        类型 1 公司事务 2公司纠纷 3 公司重大事项
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @param lawyerId    律师id
     * @return 返回电话咨询集合
     */
    PageHelper<TelConsultation> queryTelConsultation(PageHelper<TelConsultation> pageHelper, String type, String lawyerName, String companyName, long companyId, long userId, long lawyerId);

    /**
     * 为电话咨询评分
     *
     * @param id    电话咨询id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    int gradeForTelConsultation(long id, int score);

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
     * @param id     主键id
     * @param status 状态
     * @return 成功返回1 失败返回0
     */
    int changeStatus(long id, String status);

    /**
     * 确认电话咨询
     *
     * @param id       电话咨询id
     * @param status   电话咨询状态
     * @param lawyerId 律师id
     * @return 成功返回1，失败0
     */
    int confirmTelConsultation(long id, String status, long lawyerId);

    /**
     * 根据时间和公司id 查询电话咨询总数
     *
     * @param localDateTime 时间
     * @param companyId     公司id
     * @return 总数
     */
    int queryTelConsultationCountByTime(LocalDateTime localDateTime, long companyId, String type);


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
