package com.lecshop.total.affairlist.mapper;

import com.lecshop.total.affairlist.bean.DisputeDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by dujinkai on 17/8/3.
 * 纠纷详情数据库接口
 */
public interface DisputeDetailMapper {

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
     * @param params 参数
     * @return 返回纠纷详情
     */
    DisputeDetail queryByDisputeIdAndCode(Map<String, Object> params);

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
