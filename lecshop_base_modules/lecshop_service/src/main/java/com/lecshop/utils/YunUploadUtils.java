package com.lecshop.utils;

import com.upyun.UpYun;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;

/**
 * 又拍云上传工具
 *
 * @author sunluyang on 2017/7/10.
 */
public class YunUploadUtils {

    /**
     * 调试工具
     */
    private Logger logger = LoggerFactory.getLogger(YunUploadUtils.class);

    private static final YunUploadUtils INSTANCE = new YunUploadUtils();

    private YunUploadUtils() {
    }

    public static YunUploadUtils getInstance() {
        return INSTANCE;
    }

    /**
     * 上传又拍云
     *
     * @param upYunConf   又拍云设置
     * @param inputStream 输入流
     * @param bytes       图片字节
     * @return 返回图片在又拍云的地址
     */
    public String uploadToUpYun(UpYunConf upYunConf, InputStream inputStream, byte[] bytes) {
        logger.debug("Being to uploadToUpYun.....");
        if (ArrayUtils.isEmpty(bytes)) {
            logger.error("uploadToUpYun fail due to bytes is empty ....");
            return "";
        }
        try {
            String picName = String.valueOf(System.currentTimeMillis()) + ".jpg";
            String filePath = File.separator + picName;
            UpYun upYun = new UpYun(upYunConf.getNameSpace().trim(), upYunConf.getUserName().trim(), upYunConf.getPassword().trim());
            upYun.setDebug(true);
            upYun.setContentMD5(MD5Utils.getInstance().createMd5ForImageUpload(inputStream));
            upYun.setFileSecret("");
            // 上传文件
            if (upYun.writeFile(filePath, bytes, true)) {
                return upYunConf.getAddress() + picName;
            }
        } catch (Exception e) {
            logger.error("uploadToUpYun fail ....", e);
        }
        return "";
    }
}
