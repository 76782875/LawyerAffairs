package com.lecshop.utils;

import com.alibaba.fastjson.JSONObject;
import com.lecshop.total.wxsetting.bean.WxSetting;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by dujinkai on 17/7/24.
 * 微信工具类
 */
public class WxUtils {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(WxUtils.class);

    /**
     * 微信获取token接口
     */
    private static final String GETTOKENURL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=";

    /**
     * 微信获取用户信息url
     */
    private static final String WXURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=";

    /**
     * 微信推送消息url
     */
    private static final String PUSHMESSAGEURL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    /**
     * 微信SECRET
     */
    private static final String SECRET = "&secret=";

    /**
     * 微信认证回调的code
     */
    private static final String CODE = "&code=";

    /**
     * 微信url后缀
     */
    private static final String URLINFO1 = "&grant_type=authorization_code";

    private static WxUtils INSTANCE = new WxUtils();

    public static WxUtils getInstance() {
        return INSTANCE;
    }

    private WxUtils() {
    }


    /**
     * 推送公众号消息
     *
     * @param accessToken  token
     * @param pushMessages 发送的消息
     */
    public void pushMessage(String accessToken, List<String> pushMessages) {

        logger.debug("pushMessage and accessToken:{} \r\n pushMessages:{}", accessToken, pushMessages);

        if (StringUtils.isEmpty(accessToken) || CollectionUtils.isEmpty(pushMessages)) {
            logger.error("pushMessage fail due to params is null....");
            return;
        }
        pushMessages.stream().forEach(pushMessage -> {
            try {
                HttpClient httpClient = new HttpClient();
                PostMethod httppost = new PostMethod(PUSHMESSAGEURL + accessToken);
                RequestEntity se = new StringRequestEntity(pushMessage, "application/json", "UTF-8");
                httppost.setRequestEntity(se);
                //使用系统提供的默认的恢复策略
                httppost.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
                httpClient.executeMethod(httppost);
                String res = httppost.getResponseBodyAsString();
                logger.debug("pushMessage result :{} \r\n pushMessage:{}", res, pushMessage);
            } catch (Exception e) {
                logger.error("pushMessage fail....");
            }
        });
    }

    /**
     * 获取微信accesstoken
     *
     * @param wxSetting 微信设置
     * @return 返回accesstoken
     */
    public String getAccessToken(WxSetting wxSetting) {
        GetMethod getTokenMethod = new GetMethod(GETTOKENURL + wxSetting.getAppId() + "&secret=" + wxSetting.getAppSecret());
        getTokenMethod.getParams().setContentCharset("utf-8");
        HttpClient clientToken = new HttpClient();
        try {
            clientToken.executeMethod(getTokenMethod);
            return getAccessToken(getTokenMethod.getResponseBodyAsString());
        } catch (IOException e) {
            logger.error("getAccessToken fail...");
        }

        return "";
    }

    /**
     * 获得微信的openid
     *
     * @param code      微信认证回调的code
     * @param wxSetting 微信设置
     * @return 返回openid
     */
    public String getOpenId(String code, WxSetting wxSetting) {

        logger.debug("getOpenId and code:{} \r\n wxSetting:{}", code, wxSetting);

        if (StringUtils.isEmpty(code) || Objects.isNull(wxSetting)) {
            return "";
        }

        GetMethod method = new GetMethod(WXURL + wxSetting.getAppId() + SECRET + wxSetting.getAppSecret() + CODE + code + URLINFO1);
        method.getParams().setContentCharset("UTF-8");

        HttpClient clientToken = new HttpClient();

        try {
            clientToken.executeMethod(method);
            return getOpenId(method.getResponseBodyAsString());
        } catch (Exception e) {
            logger.error("getOpenId fail...");
        }

        return "";
    }

    /**
     * 获得微信的accesstoken
     *
     * @param res 微信返回的结果
     * @return 返回accesstoken
     */
    private String getAccessToken(String res) {
        logger.debug("getAccessToken and res:{}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        return (String) jsonObject.get("access_token");
    }

    /**
     * 获取用户的openId
     *
     * @param res 微信获取用户信息的结果
     * @return 返回openId
     */
    private String getOpenId(String res) {
        logger.debug("getOpenId and res:{}", res);
        JSONObject jsonObject = JSONObject.parseObject(res);
        return (String) jsonObject.get("openid");
    }

}
