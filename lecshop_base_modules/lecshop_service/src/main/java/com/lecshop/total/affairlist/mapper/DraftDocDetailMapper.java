package com.lecshop.total.affairlist.mapper;

import com.lecshop.total.affairlist.bean.DraftDocDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 草拟文书详情数据库接口
 *
 * Created by LecShop on 2017/7/31.
 */
@Repository
public interface DraftDocDetailMapper {

    /**
     * 添加草拟文书详情
     *
     * @param draftDocDetail 草拟文书详情
     * @return 成功返回1，失败返回0
     */
    int addDraftDocDetail(DraftDocDetail draftDocDetail);

    /**
     * 根据草拟文书id查询草拟文书详情
     *
     * @param docId 草拟文书id
     * @return 草拟文书详情
     */
    List<DraftDocDetail> queryDraftDocDetailByDocId(long docId);
}
