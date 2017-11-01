package com.lecshop.total.affairlist.service;

import com.lecshop.total.affairlist.bean.ImportantMatter;
import com.lecshop.utils.PageHelper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author sunluyang on 2017/7/21.
 */
public interface ImportantMatterService {
    /**
     * 分页查询纠纷事务
     *
     * @param pageHelper  分页帮助类
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @return 预约会面集合
     */
    PageHelper<ImportantMatter> queryImportantMatter(PageHelper<ImportantMatter> pageHelper, String lawyerName, String companyName, long companyId, long userId, long lawyerId);

    /**
     * 查询待处理的纠纷
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return 纠纷集合
     */
    List<ImportantMatter> queryToDoImportantMatter(long companyId, long userId);

    /**
     * 修改重大事项状态
     *
     * @param id     重大事项id
     * @param status 状态
     * @return 成功返回1，失败返回0
     */
    int changeImportantMatterStatus(long id, String status);

    /**
     * 为重大事项评分
     *
     * @param id    重大事项id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    int gradeForImportantMatter(long id, int score);

    /**
     * 律师确认接受重大事项
     *
     * @param id    主键id
     * @param money 金额
     * @return 成功返回1  失败返回0
     */
    int confirmImportantMatter(long id, BigDecimal money);

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
