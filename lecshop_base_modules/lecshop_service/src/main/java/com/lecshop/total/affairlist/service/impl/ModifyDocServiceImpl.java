package com.lecshop.total.affairlist.service.impl;

import com.lecshop.admin.profitset.service.ProfitSetService;
import com.lecshop.total.affairlist.bean.ModifyDoc;
import com.lecshop.total.affairlist.bean.ModifyDocDetail;
import com.lecshop.total.affairlist.mapper.ModifyDocMapper;
import com.lecshop.total.affairlist.service.ModifyDocDetailService;
import com.lecshop.total.affairlist.service.ModifyDocService;
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

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 文书事务service实现类
 *
 * @author sunluyang on 2017/7/12.
 */
@Service
public class ModifyDocServiceImpl implements ModifyDocService {

    /**
     * 调试日志
     */
    Logger logger = LoggerFactory.getLogger(ModifyDocServiceImpl.class);

    /**
     * 注入文书事务mapper
     */
    @Autowired
    private ModifyDocMapper modifyDocMapper;

    /**
     * 自动注入文书详情service
     */
    @Autowired
    private ModifyDocDetailService modifyDocDetailService;

    /**
     * 自动注入律师service
     */
    @Autowired
    private LawyerService lawyerService;

    /**
     * 自动注入对账报表service
     */
    @Autowired
    private TransactionRecordsService transactionRecordsService;

    /**
     * 自动注入律师收益设置service
     */
    @Autowired
    private ProfitSetService profitSetService;

    /**
     * 查询文书事务
     *
     * @param pageHelper 分页帮助类
     * @return 文书事务
     */
    @Override
    public PageHelper<ModifyDoc> queryModifyDoc(PageHelper<ModifyDoc> pageHelper, String lawyerName, String companyName, long companyId, long userId, long lawyerId) {
        logger.info("queryTelConsultation and pageHelper:{}\r\n lawyerName:{}\r\n companyName:{}\r\n companyId:{}\r\n userId:{}\r\n lawyerId:{}\r\n",
                pageHelper, lawyerName, companyName, companyId, userId, lawyerId);
        Map<String, Object> params = new HashMap<>();
        params.put("lawyerName", lawyerName);
        params.put("companyName", companyName);
        params.put("companyId", companyId);
        params.put("userId", userId);
        params.put("lawyerId", lawyerId);
        return pageHelper.setListDates(modifyDocMapper.queryModifyDoc(pageHelper.getQueryParams(params, modifyDocMapper.queryModifyDocCount(params))));
    }

    /**
     * 查询待处理的修改文书信息
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return 修改文书集合
     */
    @Override
    public List<ModifyDoc> queryToDoModifyDoc(long companyId, long userId) {
        logger.debug("queryToDoModifyDoc and companyId :{} \r\n userId :{}", companyId, userId);
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", companyId);
        map.put("userId", userId);
        return modifyDocMapper.queryToDoModifyDoc(map);
    }

    /**
     * 修改文书
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @param file      文书文件
     * @param name      文书名称
     * @param desc      修改要求
     * @return 成功返回1， 文件上传失败-1
     */
    @Override
    public int updateModifyDoc(long companyId, long userId, CommonsMultipartFile file, String name, String desc) {
        logger.debug("updateModifyDoc and companyId :{} \r\n userId :{} \r\n name :{} \r\n and desc :{}", companyId, userId, name, desc);
        if (FileUpAndDownUtils.fileUploadCommon(file, "upload.properties", this) == 1) {
            List<Lawyer> lawyers = lawyerService.queryLawyerOwn();
            int index = new Random().nextInt(lawyers.size());
            ModifyDoc doc = ModifyDoc.newInstance(companyId, userId, lawyers.get(index).getId(), name, desc, file.getOriginalFilename());
            modifyDocMapper.addModifyDoc(doc);
            return modifyDocDetailService.addModifyDocDetail(ModifyDocDetail.newInstance(doc.getId(), "1", desc, file.getOriginalFilename()));
        }
        return -1;
    }

    /**
     * 确认修改文书
     *
     * @param id       文书id
     * @param status   文书状态
     * @param lawyerId 律师id
     * @return 成功返回1，失败返回0
     */
    @Override
    public int confirmModifyDoc(long id, String status, long lawyerId) {
        logger.debug("confirmModifyDocStatus and id :{} \r\n and status :{} \r\n and lawyerId", id, status, lawyerId);
        transactionRecordsService.addTransactionRecords(TransactionRecords.buildForAdd(lawyerId, profitSetService.queryProfitSet().buildMoney(4), 6));
        return changeModifyDocStatus(id, status);
    }

    /**
     * 修改文书状态
     *
     * @param id     文书id
     * @param status 文书状态
     * @return 成功返回1，失败返回0
     */
    @Override
    public int changeModifyDocStatus(long id, String status) {
        logger.debug("changeModifyDocStatus and id :{} \r\n and status :{}", id, status);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("status", status);
        return modifyDocMapper.changeModifyDocStatus(params);
    }

    /**
     * 为修改文书评分
     *
     * @param id    文书id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    @Override
    public int gradeForModifyDoc(long id, int score) {
        logger.debug("gradeForModifyDoc and id :{} \r\n and score :{}", id, score);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("score", score);
        return modifyDocMapper.gradeForModifyDoc(params);
    }

    @Override
    public int queryModifyDocCountByTime(LocalDateTime localDateTime, long companyId) {
        Map<String, Object> params = new HashMap<>();
        params.put("time", localDateTime);
        params.put("companyId", companyId);
        return modifyDocMapper.queryModifyDocCountByTime(params);
    }


    /**
     * 查询律师的修改文书订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 修改文书订单数目
     */
    @Override
    public int queryModifyDocOrderCount(Map<String, Object> params) {
        logger.debug("queryModifyDocOrderCount and params :{}", params);
        return modifyDocMapper.queryModifyDocOrderCount(params);
    }

    /**
     * 查询公司发起的修改文书订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 修改文书订单数目
     */
    @Override
    public int queryModifyDocOrderCountOfCompany(Map<String, Object> params) {
        logger.debug("queryModifyDocOrderCountOfCompany and params :{}", params);
        return modifyDocMapper.queryModifyDocOrderCountOfCompany(params);
    }
}
