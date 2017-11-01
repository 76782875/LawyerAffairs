package com.lecshop.abutment.lawyer.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 经验实体类
 *
 * @author sunluyang on 2017/7/27.
 */
@Data
public class Experience {
    /**
     * 案件法院分布
     */
    private Map<String, String> courtCount = new HashMap<>();
    /**
     * 角色分布
     */
    private Map<String, String> roleCount = new HashMap<>();
    /**
     * 案件类型分布
     */
    private Map<String, String> casetypeCount = new HashMap<>();
}
