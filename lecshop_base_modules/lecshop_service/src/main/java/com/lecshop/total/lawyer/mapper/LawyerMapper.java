package com.lecshop.total.lawyer.mapper;

import com.lecshop.total.lawyer.bean.Lawyer;
import com.lecshop.total.lawyer.bean.LawyerExternal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 律师数据库接口
 * <p>
 * Created by LecShop on 2017/7/11.
 */
@Repository
public interface LawyerMapper {

    /**
     * 分页查询律师集合
     *
     * @param params 律师姓名及手机号码
     * @return 律师列表
     */
    List<Lawyer> queryLawyerList(Map<String, Object> params);

    /**
     * 查询律师人数
     *
     * @param params 律师姓名及手机号码
     * @return 律师人数
     */
    int queryLawyerCount(Map<String, Object> params);

    /**
     * 对外接口-分页查询律师集合
     *
     * @return 律师列表
     */
    List<LawyerExternal> externalInterface(Map<String, Object> params);

    /**
     * 对外接口-查询律师人数
     *
     * @return 律师人数
     */
    int externalInterfaceCount();

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
     * @param lawyer 律师会员
     * @return 成功返回1，失败返回0
     */
    int addLawyer(Lawyer lawyer);

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
     * 更新密码
     *
     * @param params 参数
     * @return 成功返回1 失败返回0
     */
    int updatePassword(Map<String, Object> params);

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
