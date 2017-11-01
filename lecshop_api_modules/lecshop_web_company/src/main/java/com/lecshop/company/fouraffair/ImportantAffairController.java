package com.lecshop.company.fouraffair;

import com.lecshop.company.adminutil.CompanyLoginUtils;
import com.lecshop.total.affairlist.bean.ImportantMatter;
import com.lecshop.total.affairlist.service.ImportantMatterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 重大事项事务控制器
 *
 * @author sunluyang on 2017/8/11.
 */
@Controller
public class ImportantAffairController {

    /**
     * 注入事务权限检测实体类
     */
    @Autowired
    private ClickAuthCheck clickAuthCheck;
    /**
     * 注入重大事项service
     */
    @Autowired
    private ImportantMatterService importantMatterService;

    /**
     * 跳转到重大事项页面
     *
     * @return 重大事项页面
     */
    @RequestMapping("/c_toimportantaffair")
    public ModelAndView toImportantAffair() {
        return new ModelAndView("threeaffair/importantaffair");
    }

    /**
     * 添加重大事项
     *
     * @param importantMatter 实体类
     * @param request         request请求
     * @return 添加返回码
     */
    @RequestMapping("/c_addimportantmatter")
    @ResponseBody
    public int addImportantMatter(@RequestBody ImportantMatter importantMatter, HttpServletRequest request) {
        if (!clickAuthCheck.checkIsAuthToClick(getCompanyId(request), "7")) {
            return -8;
        }
        return importantMatterService.addImportantMatter(ImportantMatter.buildImportantMatter(importantMatter, getCompanyId(request), getUserId(request)));
    }

    private long getCompanyId(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getCompanyIdFromSession(request);
    }

    private long getUserId(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getUserIdFromSession(request);
    }
}
