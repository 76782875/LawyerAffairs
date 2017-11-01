package com.lecshop.systemset;

import com.lecshop.total.disputetype.bean.DisputesType;
import com.lecshop.total.disputetype.service.DisputesTypeService;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 纠纷类型管理控制器
 *
 * @author sunluyang on 2017/7/11.
 */
@Controller
public class DisputeTypeController {

    /**
     * 注入公司纠纷类型service
     */
    @Autowired
    private DisputesTypeService disputesTypeService;

    /**
     * 跳转到公司纠纷类型配置页面
     *
     * @return 公司纠纷类型配置页面
     */
    @RequestMapping("/todisputetype")
    public ModelAndView toDisputeType() {
        return new ModelAndView("systemset/disputetype");
    }

    /**
     * 查询公司纠纷类型
     *
     * @return 公司纠纷类型
     */
    @RequestMapping("/querydisputestype")
    @ResponseBody
    public List<DisputesType> queryDisputesType() {
        return disputesTypeService.queryDisputesType();
    }

    /**
     * 删除公司纠纷类型
     *
     * @param disputesType 公司纠纷类型
     * @return   成功返回1，失败返回0
     */
    @RequestMapping("/deletedisputetype")
    @ResponseBody
    public int deleteDisputesType(@RequestBody DisputesType disputesType, boolean flag) {
        return disputesTypeService.deleteDisputesType(disputesType, flag);
    }

    /**
     * 根据id查询公司纠纷类型
     *
     * @param id 公司纠纷类型id
     * @return 公司纠纷类型
     */
    @RequestMapping("/querydisputestypebyid")
    @ResponseBody
    public DisputesType queryDisputesType(long id) {
        return disputesTypeService.queryDisputesTypeById(id);
    }

    /**
     * 修改公司纠纷类型
     *
     * @param disputesType 公司纠纷类型
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/updatedisputestype")
    @ResponseBody
    public int updateDisputesType(@RequestBody DisputesType disputesType) {
        return disputesTypeService.updateDisputesType(disputesType);
    }

    /**
     * 添加公司纠纷类型
     *
     * @param disputesType 公司纠纷类型
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/adddisputestype")
    @ResponseBody
    public int addDisputesType(@RequestBody DisputesType disputesType) {
        return disputesTypeService.addDisputesType(disputesType);
    }

    /**
     * 查询一级公司纠纷类型
     *
     * @return 一级公司纠纷类型集合
     */
    @RequestMapping("/queryfirstgradedisputestype")
    @ResponseBody
    public List<DisputesType> queryFirstGradeDisputesType() {
        return disputesTypeService.queryFirstGradeDisputesType();
    }

}
