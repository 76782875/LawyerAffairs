package com.lecshop.total.affairlist.service.impl;

import com.lecshop.total.affairlist.bean.LawyerLetterDetail;
import com.lecshop.total.affairlist.mapper.LawyerLetterDetailMapper;
import com.lecshop.total.affairlist.service.LawyerLetterDetailService;
import com.lecshop.utils.FileUpAndDownUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * 律师函详情service实现类
 *
 * Created by LecShop on 2017/7/19.
 */
@Service
public class LawyerLetterDetailServiceImpl implements LawyerLetterDetailService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(LawyerLetterDetailService.class);

    /**
     * 自动注入律师函详情数据库接口
     */
    @Autowired
    private LawyerLetterDetailMapper lawyerLetterDetailMapper;

    /**
     * 插入律师函详情
     *
     * @param lawyerLetterDetail 律师函详情
     * @return 成功返回1，失败返回0
     */
    @Override
    public int addLawyerLetterDetail(LawyerLetterDetail lawyerLetterDetail) {
        logger.debug("addLawyerLetterDetail and lawyerLetterDetail :{}", lawyerLetterDetail);
        return lawyerLetterDetailMapper.addLawyerLetterDetail(lawyerLetterDetail);
    }

    /**
     * 根据律师函id查询律师函交流详情
     *
     * @param letterId 律师函id
     * @return 律师函交流详情
     */
    @Override
    public List<LawyerLetterDetail> queryLawyerLetterDetails(long letterId) {
        logger.debug("queryLawyerLetterDetails and letterId:{}", letterId);
        return lawyerLetterDetailMapper.queryLawyerLetterDetails(letterId);
    }

    /**
     * 律师函交流
     *
     * @param file 文件
     * @param letterId 律师函id
     * @param desc 描述
     * @return 成功返回1，失败返回0，文件上传失败返回-1
     */
    public int lawyerLetterCommunion(CommonsMultipartFile file, long letterId, String desc) {
        logger.debug("lawyerLetterCommunion and file :{} \r\n and letterId :{} \r\n and desc :{}", file, letterId, desc);
        if (!file.isEmpty()) {
            if (FileUpAndDownUtils.fileUploadCommon(file, "upload.properties", this) == 1) {
                return this.addLawyerLetterDetail(LawyerLetterDetail.newInstance(letterId, "1", desc, file.getOriginalFilename()));
            }
        } else {
            return this.addLawyerLetterDetail(LawyerLetterDetail.newInstance(letterId, "1", desc, ""));
        }
        return -1;
    }

}
