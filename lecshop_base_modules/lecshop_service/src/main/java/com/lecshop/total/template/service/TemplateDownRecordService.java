package com.lecshop.total.template.service;

import java.time.LocalDateTime;

import com.lecshop.total.template.bean.TemplateDownRecord;

/**
 * 合同模板下载记录service
 *
 * @author sunluyang on 2017/8/17.
 */
public interface TemplateDownRecordService {

    /**
     * 添加合同模板下载记录
     *
     * @param templateDownRecord 合同模板下载记录
     * @return 成功返回1，失败返回0
     */
    int addTemplateDownloadRecord(TemplateDownRecord templateDownRecord);

    /**
     * 根据时间和公司id查询下载总量
     *
     * @param localDateTime 时间
     * @param companyId     公司id
     * @return 总数
     */
    int queryTemplateDownRecordCountByTime(LocalDateTime localDateTime, long companyId);

}
