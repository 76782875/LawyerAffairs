package com.lecshop.total.consultationtype.service.impl;

import com.lecshop.total.consultationtype.bean.ConsultationType;
import com.lecshop.total.consultationtype.mapper.ConsultationTypeMapper;
import com.lecshop.total.consultationtype.service.ConsultationTypeService;
import com.lecshop.utils.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公司咨询类型service实现类
 * <p>
 * Created by LecShop on 2017/7/13.
 */
@Service
public class ConsultationTypeServiceImpl implements ConsultationTypeService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(ConsultationTypeServiceImpl.class);

    /**
     * 自动注入公司咨询类型数据库接口
     */
    @Autowired
    private ConsultationTypeMapper consultationTypeMapper;

    /**
     * 分页查询公司咨询类型
     *
     * @return 公司咨询类型
     */
    @Override
    public List<ConsultationType> queryConsultationTypeList() {
        logger.debug("queryConsultationTypeList...");
        List<ConsultationType> list = consultationTypeMapper.queryConsultationTypeList();
        List<ConsultationType> finalList = new ArrayList<>();
        list.forEach(typeList -> {
            if (finalList.contains(typeList)) {
                return;
            }
            finalList.add(typeList);
            list.forEach(typeSecond -> {
                if (typeList.getId() == typeSecond.getParentId()) {
                    finalList.add(typeSecond);
                }
            });
        });
        return finalList;
    }

    /**
     * 删除公司咨询类型
     *
     * @param consultationType 公司咨询类型
     * @param flag             是否连带子类型一起删除
     * @return 成功返回>=1，失败返回0，存在子类型返回-1
     */
    @Override
    public int deleteConsultationType(ConsultationType consultationType, boolean flag) {
        logger.debug("deleteConsultationType and consultationType :{} \r\n and flag :{}", consultationType, flag);
        if (CollectionUtils.isEmpty(consultationTypeMapper.queryChildByParentId(consultationType.getId())) || flag) {
            return consultationTypeMapper.deleteConsultationType(consultationType);
        }
        return -1;
    }

    /**
     * 根据id查询公司咨询类型
     *
     * @param id 公司咨询类型id
     * @return 公司咨询类型
     */
    @Override
    public ConsultationType queryConsultationTypeById(long id) {
        logger.debug("queryConsultationTypeById and id :{}", id);
        return consultationTypeMapper.queryConsultationTypeById(id);
    }

    /**
     * 修改公司咨询类型
     *
     * @param consultationType 公司咨询类型
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateConsultationType(ConsultationType consultationType) {
        logger.debug("updateConsultationType and consultationType :{}", consultationType);
        return consultationTypeMapper.updateConsultationType(consultationType);
    }

    /**
     * 查询公司一级咨询类型
     *
     * @return 公司一级咨询类型集合
     */
    @Override
    public List<ConsultationType> queryFirstGradeConsultationType() {
        logger.debug("queryFirstGradeConsultationType...");
        return consultationTypeMapper.queryFirstGradeConsultationType();
    }

    /**
     * 添加公司咨询类型
     *
     * @param consultationType 公司咨询类型
     * @return 成功返回1，失败返回0
     */
    @Override
    public int addConsultationType(ConsultationType consultationType) {
        logger.debug("addConsultationType and consultationType :{}", consultationType);
        if (consultationTypeMapper.queryConsultationTypeByName(consultationType.getName()) != null) {
            return 0;
        }
        return consultationTypeMapper.addConsultationType(consultationType);
    }

    @Override
    public List<ConsultationType> querySecondConsultationTypeByParendId(long id) {
        return consultationTypeMapper.queryChildByParentId(id);
    }
}
