package com.lecshop.admin.payset.bean;

import lombok.Data;

import java.util.List;

/**
 * 支付设置统一类
 *
 * @author sunluyang on 2017/5/17.
 */
@Data
public class PaySetCommon {
    /**
     * 阿里支付
     */
    private AliPaySet aliPaySet = new AliPaySet();
    /**
     * 微信支付
     */
    private WechatPaySet wechatPaySet = new WechatPaySet();
    /**
     * 银联支付
     */
    private UnionPaySet unionPaySet = new UnionPaySet();

    /**
     * 用于组装支付设置对象在前端显示
     *
     * @param paySetCommon 支付设置对象
     * @param paySets      数据库映射对象
     * @return 支付设置对象
     */
    public static PaySetCommon getPaySetCommon(PaySetCommon paySetCommon, List<PaySet> paySets) {
        paySets.forEach(paySet -> {
            String value = paySet.getColumnValue();
            //支付宝
            if ("1".equals(paySet.getCodeType())) {
                if ("pid".equals(paySet.getColumnName())) {
                    paySetCommon.aliPaySet.setPid(value);
                }
                if ("key".equals(paySet.getColumnName())) {
                    paySetCommon.aliPaySet.setKey(value);
                }
                if ("payee".equals(paySet.getColumnName())) {
                    paySetCommon.aliPaySet.setPayee(value);
                }
                if ("beforeCallbackUrl".equals(paySet.getColumnName())) {
                    paySetCommon.aliPaySet.setBeforeCallbackUrl(value);
                }
                if ("backCallbackUrl".equals(paySet.getColumnName())) {
                    paySetCommon.aliPaySet.setBackCallbackUrl(value);
                }
                if ("mobilePayCallbackUrl".equals(paySet.getColumnName())) {
                    paySetCommon.aliPaySet.setMobilePayCallbackUrl(value);
                }
                if ("isUse".equals(paySet.getColumnName())) {
                    paySetCommon.aliPaySet.setIsUse(value);
                }
            }
            //微信
            if ("2".equals(paySet.getCodeType())) {
                if ("appId".equals(paySet.getColumnName())) {
                    paySetCommon.wechatPaySet.setAppId(value);
                }
                if ("appSecret".equals(paySet.getColumnName())) {
                    paySetCommon.wechatPaySet.setAppSecret(value);
                }
                if ("merchantNum".equals(paySet.getColumnName())) {
                    paySetCommon.wechatPaySet.setMerchantNum(value);
                }
                if ("apiKey".equals(paySet.getColumnName())) {
                    paySetCommon.wechatPaySet.setApiKey(value);
                }
                if ("payCallback".equals(paySet.getColumnName())) {
                    paySetCommon.wechatPaySet.setPayCallback(value);
                }
                if ("isUse".equals(paySet.getColumnName())) {
                    paySetCommon.wechatPaySet.setIsUse(value);
                }
            }
            //银联
            if ("3".equals(paySet.getCodeType())) {
                if ("merchantNum".equals(paySet.getColumnName())) {
                    paySetCommon.unionPaySet.setMerchantNum(value);
                }
                if ("apiKey".equals(paySet.getColumnName())) {
                    paySetCommon.unionPaySet.setApiKey(value);
                }
                if ("beforeCallbackUrl".equals(paySet.getColumnName())) {
                    paySetCommon.unionPaySet.setBeforeCallbackUrl(value);
                }
                if ("backCallbackUrl".equals(paySet.getColumnName())) {
                    paySetCommon.unionPaySet.setBackCallbackUrl(value);
                }
                if ("isUse".equals(paySet.getColumnName())) {
                    paySetCommon.unionPaySet.setIsUse(value);
                }
            }
        });
        return paySetCommon;
    }
}
