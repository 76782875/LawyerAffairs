package com.lecshop.total.affairlist.service;

import com.lecshop.total.affairlist.bean.LawyerLetterDetail;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;

/**
 * 律师函详情service
 * <p>
 * Created by LecShop on 2017/7/19.
 */
public interface LawyerLetterDetailService {

    /**
     * 插入律师函详情
     *
     * @param lawyerLetterDetail 律师函详情
     * @return 成功返回1，失败返回0
     */
    int addLawyerLetterDetail(LawyerLetterDetail lawyerLetterDetail);

    /**
     * 根据律师函id查询律师函详情
     *
     * @param letterId 律师函id
     * @return 返回律师涵详情
     */
    List<LawyerLetterDetail> queryLawyerLetterDetails(long letterId);

    /**
     * 律师函交流
     *
     * @param file 文件
     * @param letterId 律师函id
     * @param desc 描述
     * @return 成功返回1，失败返回0，文件上传失败返回-1
     */
    int lawyerLetterCommunion(CommonsMultipartFile file, long letterId, String desc);
}
