package com.lecshop.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * 接口数据
 *
 * @author sunluyang on 2017/8/25.
 */
public class InterfaceData {
    public static Object getInterfaceDate(Object object, String url) {
        //请求链接
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 获取菜单
            object = JSONObject.parseObject(EntityUtils.toString(httpclient.execute(httpGet).getEntity(), CommonConstant.ENCODE), object.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                httpclient.close();
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        return object;
    }
}
