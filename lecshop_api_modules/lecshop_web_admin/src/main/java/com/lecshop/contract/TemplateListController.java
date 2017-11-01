package com.lecshop.contract;

import com.lecshop.total.template.bean.Template;
import com.lecshop.total.template.service.TemplateService;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import com.lecshop.utils.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * 合同模板列表控制器
 *
 * @author sunluyang on 2017/7/11.
 */
@Controller
public class TemplateListController {

    /**
     * 自动注入合同模板service
     */
    @Autowired
    private TemplateService templateService;

    /**
     * 跳转到合同模板配置页面
     *
     * @return 合同模板配置页面
     */
    @RequestMapping("/totemplatelist")
    public ModelAndView toTemplateList() {
        return new ModelAndView("contracttemplate/templatelist");
    }

    /**
     * 分页查询合同模板
     *
     * @param pageHelper 分页帮助类
     * @param name       合同模板名称
     * @return 合同模板
     */
    @RequestMapping("/querytemplatelist")
    @ResponseBody
    public BaseResponse queryTemplateList(PageHelper<Template> pageHelper, String name) {
        return BaseResponse.build(templateService.queryTemplateList(pageHelper, name));
    }

    /**
     * 删除合同模板
     *
     * @param id 合同模板id
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/deletetemplate")
    @ResponseBody
    public int deleteTemplate(long id) {
        return templateService.deleteTemplate(id);
    }

    /**
     * 根据id查询合同模板
     *
     * @param id 合同模板id
     * @return 合同模板
     */
    @RequestMapping("/querytemplatebyid")
    @ResponseBody
    @UnAuth
    public Template queryTemplateById(long id) {
        return templateService.queryTemplateById(id);
    }

    /**
     * 修改合同模板
     *
     * @param template 合同模板
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/updatetemplate")
    @ResponseBody
    public int updateTemplate(@RequestBody Template template) {
        return templateService.updateTemplate(template);
    }

    /**
     * 添加合同模板
     *
     * @param file   文件
     * @param name   模板名称
     * @param typeId 模板类型id
     * @return 添加返回码
     */
    @RequestMapping("/addtemplate")
    @ResponseBody
    public int addTemplate(@RequestParam("fileUpload") CommonsMultipartFile file, String name, long typeId) {
        return templateService.addTemplate(file, name, typeId);
    }
}
