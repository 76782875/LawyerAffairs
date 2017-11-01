package com.lecshop.total.affairlist.mapper;

import com.lecshop.total.affairlist.bean.ImportantMatter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 重大事项mapper
 *
 * @author sunluyang on 2017/7/21.
 */
public interface ImportantMatterMapper {
    /**
     * 根据条件查询重大事项
     *
     * @param map 查询条件
     * @return 重大事项集合
     */
    List<ImportantMatter> queryImportantMatter(Map<String, Object> map);

    /**
     * 根据条件查询重大事项条数
     *
     * @param map 查询条件
     * @return 返回条数
     */
    int queryImportantMatterCount(Map<String, Object> map);

    /**
     * 查询待处理的重大事项信息
     *
     * @param map 查询条件
     * @return 重大事项集合
     */
    List<ImportantMatter> queryToDoImportantMatter(Map<String, Object> map);

    /**
     * 修改重大事项状态
     *
     * @param params 重大事项id及状态
     * @return 成功返回1，失败返回0
     */
    int changeImportantMatterStatus(Map<String, Object> params);

    /**
     * 为重大事项评分
     *
     * @param params 重大事项id及分数
     * @return 成功返回1，失败返回0
     */
    int gradeForImportantMatter(Map<String, Object> params);

    /**
     * 律师确认订单
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int confirmImportantMatter(Map<String, Object> params);

    /**
     * 根据重大事项id查询重大事项信息
     *
     * @param id 重大事项id
     * @return 重大事项信息
     */
    ImportantMatter queryImportantMatterById(long id);


    /**
     * 添加重大事项
     *
     * @param importantMatter 重大事项
     * @return 添加返回码
     */
    int addImportantMatter(ImportantMatter importantMatter);


    /**
     * 查询律师的重大事项订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 重大事项订单数目
     */
    int queryImportantMatterOrderCount(Map<String, Object> params);

    /**
     * 查询公司发起的的重大事项订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 重大事项订单数目
     */
    int queryImportantMatterOrderCountOfCompany(Map<String, Object> params);
}
