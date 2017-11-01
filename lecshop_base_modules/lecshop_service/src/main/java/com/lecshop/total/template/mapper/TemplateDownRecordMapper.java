package com.lecshop.total.template.mapper;


import com.lecshop.total.template.bean.TemplateDownRecord;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 合同模板下载mapper
 *
 * @author sunluyang on 2017/8/17.
 */
@Repository
public interface TemplateDownRecordMapper {

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
     * @param map 查询条件
     * @return 总数
     */
    int queryTemplateDownRecordCountByTime(Map<String, Object> map);

}
