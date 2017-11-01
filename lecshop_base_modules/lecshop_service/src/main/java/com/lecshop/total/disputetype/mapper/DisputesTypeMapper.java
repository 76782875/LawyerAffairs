package com.lecshop.total.disputetype.mapper;

import com.lecshop.total.disputetype.bean.DisputesType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 公司纠纷类型数据库接口
 *
 * Created by LecShop on 2017/7/11.
 */
@Repository
public interface DisputesTypeMapper {

    /**
     * 查询公司纠纷类型
     *
     * @return 公司纠纷类型
     */
    List<DisputesType> queryDisputesTypeList();

    /**
     * 查询类型下是否存在子类型
     *
     * @return 子类型
     */
    List<DisputesType> queryChildByParentId(long id);

    /**
     * 删除公司纠纷类型
     *
     * @param disputesType 公司纠纷类型
     * @return 成功返回1，失败返回0
     */
    int deleteDisputesType(DisputesType disputesType);

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
     * 根据名称查找公司纠纷类型
     *
     * @param name 公司纠纷类型名称
     * @return 公司纠纷类型
     */
    DisputesType queryDisputesTypeByName(String name);

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
}
