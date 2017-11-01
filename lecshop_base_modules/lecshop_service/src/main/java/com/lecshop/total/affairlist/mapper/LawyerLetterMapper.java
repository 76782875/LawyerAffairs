package com.lecshop.total.affairlist.mapper;

import com.lecshop.total.affairlist.bean.LawyerLetter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 律师函数据库接口
 * <p>
 * Created by LecShop on 2017/7/19.
 */
@Repository
public interface LawyerLetterMapper {

    /**
     * 根据条件查询纠纷
     *
     * @param map 查询条件
     * @return 预约会面集合
     */
    List<LawyerLetter> queryLawyerLetter(Map<String, Object> map);

    /**
     * 根据条件查询纠纷条数
     *
     * @param map 查询条件
     * @return 返回条数
     */
    int queryLawyerLetterCount(Map<String, Object> map);

    /**
     * 查询待处理的律师函信息
     *
     * @param map 查询条件
     * @return 律师函集合
     */
    List<LawyerLetter> queryToDoLawyerLetter(Map<String, Object> map);

    /**
     * 提交律师函
     *
     * @param lawyerLetter 律师函
     * @return 成功返回1，失败返回0
     */
    int addLawyerLetter(LawyerLetter lawyerLetter);

    /**
     * 修改律师涵状态
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int changeLawyerLetterStatus(Map<String, Object> params);

    /**
     * 为律师函评分
     *
     * @param params 律师函id及评分
     * @return 成功返回1，失败返回0
     */
    int gradeForLawyerLetter(Map<String, Object> params);


    /**
     * 查询律师的律师函订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 律师函订单数目
     */
    int queryLawyerLetterOrderCount(Map<String, Object> params);

    /**
     * 查询公司的律师函订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 律师函订单数目
     */
    int queryLawyerLetterOrderCountOfCompany(Map<String, Object> params);
}
