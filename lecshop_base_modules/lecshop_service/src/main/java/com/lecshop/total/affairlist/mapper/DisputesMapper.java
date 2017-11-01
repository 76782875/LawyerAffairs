package com.lecshop.total.affairlist.mapper;

import com.lecshop.total.affairlist.bean.Disputes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 纠纷处理mapper
 *
 * @author sunluyang on 2017/7/21.
 */
public interface DisputesMapper {
    /**
     * 根据条件查询纠纷
     *
     * @param map 查询条件
     * @return 预约会面集合
     */
    List<Disputes> queryDisputes(Map<String, Object> map);

    /**
     * 根据条件查询纠纷条数
     *
     * @param map 查询条件
     * @return 返回条数
     */
    int queryDisputesCount(Map<String, Object> map);

    /**
     * 添加纠纷
     *
     * @param disputes 纠纷
     * @return 添加返回码
     */
    int addDisputes(Disputes disputes);

    /**
     * 查询待处理的纠纷信息
     *
     * @param map 查询条件
     * @return 集合
     */
    List<Disputes> queryToDoDisputes(Map<String, Object> map);

    /**
     * 修改纠纷状态
     *
     * @param params 纠纷id及状态
     * @return 成功返回1，失败返回0
     */
    int changeDisputesStatus(Map<String, Object> params);

    /**
     * 为纠纷服务评分
     *
     * @param params 纠纷id及分数
     * @return 成功返回1，失败返回0
     */
    int gradeForDisputes(Map<String, Object> params);

    /**
     * 确认纠纷
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int confirmDispute(Map<String, Object> params);

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
