package com.lecshop.abutment.conversation.bean;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * 回调返回实体类
 *
 * @author sunluyang on 2017/8/18.
 */
@Data
public class CallResponseJson {
    /**
     * 错误码
     * 0	呼叫成功
     * 1	appkey不存在
     * 2	主叫号码格式错误
     * 3	被叫号码格式错误
     * 4	uid/ext长度超限
     * 5	参数格式不正确
     * 6	账户余额不足
     * 7	IP被拒绝
     * 8	主被叫号码相同
     * 11	运营商线路故障，请重试或联系我们
     * 12	短时间存在相同的呼叫，请勿重复发起
     * -1	短时间内发起大量无效呼叫，帐号被临时冻结，请稍候再试
     */
    @JSONField(name = "errcode")
    private String errCode;
    /**
     * 此次通话的32位唯一话单编号
     */
    @JSONField(name = "call_id")
    private String callId;
}
