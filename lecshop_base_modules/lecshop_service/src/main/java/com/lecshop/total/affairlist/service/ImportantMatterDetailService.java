package com.lecshop.total.affairlist.service;

import com.lecshop.total.affairlist.bean.ImportantMatterDetail;

import java.util.List;

/**
 * Created by dujinkai on 17/8/4.
 * 重大事项详情服务接口
 */
public interface ImportantMatterDetailService {

    /**
     * 添加重大事项模版
     *
     * @param importantMatterDetails 重大事项模版
     */
    void addImportantMatterDetailTemplates(List<ImportantMatterDetail> importantMatterDetails);

    /**
     * 根据重大事项id查询重大事项详情
     *
     * @param matterId 重大事项id
     * @return 返回重大事项详情
     */
    List<ImportantMatterDetail> queryImportantMatterDetails(long matterId);

    /**
     * 根据详情id查询重大事项详情
     *
     * @param detailId 详情id
     * @return 返回重大事项详情
     */
    ImportantMatterDetail queryById(long detailId);

    /**
     * 更新重大事项详情
     *
     * @param importantMatterDetail 重大事项详情
     * @return 成功返回1 失败返回0
     */
    int updateImportantMatterDetail(ImportantMatterDetail importantMatterDetail);
}
