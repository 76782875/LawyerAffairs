package com.lecshop.total.lawyer.service.impl;

import com.lecshop.admin.sms.service.SmsService;
import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.lawyer.bean.LawyerExternal;
import com.lecshop.total.lawyer.mapper.LawyerMapper;
import com.lecshop.total.lawyer.service.LawyerService;
import com.lecshop.utils.MD5Utils;
import com.lecshop.utils.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 律师service实现类
 * <p>
 * Created by LecShop on 2017/7/11.
 */
@Service
public class LawyerServiceImpl implements LawyerService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(LawyerServiceImpl.class);

    /**
     * 自动注入律师数据库接口
     */
    @Autowired
    private LawyerMapper lawyerMapper;

    /**
     * 自动注入短信设置service接口
     */
    @Autowired
    private SmsService smsService;

    /**
     * 分页查询律师集合
     *
     * @param pageHelper 分页帮助类
     * @param name       律师姓名
     * @param mobile     手机号码
     * @return 律师集合
     */
    @Override
    public PageHelper<Lawyer> queryLawyer(PageHelper<Lawyer> pageHelper, String name, String mobile, String status) {
        logger.debug("queryLawyer and pageHelper :{} \r\n and name :{} \r\n and mobile :{}\r\n and status :{}", pageHelper, name, mobile, status);
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("mobile", mobile);
        params.put("status", status);
        return pageHelper.setListDates(lawyerMapper.queryLawyerList(pageHelper.getQueryParams(params, lawyerMapper.queryLawyerCount(params))));
    }

    /**
     * 对外接口-分页查询律师集合
     *
     * @param pageHelper 分页帮助类
     * @param key        秘钥
     * @return
     */
    @Override
    public Map<String, Object> externalInterface(PageHelper<LawyerExternal> pageHelper, String key) {
        logger.debug("queryLawyer and pageHelper :{} \r\n and key :{}", pageHelper, key);
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        List<LawyerExternal> lawyerExternalList = new ArrayList<>();
        if ("LECSHOP".equals(key)) {
            lawyerExternalList = lawyerMapper.externalInterface(pageHelper.getQueryParams(params, lawyerMapper.externalInterfaceCount()));
            resultMap.put("rescode", 200);
        } else {
            resultMap.put("rescode", -1);
        }
        resultMap.put("pageHelper", pageHelper.setListDates(lawyerExternalList));
        return resultMap;
    }

    /**
     * 删除律师会员
     *
     * @param id 律师会员id
     * @return 成功返回1，失败返回0
     */
    @Override
    public int deleteLawyer(long id) {
        logger.debug("deleteLawyer and id :{}", id);
        return lawyerMapper.deleteLawyer(id);
    }

    /**
     * 批量删除律师会员
     *
     * @param ids 律师会员id数组
     * @return 成功返回>=1，失败返回0
     */
    @Override
    public int batchDeleteLawyer(long[] ids) {
        logger.debug("batchDeleteLawyer and ids :{}", ids);
        return lawyerMapper.batchDeleteLawyer(ids);
    }

    /**
     * 根据id查找律师会员
     *
     * @param id 律师会员id
     * @return 律师会员
     */
    public Lawyer queryLawyerById(long id) {
        logger.debug("queryLawyerById and id :{}", id);
        return lawyerMapper.queryLawyerById(id);
    }

    /**
     * 审核律师会员
     *
     * @param lawyer 律师会员
     * @return 成功返回1，失败返回0
     */
    @Override
    public int auditLawyer(Lawyer lawyer) {
        logger.debug("auditLawyer and lawyer :{}", lawyer);
        return lawyerMapper.auditLawyer(lawyer);
    }

    @Override
    public int addLawyer(Lawyer lawyer, boolean isFromAdmin) {
        logger.debug("addLawyer and lawyer :{} \r\n isFromAdmin:{}", lawyer, isFromAdmin);
        if (Objects.isNull(lawyer)) {
            logger.error("addLawyer fail due to aprams is null...");
            return -3;
        }
        // 校验律师手机号码是否存在,如果存在则直接返回
        if (hasMobileExsit(lawyer.getMobile())) {
            logger.error("addLawyer fail due to mobile is alerdy exist");
            return -1;
        }
        // 如果律师是来自平台添加的 则设置随机密码 和发送短信
        if (isFromAdmin) {
            lawyer.setPassword(String.valueOf((int) ((Math.random() * 9 + 1) * 100000)));
            smsService.sendMsg(lawyer.getMobile(), lawyer.getPassword());
        }
        return lawyerMapper.addLawyer(lawyer.encryptionPassword());
    }

    /**
     * 修改律师会员
     *
     * @param lawyer 律师会员
     * @return 成功返回1，失败返回0
     */
    @Override
    public int updateLawyer(Lawyer lawyer) {
        logger.debug("updateLawyer and lawyer :{}", lawyer);
        return lawyerMapper.updateLawyer(lawyer);
    }

    /**
     * 查询平台自己的律师
     *
     * @return 平台自己的律师
     */
    @Override
    public List<Lawyer> queryLawyerOwn() {
        logger.debug("queryLawyerOwn...");
        return lawyerMapper.queryLawyerOwn();
    }

    @Override
    public Lawyer queryByMobile(String mobile) {
        logger.debug("queryByMobile and mobile:{}", mobile);
        if (StringUtils.isEmpty(mobile)) {
            logger.error("queryByMobile fail due to mobile is empty...");
            return null;
        }
        return lawyerMapper.queryByMobile(mobile);
    }

    /**
     * 判断律师的手机号码是否存在
     *
     * @param mobile 手机号码
     * @return 存在返回true  不存在返回false
     */
    @Override
    public boolean hasMobileExsit(String mobile) {
        return !StringUtils.isEmpty(mobile) && Objects.nonNull(queryByMobile(mobile));

    }

    @Override
    public int updatePassword(String mobile, String password) {
        logger.debug("updatePassword and mobile :{}", mobile);
        Map<String, Object> params = new HashMap<>();
        params.put("mobile", mobile);
        params.put("password", MD5Utils.getInstance().createMd5(password));
        return lawyerMapper.updatePassword(params);
    }

    @Override
    public int updateLawyerForCenter(Lawyer lawyer) {
        logger.debug("updateLawyerForCenter and lawyer:{}", lawyer);
        if (Objects.isNull(lawyer)) {
            logger.error("updateLawyerForCenter fail due to lawyer is null....");
            return 0;
        }
        return lawyerMapper.updateLawyerForCenter(lawyer);
    }

    /**
     * 根据律师证号查询律师信息
     *
     * @param code 律师证号
     * @return 律师信息
     */
    @Override
    public Lawyer queryLawyerByCode(String code) {
        return lawyerMapper.queryLawyerByCode(code);
    }
}
