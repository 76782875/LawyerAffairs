package com.lecshop.total.affairlist.mapper;

import com.lecshop.total.affairlist.bean.ModifyDoc;

import java.util.List;
import java.util.Map;

/**
 * 文书事务mapper
 *
 * @author sunluyang on 2017/7/12.
 */
public interface ModifyDocMapper {
    /**
     * 查询文书事务
     *
     * @param map 查询条件
     * @return 返回文书集合
     */
    List<ModifyDoc> queryModifyDoc(Map<String, Object> map);

    /**
     * 查询文书事务总条数
     *
     * @param map 查询条件
     * @return 返回文书条数
     */
    int queryModifyDocCount(Map<String, Object> map);

    /**
     * 查询待处理的修改文书信息
     *
     * @param map 查询条件
     * @return 修改文书集合
     */
    List<ModifyDoc> queryToDoModifyDoc(Map<String, Object> map);

    /**
     * 修改文书
     *
     * @param modifyDoc 文书
     * @return 成功返回1，失败返回0
     */
    int addModifyDoc(ModifyDoc modifyDoc);

    /**
     * 修改文书状态
     *
     * @param params 文书id及状态
     * @return 成功返回1，失败返回0
     */
    int changeModifyDocStatus(Map<String, Object> params);

    /**
     * 为修改文书评分
     *
     * @param params 文书id及分数
     * @return 成功返回1，失败返回0
     */
    int gradeForModifyDoc(Map<String, Object> params);

    /**
     * 根据时间和公司id查询修改文书个数
     *
     * @param map 查询条件
     * @return 总数
     */
    int queryModifyDocCountByTime(Map<String, Object> map);


    /**
     * 查询律师的修改文书订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 修改文书订单数目
     */
    int queryModifyDocOrderCount(Map<String, Object> params);

    /**
     * 查询公司发起的修改文书订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 修改文书订单数目
     */
    int queryModifyDocOrderCountOfCompany(Map<String, Object> params);
}
