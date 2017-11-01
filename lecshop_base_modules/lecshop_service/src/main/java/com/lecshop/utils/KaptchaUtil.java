package com.lecshop.utils;

import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Consumer;

/**
 * 验证码控制器
 *
 * @author sunluyang on 2017/7/10.
 */
@Controller
public class KaptchaUtil {

    @Autowired
    private Producer producer;

    /**
     * 生成验证码
     *
     * @param consumer 回调接口
     */
    protected void createKaptcha(HttpServletResponse response, Consumer<String> consumer) throws Exception {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        // 获得生成的验证码
        String capText = producer.createText();
        // 回调
        consumer.accept(capText);
        // 返回验证码
        ImageIO.write(producer.createImage(capText), "jpg", response.getOutputStream());
    }
}

