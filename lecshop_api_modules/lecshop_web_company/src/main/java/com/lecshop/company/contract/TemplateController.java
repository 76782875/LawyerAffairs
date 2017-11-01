package com.lecshop.company.contract;

import com.lecshop.company.adminutil.CompanyLoginUtils;
import com.lecshop.company.fouraffair.ClickAuthCheck;
import com.lecshop.total.template.bean.Template;
import com.lecshop.total.template.bean.TemplateDownRecord;
import com.lecshop.total.template.service.TemplateDownRecordService;
import com.lecshop.total.template.service.TemplateService;
import com.lecshop.total.templatetype.bean.TemplateType;
import com.lecshop.total.templatetype.service.TemplateTypeService;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.FileUpAndDownUtils;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 模板下载列表控制器
 * <p>
 * Created by LecShop on 2017/7/24.
 */
@Controller
public class TemplateController {

    /**
     * 自动注入合同模板service
     */
    @Autowired
    private TemplateService templateService;

    /**
     * 自动注入合同模板类型service
     */
    @Autowired
    private TemplateTypeService templateTypeService;

    /**
     * 自动注入合同模板下载记录service
     */
    @Autowired
    private TemplateDownRecordService templateDownRecordService;
    /**
     * 注入操作权限实体类
     */
    @Autowired
    private ClickAuthCheck clickAuthCheck;

    /**
     * 跳转至模板下载页面
     *
     * @return 模板下载页面
     */
    @RequestMapping("/c_totemplatedownload")
    public ModelAndView toTemplateDownload() {
        return new ModelAndView("contract/template");
    }

    /**
     * 查询合同模板下载列表
     *
     * @param pageHelper 分页帮助类
     * @param name       合同模板名称
     * @param typeId     合同模板类型id
     * @param typeIds    合同模板类型id数组
     * @return 合同模板
     */
    @RequestMapping("/c_querytemplatelist")
    @ResponseBody
    public BaseResponse queryTemplate(PageHelper<Template> pageHelper, String name, Long[] typeIds, long typeId) {
        return BaseResponse.build(templateService.queryTemplateForDownload(pageHelper, name, typeIds, typeId));
    }

    /**
     * 按等级查询合同模板类型
     *
     * @return 合同模板类型
     */
    @RequestMapping("/c_querytemplatetypebygrade")
    @ResponseBody
    public List<TemplateType> queryTemplateTypeByGrade() {
        return templateTypeService.queryTemplateTypeByGrade(1);
    }

    /**
     * 根据父级id查询合同模板类型
     *
     * @param parentId 父级id
     * @return 合同模板类型
     */
    @RequestMapping("/c_querytemplatetypebyparentid")
    @ResponseBody
    public List<TemplateType> queryTemplateTypeByParentId(long parentId) {
        return templateTypeService.queryChildByParentId(parentId);
    }

    /**
     * 模板下载
     *
     * @param name 模板名称
     */
    @RequestMapping("/c_templatedownload")
    @ResponseBody
    public int templteDownload(HttpServletResponse response, String name, long id, HttpServletRequest request) {
        if (!clickAuthCheck.checkIsAuthToClick(CompanyLoginUtils.getInstance().getCompanyIdFromSession(request), "5")) {
            return -8;
        }
        FileUpAndDownUtils.fileDownloadCommon(response, "upload.properties", name, this);
        return templateDownRecordService.addTemplateDownloadRecord(new TemplateDownRecord().buildTemplateDownRecord(CompanyLoginUtils.getInstance().getCompanyIdFromSession(request), CompanyLoginUtils.getInstance().getUserIdFromSession(request), id));
    }

    /**
     * 根据一级父级id查询合同模板类型id数组
     *
     * @param parentId 父级id
     * @return 合同模板类型id数组
     */
    @RequestMapping("/c_querytemplateidsbyfirstparentid")
    @ResponseBody
    public Long[] queryTemplateTypeIdsByParentId(long parentId) {
        return templateTypeService.queryTemplateIdsByFirstParentId(parentId);
    }

    /**
     * 根据二级父级id查询合同模板id数组
     *
     * @param parentId 父级id
     * @return 合同模板id数组
     */
    @RequestMapping("/c_querytemplatetypeidsbysecondparentid")
    @ResponseBody
    public Long[] queryTemplateIdsBySecondParentId(long parentId) {
        return templateTypeService.queryTemplateIdsBySecondParentId(parentId);
    }
}
