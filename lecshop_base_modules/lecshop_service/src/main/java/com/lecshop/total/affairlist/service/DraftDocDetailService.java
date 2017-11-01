package com.lecshop.total.affairlist.service;

import com.lecshop.total.affairlist.bean.DraftDocDetail;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * 草拟文书详情service
 *
 * Created by LecShop on 2017/8/1.
 */
public interface DraftDocDetailService {

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

    /**
     * 草拟文书交流
     *
     * @param file 文件
     * @param docId 草拟文书id
     * @param desc 描述
     * @return 成功返回1，失败返回0，文件上传失败返回-1
     */
    int draftDocCommunion(CommonsMultipartFile file, long docId, String desc);

}
