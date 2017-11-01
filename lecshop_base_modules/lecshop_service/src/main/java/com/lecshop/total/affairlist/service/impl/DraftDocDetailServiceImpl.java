package com.lecshop.total.affairlist.service.impl;

import com.lecshop.total.affairlist.bean.DraftDocDetail;
import com.lecshop.total.affairlist.mapper.DraftDocDetailMapper;
import com.lecshop.total.affairlist.service.DraftDocDetailService;
import com.lecshop.utils.FileUpAndDownUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * 草拟文书详情service实现类
 *
 * Created by LecShop on 2017/8/1.
 */
@Service
public class DraftDocDetailServiceImpl implements DraftDocDetailService {

    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(DraftDocDetailServiceImpl.class);

    /**
     * 自动注入草拟文书详情数据库接口
     */
    @Autowired
    private DraftDocDetailMapper draftDocDetailMapper;

    /**
     * 添加草拟文书详情
     *
     * @param draftDocDetail 草拟文书详情
     * @return 成功返回1，失败返回0
     */
    @Override
    public int addDraftDocDetail(DraftDocDetail draftDocDetail) {
        logger.debug("addDraftDocDetail and draftDocDetail :{}", draftDocDetail);
        return draftDocDetailMapper.addDraftDocDetail(draftDocDetail);
    }

    /**
     * 根据草拟文书id查询草拟文书详情
     *
     * @param docId 草拟文书id
     * @return 草拟文书详情
     */
    @Override
    public List<DraftDocDetail> queryDraftDocDetailByDocId(long docId) {
        logger.debug("queryDraftDocDetailByDocId and docId :{}", docId);
        return draftDocDetailMapper.queryDraftDocDetailByDocId(docId);
    }

    /**
     * 草拟文书交流
     *
     * @param file  文件
     * @param docId 草拟文书id
     * @param desc  描述
     * @return 成功返回1，失败返回0，文件上传失败返回-1
     */
    @Override
    public int draftDocCommunion(CommonsMultipartFile file, long docId, String desc) {
        logger.debug("draftDocCommunion and file :{} \r\n and docId :{} \r\n and desc :{}", file, docId, desc);
        if (!file.isEmpty()) {
            if (FileUpAndDownUtils.fileUploadCommon(file, "upload.properties", this) == 1) {
                return this.addDraftDocDetail(DraftDocDetail.newInstance(docId, "1", desc, file.getOriginalFilename()));
            }
        } else {
            return this.addDraftDocDetail(DraftDocDetail.newInstance(docId, "1", desc, ""));
        }
        return -1;
    }

}
