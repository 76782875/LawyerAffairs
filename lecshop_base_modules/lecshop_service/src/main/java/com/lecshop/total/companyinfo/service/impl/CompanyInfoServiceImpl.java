package com.lecshop.total.companyinfo.service.impl;

import com.lecshop.company.companyauth.service.CompanyRoleService;
import com.lecshop.total.companyinfo.bean.CompanyInfo;
import com.lecshop.total.companyinfo.mapper.CompanyInfoMapper;
import com.lecshop.total.companyinfo.service.CompanyInfoService;
import com.lecshop.total.user.bean.User;
import com.lecshop.total.user.service.UserService;
import com.lecshop.total.viprecord.bean.VipModifyRecord;
import com.lecshop.total.viprecord.service.VipModifyRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 公司信息service实现类
 *
 * @author sunluyang on 2017/7/14.
 */
@Service
public class CompanyInfoServiceImpl implements CompanyInfoService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(CompanyInfoServiceImpl.class);

    /**
     * 公司信息mapper
     */
    @Autowired
    private CompanyInfoMapper companyInfoMapper;
    /**
     * 用户service
     */
    @Autowired
    private UserService userService;
    /**
     * 公司角色service
     */
    @Autowired
    private CompanyRoleService companyRoleService;
    /**
     * VIP修改记录service
     */
    @Autowired
    private VipModifyRecordService vipModifyRecordService;

    /**
     * 修改公司信息
     *
     * @param companyInfo 公司信息
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateCompanyInfo(CompanyInfo companyInfo) {
        logger.debug("updateCompanyInfo and companyInfo :{}", companyInfo);
        return companyInfoMapper.updateCompanyInfo(companyInfo);
    }

    /**
     * 修改公司信息（个人中心）
     *
     * @param companyInfo 公司信息
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateCompanyInfoForPersonCentre(CompanyInfo companyInfo) {
        logger.debug("updateCompanyInfoForPersonCentre and companyInfo :{}", companyInfo);
        return companyInfoMapper.updateCompanyInfoForPersonCentre(companyInfo);
    }

    /**
     * 添加公司
     *
     * @param companyInfo 公司信息
     * @return -1 手机号码已存在 -2 公司名称已存在
     */
    @Override
    public int addCompanyAndUser(CompanyInfo companyInfo) {
        logger.debug("addCompanyMember and companyInfo :{}", companyInfo);
        if (!Objects.isNull(userService.queryUserByMobile(companyInfo.getUser().getMobile()))) {
            return -1;
        }
        if (!Objects.isNull(userService.queryUserByName(companyInfo.getName()))) {
            return -2;
        }
        addCompanyInfo(companyInfo);
        User user = new User().getDefaultUser(companyInfo.getName(), companyInfo.getUser().getPassword(), companyInfo.getUser().getMobile(), companyInfo.getId(), "0", companyInfo.getUser().getStatus());
        userService.addCompanyStaff(user);
        return companyRoleService.addUserRole(user.getId(), 1);
    }

    /**
     * 添加公司信息
     *
     * @param companyInfo 公司信息实体类
     * @return 添加返回码
     */
    @Override
    public int addCompanyInfo(CompanyInfo companyInfo) {
        logger.debug("addCompanyInfo and companyInfo :{}", companyInfo);
        return companyInfoMapper.addCompanyInfo(companyInfo.getDefaultTotalConsumption());
    }

    /**
     * 根据公司id查询公司信息
     *
     * @param id 公司id
     * @return 公司信息
     */
    @Override
    public CompanyInfo queryCompanyInfoById(long id) {
        logger.debug("queryCompanyInfoById and id :{}", id);
        return companyInfoMapper.queryCompanyInfoById(id);
    }

    /**
     * 修改会员等级
     *
     * @param companyInfo 公司信息
     * @return 修改返回码
     */
    @Override
    public int updateCompanyInfoForVip(CompanyInfo companyInfo) {
        logger.debug("updateCompanyInfoForVip and companyInfo :{}", companyInfo);
        return companyInfoMapper.updateCompanyInfoForVip(companyInfo);
    }

    /**
     * 编辑公司VIP等级
     *
     * @param vipModifyRecord VIP等级
     * @return 修改返回码
     */
    @Override
    public int editCompanyInfoVip(VipModifyRecord vipModifyRecord) {
        logger.debug("editCompanyInfoVip and vipModifyRecord :{}", vipModifyRecord);
        CompanyInfo companyInfo = companyInfoMapper.queryCompanyInfoById(vipModifyRecord.getCompanyId());
        String vipType = companyInfo.getVipType();
        LocalDateTime startTime = "0".equals(vipType) || "4".equals(vipType) ? LocalDateTime.now() : companyInfo.getStartTime();
        LocalDateTime endTime = startTime.plusYears(1);
        updateCompanyInfoForVip(companyInfo.buildVip(vipModifyRecord.getNewVip(), startTime));
        //VIP记录表插入数据
        return vipModifyRecordService.addVipModifyRecord(vipModifyRecord.buildVipModifyRecordForAdmin(companyInfo.getId(), vipType, startTime, endTime));
    }
}
