package com.lecshop.total.template.service.impl;

import com.lecshop.total.template.bean.Template;
import com.lecshop.total.template.mapper.TemplatesMapper;
import com.lecshop.total.template.service.TemplateService;
import com.lecshop.utils.FileUpAndDownUtils;
import com.lecshop.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 合同模板service实现类
 * <p>
 * Created by LecShop on 2017/7/11.
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);

    /**
     * 合同模板数据库接口
     */
    @Autowired
    private TemplatesMapper templatesMapper;

    /**
     * 分页查询合同模板
     *
     * @param pageHelper 分页帮助类
     * @param name       合同模板名称
     * @return 合同模板
     */
    @Override
    public PageHelper<Template> queryTemplateList(PageHelper<Template> pageHelper, String name) {
        logger.debug("queryTemplateList and pageHelper :{} \r\n and name :{}", pageHelper, name);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return pageHelper.setListDates(templatesMapper.queryTemplateList(pageHelper.getQueryParams(params, templatesMapper.queryTemplateCount(params))));
    }

    /**
     * 删除合同模板
     *
     * @param id 合同模板id
     * @return 成功返回1，失败赶回0
     */
    @Override
    public int deleteTemplate(long id) {
        logger.debug("deleteTemplate and id :{}", id);
        return templatesMapper.deleteTemplate(id);
    }

    /**
     * 根据id查询合同模板
     *
     * @param id 合同模板id
     * @return 合同模板
     */
    @Override
    public Template queryTemplateById(long id) {
        logger.debug("queryTemplateById and id :{}", id);
        return templatesMapper.queryTemplateById(id);
    }

    /**
     * 修改合同模板
     *
     * @param template 合同模板
     * @return 成功返回1，失败赶回0
     */
    @Override
    public int updateTemplate(Template template) {
        logger.debug("updateTemplate and template :{}", template);
        return templatesMapper.updateTemplate(template);
    }

    /**
     * 添加合同模板
     *
     * @param file   合同模版文件
     * @param name   模板名称
     * @param typeId 模板类型id
     * @return 添加返回码 -1 文件上传失败 1 成功 2 模板名称重复
     */
    @Override
    public int addTemplate(CommonsMultipartFile file, String name, long typeId) {
        logger.debug("addTemplate and name :{}\r\n typeId:{}", name, typeId);
        if (!Objects.isNull(templatesMapper.queryTemplateByName(name))) {
            return 2;
        }
        if (FileUpAndDownUtils.fileUploadCommon(file, "upload.properties", this) == 1) {
            return templatesMapper.addTemplate(new Template().toGetTemplate(name, typeId, file.getOriginalFilename()));
        }
        return -1;
    }

    /**
     * 查询合同模板下载列表
     *
     * @param pageHelper 分页帮助类
     * @param name       合同模板名称
     * @param typeIds    合同模板类型id数组
     * @param typeId     合同模板类型id
     * @return 合同模板下载列表
     */
    @Override
    public PageHelper<Template> queryTemplateForDownload(PageHelper<Template> pageHelper, String name, Long[] typeIds, long typeId) {
        logger.debug("queryTemplateForDownload and pageHelper :{} \r\n name :{} \r\n typeIds :{} \r\n and typeId :{}", pageHelper, name, typeIds, typeId);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("typeIds", typeIds);
        params.put("typeId", typeId);
        return pageHelper.setListDates(templatesMapper.queryTemplateForDownload(pageHelper.getQueryParams(params, templatesMapper.queryTemplateForDownloadCount(params))));
    }
}
