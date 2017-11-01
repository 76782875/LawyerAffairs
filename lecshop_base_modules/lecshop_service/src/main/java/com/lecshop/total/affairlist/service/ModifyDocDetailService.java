package com.lecshop.total.affairlist.service;

import com.lecshop.total.affairlist.bean.ModifyDocDetail;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * 文书交流信息service接口
 *
 * @author sunluyang on 2017/7/12.
 */
public interface ModifyDocDetailService {
    /**
     * 根据文书id查询文书交流详情
     *
     * @return 文书交流详情集合
     */
    List<ModifyDocDetail> queryModifyDocDetailById(long docId);

    /**
     * 添加修改文书详情
     *
     * @param modifyDocDetail 修改文书详情
     * @return 成功返回1 失败返回0
     */
    int addModifyDocDetail(ModifyDocDetail modifyDocDetail);

    /**
     * 修改文书交流
     *
     * @param file 文件
     * @param docId 修改文书id
     * @param desc 描述
     * @return 成功返回1，失败返回0，文件上传失败返回-1
     */
    int modifyDocCommunion(CommonsMultipartFile file, long docId, String desc);

}
