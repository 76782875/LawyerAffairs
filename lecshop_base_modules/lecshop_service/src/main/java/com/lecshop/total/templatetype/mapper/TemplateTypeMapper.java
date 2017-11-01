package com.lecshop.total.templatetype.mapper;

import com.lecshop.total.templatetype.bean.TemplateType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 合同模版类型数据库接口
 * <p>
 * Created by LecShop on 2017/7/11.
 */
@Repository
public interface TemplateTypeMapper {

    /**
     * 查询合同模板类型
     *
     * @return 合同模板类型
     */
    List<TemplateType> queryTemplateTypeList();

    /**
     * 根据父级id查询子类型
     *
     * @param parentId 父级id
     * @return 子类型
     */
    List<TemplateType> queryChildByParentId(long parentId);

    /**
     * 删除合同模板类型
     *
     * @param templateType 合同模板类型
     * @return 成功返回>=1，失败返回0
     */
    int deleteTemplateType(TemplateType templateType);

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
     * @return 成功返回1，失败返回0
     */
    int updateTemplateType(TemplateType templateType);

    /**
     * 添加合同模板类型
     *
     * @param templateType 合同模板类型
     * @return 成功返回1，失败返回0
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
     * 按名称查找合同模板类型
     *
     * @param name 合同模板类型名称
     * @return 合同模板类型
     */
    TemplateType queryTemplateTypeByName(String name);

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
