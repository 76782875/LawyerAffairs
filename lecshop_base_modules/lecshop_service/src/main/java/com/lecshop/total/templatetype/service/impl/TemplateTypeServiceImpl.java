package com.lecshop.total.templatetype.service.impl;

import com.lecshop.total.templatetype.bean.TemplateType;
import com.lecshop.total.templatetype.mapper.TemplateTypeMapper;
import com.lecshop.total.templatetype.service.TemplateTypeService;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 合同模板类型service实现类
 * <p>
 * Created by LecShop on 2017/7/11.
 */
@Service
public class TemplateTypeServiceImpl implements TemplateTypeService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(TemplateTypeServiceImpl.class);

    /**
     * 自动注入合同模板类型service
     */
    @Autowired
    private TemplateTypeMapper templateTypeMapper;

    /**
     * 查询合同模板类型
     *
     * @return 合同模板类型
     */
    @Override
    public List<TemplateType> queryTemplateTypeList() {
        logger.debug("queryTemplateTypeList...");
        List<TemplateType> lists = templateTypeMapper.queryTemplateTypeList();
        List<TemplateType> firstList = lists.stream().filter(templateType -> templateType.getGrade() == 1).collect(Collectors.toList());
        List<TemplateType> secondList = lists.stream().filter(templateType -> templateType.getGrade() == 2).collect(Collectors.toList());
        List<TemplateType> thirdList = lists.stream().filter(templateType -> templateType.getGrade() == 3).collect(Collectors.toList());
        List<TemplateType> finalList = new ArrayList<>();
        firstList.forEach(templateType -> {
            finalList.add(templateType);
            secondList.forEach(templateType1 -> {
                if (!finalList.contains(templateType1)&&templateType1.getParentId() == templateType.getId()) {
                    finalList.add(templateType1);
                    thirdList.forEach(templateType2 -> {
                        if (!finalList.contains(templateType2)&&templateType2.getParentId() == templateType1.getId()) {
                            finalList.add(templateType2);
                        }
                    });
                }
            });

        });
        return finalList;
    }

    /**
     * 删除合同模板类型
     *
     * @param templateType 合同模板类型
     * @param flag 是否连同子类型一起删除
     * @return 成功返回>=1，失败返回0，存在子类型返回-1
     */
    @Override
    public int deleteTemplateType(TemplateType templateType, boolean flag) {
        logger.debug("deleteTemplateType and templateType :{} \r\n and flag :{}", templateType, flag);
        if (CollectionUtils.isEmpty(templateTypeMapper.queryChildByParentId(templateType.getId())) || flag) {
            return templateTypeMapper.deleteTemplateType(templateType);
        }
        return -1;
    }

    /**
     * 根据id查询合同模板类型
     *
     * @param id 合同模板类型id
     * @return 合同模板类型
     */
    @Override
    public TemplateType queryTemplateTypeById(long id) {
        logger.debug("queryTemplateTypeById and id :{}", id);
        return templateTypeMapper.queryTemplateTypeById(id);
    }

    /**
     * 修改合同模板类型
     *
     * @param templateType 合同模板类型
     * @return 成功返回1，失败返回0，存在同名模板类型返回-1
     */
    @Override
    public int updateTemplateType(TemplateType templateType) {
        logger.debug("updateTemplateType and templateType :{}", templateType);
        TemplateType type = templateTypeMapper.queryTemplateTypeByName(templateType.getName());
        if (type != null && type.getId() != templateType.getId()) {
            return -1;
        }
        return templateTypeMapper.updateTemplateType(templateType);
    }

    /**
     * 添加合同模板类型
     *
     * @param templateType 合同模板类型
     * @return 成功返回1，失败返回0，存在同名模板类型返回-1
     */
    @Override
    public int addTemplateType(TemplateType templateType) {
        logger.debug("addTemplateType and templateType :{}", templateType);
        if (templateTypeMapper.queryTemplateTypeByName(templateType.getName()) != null) {
            return -1;
        }
        return templateTypeMapper.addTemplateType(templateType);
    }

    /**
     * 按等级查询合同模板类型
     *
     * @param grade 合同模板等级
     * @return 合同模板类型集合
     */
    @Override
    public List<TemplateType> queryTemplateTypeByGrade(int grade) {
        logger.debug("queryTemplateTypeByGrade and grade :{}", grade);
        return templateTypeMapper.queryTemplateTypeByGrade(grade);
    }

    /**
     * 根据父级id查询子类型
     *
     * @param parentId 父级id
     * @return 子类型
     */
    @Override
    public List<TemplateType> queryChildByParentId(long parentId) {
        logger.debug("queryChildByParentId and parentId :{}", parentId);
        return templateTypeMapper.queryChildByParentId(parentId);
    }

    /**
     * 根据一级类型id查询下属合同模板id数组
     *
     * @param parentId 一级类型id
     * @return 合同模板id数组
     */
    @Override
    public Long[] queryTemplateIdsByFirstParentId(long parentId) {
        logger.debug("queryTemplateIdsByFirstParentId and parentId :{}", parentId);
        return templateTypeMapper.queryTemplateIdsByFirstParentId(parentId);
    }

    /**
     * 根据二级类型id查询下属合同模板id数组
     *
     * @param parentId 二级类型id
     * @return 合同模板id数组
     */
    @Override
    public Long[] queryTemplateIdsBySecondParentId(long parentId) {
        logger.debug("queryTemplateIdsBySecondParentId and parentId :{}", parentId);
        return templateTypeMapper.queryTemplateIdsBySecondParentId(parentId);
    }
}
