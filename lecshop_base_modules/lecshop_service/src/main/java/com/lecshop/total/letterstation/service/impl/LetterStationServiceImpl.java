package com.lecshop.total.letterstation.service.impl;

import com.lecshop.total.letterstation.bean.LetterStation;
import com.lecshop.total.letterstation.mapper.LetterStationMapper;
import com.lecshop.total.letterstation.service.LetterStationService;
import com.lecshop.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 站内信service实现类
 *
 * Created by LecShop on 2017/7/28.
 */
@Service
public class LetterStationServiceImpl implements LetterStationService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(LetterStationServiceImpl.class);

    /**
     * 自动注入站内信数据库接口
     */
    @Autowired
    private LetterStationMapper letterStationMapper;

    /**
     * 发送站内信
     *
     * @param ids 用户id数组
     * @param title 标题
     * @param content 内容
     * @return 成功返回>=1，失败返回0
     */
    @Override
    public int sendLetterStation(long[] ids, String title, String content) {
        logger.debug("sendLetterStation and ids :{} \r\n and title :{} \r\n and content :{}", ids, title, content);
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        params.put("title", title);
        params.put("content", content);
        return letterStationMapper.sendLetterStation(params);
    }

    /**
     * 查询未读站内信
     *
     * @param userId 用户id
     * @return 未读站内信
     */
    @Override
    public int queryLetterStationUnreadCount(long userId) {
        logger.debug("queryLetterStationCount and userId :{}", userId);
        return letterStationMapper.queryLetterStationUnreadCount(userId);
    }

    /**
     * 查询站内信
     *
     * @param userId 用户id
     * @return 站内信集合
     */
    @Override
    public PageHelper<LetterStation> queryLetterStation(PageHelper<LetterStation> pageHelper, long userId) {
        logger.debug("queryLetterStation pageHelper :{} \r\n and userId :{}", pageHelper, userId);
        Map<String, Object> params = new HashMap<>();
        params.put("userId", userId);
        return pageHelper.setListDates(letterStationMapper.queryLetterStation(pageHelper.getQueryParams(params, letterStationMapper.queryLetterStationCount(params))));
    }

    /**
     * 删除站内信
     *
     * @param id 站内信id
     * @return 成功返回1，失败返回0
     */
    @Override
    public int deleteLetterStation(long id) {
        logger.debug("deleteLetterStation and id :{}", id);
        return letterStationMapper.deleteLetterStation(id);
    }

    /**
     * 将站内信设为已读
     *
     * @param id 站内信id
     * @return 成功返回1，失败返回0
     */
    @Override
    public int readLetterStation(long id) {
        logger.debug("readLetterStation and id :{}", id);
        return letterStationMapper.readLetterStation(id);
    }
}
