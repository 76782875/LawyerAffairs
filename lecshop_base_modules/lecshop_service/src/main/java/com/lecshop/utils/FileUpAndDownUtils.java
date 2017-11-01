package com.lecshop.utils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * 文件上传
 *
 * @author sunluyang on 2017/5/26.
 */
public class FileUpAndDownUtils {

    /**
     * 调试日志
     */
    private static final Logger logger = LoggerFactory.getLogger(FileUpAndDownUtils.class);

    /**
     * 文件上传公共方法
     *
     * @param file           文件
     * @param propertiesPath 配置文件中的文件上传路径
     * @param object         调用类
     * @return 上传返回值
     */
    public static int fileUploadCommon(CommonsMultipartFile file, String propertiesPath, Object object) {
        //当要同时上传多个文件时，可以给定多个MultipartFile参数(数组)
        if (!file.isEmpty()) {
            String path = filePath(propertiesPath, object, "FILEPATH") + File.separator + file.getOriginalFilename();// 存放位置
            logger.debug("fileUploadCommon and Properties path:{}:", path);
            File pathFile = new File(path);
            try {
                //该方法里对IO进行了自动操作，不需要额外的再去关闭IO流
                // 复制临时文件到指定目录下
                FileUtils.copyInputStreamToFile(file.getInputStream(), pathFile);
            } catch (IOException e) {
                logger.error("fileUploadCommon error", e);
                return -1;
            }
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 文件下载公共方法
     *
     * @param response       response响应
     * @param propertiesPath 配置文件中的文件上传路径
     * @param fileName       文件名称
     * @param object         调用类
     */
    public static void fileDownloadCommon(HttpServletResponse response, String propertiesPath, String fileName, Object object) {
        try {
            String path = filePath(propertiesPath, object, "FILEPATH") + File.separator + fileName;// 存放位置
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "filename=\"" + fileName + "\"");
            response.setContentType("multipart/form-data");
            File file = new File(path);
            InputStream inputStream = new FileInputStream(file);
            OutputStream outputStream = response.getOutputStream();
            int buff;
            while ((buff = inputStream.read()) != -1) {
                outputStream.write(buff);
            }
            inputStream.close();
            outputStream.close();
            outputStream.flush();
        } catch (IOException e) {
            logger.error("fileDownloadCommon error", e);
            e.printStackTrace();
        }
    }

    /**
     * 网络下载文件
     *
     * @param url 网址
     * @param propertiesPath
     * @param fileName
     * @param object
     */
    public static void downInternetFile(String url, String propertiesPath, String fileName, Object object) {
        String path = filePath(propertiesPath, object, "VOICEPATH");// 存放位置
        //文件保存位置
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        File resultFile = new File(path + File.separator + fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(resultFile);
            fileOutputStream.write(DownLoadUtil.getInstance().httpDownload(url));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件路径处理
     */
    private static String filePath(String propertiesPath, Object object, String name) {
        //得到上传文件的保存目录
        String savePath = readValue(propertiesPath, object).getProperty(name);
        File savePathFile = new File(savePath);
        //判断上传文件的保存目录是否存在
        if (!savePathFile.exists() && !savePathFile.isDirectory()) {
            logger.debug("path is not exist to create path");
            //创建目录
            savePathFile.mkdir();
        }
        return savePath;
    }

    /**
     * 读取配置文件信息
     */
    private static Properties readValue(String propertiesPath, Object object) {
        Properties properties = new Properties();
        try (InputStream inputStream = object.getClass().getClassLoader().getResourceAsStream(propertiesPath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            logger.error("read Properties error", e);
        }
        return properties;
    }
}
