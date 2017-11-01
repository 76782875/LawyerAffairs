package com.lecshop.usercenter;

import com.lecshop.total.affairlist.bean.AppointMeet;
import com.lecshop.total.affairlist.service.AppointMeetService;
import com.lecshop.util.LoginUtils;
import com.lecshop.utils.BaseResponse;
import com.lecshop.utils.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dujinkai on 17/7/28.
 * 预约会面(抢单) 实体
 */
@Controller
public class AppointMeetContrloller {

    /**
     * 注入预约服务接口
     */
    @Autowired
    private AppointMeetService appointMeetService;

    /**
     * 跳转到抢单页面
     *
     * @return 返回抢单页面
     */
    @RequestMapping("/toappointmeet")
    public ModelAndView toAppointMeet() {
        return new ModelAndView("center/appointmeet");
    }

    /**
     * 查询预约会面信息
     *
     * @param pageHelper 分类实体
     * @return 返回预约会面信息
     */
    @ResponseBody
    @RequestMapping("/queryappointmeets")
    public BaseResponse queryAppointMeets(PageHelper<AppointMeet> pageHelper) {
        return BaseResponse.build(appointMeetService.queryTelConsultation(pageHelper, "3", "", "", "", 0, 0,0));
    }

    /**
     * 抢单(预约会面)
     *
     * @param id 预约会面id
     * @return 成功返回1 失败返回0
     */
    @RequestMapping("/grabappointmeet")
    @ResponseBody
    public int grabAppointMeet(HttpServletRequest request, long id) {
        return appointMeetService.grabAppointMeet(LoginUtils.getInstance().getLawyerId(request), id);
    }
}
