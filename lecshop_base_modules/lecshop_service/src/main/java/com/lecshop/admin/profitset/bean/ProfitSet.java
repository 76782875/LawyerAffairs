package com.lecshop.admin.profitset.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 律师收益设置实体类
 *
 * @author sunluyang on 2017/7/11.
 */
@Data
public class ProfitSet {
    /**
     * 主键id
     */
    private long id;
    /**
     * 每月多少号
     */
    private int withdrawTime;
    /**
     * 电话咨询一次多少钱
     */
    private BigDecimal telConMoney;
    /**
     * 电话咨询一次 律师的佣金设置
     */
    private BigDecimal telConCommission;
    /**
     * 预约会面多少钱 （指定律师）
     */
    private BigDecimal meetMoney;
    /**
     * 预约会面 律师的佣金(指定律师)
     */
    private BigDecimal meetCommission;
    /**
     * 预约会面一次多少钱（不指定律师）
     */
    private BigDecimal meetAllMoney;
    /**
     * 预约会面的佣金 （不指定律师）
     */
    private BigDecimal meetAllCommisson;

    /**
     * 修改文书多少钱
     */
    private BigDecimal modifyDocMoney;
    /**
     * 修改文书的佣金
     */
    private BigDecimal modifyDocCommission;
    /**
     * 草拟文书多少钱
     */
    private BigDecimal draftDocMoney;
    /**
     * 草拟文书佣金
     */
    private BigDecimal draftDocCommission;
    /**
     * 律师函多少钱
     */
    private BigDecimal lawyerLetterMoney;
    /**
     * 律师函佣金
     */
    private BigDecimal lawyerLetterCommission;

    /**
     * 律师各类服务实际收入金额
     *
     * @param type 类型
     *             1 电话咨询
     *             2 指定会面
     *             3 非指定会面
     *             4 修改文书
     *             5 草拟文书
     *             6 律师函
     * @return 返回金额
     */
    public BigDecimal buildMoney(int type) {
        switch (type) {
            case 1:
                return this.telConMoney.multiply(this.telConCommission);
            case 2:
                return this.meetMoney.multiply(this.meetCommission);
            case 3:
                return this.meetAllMoney.multiply(this.meetAllCommisson);
            case 4:
                return this.modifyDocMoney.multiply(this.modifyDocCommission);
            case 5:
                return this.draftDocMoney.multiply(this.draftDocCommission);
            case 6:
                return this.lawyerLetterMoney.multiply(this.lawyerLetterCommission);
            default:
                return new BigDecimal(0);
        }
    }
}
