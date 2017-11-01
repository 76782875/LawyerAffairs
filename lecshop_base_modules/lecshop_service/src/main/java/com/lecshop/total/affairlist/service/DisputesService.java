package com.lecshop.total.affairlist.service;

import com.lecshop.total.affairlist.bean.Disputes;
import com.lecshop.utils.PageHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author sunluyang on 2017/7/21.
 */
public interface DisputesService {
    /**
     * 分页查询纠纷事务
     *
     * @param pageHelper  分页帮助类
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 预约会面集合
     */
    PageHelper<Disputes> queryDisputes(PageHelper<Disputes> pageHelper, String lawyerName, String companyName, long companyId, long userId, long lawyerId);

    /**
     * 查询待处理的纠纷
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return 纠纷集合
     */
    List<Disputes> queryToDoDisputes(long companyId, long userId);

    /**
     * 添加纠纷
     *
     * @param disputes 纠纷
     * @return 添加返回码
     */
    int addDisputes(Disputes disputes, long companyId, long userId);

    /**
     * 修改纠纷状态
     *
     * @param id     纠纷id
     * @param status 状态
     * @return 成功返回1，失败返回0
     */
    int changeDisputesStatus(long id, String status);

    /**
     * 为纠纷服务评分
     *
     * @param id    纠纷id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    int gradeForDisputes(long id, int score);

    /**
     * 确认纠纷
     *
     * @param id    主键id
     * @param money 金额
     * @return 成功返回1 失败返回0
     */
    int confirmDispute(long id, BigDecimal money);

    /**
     * 根据纠纷id查询纠纷信息
     *
     * @param id 纠纷id
     * @return 纠纷信息
     */
    Disputes queryDisputesById(long id);


    /**
     * 查询律师的纠纷订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 纠纷订单数目
     */
    int queryDisputesOrderCount(Map<String, Object> params);

    /**
     * 查询公司发起的纠纷订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 纠纷订单数目
     */
    int queryDisputesOrderCountOfCompany(Map<String, Object> params);
}
