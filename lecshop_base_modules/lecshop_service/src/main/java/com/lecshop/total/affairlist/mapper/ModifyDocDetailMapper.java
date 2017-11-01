package com.lecshop.total.affairlist.mapper;

import com.lecshop.total.affairlist.bean.ModifyDocDetail;

import java.util.List;

/**
 * 文书交流详情mapper
 *
 * @author sunluyang on 2017/7/12.
 */
public interface ModifyDocDetailMapper {

    /**
     * 根据文书id查询文书交流详情
     *
     * @return 文书交流详情集合
     */
    List<ModifyDocDetail> queryModifyDocDetailById(long docId);

    /**
     * 添加文书交流详情
     *
     * @param modifyDocDetail 文书交流详情
     * @return 成功返回1，失败返回0
     */
    int addModifyDocDetail(ModifyDocDetail modifyDocDetail);
}
