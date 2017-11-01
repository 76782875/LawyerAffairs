package com.lecshop.total.templatetype.service;

import com.lecshop.total.templatetype.bean.TemplateType;

import java.util.List;

/**
 * 合同模版类型service
 * <p>
 * Created by LecShop on 2017/7/11.
 */
public interface TemplateTypeService {

    /**
     * 查询合同模板类型
     *
     * @return 合同模板类型
     */
    List<TemplateType> queryTemplateTypeList();

    /**
     * 删除合同模板类型
     *
     * @param templateType 合同模板类型
     * @param flag 是否连同子类型一起删除
     * @return 成功返回>=1，失败返回0，存在子类型返回-1
     */
    int deleteTemplateType(TemplateType templateType, boolean flag);

    /**
     * 根据id查询合同模板类型
     *
     * @param id 合同模板类型id
     * @return 合同模板类型
     */
    TemplateType queryTemplateTypeById(long id);

    /**
     * 修改合同模板类型
     *
     * @param templateType 合同模板类型
     * @return 成功返回1，失败返回0，存在同名模板类型返回-1
     */
    int updateTemplateType(TemplateType templateType);

    /**
     * 添加合同模板类型
     *
     * @param templateType 合同模板类型
     * @return 成功返回1，失败返回0，存在同名模板类型返回-1
     */
    int addTemplateType(TemplateType templateType);

    /**
     * 按等级查询合同模板类型
     *
     * @param grade 合同模板等级
     * @return 合同模板类型集合
     */
    List<TemplateType> queryTemplateTypeByGrade(int grade);

    /**
     * 根据父级id查询子类型
     *
     * @param parentId 父级id
     * @return 子类型
     */
    List<TemplateType> queryChildByParentId(long parentId);

    /**
     * 根据一级类型id查询下属合同模板id数组
     *
     * @param parentId 一级类型id
     * @return 合同模板id数组
     */
    Long[] queryTemplateIdsByFirstParentId(long parentId);

    /**
     * 根据二级类型id查询下属合同模板id数组
     *
     * @param parentId 二级类型id
     * @return 合同模板id数组
     */
    Long[] queryTemplateIdsBySecondParentId(long parentId);
}
