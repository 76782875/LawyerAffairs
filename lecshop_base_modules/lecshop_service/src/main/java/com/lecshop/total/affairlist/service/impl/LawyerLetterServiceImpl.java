package com.lecshop.total.affairlist.service.impl;

import com.lecshop.admin.profitset.service.ProfitSetService;
import com.lecshop.total.affairlist.bean.LawyerLetter;
import com.lecshop.total.affairlist.bean.LawyerLetterDetail;
import com.lecshop.total.affairlist.mapper.LawyerLetterMapper;
import com.lecshop.total.affairlist.service.LawyerLetterDetailService;
import com.lecshop.total.affairlist.service.LawyerLetterService;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.lawyer.service.LawyerService;
import com.lecshop.total.transactionrecords.bean.TransactionRecords;
import com.lecshop.total.transactionrecords.service.TransactionRecordsService;
import com.lecshop.utils.FileUpAndDownUtils;
import com.lecshop.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 律师函service实现类
 * <p>
 * Created by LecShop on 2017/7/19.
 */
@Service
public class LawyerLetterServiceImpl implements LawyerLetterService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(LawyerLetterServiceImpl.class);

    /**
     * 自动注入律师函数据库接口
     */
    @Autowired
    private LawyerLetterMapper lawyerLetterMapper;

    /**
     * 自动注入律师service
     */
    @Autowired
    private LawyerService lawyerService;

    /**
     * 自动注入律师函详情service
     */
    @Autowired
    private LawyerLetterDetailService lawyerLetterDetailService;

    /**
     * 自动注入对账报表service
     */
    @Autowired
    private TransactionRecordsService transactionRecordsService;

    /**
     * 律师收益设置service
     */
    @Autowired
    private ProfitSetService profitSetService;

    /**
     * 分页查询律师函信息
     *
     * @param pageHelper  分页帮助类
     * @param lawyerName  律师名称
     * @param companyName 公司名称
     * @param companyId   公司id
     * @param userId      用户id
     * @param lawyerId    律师id
     * @return 律师函信息
     */
    @Override
    public PageHelper<LawyerLetter> queryLawyerLetter(PageHelper<LawyerLetter> pageHelper, String lawyerName, String companyName, long companyId, long userId, long lawyerId) {
        logger.info("queryLawyerLetter and pageHelper:{}\r\n lawyerName:{}\r\n companyName:{}\r\n companyId:{}\r\n userId:{}\r\n lawyerId:{}\r\n",
                pageHelper, lawyerName, companyName, companyId, userId, lawyerId);
        Map<String, Object> params = new HashMap<>();
        params.put("lawyerName", lawyerName);
        params.put("companyName", companyName);
        params.put("companyId", companyId);
        params.put("userId", userId);
        params.put("lawyerId", lawyerId);
        return pageHelper.setListDates(lawyerLetterMapper.queryLawyerLetter(pageHelper.getQueryParams(params, lawyerLetterMapper.queryLawyerLetterCount(params))));
    }

    /**
     * 查询待处理律师函
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return 律师函集合
     */
    @Override
    public List<LawyerLetter> queryToDoLawyerLetter(long companyId, long userId) {
        logger.info("queryToDoLawyerLetter and companyId:{}\r\n userId:{}\r\n", companyId, userId);
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", companyId);
        map.put("userId", userId);
        return lawyerLetterMapper.queryToDoLawyerLetter(map);
    }

    /**
     * 提交律师函
     *
     * @param file      律师函文件
     * @param companyId 公司id
     * @param userId    用户id
     * @param mobile    电话
     * @param address   地址
     * @param desc      描述
     * @return 成功返回1， 文件上传失败-1
     */
    @Override
    public int addLawyerLetter(CommonsMultipartFile file, long companyId, long userId, String mobile, String address, String desc) {
        logger.debug("addLawyerLetter and companyId :{} \r\n and userId :{} \r\n and mobile :{} \r\n and address :{} \r\n and desc", companyId, userId, mobile, address, desc);
        if (FileUpAndDownUtils.fileUploadCommon(file, "upload.properties", this) == 1) {
            List<Lawyer> lawyers = lawyerService.queryLawyerOwn();
            int index = new Random().nextInt(lawyers.size());
            LawyerLetter letter = LawyerLetter.newInstance(companyId, userId, lawyers.get(index).getId(), mobile, address);
            lawyerLetterMapper.addLawyerLetter(letter);
            LawyerLetterDetail lawyerLetterDetail = LawyerLetterDetail.newInstance(letter.getId(), "1", desc, file.getOriginalFilename());
            return lawyerLetterDetailService.addLawyerLetterDetail(lawyerLetterDetail);
        }
        return -1;
    }

    /**
     * 确认律师函
     *
     * @param id       主键id
     * @param status   状态
     * @param lawyerId 律师id
     * @return 成功返回1 失败返回0
     */
    @Override
    public int confirmLawyerLetter(long id, String status, long lawyerId) {
        logger.debug("confirmLawyerLetter and id :{} \r\n and status :{} \r\n and lawyerId :{}", id, status, lawyerId);
        transactionRecordsService.addTransactionRecords(TransactionRecords.buildForAdd(lawyerId, profitSetService.queryProfitSet().buildMoney(6), 4));
        return changeLawyerLetterStatus(id, status);
    }

    /**
     * 修改律师函状态
     *
     * @param id     主键id
     * @param status 状态
     * @return 成功返回1 失败返回0
     */
    @Override
    public int changeLawyerLetterStatus(long id, String status) {
        logger.debug("changeLawyerLetterStatus and id :{} \r\n status:{}", id, status);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("status", status);
        return lawyerLetterMapper.changeLawyerLetterStatus(params);
    }

    /**
     * 为律师函评分
     *
     * @param id    律师函id
     * @param score 评分
     * @return 成功返回1，失败返回0
     */
    @Override
    public int gradeForLawyerLetter(long id, int score) {
        logger.debug("gradeForLawyerLetter and id :{} \r\n and score :{}", id, score);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("score", score);
        return lawyerLetterMapper.gradeForLawyerLetter(params);
    }


    /**
     * 查询律师的律师函订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 律师函订单数目
     */
    @Override
    public int queryLawyerLetterOrderCount(Map<String, Object> params) {
        logger.debug("queryLawyerLetterOrderCount and params :{}", params);
        return lawyerLetterMapper.queryLawyerLetterOrderCount(params);
    }

    /**
     * 查询公司的律师函订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 律师函订单数目
     */
    @Override
    public int queryLawyerLetterOrderCountOfCompany(Map<String, Object> params) {
        logger.debug("queryLawyerLetterOrderCountOfCompany and params :{}", params);
        return lawyerLetterMapper.queryLawyerLetterOrderCountOfCompany(params);
    }
}
