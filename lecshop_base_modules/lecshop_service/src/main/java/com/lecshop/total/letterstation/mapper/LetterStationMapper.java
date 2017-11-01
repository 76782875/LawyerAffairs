package com.lecshop.total.letterstation.mapper;

import com.lecshop.total.letterstation.bean.LetterStation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 站内信数据库接口
 *
 * Created by LecShop on 2017/7/28.
 */
@Repository
public interface LetterStationMapper {

    /**
     * 发送站内信
     *
     * @param params 用户id数组、站内信标题、站内信内容
     * @return 成功返回>=1，失败返回0
     */
    int sendLetterStation(Map<String, Object> params);

    /**
     * 查询未读站内信
     *
     * @param userId 用户id
     * @return 未读站内信
     */
    int queryLetterStationUnreadCount(long userId);

    /**
     * 查询站内信
     *
     * @param params 用户id
     * @return 站内信集合
     */
    List<LetterStation> queryLetterStation(Map<String, Object> params);

    /**
     * 查询站内信总记录数
     *
     * @param params 用户id
     * @return 站内信总记录数
     */
    int queryLetterStationCount(Map<String, Object> params);

    /**
     * 删除站内信
     *
     * @param id 站内信id
     * @return 成功返回1，失败返回0
     */
    int deleteLetterStation(long id);

    /**
     * 将站内信设为已读
     *
     * @param id 站内信id
     * @return 成功返回1，失败返回0
     */
    int readLetterStation(long id);
}
