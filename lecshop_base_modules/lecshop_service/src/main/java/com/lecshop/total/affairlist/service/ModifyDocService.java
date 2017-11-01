package com.lecshop.total.affairlist.service;

import com.lecshop.total.affairlist.bean.ModifyDoc;
import com.lecshop.utils.PageHelper;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;


/**
 * 文书事务service接口
 *
 * @author sunluyang on 2017/7/12.
 */
public interface ModifyDocService {

    /**
     * 分页查询文书事务
     *
     * @param pageHelper 分页帮助类
     * @return 文书事务
     */
    PageHelper<ModifyDoc> queryModifyDoc(PageHelper<ModifyDoc> pageHelper, String lawyerName, String companyName, long companyId, long userId, long lawyerId);

    /**
     * 查询待处理的修改文书信息
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return 修改文书集合
     */
    List<ModifyDoc> queryToDoModifyDoc(long companyId, long userId);

    /**
     * 修改文书
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @param file      文书文件
     * @param name      文书名称
     * @param desc      修改要求
     * @return 成功返回1，文件上传失败-1
     */
    int updateModifyDoc(long companyId, long userId, CommonsMultipartFile file, String name, String desc);

    /**
     * 确认修改文书
     *
     * @param id       文书id
     * @param status   文书状态
     * @param lawyerId 律师id
     * @return 成功返回1，失败返回0
     */
    int confirmModifyDoc(long id, String status, long lawyerId);

    /**
     * 修改文书状态
     *
     * @param id     文书id
     * @param status 文书状态
     * @return 成功返回1，失败返回0
     */
    int changeModifyDocStatus(long id, String status);

    /**
     * 为修改文书评分
     *
     * @param id    文书id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    int gradeForModifyDoc(long id, int score);

    /**
     * 根据时间和公司id查询修改文书个数
     *
     * @param localDateTime 时间
     * @param companyId     公司id
     * @return 总数
     */
    int queryModifyDocCountByTime(LocalDateTime localDateTime, long companyId);


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
