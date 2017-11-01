package com.lecshop.total.template.service;

import com.lecshop.total.template.bean.Template;
import com.lecshop.utils.PageHelper;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 合同模板service
 * <p>
 * Created by LecShop on 2017/7/11.
 */
public interface TemplateService {

    /**
     * 分页查询合同模板
     *
     * @param pageHelper 分页帮助类
     * @param name       合同模板名称
     * @return 合同模板
     */
    PageHelper<Template> queryTemplateList(PageHelper<Template> pageHelper, String name);

    /**
     * 删除合同模板
     *
     * @param id 合同模板id
     * @return 成功返回1，失败赶回0
     */
    int deleteTemplate(long id);

    /**
     * 根据id查询合同模板
     *
     * @param id 合同模板id
     * @return 合同模板
     */
    Template queryTemplateById(long id);

    /**
     * 修改合同模板
     *
     * @param template 合同模板
     * @return 成功返回1，失败赶回0
     */
    int updateTemplate(Template template);

    /**
     * 添加合同模板
     *
     * @param name   模板名称
     * @param typeId 模板类型id
     * @param file   合同模版文件
     * @return 添加返回码 -1 文件上传失败 1 成功 2 模板名称重复
     */
    int addTemplate(CommonsMultipartFile file, String name, long typeId);

    /**
     * 查询合同模板下载列表
     *
     * @param pageHelper 分页帮助类
     * @param name 合同模板名称
     * @param typeIds 合同模板类型id数组
     * @param typeId 合同模板类型id
     * @return 合同模板下载列表
     */
    PageHelper<Template> queryTemplateForDownload(PageHelper<Template> pageHelper, String name, Long[] typeIds, long typeId);
}
