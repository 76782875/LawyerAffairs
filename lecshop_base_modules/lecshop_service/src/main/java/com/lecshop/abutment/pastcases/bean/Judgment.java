package com.lecshop.abutment.pastcases.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 按键实体类
 *
 * @author sunluyang on 2017/8/1.
 */
@Data
public class Judgment {
    /**
     * 裁判文书id
     */
    private String id;
    /**
     * 审判时间
     */
    private String time;
    /**
     * 裁判文书标题
     */
    private String title;
}
