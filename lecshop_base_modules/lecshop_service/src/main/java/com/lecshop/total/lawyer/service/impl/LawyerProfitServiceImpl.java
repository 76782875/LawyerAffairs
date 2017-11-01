package com.lecshop.total.lawyer.service.impl;

import com.lecshop.total.affairlist.service.*;
import com.lecshop.total.lawyer.bean.LawyerProfit;
import com.lecshop.total.lawyer.mapper.LawyerProfitMapper;
import com.lecshop.total.lawyer.service.LawyerProfitService;
import com.lecshop.total.transactionrecords.service.TransactionRecordsService;
import com.lecshop.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 律师收益service实现类
 * <p>
 * Created by LecShop on 2017/8/21.
 */
@Service
public class LawyerProfitServiceImpl implements LawyerProfitService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(LawyerProfitServiceImpl.class);

    /**
     * 自动注入律师收益数据库接口
     */
    @Autowired
    private LawyerProfitMapper lawyerProfitMapper;

    /**
     * 自动注入对账service
     */
    @Autowired
    private TransactionRecordsService transactionRecordsService;

    /**
     * 自动注入律师函service
     */
    @Autowired
    private LawyerLetterService lawyerLetterService;

    /**
     * 自动注入草拟文书service
     */
    @Autowired
    private DraftDocService draftDocService;

    /**
     * 自动注入修改文书service
     */
    @Autowired
    private ModifyDocService modifyDocService;

    /**
     * 自动注入电话咨询service
     */
    @Autowired
    private TelConsultationService telConsultationService;

    /**
     * 自动注入预约会面service
     */
    @Autowired
    private AppointMeetService appointMeetService;

    /**
     * 自动注入纠纷service
     */
    @Autowired
    private DisputesService disputesService;

    /**
     * 自动注入重大事项service
     */
    @Autowired
    private ImportantMatterService importantMatterService;

    /**
     * 分页查询律师收益
     *
     * @param pageHelper 分页帮助类
     * @param startTime  起始时间
     * @param endTime    终止时间
     * @return 律师收益
     */
    @Override
    public PageHelper<LawyerProfit> queryLawyerProfit(PageHelper<LawyerProfit> pageHelper, String startTime, String endTime) {
        logger.debug("queryLawyerProfit and pageHelper :{} \r\n and startTime :{} \r\n and endTime :{}", pageHelper, startTime, endTime);
        Map<String, Object> params = new HashMap<>();
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("startRowNum", pageHelper.getStartRowNum());
        params.put("pageSize", pageHelper.getPageSize());
        List<LawyerProfit> lawyerProfits = lawyerProfitMapper.queryLawyerProfit(params);
        lawyerProfits.forEach(lawyerProfit -> {
            params.put("id", lawyerProfit.getId());
            BigDecimal money = transactionRecordsService.queryLawyerTotalIncome(params) != null ? transactionRecordsService.queryLawyerTotalIncome(params) : new BigDecimal(0);
            lawyerProfit.setMoney(money);
            int orderCount = lawyerLetterService.queryLawyerLetterOrderCount(params) +
                    draftDocService.queryDraftDocOrderCount(params) +
                    modifyDocService.queryModifyDocOrderCount(params) +
                    telConsultationService.queryTelConsultationOrderCount(params) +
                    appointMeetService.queryMeetingOrderCount(params) +
                    disputesService.queryDisputesOrderCount(params) +
                    importantMatterService.queryImportantMatterOrderCount(params);
            lawyerProfit.setDoneOrder(orderCount);
        });
        Map<String, Object> parameters = new HashMap<>();
        pageHelper.getQueryParams(parameters, lawyerProfitMapper.queryLawyerCount(parameters));
        return pageHelper.setListDates(lawyerProfits);
    }

}
