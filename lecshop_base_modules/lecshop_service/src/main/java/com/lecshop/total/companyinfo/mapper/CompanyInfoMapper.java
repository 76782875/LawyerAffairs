package com.lecshop.total.companyinfo.mapper;

import com.lecshop.total.companyinfo.bean.CompanyInfo;
import org.springframework.stereotype.Repository;

/**
 * 公司信息数据库接口
 *
 * @author sunluyang on 2017/7/14.
 */
@Repository
public interface CompanyInfoMapper {

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
}
