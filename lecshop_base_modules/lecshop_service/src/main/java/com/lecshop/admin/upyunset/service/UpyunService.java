package com.lecshop.admin.upyunset.service;

import com.lecshop.admin.upyunset.bean.Upyun;

import java.io.InputStream;

/**
 * 又拍云service
 *
 * Created by LecShop on 2017/7/13.
 */
public interface UpyunService {

    /**
     * 查询又拍云
     *
     * @return 又拍云
     */
    Upyun queryUpyun();

    /**
     * 上传图片到又拍云
     *
     * @param inputStream 输入流
     * @param bytes       图片的字节
     * @return 返回图片在又拍云的地址
     */
    String uploadToUpYun(InputStream inputStream, byte[] bytes);

    /**
     * 修改又拍云
     *
     * @param upyun 又拍云
     * @return 成功返回1，失败返回0
     */
    int updateUpyun(Upyun upyun);
}
