package com.lecshop.total.letterstation.service;

import com.lecshop.total.letterstation.bean.LetterStation;
import com.lecshop.utils.PageHelper;

import java.util.List;

/**
 * 站内信service
 *
 * Created by LecShop on 2017/7/28.
 */
public interface LetterStationService {

    /**
     * 发送站内信
     *
     * @param ids 用户id数组
     * @param title 标题
     * @param content 内容
     * @return 成功返回>=1，失败返回0
     */
    int sendLetterStation(long[] ids, String title, String content);

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
     * @param userId 用户id
     * @return 站内信集合
     */
    PageHelper<LetterStation> queryLetterStation(PageHelper<LetterStation> pageHelper, long userId);

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
