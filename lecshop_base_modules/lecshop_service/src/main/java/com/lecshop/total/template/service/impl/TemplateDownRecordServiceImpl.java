package com.lecshop.total.template.service.impl;

import com.lecshop.total.template.bean.TemplateDownRecord;
import com.lecshop.total.template.mapper.TemplateDownRecordMapper;
import com.lecshop.total.template.service.TemplateDownRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 合同模板下载记录service实现类
 *
 * @author sunluyang on 2017/8/17.
 */
@Service
public class TemplateDownRecordServiceImpl implements TemplateDownRecordService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(TemplateDownRecordServiceImpl.class);

    /**
     * 自动注入合同模板下载记录数据库接口
     */
    @Autowired
    private TemplateDownRecordMapper templateDownRecordMapper;

    /**
     * 添加合同模板下载记录
     *
     * @param templateDownRecord 合同模板下载记录
     * @return 成功返回1，失败返回0
     */
    @Override
    public int addTemplateDownloadRecord(TemplateDownRecord templateDownRecord) {
        logger.debug("addTemplateDownloadRecord and templateDownRecord :{}", templateDownRecord);
        return templateDownRecordMapper.addTemplateDownloadRecord(templateDownRecord);
    }

    @Override
    public int queryTemplateDownRecordCountByTime(LocalDateTime localDateTime, long companyId) {
        logger.debug("queryTemplateDownRecordCountByTime and localDateTime :{}\r\n companyId:{}", localDateTime, companyId);
        Map<String, Object> map = new HashMap<>();
        map.put("time", localDateTime);
        map.put("companyId", companyId);
        return templateDownRecordMapper.queryTemplateDownRecordCountByTime(map);
    }
}
