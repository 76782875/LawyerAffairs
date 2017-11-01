package com.lecshop.total.lawyer.service;

import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.lawyer.bean.LawyerExternal;
import com.lecshop.utils.PageHelper;

import java.util.List;
import java.util.Map;

/**
 * 律师service
 *
 * @author sunluyang on 2017/7/10.
 */
public interface LawyerService {

    /**
     * 分页查询律师集合
     *
     * @param pageHelper 分页帮助类
     * @param name       律师姓名
     * @param mobile     手机号码
     * @return 律师集合
     */
    PageHelper<Lawyer> queryLawyer(PageHelper<Lawyer> pageHelper, String name, String mobile, String status);

    /**
     * 对外接口-分页查询律师集合
     *
     * @param pageHelper 分页帮助类
     * @param key        秘钥
     * @return 律师集合
     */
    Map<String, Object> externalInterface(PageHelper<LawyerExternal> pageHelper, String key);

    /**
     * 删除律师会员
     *
     * @param id 律师会员id
     * @return 成功返回1，失败返回0
     */
    int deleteLawyer(long id);

    /**
     * 批量删除律师会员
     *
     * @param ids 律师会员id数组
     * @return 成功返回>=1，失败返回0
     */
    int batchDeleteLawyer(long[] ids);

    /**
     * 根据id查找律师会员
     *
     * @param id 律师会员id
     * @return 律师会员
     */
    Lawyer queryLawyerById(long id);

    /**
     * 审核律师会员
     *
     * @param lawyer 律师会员
     * @return 成功返回1，失败返回0
     */
    int auditLawyer(Lawyer lawyer);

    /**
     * 添加律师会员
     *
     * @param lawyer      律师会员
     * @param isFromAdmin 是否来自平台添加
     * @return 成功返回1，失败返回0  手机号码已经存在返回-1 参数错误 -3
     */
    int addLawyer(Lawyer lawyer, boolean isFromAdmin);

    /**
     * 修改律师会员
     *
     * @param lawyer 律师会员
     * @return 成功返回1，失败返回0
     */
    int updateLawyer(Lawyer lawyer);

    /**
     * 查询平台自己的律师
     *
     * @return 平台自己的律师
     */
    List<Lawyer> queryLawyerOwn();

    /**
     * 根据手机号码查询律师信息
     *
     * @param mobile 手机号码
     * @return 返回律师信息
     */
    Lawyer queryByMobile(String mobile);

    /**
     * 手机号码是否存在
     *
     * @param mobile 手机号码
     * @return 存在返回true  不存在返回false
     */
    boolean hasMobileExsit(String mobile);

    /**
     * 更新密码
     *
     * @param mobile   手机号码
     * @param password 密码
     * @return 成功返回1 失败返回0
     */
    int updatePassword(String mobile, String password);

    /**
     * 更新律师信息(律师端个人中心)
     *
     * @param lawyer 律师信息
     * @return 成功返回1 失败返回0
     */
    int updateLawyerForCenter(Lawyer lawyer);

    /**
     * 根据律师证查询律师信息
     *
     * @param code 律师证号
     * @return 律师信息
     */
    Lawyer queryLawyerByCode(String code);
}
