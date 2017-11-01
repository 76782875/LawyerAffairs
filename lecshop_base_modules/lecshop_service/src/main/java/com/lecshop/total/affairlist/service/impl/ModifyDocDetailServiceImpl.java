package com.lecshop.total.affairlist.service.impl;

import com.lecshop.total.affairlist.bean.ModifyDocDetail;
import com.lecshop.total.affairlist.mapper.ModifyDocDetailMapper;
import com.lecshop.total.affairlist.service.ModifyDocDetailService;
import com.lecshop.utils.FileUpAndDownUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * 文书交流信息service实现类
 *
 * @author sunluyang on 2017/7/12.
 */
@Service
public class ModifyDocDetailServiceImpl implements ModifyDocDetailService {

    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(ModifyDocDetailServiceImpl.class);

    /**
     * 注入文书事务交流详情mapper
     */
    @Autowired
    private ModifyDocDetailMapper modifyDocDetailMapper;

    /**
     * 根据文书id查询文书事务交流详情
     *
     * @param docId 文书id
     * @return 文书事务交流详情
     */
    @Override
    public List<ModifyDocDetail> queryModifyDocDetailById(long docId) {
        logger.debug("queryModifyDocDetailById and docId :{}", docId);
        return modifyDocDetailMapper.queryModifyDocDetailById(docId);
    }

    /**
     * 添加修改文书详情
     *
     * @param modifyDocDetail 修改文书详情
     * @return 成功返回1，失败返回0
     */
    @Override
    public int addModifyDocDetail(ModifyDocDetail modifyDocDetail) {
        logger.debug("addModifyDocDetail and modifyDocDetail :{}", modifyDocDetail);
        return modifyDocDetailMapper.addModifyDocDetail(modifyDocDetail);
    }

    /**
     * 修改文书交流
     *
     * @param file  文件
     * @param docId 修改文书id
     * @param desc  描述
     * @return 成功返回1，失败返回0，文件上传失败返回-1
     */
    @Override
    public int modifyDocCommunion(CommonsMultipartFile file, long docId, String desc) {
        logger.debug("modifyDocCommunion and file :{} \r\n and docId :{} \r\n and desc :{}", file, docId, desc);
        if (!file.isEmpty()) {
            if (FileUpAndDownUtils.fileUploadCommon(file, "upload.properties", this) == 1) {
                return this.addModifyDocDetail(ModifyDocDetail.newInstance(docId, "1", desc, file.getOriginalFilename()));
            }
        } else {
            return this.addModifyDocDetail(ModifyDocDetail.newInstance(docId, "1", desc, ""));
        }
        return -1;
    }

}
