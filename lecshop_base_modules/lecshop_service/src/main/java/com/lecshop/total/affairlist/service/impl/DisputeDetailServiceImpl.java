package com.lecshop.total.affairlist.service.impl;

import com.lecshop.total.affairlist.bean.DisputeDetail;
import com.lecshop.total.affairlist.mapper.DisputeDetailMapper;
import com.lecshop.total.affairlist.service.DisputeDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 17/8/3.
 * 纠纷服务接口实现
 */
@Service
public class DisputeDetailServiceImpl implements DisputeDetailService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(DisputeDetailServiceImpl.class);

    /**
     * 注入纠纷详情数据库接口
     */
    @Autowired
    private DisputeDetailMapper disputeDetailMapper;

    @Override
    public List<DisputeDetail> queryByDisputeId(long disputeId) {
        logger.debug("queryByDisputeId and disputeId:{}", disputeId);
        return disputeDetailMapper.queryByDisputeId(disputeId);
    }

    @Override
    public DisputeDetail queryByDisputeIdAndCode(long disputeId, String code) {
        logger.debug("queryByDisputeIdAndCode and disputeId:{} \r\n code:{}", disputeId, code);
        Map<String, Object> params = new HashMap<>();
        params.put("disputeId", disputeId);
        params.put("code", code);
        return disputeDetailMapper.queryByDisputeIdAndCode(params);
    }

    @Override
    public int updateDisputeDetail(DisputeDetail disputeDetail) {
        logger.debug("updateDisputeDetail and disputeDetail:{}", disputeDetail);
        if (Objects.isNull(disputeDetail)) {
            logger.error("updateDisputeDetail fail due to params is null...");
            return 0;
        }

        return disputeDetailMapper.updateDisputeDetail(disputeDetail);
    }

    @Override
    public int addDisputeDetail(List<DisputeDetail> disputeDetails) {
        logger.debug("addDisputeDetail and disputeDetails:{}", disputeDetails);
        return disputeDetailMapper.addDisputeDetail(disputeDetails);
    }
}
