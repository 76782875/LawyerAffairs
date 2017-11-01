package com.lecshop.util;

import com.lecshop.admin.upyunset.service.UpyunService;
import com.lecshop.utils.FileUpAndDownUtils;
import com.lecshop.utils.UnAuth;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

/**
 * Created by dujinkai on 17/7/25.
 * 又拍云控制器
 */
@Controller
public class UpyunController {


    /**
     * 自动注入又拍云service
     */
    @Autowired
    private UpyunService upyunService;

    /**
     * 上传图片
     *
     * @return 返回图片在又拍云的地址
     * @throws Exception 异常
     */
    @RequestMapping("/uploadtoupyun")
    @ResponseBody
    @UnAuth
    public String uploadToUpYun(MultipartHttpServletRequest request, String name) throws Exception {
        if (StringUtils.isEmpty(name)) {
            name = "image";
        }
        MultipartFile multipartFile = request.getFile(name);
        if (Objects.isNull(multipartFile)) {
            return "";
        }
        return upyunService.uploadToUpYun(multipartFile.getInputStream(), multipartFile.getBytes());
    }

    /**
     * 上传文件到本地
     *
     * @param file 文件
     * @return 返回文件名称
     */
    @RequestMapping("/uploadtolocal")
    @ResponseBody
    @UnAuth
    public UploadRes uploadToLocal(@RequestParam("image") CommonsMultipartFile file, HttpServletResponse response) throws Exception {
        FileUpAndDownUtils.fileUploadCommon(file, "upload.properties", this);
        return UploadRes.build(file.getOriginalFilename());
    }

    /**
     * 跳转到下载页面
     *
     * @param name 下载文件的名称
     * @return 返回下载页面
     */
    @RequestMapping("/todownload")
    @UnAuth
    public ModelAndView toDownLoad(String name) {
        return new ModelAndView("common/download").addObject("name", name);
    }


    /**
     * 模板下载
     *
     * @param name 模板名称
     */
    @RequestMapping("/download")
    @UnAuth
    public void download(HttpServletResponse response, String name) {
        FileUpAndDownUtils.fileDownloadCommon(response, "upload.properties", name, this);
    }

    @Data
    private static class UploadRes {
        private String res;

        public static UploadRes build(String res) {
            UploadRes uploadRes = new UploadRes();
            uploadRes.res = res;
            return uploadRes;
        }
    }


}
