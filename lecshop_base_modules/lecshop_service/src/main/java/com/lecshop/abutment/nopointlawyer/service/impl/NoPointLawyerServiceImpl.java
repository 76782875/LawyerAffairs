package com.lecshop.abutment.nopointlawyer.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.lecshop.abutment.nopointlawyer.bean.ResponseJsonc;
import com.lecshop.abutment.nopointlawyer.bean.SearchBeanc;
import com.lecshop.abutment.nopointlawyer.service.NoPointLawyerService;
import com.lecshop.lawyer.wxauth.service.WxAuthService;
import com.lecshop.total.affairlist.bean.AppointMeet;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.lawyer.service.LawyerService;
import com.lecshop.total.wxsetting.service.WxSettingService;
import com.lecshop.utils.CommonConstant;
import com.lecshop.utils.InterfaceData;
import com.lecshop.utils.PushMessage;
import com.lecshop.utils.WxUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 不指定律师推送service
 *
 * @author sunluyang on 2017/8/3.
 */
@Service
public class NoPointLawyerServiceImpl implements NoPointLawyerService {

    /**
     * 调试日志
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(NoPointLawyerServiceImpl.class);

    /**
     * 律师service
     */
    @Autowired
    private LawyerService lawyerService;
    /**
     * 微信权限service
     */
    @Autowired
    private WxAuthService wxAuthService;
    /**
     * 微信设置service
     */
    @Autowired
    private WxSettingService wxSettingService;

    /**
     * 推送消息
     *
     * @param appointMeet 预约会面service
     * @param searchBeanc 搜索条件
     */
    @Override
    public void pushMessage(AppointMeet appointMeet, SearchBeanc searchBeanc) {
        LOGGER.info("pushMessage and appointMeet:{}\r\n searchBeanc:{}", appointMeet, searchBeanc);
        List<String> openIdList = getLawyerOpenIds(searchBeanc, appointMeet.getLawyerId());
        if (!CollectionUtils.isEmpty(openIdList)) {
            List<String> pushMessageList = openIdList.stream().map(openId -> JSONObject.toJSONString(PushMessage.buildPushMessage(new PushMessage(), openId, appointMeet))).collect(Collectors.toList());
            WxUtils.getInstance().pushMessage(WxUtils.getInstance().getAccessToken(wxSettingService.queryWxSetting()), pushMessageList);
        }
    }

    /**
     * 获取律师openID集合
     *
     * @return openID集合
     */
    private List<String> getLawyerOpenIds(SearchBeanc searchBeanc, long lawyerId) {
        List<Long> lawyerIds = lawyerId != -1 ? new ArrayList<Long>() {{
            add(lawyerId);
        }} : getLawyerIdsByInterface(searchBeanc);
        return !CollectionUtils.isEmpty(lawyerIds) ? wxAuthService.queryOpenIdsByLawyerList(lawyerIds) : null;
    }

    /**
     * 调用接口查询律师
     *
     * @param searchBeanc 查询参数
     * @return 律师id集合
     */
    private List<Long> getLawyerIdsByInterface(SearchBeanc searchBeanc) {
        ResponseJsonc responseJsonc = (ResponseJsonc) InterfaceData.getInterfaceDate(new ResponseJsonc(), getInterfaceUrl(searchBeanc));
        if (!interfaceIsTrue(responseJsonc)) {
            return null;
        } else {
            searchBeanc.setPi(searchBeanc.getPi() + 1);
        }
        while (searchBeanc.getPi() != 1 && (int) Math.ceil(responseJsonc.getTotalcount() / searchBeanc.getPs()) >= searchBeanc.getPi()) {
            responseJsonc.getA_list().addAll(((ResponseJsonc) InterfaceData.getInterfaceDate(new ResponseJsonc(), getInterfaceUrl(searchBeanc))).getA_list());
            searchBeanc.setPi(searchBeanc.getPi() + 1);
        }
        List<Long> lawyerIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(responseJsonc.getA_list())) {
            responseJsonc.getA_list().forEach(lawyerInfos -> {
                Lawyer lawyer = lawyerService.queryLawyerByCode(lawyerInfos.getCode());
                if (Objects.nonNull(lawyer)) {
                    lawyerIds.add(lawyer.getId());
                }
            });
        }
        return lawyerIds;
    }

    /**
     * 获取调用接口URL
     *
     * @param searchBeanc 接口参数
     * @return 调用接口URL
     */
    private String getInterfaceUrl(SearchBeanc searchBeanc) {
        StringBuilder param = new StringBuilder("&casetype=" + searchBeanc.getCasetype());
        if (!StringUtils.isEmpty(searchBeanc.getCasetypefree())) {
            param.append("&casetypefree=").append(searchBeanc.getCasetypefree());
        }
        LOGGER.info("getInterfaceUrl and searchBeanc:{}\r\n url:{}", searchBeanc, CommonConstant.APPOINT_LAWYER_INTERFACE + "?pi=" + searchBeanc.getPi() + "&ps=" + searchBeanc.getPs() + param);
        return CommonConstant.APPOINT_LAWYER_INTERFACE + "?pi=" + searchBeanc.getPi() + "&ps=" + searchBeanc.getPs() + param;
    }

    /**
     * 接口是否调用成功
     *
     * @param responseJsonc 接口返回数据
     * @return true 成功 false 失败
     */
    private boolean interfaceIsTrue(ResponseJsonc responseJsonc) {
        return responseJsonc.getResult() == 1 && !CollectionUtils.isEmpty(responseJsonc.getA_list()) && StringUtils.isEmpty(responseJsonc.getError());
    }
}
