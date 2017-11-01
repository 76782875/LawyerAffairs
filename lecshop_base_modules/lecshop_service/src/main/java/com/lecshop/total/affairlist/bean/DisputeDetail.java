package com.lecshop.total.affairlist.bean;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.lecshop.utils.CustomLocalDateTimeDeserializer;
import com.lecshop.utils.CustomLocalDateTimeSerializer;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by dujinkai on 17/8/3.
 * 纠纷详情
 */
@Data
public class DisputeDetail {

    /**
     * 主键id
     */
    private long id;

    /**
     * 纠纷id
     */
    private long disputeId;

    /**
     * 1 用户 2 律师
     */
    private String type;

    /**
     * 描述
     */
    private String desc;

    /**
     * 文件下载地址
     */
    private String url;

    /**
     * 0 未填写 1 已填写
     */
    private String status;

    /**
     * code 码  做唯一标示
     */
    private String code;

    /**
     * 创建时间
     */
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    public DisputeDetail buildDisputesForCompanyUpdate(String url) {
        this.url = url;
        return this;
    }

    public List<DisputeDetail> disputeDetailList(long disputeId) {
        List<DisputeDetail> disputeDetails = new ArrayList<>();
        returnMap().forEach((key, value) -> disputeDetails.add(buildDisputes(new DisputeDetail(), disputeId, value, key)));
        return disputeDetails;
    }

    private DisputeDetail buildDisputes(DisputeDetail disputeDetail, long disputeId, String type, String code) {
        disputeDetail.disputeId = disputeId;
        disputeDetail.type = type;
        disputeDetail.desc = "";
        disputeDetail.url = "";
        disputeDetail.status = "0";
        disputeDetail.code = code;
        return disputeDetail;
    }

    private Map<String, String> returnMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("001", "2");
        map.put("002", "2");
        map.put("003", "2");
        map.put("004", "2");
        map.put("005", "1");
        map.put("006", "1");
        map.put("007", "1");
        map.put("011", "2");
        map.put("012", "2");
        map.put("021", "2");
        map.put("031", "2");
        map.put("032", "2");
        map.put("033", "2");
        map.put("034", "1");
        map.put("041", "2");
        map.put("051", "2");
        map.put("052", "2");
        map.put("053", "2");
        map.put("054", "1");
        map.put("061", "2");
        map.put("062", "2");
        map.put("063", "1");
        map.put("071", "2");
        map.put("072", "2");
        map.put("081", "2");
        map.put("082", "2");
        map.put("091", "2");
        map.put("092", "2");
        map.put("101", "2");
        map.put("102", "1");
        map.put("111", "2");
        map.put("121", "2");
        map.put("131", "2");
        map.put("141", "2");
        map.put("142", "1");
        map.put("151", "2");
        map.put("152", "2");
        map.put("153", "2");
        map.put("154", "2");
        map.put("155", "2");
        map.put("156", "1");
        map.put("161", "2");
        map.put("162", "2");
        map.put("163", "1");
        map.put("171", "2");
        map.put("172", "2");
        map.put("181", "2");
        map.put("182", "2");
        map.put("191", "2");
        map.put("192", "2");
        map.put("201", "2");
        map.put("211", "2");
        map.put("212", "2");
        map.put("213", "2");
        map.put("221", "2");
        map.put("231", "2");
        map.put("232", "2");
        map.put("233", "2");
        map.put("234", "2");
        map.put("241", "2");
        map.put("242", "2");
        map.put("251", "2");
        map.put("261", "2");
        map.put("262", "2");
        map.put("271", "2");
        return map;
    }
}
