package com.lecshop.total.consultationtype.service;

import com.lecshop.total.consultationtype.bean.ConsultationType;

import java.util.List;

/**
 * 公司咨询类型service
 * <p>
 * Created by LecShop on 2017/7/13.
 */
public interface ConsultationTypeService {

    /**
     * 查询公司咨询类型
     *
     * @return 公司咨询类型
     */
    List<ConsultationType> queryConsultationTypeList();

    /**
     * 删除公司咨询类型
     *
     * @param consultationType 公司咨询类型
     * @param flag             是否连带子类型一起删除
     * @return 成功返回>=1，失败返回0，存在子类型返回-1
     */
    int deleteConsultationType(ConsultationType consultationType, boolean flag);

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

    List<ConsultationType> querySecondConsultationTypeByParendId(long id);
}
