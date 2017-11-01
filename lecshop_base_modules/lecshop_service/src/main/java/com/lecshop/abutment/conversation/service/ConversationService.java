package com.lecshop.abutment.conversation.service;

import com.lecshop.abutment.conversation.bean.CallList;

/**
 * 通话接口service
 *
 * @author sunluyang on 2017/8/2.
 */
public interface ConversationService {
    /**
     * 获取调用接口查询律师数据
     *
     * @return 律师数据
     */
    int initiateCall(String phone, String call, long userId, long companyId, String lawyerCode, String type, String basePath);

    /**
     * 电话接口回调
     *
     * @param callList 回调参数
     * @return 0返回成功 -1失败
     */
    int receiveCallBackUrl(CallList callList);
}
