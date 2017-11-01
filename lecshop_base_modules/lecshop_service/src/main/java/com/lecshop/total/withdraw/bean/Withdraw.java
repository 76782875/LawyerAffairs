package com.lecshop.total.withdraw.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.withdrawset.bean.WithdrawSet;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dujinkai on 17/7/26.
 * 提现实体类
 */
@Data
public class Withdraw {

    /**
     * 主键id
     */
    private long id;

    /**
     * 律师id
     */
    private long lawyerId;

    /**
     * 提现金额
     */
    private BigDecimal money;

    /**
     * 状态 0 申请  1 审核通过 2 拒绝 3 已打款
     */
    private String status;

    /**
     * 交流流水号
     */
    private String tradeNo;

    /**
     * 交易id
     */
    private long transId;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 律师信息
     */
    private Lawyer lawyer;

    /**
     * 律师提现设置
     */
    private WithdrawSet withdrawSet;

    /**
     * 设置默认值
     *
     * @param lawyerId 律师id
     * @return 返回当前对象
     */
    public Withdraw setDetailValuesFroApply(long lawyerId) {
        this.lawyerId = lawyerId;
        this.status = "0";
        this.tradeNo = mathString(new Date()) + (int) (Math.random() * 100);
        return this;
    }


    /**
     * 当前时间的字符串
     */
    private String mathString(Date date) {
        SimpleDateFormat time = new SimpleDateFormat("yyyyMMddHHmmss", Locale.UK);
        return time.format(date);
    }
}
