package com.lecshop.total.companyinfo.service.impl;

import com.lecshop.total.affairlist.service.*;
import com.lecshop.total.companyinfo.bean.CompanyTransaction;
import com.lecshop.total.companyinfo.mapper.CompanyTransactionMapper;
import com.lecshop.total.companyinfo.service.CompanyTransactionService;
import com.lecshop.total.renewrecord.service.RenewRecordService;
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
 * 公司对账service实现类
 *
 * Created by LecShop on 2017/8/23.
 */
@Service
public class CompanyTransactionServicceImpl implements CompanyTransactionService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(CompanyTransactionServicceImpl.class);

    /**
     * 自动注入公司对账数据库接口
     */
    @Autowired
    private CompanyTransactionMapper companyTransactionMapper;

    /**
     * 自动注入续费记录service
     */
    @Autowired
    private RenewRecordService renewRecordService;

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
     * 分页查询公司对账
     *
     * @param pageHelper 分页帮助类
     * @param startTime  起始时间
     * @param endTime    终止时间
     * @return 公司对账
     */
    @Override
    public PageHelper<CompanyTransaction> queryCompanyTransaction(PageHelper<CompanyTransaction> pageHelper, String startTime, String endTime) {
        logger.debug("queryCompanyTransaction and pageHelper :{} \r\n and startTime :{} \r\n and endTime :{}", pageHelper, startTime, endTime);
        Map<String, Object> params = new HashMap<>();
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        params.put("startRowNum", pageHelper.getStartRowNum());
        params.put("pageSize", pageHelper.getPageSize());
        List<CompanyTransaction> companyTransactions = companyTransactionMapper.queryCompany(params);
        companyTransactions.forEach(companyTransaction -> {
            params.put("id", companyTransaction.getId());
            BigDecimal money = renewRecordService.querySumMoneyByCompanyId(params) != null ? renewRecordService.querySumMoneyByCompanyId(params) : new BigDecimal(0);
            companyTransaction.setMoney(money);
            int orderCount = lawyerLetterService.queryLawyerLetterOrderCountOfCompany(params) +
                    draftDocService.queryDraftDocOrderCountOfCompany(params) +
                    modifyDocService.queryModifyDocOrderCountOfCompany(params) +
                    telConsultationService.queryTelConsultationOrderCountOfCompany(params) +
                    appointMeetService.queryMeetingOrderCountOfCompany(params) +
                    disputesService.queryDisputesOrderCountOfCompany(params) +
                    importantMatterService.queryImportantMatterOrderCountOfCompany(params);
            companyTransaction.setAffairCount(orderCount);
        });
        Map<String, Object> parameters = new HashMap<>();
        pageHelper.getQueryParams(parameters, companyTransactionMapper.queryCompanyCount(parameters));
        return pageHelper.setListDates(companyTransactions);
    }

}
