package com.lecshop.abutment.conversation.bean;

import lombok.Data;

import java.util.Random;

/**
 * 通话接口参数实体类
 *
 * @author sunluyang on 2017/8/2.
 */
@Data
public class Parameter {

    /**
     * 产生随机字符串
     */
    private static Random randGen = null;
    /**
     * 数字和字母
     */
    private static char[] numbersAndLetters = null;

    /**
     * 分配的appkey(必填)
     */
    private String appKey;
    /**
     * 主叫号码，支持手机/座机(必填)
     */
    private String phone;
    /**
     * 被叫号码，支持手机/座机(必填)
     */
    private String call;
    /**
     * 商户内部的用户ID标识
     */
    private String uid;
    /**
     * 接收通话清单的URL地址，不可包含 ? &(return_url)
     */
    private String returnUrl;
    /**
     * 0：主叫端随机显号（默认） 1：被叫端显示主叫号码（+0.01元/分钟）
     */
    private int phoneShow;
    /**
     * 0：被叫端随机显号（默认） 1：被叫端显示主叫号码（+0.01元/分钟）
     */
    private int callShow;
    /**
     * 0：关闭录音（默认）  1：开启录音（+0.01元/分钟）
     */
    private int record;
    /**
     * 最大允许通话分钟数 （+0.01元/分钟）
     */
    private int maxlength;
    /**
     * 商户备注信息，通话清单中原样返回
     */
    private String ext;

    public String buildUrl(String phone, String call, long userId, long companyId, String lawyerId, String type, String basePath) {
        this.appKey = "8c7811399efd3c5ab0f607392461cd1a";
        this.phone = phone;
        this.call = call;
        this.returnUrl = basePath + "c_receivecallbackurl.htm";
        this.phoneShow = 0;
        this.callShow = 0;
        this.record = 1;
        this.ext = type;
        this.uid = companyId + "-" + userId + "-" + lawyerId + "-" + randomString();
        return "http://api.id98.cn/api/v2/callback?appkey=" + this.appKey + "&phone=" + this.phone + "&call=" + this.call +
                "&return_url=" + this.returnUrl + "&record=" + this.record + "&phoneShow=" + this.phoneShow + "&callShow="
                + this.callShow + "&uid=" + this.uid + "&ext=" + this.ext;
    }

    /**
     * 字符串
     */
    private static String randomString() {
        if (randGen == null) {
            randGen = new Random();
            numbersAndLetters = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
        }
        char[] randBuffer = new char[8];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(35)];
        }
        return new String(randBuffer);
    }
}
