package com.lecshop.total.affairlist.mapper;

import com.lecshop.total.affairlist.bean.DraftDoc;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 草拟文书数据库接口
 * <p>
 * Created by LecShop on 2017/7/18.
 */
@Repository
public interface DraftDocMapper {

    /**
     * 查询草拟文书
     *
     * @param map 查询条件
     * @return 返回草拟文书集合
     */
    List<DraftDoc> queryDraftDoc(Map<String, Object> map);

    /**
     * 查询草拟文书总条数
     *
     * @param map 查询条件
     * @return 返回草拟文书条数
     */
    int queryDraftDocCount(Map<String, Object> map);

    /**
     * 查询待处理的草拟信息
     *
     * @param map 查询条件
     * @return 草拟信息集合
     */
    List<DraftDoc> queryToDoDraftDoc(Map<String, Object> map);

    /**
     * 添加草拟文书
     *
     * @param draftDoc 草拟文书
     * @return 成功返回1，失败返回0
     */
    int addDraftDoc(DraftDoc draftDoc);

    /**
     * 修改草拟文书状态
     *
     * @param params 文书id及状态
     * @return 成功返回1，失败返回0
     */
    int updateDraftDocStatus(Map<String, Object> params);

    /**
     * 为草拟文书评分
     *
     * @param params 文书id及分数
     * @return 成功返回1，失败返回0
     */
    int gradeForDraftDoc(Map<String, Object> params);


    /**
     * 查询律师的草拟文书订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 草拟文书订单数目
     */
    int queryDraftDocOrderCount(Map<String, Object> params);

    /**
     * 查询公司发起的草拟文书订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 草拟文书订单数目
     */
    int queryDraftDocOrderCountOfCompany(Map<String, Object> params);

}
