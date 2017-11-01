package com.lecshop.systemset;

import com.lecshop.total.consultationtype.bean.ConsultationType;
import com.lecshop.total.consultationtype.service.ConsultationTypeService;
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
 * 公司咨询类型控制器
 *
 * Created by LecShop on 2017/7/13.
 */
@Controller
public class ConsultationTypeController {

    /**
     * 注入公司咨询类型service
     */
    @Autowired
    private ConsultationTypeService consultationTypeService;

    /**
     * 跳转至公司咨询类型配置页面
     *
     * @return 公司咨询类型配置页面
     */
    @RequestMapping("/toconsultationtype")
    public ModelAndView toConsultationType() {
        return new ModelAndView("systemset/consultationtype");
    }

    /**
     * 查询公司咨询类型
     *
     * @return 公司咨询类型
     */
    @RequestMapping("/queryconsultationtype")
    @ResponseBody
    public List<ConsultationType> queryConsultationTypeList() {
        return consultationTypeService.queryConsultationTypeList();
    }

    /**
     * 删除公司咨询类型
     *
     * @param consultationType 公司咨询类型
     * @return 成功返回>=1，失败返回0，存在子类型返回-1
     */
    @RequestMapping("/deleteconsultationtypebyid")
    @ResponseBody
    public int deleteConsultationType(@RequestBody ConsultationType consultationType, boolean flag) {
        return consultationTypeService.deleteConsultationType(consultationType, flag);
    }

    /**
     * 根据id查询公司咨询类型
     *
     * @param id 公司咨询类型id
     * @return 公司咨询类型
     */
    @RequestMapping("/queryconsultationtypebyid")
    @ResponseBody
    public ConsultationType queryConsultationTypeById(long id) {
        return consultationTypeService.queryConsultationTypeById(id);
    }

    /**
     * 修改公司咨询类型
     *
     * @param consultationType 公司咨询类型
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/updateconsultationtype")
    @ResponseBody
    public int updateConsultationType(@RequestBody ConsultationType consultationType) {
        return consultationTypeService.updateConsultationType(consultationType);
    }

    /**
     * 查询公司一级咨询类型
     *
     * @return 公司一级咨询类型集合
     */
    @RequestMapping("/queryfirstgradeconsultationtype")
    @ResponseBody
    public List<ConsultationType> queryFirstGradeConsultationType() {
        return consultationTypeService.queryFirstGradeConsultationType();
    }

    /**
     * 添加公司咨询类型
     *
     * @param consultationType 公司咨询类型
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/addconsultationtype")
    @ResponseBody
    public int addConsultationType(@RequestBody ConsultationType consultationType) {
        return consultationTypeService.addConsultationType(consultationType);
    }
}
