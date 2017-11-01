package com.lecshop.utils;

import lombok.Data;

import java.util.List;

/**
 * 分页基础响应类
 *
 * @author sunluyang on 2017/7/10.
 */
@Data
public class BaseResponseExternal {
    /**
     * 返回的数据
     */
    private List data;
    /**
     * 总记录数
     */
    private int recordsTotal;
    /**
     * 总的页数
     */
    private int totalPage;
    /**
     * 200 成功  －1 秘钥错误
     */
    private int rescode;

    private BaseResponseExternal() {

    }

    private BaseResponseExternal(PageHelper pageHelper, int rescode) {
        this.data = pageHelper.getList();
        this.recordsTotal = pageHelper.getRows();
        this.totalPage = pageHelper.getTotalPages();
        this.rescode = rescode;
    }

    /**
     * 构造分页响应实体
     *
     * @param pageHelper 分页帮助类
     * @return 返回分页响应实体
     */
    public static BaseResponseExternal build(PageHelper pageHelper, int rescode) {
        return new BaseResponseExternal(pageHelper, rescode);
    }
}
