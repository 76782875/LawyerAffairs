package com.lecshop.total.consultationtype.mapper;

import com.lecshop.total.consultationtype.bean.ConsultationType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 公司咨询类型数据库接口
 *
 * Created by LecShop on 2017/7/13.
 */
@Repository
public interface ConsultationTypeMapper {

    /**
     * 分页查询公司咨询类型
     *
     * @return 公司咨询类型集合
     */
    List<ConsultationType> queryConsultationTypeList();

    /**
     * 查询类型下是否存在子类型
     *
     * @return 子类型
     */
    List<ConsultationType> queryChildByParentId(long id);

    /**
     * 删除公司咨询类型（若存在子类型，连带子类型一起删除）
     *
     * @param consultationType 公司咨询类型
     * @return 成功返回>=1，失败返回0
     */
    int deleteConsultationType(ConsultationType consultationType);

    /**
     * 根据id查询公司咨询类型
     *
     * @param id 公司咨询类型id
     * @return 公司咨询类型
     */
    ConsultationType queryConsultationTypeById(long id);

    /**
     * 修改公司咨询类型
     *
     * @param consultationType 公司咨询类型
     * @return 成功返回1，失败返回0
     */
    int updateConsultationType(ConsultationType consultationType);

    /**
     * 根据名称查找公司咨询类型
     *
     * @param name 公司咨询类型名称
     * @return 公司咨询类型
     */
    ConsultationType queryConsultationTypeByName(String name);

    /**
     * 查询公司一级咨询类型
     *
     * @return 公司一级咨询类型集合
     */
    List<ConsultationType> queryFirstGradeConsultationType();

    /**
     * 添加公司咨询类型
     *
     * @param consultationType 公司咨询类型
     * @return 成功返回1，失败返回0
     */
    int addConsultationType(ConsultationType consultationType);

}
