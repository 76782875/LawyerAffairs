package com.lecshop.total.companyinfo.service;

import com.lecshop.total.companyinfo.bean.CompanyInfo;
import com.lecshop.total.viprecord.bean.VipModifyRecord;

/**
 * 公司信息service
 *
 * @author sunluyang on 2017/7/14.
 */
public interface CompanyInfoService {

    /**
     * 修改公司信息
     *
     * @param companyInfo 公司信息
     * @return 成功返回1，失败返回0
     */
    int updateCompanyInfo(CompanyInfo companyInfo);

    /**
     * 修改公司信息（个人中心）
     *
     * @param companyInfo 公司信息
     * @return 成功返回1，失败返回0
     */
    int updateCompanyInfoForPersonCentre(CompanyInfo companyInfo);

    /**
     * 添加公司-注册
     *
     * @param companyInfo 公司信息实体类
     * @return 添加返回码
     */
    int addCompanyInfo(CompanyInfo companyInfo);

    /**
     * 添加公司
     *
     * @param companyInfo 公司信息
     * @return 成功返回1，失败返回0
     */
    int addCompanyAndUser(CompanyInfo companyInfo);

    /**
     * 根据公司id查询公司信息
     *
     * @param id 公司id
     * @return 公司信息
     */
    CompanyInfo queryCompanyInfoById(long id);

    /**
     * 修改会员等级
     *
     * @param companyInfo 公司信息
     * @return 修改返回码
     */
    int updateCompanyInfoForVip(CompanyInfo companyInfo);

    /**
     * 编辑公司VIP等级
     *
     * @param vipModifyRecord VIP等级
     * @return 修改返回码
     */
    int editCompanyInfoVip(VipModifyRecord vipModifyRecord);
}
