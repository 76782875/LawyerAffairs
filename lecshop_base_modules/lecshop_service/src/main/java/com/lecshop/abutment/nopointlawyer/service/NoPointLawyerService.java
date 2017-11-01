package com.lecshop.abutment.nopointlawyer.service;

import com.lecshop.abutment.nopointlawyer.bean.SearchBeanc;
import com.lecshop.total.affairlist.bean.AppointMeet;

/**
 * 未指定律师service
 *
 * @author sunluyang on 2017/8/3.
 */
public interface NoPointLawyerService {

    void pushMessage(AppointMeet appointMeet, SearchBeanc searchBeanc);
}
