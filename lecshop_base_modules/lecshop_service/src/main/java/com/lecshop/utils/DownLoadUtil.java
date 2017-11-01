package com.lecshop.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 网络下载工具类
 *
 * @author dujinkai
 */
public class DownLoadUtil {

    /**
     * 私有构造器
     */
    private DownLoadUtil() {

    }

    private static DownLoadUtil downLoadUtil = new DownLoadUtil();


    public static DownLoadUtil getInstance() {
        return downLoadUtil;
    }

    /**
     * 从指定的url下载图片或者文本
     *
     * @param httpUrl 下载地址
     * @return 返回下载的字节数组
     * @throws MalformedURLException
     */
    public final byte[] httpDownload(String httpUrl) throws MalformedURLException {
        return httpDownload(httpUrl, 60000 * 60);
    }

    /**
     * 从指定的url下载图片或者文本
     *
     * @param httpUrl 下载地址
     * @return 返回下载的字节数组
     * @throws MalformedURLException
     */
    private final byte[] httpDownload(String httpUrl, int timeout) throws MalformedURLException {
        // 参数校验
        ParamsValidateUtil.getInstance().validateParams(httpUrl);
        URL url = new URL(httpUrl);
        InputStream inStream = null;
        HttpURLConnection conn = null;
        try {
            // 打开连接
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(timeout);
            // 获得输入流
            inStream = conn.getInputStream();
            byte[] bytes = StreamUtils.getInstance().stream2Bytes(inStream);
            inStream.close();
            conn.disconnect();
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException("Down load from " + httpUrl + "fail", e);
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (conn != null) {
                    conn.disconnect();
                }
            } catch (Exception e2) {
                throw new RuntimeException("close resource fail", e2);
            }
        }
    }
}
