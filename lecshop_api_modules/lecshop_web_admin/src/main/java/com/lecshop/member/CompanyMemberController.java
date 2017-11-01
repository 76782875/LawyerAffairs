package com.lecshop.member;

import com.lecshop.adminutil.AdminLoginUtils;
import com.lecshop.total.companyinfo.bean.CompanyInfo;
import com.lecshop.total.companyinfo.service.CompanyInfoService;
import com.lecshop.total.letterstation.service.LetterStationService;
import com.lecshop.total.user.bean.User;
import com.lecshop.total.user.service.UserService;
import com.lecshop.total.viprecord.bean.VipModifyRecord;
import com.lecshop.total.viprecord.service.VipModifyRecordService;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 公司会员控制器
 *
 * @author sunluyang on 2017/7/24.
 */
@Controller
public class CompanyMemberController {
    /**
     * 注入公司员工service
     */
    @Autowired
    private UserService userService;

    /**
     * 注入公司信息service
     */
    @Autowired
    private CompanyInfoService companyInfoService;

    /**
     * 注入站内信service
     */
    @Autowired
    private LetterStationService letterStationService;
    /**
     * 注入VIP修改记录service
     */
    @Autowired
    private VipModifyRecordService vipModifyRecordService;

    /**
     * 跳转到公司会员列表
     *
     * @return 公司会员列表
     */
    @RequestMapping("/tocompanymemberlist")
    public ModelAndView toCompanyMemberList() {
        return new ModelAndView("member/companymember");
    }

    /**
     * 分页查询公司会员信息
     *
     * @param pageHelper 分页帮助类
     * @param name       公司名称
     * @param userName   用户昵称
     * @param mobile     用户手机
     * @return 公司会员信息
     */
    @RequestMapping("/querycompanymemberinfos")
    @ResponseBody
    public BaseResponse queryCompanyMemberInfos(PageHelper<User> pageHelper, String name, String userName, String mobile) {
        return BaseResponse.build(userService.queryCompanyMemberInfos(pageHelper, name, userName, mobile));
    }

    /**
     * 根据id查询公司信息
     *
     * @param userId 公司id
     * @return 公司信息
     */
    @RequestMapping("/querycompanyinfobyid")
    @ResponseBody
    public User queryCompanyInfoById(long companyId, long userId) {
        return userService.queryUserAndCompany(companyId, userId);
    }

    /**
     * 删除公司会员
     *
     * @param id 公司会员id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/deletecompanymember")
    @ResponseBody
    public int deleteCompanyMember(long id) {
        return userService.deleteCompanyMember(id);
    }

    /**
     * 批量删除公司会员
     *
     * @param ids 公司会员id数组
     * @return 成功返回>=1，失败返回0
     */
    @RequestMapping("/batchdeletecompanymember")
    @ResponseBody
    public int batchDeleteCompanyMember(long[] ids) {
        return userService.batchDeleteCompanyMember(ids);
    }

    /**
     * 修改公司信息
     *
     * @param companyInfo 公司信息
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/updatecompanymember")
    @ResponseBody
    public int updateCompanyInfo(@RequestBody CompanyInfo companyInfo) {
        return companyInfoService.updateCompanyInfo(companyInfo);
    }

    /**
     * 添加公司
     *
     * @param companyInfo 公司信息
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/addcompanymember")
    @ResponseBody
    public int addCompanyAndUser(@RequestBody CompanyInfo companyInfo) {
        return companyInfoService.addCompanyAndUser(companyInfo);
    }

    /**
     * 发送站内信
     *
     * @param ids     用户id数组
     * @param title   标题
     * @param content 内容
     * @return 成功返回>=1，失败返回0
     */
    @RequestMapping("/sendletterstation")
    @ResponseBody
    public int sendLetterStation(long[] ids, String title, String content) {
        return letterStationService.sendLetterStation(ids, title, content);
    }

    /**
     * 根据公司id查询公司信息
     *
     * @param companyId 公司id
     * @return 公司信息
     */
    @RequestMapping("/querycompanyinfobycompanyid")
    @ResponseBody
    public CompanyInfo queryCompanyInfoByCompanyId(long companyId) {
        return companyInfoService.queryCompanyInfoById(companyId);
    }

    /**
     * 编辑公司VIP
     *
     * @param vipModifyRecord VIP修改实体类
     * @return 编辑返回码
     */
    @RequestMapping("editcompanyinfovip")
    @ResponseBody
    public int editCompanyInfoVip(VipModifyRecord vipModifyRecord, HttpServletRequest request) {
        vipModifyRecord.setManagerId(AdminLoginUtils.getInstance().getManagerFromSession(request).getId());
        return companyInfoService.editCompanyInfoVip(vipModifyRecord);
    }

    /**
     * 跳转到VIP修改记录页面
     *
     * @return VIP修改记录页面
     */
    @RequestMapping("tovipmodifyrecord")
    @ResponseBody
    public ModelAndView toVipModifyRecord() {
        return new ModelAndView("member/vipmodifyrecord");
    }

    /**
     * 分页查询公司VIP修改记录
     *
     * @param pageHelper  分页帮助类
     * @param companyName 公司名称
     * @return VIP修改记录
     */
    @RequestMapping("/queryvipmodifyrecord")
    @ResponseBody
    public BaseResponse queryVipModifyRecord(PageHelper<VipModifyRecord> pageHelper, String companyName) {
        return BaseResponse.build(vipModifyRecordService.queryVipModifyRecord(pageHelper, companyName));
    }
}
