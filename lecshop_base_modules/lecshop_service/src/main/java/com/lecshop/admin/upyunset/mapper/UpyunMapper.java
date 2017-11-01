package com.lecshop.admin.upyunset.mapper;

import com.lecshop.admin.upyunset.bean.Upyun;
import org.springframework.stereotype.Repository;

/**
 * 又拍云数据库接口
 *
 * Created by LecShop on 2017/7/13.
 */
@Repository
public interface UpyunMapper {

    /**
     * 查询又拍云
     *
     * @return 又拍云
     */
    Upyun queryUpyun();

    /**
     * 修改又拍云
     *
     * @param upyun 又拍云
     * @return 成功返回1，失败返回0
     */
    int updateUpyun(Upyun upyun);
}
