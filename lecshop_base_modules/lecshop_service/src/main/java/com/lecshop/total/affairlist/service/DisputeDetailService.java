package com.lecshop.total.affairlist.service;

import com.lecshop.total.affairlist.bean.DisputeDetail;

import java.util.List;

/**
 * Created by dujinkai on 17/8/3.
 * 纠纷详情服务接口
 */
public interface DisputeDetailService {

    /**
     * 根据纠纷id查询纠纷详情
     *
     * @param disputeId 纠纷id
     * @return 返回纠纷详情
     */
    List<DisputeDetail> queryByDisputeId(long disputeId);


    /**
     * 根据纠纷id和code 查询纠纷详情
     *
     * @param disputeId 纠纷id
     * @param code      纠纷code
     * @return 返回纠纷详情
     */
    DisputeDetail queryByDisputeIdAndCode(long disputeId, String code);

    /**
     * 更新纠纷详情
     *
     * @param disputeDetail 纠纷详情
     * @return 成功返回1 失败返回0
     */
    int updateDisputeDetail(DisputeDetail disputeDetail);

    /**
     * 添加纠纷详情
     *
     * @param disputeDetails 纠纷详情集合
     * @return 添加返回码
     */
    int addDisputeDetail(List<DisputeDetail> disputeDetails);
}
