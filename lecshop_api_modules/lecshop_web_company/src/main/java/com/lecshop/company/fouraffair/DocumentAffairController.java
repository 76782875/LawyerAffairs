package com.lecshop.company.fouraffair;

import com.lecshop.total.affairlist.service.DraftDocService;
import com.lecshop.total.affairlist.service.LawyerLetterService;
import com.lecshop.total.affairlist.service.ModifyDocService;
import com.lecshop.company.adminutil.CompanyLoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 文书控制器
 * Created by LecShop on 2017/7/18.
 */
@Controller
public class DocumentAffairController {

    /**
     * 自动注入文书事务service
     */
    @Autowired
    private ModifyDocService modifyDocService;

    /**
     * 自动注入草拟文书service
     */
    @Autowired
    private DraftDocService draftDocService;

    /**
     * 自动注入律师函service
     */
    @Autowired
    private LawyerLetterService lawyerLetterService;
    /**
     * 注入事务权限检测实体类
     */
    @Autowired
    private ClickAuthCheck clickAuthCheck;

    /**
     * 跳转至修改文书页面
     *
     * @return 修改文书页面
     */
    @RequestMapping("/c_tomodifydoc")
    public ModelAndView toModifyDoc() {
        return new ModelAndView("dochandle/modifydoc");
    }

    /**
     * 跳转至草拟文书页面
     *
     * @return 文书页面
     */
    @RequestMapping("/c_todraftdoc")
    public ModelAndView toDraftDoc() {
        return new ModelAndView("dochandle/draftdoc");
    }

    /**
     * 跳转至律师函页面
     *
     * @return 律师函页面
     */
    @RequestMapping("/c_tolawyerletter")
    public ModelAndView toLawyerLetter() {
        return new ModelAndView("dochandle/lawyerletter");
    }

    /**
     * 修改文书
     *
     * @param file 文件
     * @param name 文书名称
     * @param desc 修改要求
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_updatemodifydoc")
    @ResponseBody
    public int updateModify(HttpServletRequest request, @RequestParam("fileUpload") CommonsMultipartFile file, String name, String desc) {
        if (!clickAuthCheck.checkIsAuthToClick(getCompanyId(request), "4")) {
            return -8;
        }
        return modifyDocService.updateModifyDoc(getCompanyId(request), getUserId(request), file, name, desc);
    }

    /**
     * 提交律师函
     *
     * @param file    上传证据
     * @param desc    情况描述
     * @param mobile  联系电话
     * @param address 收件地址
     * @return 成功返回1， 文件上传失败-1
     */
    @RequestMapping("/c_addlawyerletter")
    @ResponseBody
    public int addLawyerLetter(HttpServletRequest request, @RequestParam("fileUpload") CommonsMultipartFile file, String desc, String mobile, String address) {
        if (!clickAuthCheck.checkIsAuthToClick(getCompanyId(request), "8")) {
            return -8;
        }
        return lawyerLetterService.addLawyerLetter(file, getCompanyId(request), getUserId(request), mobile, address, desc);
    }

    /**
     * 添加草拟文书
     *
     * @param name 草拟文书名称
     * @param desc 草拟要求
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/c_adddraftdoc")
    @ResponseBody
    public int addDraftDoc(HttpServletRequest request, String name, String desc) {
        if (!clickAuthCheck.checkIsAuthToClick(getCompanyId(request), "8")) {
            return -8;
        }
        return draftDocService.addDraftDoc(getUserId(request), getCompanyId(request), name, desc);
    }

    private long getCompanyId(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getCompanyIdFromSession(request);
    }

    private long getUserId(HttpServletRequest request) {
        return CompanyLoginUtils.getInstance().getUserIdFromSession(request);
    }
}
