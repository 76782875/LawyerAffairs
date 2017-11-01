package com.lecshop.total.affairlist.service.impl;

import com.lecshop.admin.profitset.service.ProfitSetService;
import com.lecshop.total.affairlist.bean.DraftDoc;
import com.lecshop.total.affairlist.bean.DraftDocDetail;
import com.lecshop.total.affairlist.mapper.DraftDocMapper;
import com.lecshop.total.affairlist.service.DraftDocDetailService;
import com.lecshop.total.affairlist.service.DraftDocService;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.lawyer.service.LawyerService;
import com.lecshop.total.transactionrecords.bean.TransactionRecords;
import com.lecshop.total.transactionrecords.service.TransactionRecordsService;
import com.lecshop.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 草拟文书service实现类
 * <p>
 * Created by LecShop on 2017/7/18.
 */
@Service
public class DraftDocServiceImpl implements DraftDocService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(DraftDocServiceImpl.class);

    /**
     * 自动注入草拟文书数据库接口
     */
    @Autowired
    private DraftDocMapper draftDocMapper;
    /**
     * 自动注入草拟文书详情service
     */
    @Autowired
    private DraftDocDetailService draftDocDetailService;
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

    @Override
    public PageHelper<DraftDoc> queryDraftDoc(PageHelper<DraftDoc> pageHelper, String lawyerName, String companyName, long companyId, long userId, long lawyerId) {
        logger.info("queryTelConsultation and pageHelper:{}\r\n lawyerName:{}\r\n companyName:{}\r\n companyId:{}\r\n userId:{}\r\n lawyerId:{}\r\n",
                pageHelper, lawyerName, companyName, companyId, userId, lawyerId);
        Map<String, Object> params = new HashMap<>();
        params.put("lawyerName", lawyerName);
        params.put("companyName", companyName);
        params.put("companyId", companyId);
        params.put("userId", userId);
        params.put("lawyerId", lawyerId);
        return pageHelper.setListDates(draftDocMapper.queryDraftDoc(pageHelper.getQueryParams(params, draftDocMapper.queryDraftDocCount(params))));
    }

    /**
     * 查询待处理的草拟文书信息
     *
     * @param companyId 公司id
     * @param userId    用户id
     * @return 草拟文书集合
     */
    @Override
    public List<DraftDoc> queryToDoDraftDoc(long companyId, long userId) {
        logger.debug("queryToDoDraftDoc and companyId :{} \r\n and userId :{}", companyId, userId);
        Map<String, Object> map = new HashMap<>();
        map.put("companyId", companyId);
        map.put("userId", userId);
        return draftDocMapper.queryToDoDraftDoc(map);
    }

    /**
     * 添加草拟文书
     *
     * @param userId    用户id
     * @param companyId 公司id
     * @param name      草拟文书名称
     * @param desc      草拟要求
     * @return 成功返回1，失败返回0
     */
    @Override
    public int addDraftDoc(long userId, long companyId, String name, String desc) {
        logger.debug("addDraftDoc and userId :{} \r\n and companyId :{} and name :{} \r\n and desc :{}", userId, companyId, name, desc);
        List<Lawyer> lawyers = lawyerService.queryLawyerOwn();
        int index = new Random().nextInt(lawyers.size());
        DraftDoc doc = DraftDoc.newInstance(companyId, userId, lawyers.get(index).getId(), name);
        draftDocMapper.addDraftDoc(doc);
        return draftDocDetailService.addDraftDocDetail(DraftDocDetail.newInstance(doc.getId(), "1", desc));
    }

    /**
     * 确认草拟文书
     *
     * @param id       文书id
     * @param status   文书状态
     * @param lawyerId 律师id
     * @return 成功返回1，失败返回0
     */
    @Override
    public int confirmDraftDoc(long id, String status, long lawyerId) {
        logger.debug("confirmDraftDoc and id :{} \r\n and status :{} \r\n and lawyerId :{}", id, status, lawyerId);
        transactionRecordsService.addTransactionRecords(TransactionRecords.buildForAdd(lawyerId, profitSetService.queryProfitSet().buildMoney(5), 5));
        return updateDraftDocStatus(id, status);
    }

    /**
     * 修改草拟文书状态
     *
     * @param id     文书id
     * @param status 文书状态
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateDraftDocStatus(long id, String status) {
        logger.debug("updateDraftDocStatus and id :{} \r\n and status :{}", id, status);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("status", status);
        return draftDocMapper.updateDraftDocStatus(params);
    }

    /**
     * 为草拟文书评分
     *
     * @param id    文书id
     * @param score 分数
     * @return 成功返回1，失败返回0
     */
    @Override
    public int gradeForDraftDoc(long id, int score) {
        logger.debug("gradeForDraftDoc and id :{} \r\n and score :{}", id, score);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("score", score);
        return draftDocMapper.gradeForDraftDoc(params);
    }


    /**
     * 查询律师的草拟文书订单数目
     *
     * @param params 律师id、起始时间及终止时间
     * @return 草拟文书订单数目
     */
    @Override
    public int queryDraftDocOrderCount(Map<String, Object> params) {
        logger.debug("queryDraftDocOrderCount and params :{}", params);
        return draftDocMapper.queryDraftDocOrderCount(params);
    }

    /**
     * 查询公司发起的草拟文书订单数目
     *
     * @param params 公司id、起始时间及终止时间
     * @return 草拟文书订单数目
     */
    @Override
    public int queryDraftDocOrderCountOfCompany(Map<String, Object> params) {
        logger.debug("queryDraftDocOrderCountOfCompany and params :{}", params);
        return draftDocMapper.queryDraftDocOrderCountOfCompany(params);
    }
}
