package com.lecshop.systemset;

import com.lecshop.admin.upyunset.bean.Upyun;
import com.lecshop.admin.upyunset.service.UpyunService;
import com.lecshop.utils.UnAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

/**
 * 又拍云接口设置控制器
 * Created by LecShop on 2017/7/13.
 */
@Controller
public class UpyunSetController {

    /**
     * 注入又拍云service
     */
    @Autowired
    private UpyunService upyunService;

    /**
     * 跳转至又拍云页面
     *
     * @return 又拍云页面
     */
    @RequestMapping("/toupyunset")
    public ModelAndView toUpyunSet() {
        return new ModelAndView("systemset/upyunset");
    }

    /**
     * 查询又拍云
     *
     * @return 又拍云
     */
    @RequestMapping("/queryupyun")
    @ResponseBody
    public Upyun queryUpyun() {
        return upyunService.queryUpyun();
    }

    /**
     * 修改又拍云设置
     *
     * @param upyun 又拍云
     * @return 成功返回1，失败返回0
     */
    @RequestMapping("/updateupyunset")
    @ResponseBody
    public int updateUpyun(@RequestBody Upyun upyun) {
        return upyunService.updateUpyun(upyun);
    }

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
}
