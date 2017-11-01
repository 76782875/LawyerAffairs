package com.lecshop.company.fouraffair;

import com.lecshop.company.adminutil.CompanyLoginUtils;
import com.lecshop.total.affairlist.bean.Disputes;
import com.lecshop.total.affairlist.service.DisputesService;
import com.lecshop.total.disputetype.bean.DisputesType;
import com.lecshop.total.disputetype.service.DisputesTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 纠纷服务控制器
 *
 * @author sunluyang on 2017/8/2.
 */
@Controller
public class DisputesAffairController {
    /**
     * 注入纠纷类型service
     */
    @Autowired
    private DisputesTypeService disputesTypeService;
    /**
     * 注入纠纷service
     */
    @Autowired
    private DisputesService disputesService;
    /**
     * 注入事务权限检测实体类
     */
    @Autowired
    private ClickAuthCheck clickAuthCheck;

    /**
     * 跳转到纠纷
     *
     * @return 纠纷列表
     */
    @RequestMapping("/c_todisputesaffair")
    public ModelAndView toDisputesAffair() {
        return new ModelAndView("threeaffair/disputesaffair");
    }

    /**
     * 查询一级纠纷咨询类型
     *
     * @return 一级纠纷咨询类型
     */
    @RequestMapping("/c_queryfirstdisputestype")
    @ResponseBody
    public List<DisputesType> queryFirstDisputesType() {
        return disputesTypeService.queryFirstGradeDisputesType();
    }

    /**
     * 查询二级纠纷咨询类型
     *
     * @return 二级纠纷咨询类型
     */
    @RequestMapping("/c_queryseconddisputestype")
    @ResponseBody
    public List<DisputesType> querySecondDisputesType(long id) {
        return disputesTypeService.querySecondGradeDisputesTypeByParentId(id);
    }

    /**
     * 添加纠纷
     *
     * @param disputes 纠纷实体类
     * @param request  request请求
     * @return 添加返回码
     */
    @RequestMapping("/c_adddisputes")
    @ResponseBody
    public int addDisputes(@RequestBody Disputes disputes, HttpServletRequest request) {
        if (!clickAuthCheck.checkIsAuthToClick(getCompanyId(request), "7")) {
            return -8;
        }
        return disputesService.addDisputes(disputes, getCompanyId(request), getUserId(request));
    }

    private long getCompanyId(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getCompanyIdFromSession(request);
    }

    private long getUserId(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getUserIdFromSession(request);
    }
}
