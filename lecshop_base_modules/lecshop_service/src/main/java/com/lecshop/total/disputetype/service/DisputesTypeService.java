package com.lecshop.total.disputetype.service;

import com.lecshop.total.disputetype.bean.DisputesType;

import java.util.List;

/**
 * 公司纠纷类型service
 *
 * Created by LecShop on 2017/7/11.
 */
public interface DisputesTypeService {

    /**
     * 查询公司纠纷类型
     *
     * @return 公司纠纷类型
     */
    List<DisputesType> queryDisputesType();

    /**
     * 删除公司纠纷类型
     *
     * @param disputesType 公司纠纷类型
     * @param flag 是否连带子类型一起删除
     * @return 成功返回1，失败返回0，存在子类型返回-1
     */
    int deleteDisputesType(DisputesType disputesType, boolean flag);

    /**
     * 根据id查询公司纠纷类型
     *
     * @param id 公司纠纷类型id
     * @return   公司纠纷类型
     */
    DisputesType queryDisputesTypeById(long id);

    /**
     * 修改公司纠纷类型
     *
     * @param disputesType 公司纠纷类型
     * @return 成功返回1，失败返回0
     */
    int updateDisputesType(DisputesType disputesType);

    /**
     * 添加公司纠纷类型
     *
     * @param disputesType 公司纠纷类型
     * @return 成功返回1，失败返回0
     */
    int addDisputesType(DisputesType disputesType);

    /**
     * 查询一级公司纠纷类型
     *
     * @return 一级公司纠纷类型集合
     */
    List<DisputesType> queryFirstGradeDisputesType();

    /**
     * 查询类型下是否存在子类型
     *
     * @return 子类型
     */
    List<DisputesType> querySecondGradeDisputesTypeByParentId(long id);

}
