package com.lecshop.total.affairlist.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dujinkai on 17/8/4.
 * 重大事项详情
 */
@Data
public class ImportantMatterDetail {
    /**
     * 主键id
     */
    private long id;

    /**
     * 重大事项id
     */
    private long matterId;

    /**
     * 描述
     */
    private String desc;

    /**
     * 下载地址
     */
    private String url;

    /**
     * 时间
     */
    private String time;

    /**
     * 层级  1 为1级 2 为2级
     */
    private String grade;

    /**
     * 父级id
     */
    private long parentId;

    /**
     * 排序
     */
    private int sort;

    /**
     * 阶段名称
     */
    private String name;

    /**
     * 子集
     */
    private List<ImportantMatterDetail> childs;

    /**
     * 判断是否是1级
     *
     * @return 成功返回true 失败返回fasle
     */
    @JsonIgnore
    public boolean isFirst() {
        return "1".equals(grade);
    }


    /**
     * 增加子节点
     * @param importantMatterDetail 重大事项详情
     */
    public void addChild(ImportantMatterDetail importantMatterDetail) {
        if (CollectionUtils.isEmpty(childs)) {
            childs = new ArrayList<>();
        }

        childs.add(importantMatterDetail);
    }

    /**
     * 设置子集的父id
     */
    public void setChildsParentId() {
        if (CollectionUtils.isEmpty(childs)) {
            return;
        }

        childs.stream().forEach(child -> child.setParentId(this.id));
    }
}
