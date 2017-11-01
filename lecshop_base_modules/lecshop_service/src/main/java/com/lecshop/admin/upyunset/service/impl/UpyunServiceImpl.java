package com.lecshop.admin.upyunset.service.impl;

import com.lecshop.admin.upyunset.bean.Upyun;
import com.lecshop.admin.upyunset.mapper.UpyunMapper;
import com.lecshop.admin.upyunset.service.UpyunService;
import com.lecshop.utils.YunUploadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 又拍云service实现类
 * <p>
 * Created by LecShop on 2017/7/13.
 */
@Service
public class UpyunServiceImpl implements UpyunService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(UpyunServiceImpl.class);

    /**
     * 自动注入又拍云数据库接口
     */
    @Autowired
    private UpyunMapper upyunMapper;

    /**
     * 查询又拍云
     *
     * @return 又拍云
     */
    @Override
    public Upyun queryUpyun() {
        logger.debug("queryUpyun...");
        return upyunMapper.queryUpyun();
    }

    @Override
    public String uploadToUpYun(InputStream inputStream, byte[] bytes) {
        logger.debug("Begin to uploadToUpYun....");
        return YunUploadUtils.getInstance().uploadToUpYun(queryUpyun().getUpYunConf(), inputStream, bytes);
    }

    /**
     * 修改又拍云
     *
     * @param upyun 又拍云
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateUpyun(Upyun upyun) {
        logger.debug("updateUpyun and upyun :{}", upyun);
        return upyunMapper.updateUpyun(upyun);
    }
}
