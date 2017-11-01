package com.lecshop.company.fouraffair;

import com.lecshop.total.consultationtype.bean.ConsultationType;
import com.lecshop.total.consultationtype.service.ConsultationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 公司事务控制器
 *
 * @author sunluyang on 2017/7/27.
 */
@Controller
public class CompanyAffairController {
    /**
     * 注入咨询类型service
     */
    @Autowired
    private ConsultationTypeService consultationTypeService;

    /**
     * 跳转到公司事务列表
     *
     * @return 公司事务列表
     */
    @RequestMapping("/c_tocompanyaffair")
    public ModelAndView toCompanyAffair() {
        return new ModelAndView("threeaffair/affairconsult");
    }

    /**
     * 跳转到没有指定律师的预约会面咨询页面
     *
     * @return 没有指定律师的预约会面咨询页面
     */
    @RequestMapping("/c_tonopointmeet")
    public ModelAndView toNoPointMeet() {
        return new ModelAndView("threeaffair/nopointmeet");
    }

    /**
     * 查询一级公司咨询类型
     *
     * @return 一级公司咨询类型集合
     */
    @RequestMapping("/c_queryfirstconsultationtype")
    @ResponseBody
    public List<ConsultationType> queryFirstConsultationType() {
        return consultationTypeService.queryFirstGradeConsultationType();
    }

    /**
     * 查询二级公司咨询类型
     *
     * @return 二级公司咨询类型
     */
    @RequestMapping("/c_querysecondconsultationtype")
    @ResponseBody
    public List<ConsultationType> querySecondConsultationType(long id) {
        return consultationTypeService.querySecondConsultationTypeByParendId(id);
    }
}
