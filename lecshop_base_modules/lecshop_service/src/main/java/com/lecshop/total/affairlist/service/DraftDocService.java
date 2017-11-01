package com.lecshop.total.affairlist.service;

import com.lecshop.total.affairlist.bean.DraftDoc;
import com.lecshop.utils.PageHelper;

import java.util.List;
import java.util.Map;

/**
 * 草拟文书service
 * <p>
 * Created by LecShop on 2017/7/18.
 */
public interface DraftDocService {

    /**
     * 分页查询草拟文书
     *
     * @param pageHelper 分页帮助类
     * @return 文书事务
     */
    PageHelper<DraftDoc> queryDraftDoc(PageHelper<DraftDoc> pageHelper, String lawyerName, String companyName, long companyId, long userId,long lawyerId);

    /**
     * 查询待处理的草拟文书信息
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return 草拟文书集合
     */
    List<DraftDoc> queryToDoDraftDoc(long companyId, long userId);

    /**
     * 添加草拟文书
     *
     * @param userId    用户id
     * @param companyId 公司id
     * @param name      草拟文书名称
     * @param desc      草拟要求
     * @return 成功返回1，失败返回0
     */
    int addDraftDoc(long userId, long companyId, String name, String desc);

    /**
     * 确认草拟文书
     *
     * @param id       文书id
     * @param status   文书状态
     * @param lawyerId 律师id
     * @return 成功返回1，失败返回0
     */
    int confirmDraftDoc(long id, String status, long lawyerId);

    /**
     * 修改草拟文书状态
     *
     * @param id 文书id
     * @param status 文书状态
     * @return 成功返回1，失败返回0
     */
    int updateDraftDocStatus(long id, String status);

    /**
     * 为草拟文书评分
     *
     * @param id 文书id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    int gradeForDraftDoc(long id, int score);


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
