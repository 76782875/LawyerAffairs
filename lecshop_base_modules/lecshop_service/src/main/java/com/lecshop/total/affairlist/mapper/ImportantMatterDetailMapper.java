package com.lecshop.total.affairlist.mapper;

import com.lecshop.total.affairlist.bean.ImportantMatterDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/8/4.
 * 重大事项详情数据库接口
 */
public interface ImportantMatterDetailMapper {

    /**
     * 新增重大事项模版
     *
     * @param importantMatterDetails 重大事项
     */
    void addImportantMatterDetailTemplates(List<ImportantMatterDetail> importantMatterDetails);

    /**
     * 查询重大事项详情
     *
     * @param params 参数
     * @return 返回重大事项详情
     */
    List<ImportantMatterDetail> queryImportantMatterDetailTemplates(Map<String, Object> params);

    /**
     * 根据id查询重大事项详情
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
