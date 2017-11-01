package com.lecshop.total.disputetype.service.impl;

import com.lecshop.total.disputetype.bean.DisputesType;
import com.lecshop.total.disputetype.mapper.DisputesTypeMapper;
import com.lecshop.total.disputetype.service.DisputesTypeService;
import com.lecshop.utils.PageHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 公司纠纷类型service实现类
 * <p>
 * Created by LecShop on 2017/7/11.
 */
@Service
public class DisputesTypeServiceImpl implements DisputesTypeService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(DisputesTypeServiceImpl.class);

    /**
     * 自动注入公司纠纷类型数据库接口
     */
    @Autowired
    private DisputesTypeMapper disputesTypeMapper;

    /**
     * 查询公司纠纷类型
     *
     * @return 公司纠纷类型
     */
    @Override
    public List<DisputesType> queryDisputesType() {
        logger.debug("queryDisputesType...");
        List<DisputesType> list = disputesTypeMapper.queryDisputesTypeList();
        List<DisputesType> finalList = new ArrayList<>();
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
     * 删除公司纠纷类型
     *
     * @param disputesType 公司纠纷类型
     * @param flag         是否连带子类型一起删除
     * @return 成功返回1，失败返回0，存在子类型返回-1
     */
    @Override
    public int deleteDisputesType(DisputesType disputesType, boolean flag) {
        logger.debug("deleteDisputesType and disputesType :{} \r\n and flag :{}", disputesType, flag);
        if (CollectionUtils.isEmpty(disputesTypeMapper.queryChildByParentId(disputesType.getId())) || flag) {
            return disputesTypeMapper.deleteDisputesType(disputesType);
        }
        return -1;
    }

    /**
     * 根据id查询公司纠纷类型
     *
     * @param id 公司纠纷类型id
     * @return 公司纠纷类型
     */
    @Override
    public DisputesType queryDisputesTypeById(long id) {
        logger.debug("queryDisputesTypeById and id :{}", id);
        return disputesTypeMapper.queryDisputesTypeById(id);
    }

    /**
     * 修改公司纠纷类型
     *
     * @param disputesType 公司纠纷类型
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateDisputesType(DisputesType disputesType) {
        logger.debug("updateDisputesType and disputesType :{}", disputesType);
        return disputesTypeMapper.updateDisputesType(disputesType);
    }

    /**
     * 添加公司纠纷类型
     *
     * @param disputesType 公司纠纷类型
     * @return 成功返回1，失败返回0
     */
    @Override
    public int addDisputesType(DisputesType disputesType) {
        logger.debug("addDisputesType and disputesType :{}", disputesType);
        if (disputesTypeMapper.queryDisputesTypeByName(disputesType.getName()) != null) {
            return 0;
        }
        return disputesTypeMapper.addDisputesType(disputesType);
    }

    /**
     * 查询一级公司纠纷类型
     *
     * @return 一级公司纠纷类型集合
     */
    @Override
    public List<DisputesType> queryFirstGradeDisputesType() {
        logger.debug("queryFirstDisputesType...");
        return disputesTypeMapper.queryFirstGradeDisputesType();
    }

    @Override
    public List<DisputesType> querySecondGradeDisputesTypeByParentId(long id) {
        logger.debug("querySecondGradeDisputesTypeByParentId and id:{}", id);
        return disputesTypeMapper.queryChildByParentId(id);
    }
}
