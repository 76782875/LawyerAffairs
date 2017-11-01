package com.lecshop.admin.payset.service.impl;

import com.lecshop.admin.payset.bean.PaySet;
import com.lecshop.admin.payset.bean.PaySetCommon;
import com.lecshop.admin.payset.mapper.PaySetMapper;
import com.lecshop.admin.payset.service.PaySetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付接口设置service实现层
 *
 * @author sunluyang on 2017/5/17.
 */
@Service
public class PaySetServiceImpl implements PaySetService {

    private Logger logger = LoggerFactory.getLogger(PaySetServiceImpl.class);

    /**
     * 注入支付接口设置mapper
     */
    @Autowired
    private PaySetMapper paySetMapper;

    /**
     * 查询支付接口设置
     *
     * @return 返回PaySetCommon
     */
    @Override
    public PaySetCommon queryPaySet() {
        logger.debug("queryPaySet...");
        return PaySetCommon.getPaySetCommon(new PaySetCommon(), paySetMapper.queryPaySet());
    }

    /**
     * 编辑支付接口设置
     *
     * @param paySetCommon 实体类参数
     * @param codeType     支付设置类型 1 支付宝 2 微信 3 银联
     * @return -1编辑出错 >=1成功
     */
    @Override
    @Transactional
    public int editPaySet(PaySetCommon paySetCommon, String codeType) {
        List<PaySet> list = new ArrayList<>();
        if (!("1".equals(codeType) || "2".equals(codeType) || "3".equals(codeType))) {
            logger.error("editPaySet error codeType is illegal");
            return -1;
        }
        //先删后增
        paySetMapper.deletePaySet(codeType);
        if ("1".equals(codeType)) {
            logger.debug("editPaySet aliPay...");
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getAliPaySet().getPid(), "pid"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getAliPaySet().getKey(), "key"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getAliPaySet().getPayee(), "payee"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getAliPaySet().getBeforeCallbackUrl(), "beforeCallbackUrl"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getAliPaySet().getBackCallbackUrl(), "backCallbackUrl"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getAliPaySet().getBackCallbackUrl(), "mobilePayCallbackUrl"));
        }
        if ("2".equals(codeType)) {
            logger.debug("editPaySet wechatPay...");
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getWechatPaySet().getAppId(), "appId"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getWechatPaySet().getAppSecret(), "appSecret"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getWechatPaySet().getMerchantNum(), "merchantNum"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getWechatPaySet().getApiKey(), "apiKey"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getWechatPaySet().getPayCallback(), "payCallback"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getWechatPaySet().getIsUse(), "isUse"));
        }
        if ("3".equals(codeType)) {
            logger.debug("editPaySet unionPay...");
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getUnionPaySet().getMerchantNum(), "merchantNum"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getUnionPaySet().getApiKey(), "apiKey"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getUnionPaySet().getBackCallbackUrl(), "backCallbackUrl"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getUnionPaySet().getBeforeCallbackUrl(), "beforeCallbackUrl"));
            list.add(PaySet.getPaySet(new PaySet(), codeType, paySetCommon.getUnionPaySet().getIsUse(), "isUse"));
        }
        return paySetMapper.addPaySet(list);
    }
}
