package com.lecshop.total.affairlist.service.impl;

import com.lecshop.total.affairlist.bean.ImportantMatterDetail;
import com.lecshop.total.affairlist.mapper.ImportantMatterDetailMapper;
import com.lecshop.total.affairlist.service.ImportantMatterDetailService;
import com.lecshop.total.affairlist.service.ImportantMatterService;
import com.lecshop.utils.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by dujinkai on 17/8/4.
 * 重大事项详情服务接口实现
 */
@Service
public class ImportantMatterDetailServiceImpl implements ImportantMatterDetailService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(ImportantMatterDetailServiceImpl.class);

    /**
     * 注入重大事项数据库接口
     */
    @Autowired
    private ImportantMatterDetailMapper importantMatterDetailMapper;

    /**
     * 注入重大事项服务接口
     */
    @Autowired
    private ImportantMatterService importantMatterService;

    @Override
    public void addImportantMatterDetailTemplates(List<ImportantMatterDetail> importantMatterDetails) {
        logger.debug("addImportantMatterDetailTemplates and importantMatterDetails:{}", importantMatterDetails);

        if (CollectionUtils.isEmpty(importantMatterDetails)) {
            return;
        }

        // 首先插入一级
        importantMatterDetailMapper.addImportantMatterDetailTemplates(importantMatterDetails);

        //设子集父亲id
        importantMatterDetails.stream().forEach(importantMatterDetail -> importantMatterDetail.setChildsParentId());

        // 插入所有的二级i
        importantMatterDetailMapper.addImportantMatterDetailTemplates(importantMatterDetails.stream().flatMap(importantMatterDetail -> importantMatterDetail.getChilds().stream()).collect(Collectors.toList()));

        // 改变重大事项的状态
        importantMatterService.changeImportantMatterStatus(importantMatterDetails.get(0).getMatterId(), "3");
    }

    @Override
    public List<ImportantMatterDetail> queryImportantMatterDetails(long matterId) {
        logger.debug("queryImportantMatterDetails and matterId:{}", matterId);
        List<ImportantMatterDetail> parentImportantMatterDetails = queryImportantMatterDetailsByGrade(matterId, "1");
        List<ImportantMatterDetail> childImportantMatterDetails = queryImportantMatterDetailsByGrade(matterId, "2");

        if (CollectionUtils.isEmpty(parentImportantMatterDetails) || CollectionUtils.isEmpty(childImportantMatterDetails)) {
            return parentImportantMatterDetails;
        }

        IteratorUtils.zip(parentImportantMatterDetails, childImportantMatterDetails, (importantMatterDetail, importantMatterDetail2) -> importantMatterDetail.getId() == importantMatterDetail2.getParentId(), ImportantMatterDetail::addChild);

        return parentImportantMatterDetails;
    }

    @Override
    public ImportantMatterDetail queryById(long detailId) {
        logger.debug("queryById and detailId:{}", detailId);
        return importantMatterDetailMapper.queryById(detailId);
    }

    @Override
    public int updateImportantMatterDetail(ImportantMatterDetail importantMatterDetail) {
        logger.debug("updateImportantMatterDetail and importantMatterDetail:{} ", importantMatterDetail);

        if (Objects.isNull(importantMatterDetail)) {
            logger.error("updateImportantMatterDetail fail due to params is null...");
            return 0;
        }

        return importantMatterDetailMapper.updateImportantMatterDetail(importantMatterDetail);
    }

    /**
     * 根据重大事项id和层级查询重大事项详情
     *
     * @param matterId 重大事项id
     * @param grade    层级
     * @return 返回重大事项详情
     */
    private List<ImportantMatterDetail> queryImportantMatterDetailsByGrade(long matterId, String grade) {
        Map<String, Object> params = new HashMap<>();
        params.put("matterId", matterId);
        params.put("grade", grade);
        return importantMatterDetailMapper.queryImportantMatterDetailTemplates(params);
    }
}
