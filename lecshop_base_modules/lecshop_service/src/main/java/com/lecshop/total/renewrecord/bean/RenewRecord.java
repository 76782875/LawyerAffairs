package com.lecshop.total.renewrecord.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 续费记录实体类
 *
 * @author sunluyang on 2017/7/27.
 */
@Data
public class RenewRecord {

    /**
     * 主键id
     */
    private long id;

    /**
     * 用户id
     */
    private long userId;

    /**
     * 公司id
     */
    private long companyId;

    /**
     * 支付状态 0 未支付 1已支付
     */
    private String status;

    /**
     * 续费金额
     */
    private BigDecimal renewMoney;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 续费人名称
     */
    private String renewName;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 类型 0 续费类型A 1 续费类型B  2续费类型C  4充值类型D 5充值类型E....
     */
    private String type;

    /**
     * 订单名称
     */
    private String orderName;

    public void getDefaultRenewRecord(long userId, long companyId) {
        this.userId = userId;
        this.companyId = companyId;
        this.createTime = LocalDateTime.now();
        this.status = "0";
        this.orderNo = String.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        this.renewMoney = getDefaultRenewMoney(this.type);
        this.orderName = getDefaultOrderName(this.type);
    }

    private BigDecimal getDefaultRenewMoney(String type) {
        Map<String, BigDecimal> map = new HashMap<>();
        map.put("0", getFormatDecimal(0.1));
        map.put("1", getFormatDecimal(0.1));
        map.put("2", getFormatDecimal(0.1));
        map.put("3", getFormatDecimal(100));
        map.put("4", getFormatDecimal(500));
        map.put("5", getFormatDecimal(2000));
        map.put("6", getFormatDecimal(0.1));
        return map.get(Objects.isNull(map.get(type)) ? "6" : type);
    }

    private String getDefaultOrderName(String type) {
        Map<String, String> map = new HashMap<>();
        map.put("0", "创业期法律服务");
        map.put("1", "经营期法律服务");
        map.put("2", "爆发期法律服务");
        map.put("3", "普通充值");
        map.put("4", "高级充值");
        map.put("5", "超级充值");
        map.put("6", "未知充值");
        return map.get(Objects.isNull(map.get(type)) ? "6" : type);
    }

    private BigDecimal getFormatDecimal(double price) {
        return new BigDecimal(price).setScale(2, BigDecimal.ROUND_DOWN);
    }
}
