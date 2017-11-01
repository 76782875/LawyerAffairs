package com.lecshop.abutment.lawyer.bean;

import com.lecshop.utils.PageHelper;
import lombok.Data;

/**
 * 搜索条件
 *
 * @author sunluyang on 2017/7/27.
 */
@Data
public class SearchBean {
    /**
     * 搜索词：律师名字或律所
     */
    private String keywords;
    /**
     * 公司名称
     */
    private String company;
    /**
     * 法院
     */
    private String court;
    /**
     * 原告：1 被告：2 全部：0
     */
    private int role;
    /**
     * 案件类型：案件类型编码
     */
    private String casetype;
    /**
     * 自定义案件类型
     */
    private String casetypefree;
    /**
     * 密钥
     */
    private String key;
    /**
     * 页码，从1开始
     */
    private int pi;
    /**
     * 分页大小
     */
    private int ps;

    public SearchBean buildPage(PageHelper<LawyerInfo> pageHelper) {
        this.pi = pageHelper.getPageNum() + 1;
        this.ps = pageHelper.getPageSize();
        return this;
    }
}
