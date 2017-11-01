package com.lecshop.total.transactionrecords.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 对账报表实体类
 * <p>
 * Created by LecShop on 2017/7/12.
 */
@Data
public class TransactionRecords {

    /**
     * 主键id
     */
    private long id;

    /**
     * 律师id
     */
    private long lawyerId;

    /**
     * 发生的金额
     */
    private BigDecimal money;

    /**
     * 1.收入 2 支出
     */
    private String type;

    /**
     * 描述
     */
    private String desc;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 律师
     */
    private Lawyer lawyer;

    /**
     * 构造提现记录
     *
     * @param lawyerId 律师id
     * @param money    金额
     * @return 返回律师交易记录
     */
    public static TransactionRecords buildForWithdraw(long lawyerId, BigDecimal money) {
        TransactionRecords transactionRecords = new TransactionRecords();
        transactionRecords.lawyerId = lawyerId;
        transactionRecords.money = money;
        transactionRecords.type = "2";
        transactionRecords.desc = "律师提现";
        return transactionRecords;
    }


    /**
     * 律师提现拒绝,返还提现金额
     *
     * @param lawyerId 律师id
     * @param money    金额
     * @return 返回律师交易记录
     */
    public static TransactionRecords buildForRefuseWithdraw(long lawyerId, BigDecimal money) {
        TransactionRecords transactionRecords = new TransactionRecords();
        transactionRecords.lawyerId = lawyerId;
        transactionRecords.money = money;
        transactionRecords.type = "1";
        transactionRecords.desc = "律师提现拒绝,返还提现金额";
        return transactionRecords;
    }

    /**
     * 添加律师收益记录
     *
     * @param lawyerId 律师id
     * @param money    金额
     * @return 返回律师交易记录
     */
    public static TransactionRecords buildForAdd(long lawyerId, BigDecimal money, int type) {
        TransactionRecords transactionRecords = new TransactionRecords();
        transactionRecords.lawyerId = lawyerId;
        transactionRecords.money = money;
        transactionRecords.type = "1";
        transactionRecords.desc = transactionRecords.returnDesc(type);
        return transactionRecords;
    }

    /**
     * 三大事务对应的类型
     *
     * @param type 类型
     * @return 对应类型
     */
    private String returnDesc(int type) {
        switch (type) {
            case 1:
                return "电话咨询";
            case 2:
                return "指定预约";
            case 3:
                return "非指定预约";
            case 4:
                return "律师函";
            case 5:
                return "草拟文书";
            case 6:
                return "修改文书";
            case 7:
                return "纠纷服务";
            case 8:
                return "重大事项";
            default:
                return "未知事务";
        }
    }
}
