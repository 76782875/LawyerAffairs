package com.lecshop.contract;

import com.lecshop.total.templatetype.bean.TemplateType;
import com.lecshop.total.templatetype.service.TemplateTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 合同模板类型配置控制器
 *
 * @author sunluyang on 2017/7/11.
 */
@Controller
public class TemplateTypeSetController {

    /**
     * 自动注入合同模板类型service
     */
    @Autowired
    private TemplateTypeService templateTypeService;

    /**
     * 跳转到合同模板配置页面
     *
     * @return 合同模板配置页面
     */
    @RequestMapping("/totemplatetypeset")
    public ModelAndView toTemplateTypeSet() {
        return new ModelAndView("contracttemplate/templateset");
    }

    /**
     * 查询合同模板类型
     *
     * @return 合同模板类型
     */
    @RequestMapping("/querytemplatetype")
    @ResponseBody
    public List<TemplateType> queryTemplateType() {
        return templateTypeService.queryTemplateTypeList();
    }

    /**
     * 删除合同模板类型
     *
     * @param templateType 合同模板类型
     * @param flag 是否连同子类型一起删除
     * @return 成功返回>=1，失败返回0，存在子类型返回-1
     */
    @RequestMapping("/deletetemplatetype")
    @ResponseBody
    public int deleteTemplateType(@RequestBody TemplateType templateType, boolean flag) {
        return templateTypeService.deleteTemplateType(templateType, flag);
    }

    /**
     * 根据id查询合同模板类型
     *
     * @param id 合同模板类型id
     * @return 合同模板类型
     */
    @RequestMapping("/querytemplatetypebyid")
    @ResponseBody
    public TemplateType queryTemplateTypeById(long id) {
        return templateTypeService.queryTemplateTypeById(id);
    }

    /**
     * 修改合同模板类型
     *
     * @param templateType 合同模板类型
     * @return 成功返回1，失败返回0，存在同名模板类型返回-1
     */
    @RequestMapping("/updatetemplatetype")
    @ResponseBody
    public int updateTemplateType(@RequestBody TemplateType templateType) {
        return templateTypeService.updateTemplateType(templateType);
    }

    /**
     * 添加合同模板类型
     *
     * @param templateType 合同模板类型
     * @return 成功返回1，失败返回0，存在同名模板类型返回-1
     */
    @RequestMapping("/addditemplatetype")
    @ResponseBody
    public int addTemplateType(@RequestBody TemplateType templateType) {
        return templateTypeService.addTemplateType(templateType);
    }

    /**
     * 按等级查询合同模板类型
     *
     * @param grade 合同模板等级
     * @return 合同模板类型集合
     */
    @RequestMapping("/querytemplatetypebygrade")
    @ResponseBody
    public List<TemplateType> queryTemplateTypeByGrade(int grade) {
        return templateTypeService.queryTemplateTypeByGrade(grade);
    }
}
