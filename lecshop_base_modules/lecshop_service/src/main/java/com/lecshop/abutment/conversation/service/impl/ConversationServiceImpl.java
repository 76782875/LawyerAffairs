package com.lecshop.abutment.conversation.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lecshop.abutment.conversation.bean.CallList;
import com.lecshop.abutment.conversation.bean.CallResponseJson;
import com.lecshop.abutment.conversation.bean.Parameter;
import com.lecshop.abutment.conversation.service.ConversationService;
import com.lecshop.total.affairlist.bean.TelConsultation;
import com.lecshop.total.affairlist.service.TelConsultationService;
import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.FileUpAndDownUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 通话接口service
 *
 * @author sunluyang on 2017/8/2.
 */
@Service
public class ConversationServiceImpl implements ConversationService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(ConversationServiceImpl.class);
    /**
     * 注入电话咨询service
     */
    @Autowired
    private TelConsultationService telConsultationService;

    /**
     * 获取调用接口查询律师数据
     *
     * @return 律师数据
     */
    @Override
    public int initiateCall(String phone, String call, long userId, long companyId, String lawyerId, String type, String basePath) {
        logger.debug("initiateCall and phone:{}\r\ncall:{}\r\nuserId:{}\r\nlawyerCode:{}\r\n", phone, call, userId, lawyerId);
        int result = -2;
        //请求链接
        HttpGet httpGet = new HttpGet(new Parameter().buildUrl(phone, call, userId, companyId, lawyerId, type, basePath));
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpResponse httpResponse = httpclient.execute(httpGet);
            logger.debug("response and status:{}", httpResponse.getStatusLine().getStatusCode());
            String responseString = EntityUtils.toString(httpResponse.getEntity(), CommonConstant.ENCODE);
            logger.info("initiateCall and result:{}", responseString);
            result = Integer.parseInt(JSONObject.parseObject(responseString, CallResponseJson.class).getErrCode());
        } catch (Exception e) {
            logger.error("initiateCall error", e);
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                httpclient.close();
            } catch (Exception ignored) {
                logger.error("initiateCall error", ignored);
                ignored.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 电话接口回调
     *
     * @param callList 回调参数
     * @return 0返回成功 -1失败
     */
    @Override
    public int receiveCallBackUrl(CallList callList) {
        logger.debug("receiveCallBackUrl and callList:{}", callList);
        if (Objects.nonNull(callList)) {
            if ("2".equals(callList.getEndtype())) {
                String type = callList.getExt();
                String[] Ids = callList.getUid().split("-");
                long userId = Long.parseLong(Ids[1]);
                long lawyerId = Long.parseLong(Ids[2]);
                long companyId = Long.parseLong(Ids[0]);
                if ("1".equals(type) || "2".equals(type) || "3".equals(type)) {
                    //添加通话记录
                    telConsultationService.addTelConsultation(TelConsultation.buildTelConsultation(companyId, userId, lawyerId, type));
                }
                FileUpAndDownUtils.downInternetFile(callList.getRecordurl(), "upload.properties", callList.getUid() + ".wav", this);
            }
            return 0;
        }
        logger.error("receiveCallBackUrl fail due to callList is null");
        return -1;
    }
}
