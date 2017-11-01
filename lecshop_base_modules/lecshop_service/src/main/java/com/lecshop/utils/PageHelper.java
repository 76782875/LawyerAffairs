package com.lecshop.utils;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 分页帮助类
 *
 * @author sunluyang on 2017/7/10.
 */
@Data
public class PageHelper<T> {

    /**
     * 当前查询的列表数据
     */
    private List<T> list;

    /**
     * 总计录数
     */
    private int rows;

    /**
     * 每页显示记录数 默认10条记录
     */
    private int pageSize = 10;

    /**
     * 当前页码  默认第一页
     */
    private int pageNum = 0;

    /**
     * 获取开始页
     *
     * @return 返回开始页
     */
    public int getStartRowNum() {
        return pageNum * pageSize;
    }

    /**
     * 设置当前的数据列表
     *
     * @param list 当前数据
     * @return 返回当前对象
     */
    public PageHelper setListDates(List<T> list) {
        this.list = list;
        return this;
    }

    /**
     * 获得设置通用分页
     *
     * @param params 查询参数
     * @param rows   总记录数
     * @return 返回查询参数
     */
    public Map<String, Object> getQueryParams(Map<String, Object> params, int rows) {
        this.setRows(rows);
        params.put(CommonConstant.START_ROW_NUM, this.getStartRowNum());
        params.put(CommonConstant.PAGE_SIZE, this.getPageSize());
        return params;
    }

    /**
     * 获得总页数
     *
     * @return 返回总的页数 此方法不能删除
     */
    public int getTotalPages() {
        return rows % pageSize == 0 ? rows / pageSize : rows / pageSize + 1;
    }

}
