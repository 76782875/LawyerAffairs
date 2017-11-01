package com.lecshop.reportforms;

import com.lecshop.total.transactionrecords.bean.TransactionRecords;
import com.lecshop.total.transactionrecords.service.TransactionRecordsService;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 对账报表控制器
 *
 * Created by LecShop on 2017/7/12.
 */
@Controller
public class TransactionRecordsController {

    /**
     * 自动注入对账报表service
     */
    @Autowired
    private TransactionRecordsService transactionRecordsService;

    /**
     * 跳转至对账报表页面
     *
     * @return 对账报表页面
     */
    @RequestMapping("/totransactionrecord")
    public ModelAndView toTransactionRecord() {
        return new ModelAndView("reportforms/transactionrecords");
    }

    /**
     * 分页查询对账报表
     *
     * @param pageHelper 分页帮助类
     * @param name 律师姓名
     * @return 对账报表
     */
    @RequestMapping("/querytransactionrecord")
    @ResponseBody
    public BaseResponse queryTransactionRecordsList(PageHelper<TransactionRecords> pageHelper, String name) {
        return BaseResponse.build(transactionRecordsService.queryTransactionRecordsList(pageHelper, name));
    }

}
