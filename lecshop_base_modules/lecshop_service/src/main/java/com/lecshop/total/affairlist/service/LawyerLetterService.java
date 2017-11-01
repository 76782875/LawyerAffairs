package com.lecshop.total.affairlist.service;

import com.lecshop.total.affairlist.bean.LawyerLetter;
import com.lecshop.utils.PageHelper;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.Map;

/**
 * 律师函service
 * <p>
 * Created by LecShop on 2017/7/19.
 */
public interface LawyerLetterService {

    /**
     * 分页查询律师函信息
     *
     * @param pageHelper  分页帮助类
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @param companyId 公司id
     * @param userId 用户id
     * @param lawyerId 律师id
     * @return 律师函信息
     */
    PageHelper<LawyerLetter> queryLawyerLetter(PageHelper<LawyerLetter> pageHelper, String lawyerName, String companyName, long companyId, long userId, long lawyerId);

    /**
     * 查询待处理的律师函信息
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return 律师函集合
     */
    List<LawyerLetter> queryToDoLawyerLetter(long companyId, long userId);

    /**
     * 提交律师函
     *
     * @param file      律师函文件
     * @param companyId 公司id
     * @param userId    用户id
     * @param mobile    电话
     * @param address   地址
     * @param desc      描述
     * @return 成功返回1， 文件上传失败-1
     */
    int addLawyerLetter(CommonsMultipartFile file, long companyId, long userId, String mobile, String address, String desc);

    /**
     * 确认律师函
     *
     * @param id       主键id
     * @param status   状态
     * @param lawyerId 律师id
     * @return 成功返回1 失败返回0
     */
    int confirmLawyerLetter(long id, String status, long lawyerId);

    /**
     * 修改律师函状态
     *
     * @param id     主键id
     * @param status 状态
     * @return 成功返回1 失败返回0
     */
    int changeLawyerLetterStatus(long id, String status);

    /**
     * 为律师函评分
     *
     * @param id 律师函id
     * @param score 评分
     * @return 成功返回1，失败返回0
     */
    int gradeForLawyerLetter(long id, int score);


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
