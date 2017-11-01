package com.lecshop.total.template.mapper;

import com.lecshop.total.template.bean.Template;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 合同模板数据库接口
 * <p>
 * Created by LecShop on 2017/7/11.
 */
@Repository
public interface TemplatesMapper {

    /**
     * 分页查询合同模板
     *
     * @param params 合同模板名称
     * @return 合同模板
     */
    List<Template> queryTemplateList(Map<String, Object> params);

    /**
     * 查询合同模板总记录数
     *
     * @param params 合同模板名称
     * @return 合同模板总记录数
     */
    int queryTemplateCount(Map<String, Object> params);

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
     * @param template 合同模板实体类
     * @return 添加返回码
     */
    int addTemplate(Template template);

    /**
     * 根据名称查询合同模板信息
     *
     * @param name 模板名称
     * @return 合同模板实体类
     */
    Template queryTemplateByName(String name);

    /**
     * 查询合同模板下载列表
     *
     * @param params 查询参数
     * @return 合同模板
     */
    List<Template> queryTemplateForDownload(Map<String, Object> params);

    /**
     * 查询合同模板下载列表总记录数
     *
     * @param params 查询参数
     * @return 合同模板下载列表总记录数
     */
    int queryTemplateForDownloadCount(Map<String, Object> params);
}
