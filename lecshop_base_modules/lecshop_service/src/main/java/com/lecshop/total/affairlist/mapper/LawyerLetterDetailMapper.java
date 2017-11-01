package com.lecshop.total.affairlist.mapper;

import com.lecshop.total.affairlist.bean.LawyerLetterDetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 律师函详情数据库接口
 * <p>
 * Created by LecShop on 2017/7/19.
 */
@Repository
public interface LawyerLetterDetailMapper {

    /**
     * 插入律师函详情
     *
     * @param lawyerLetterDetail 律师函详情
     * @return 成功返回1，失败返回0
     */
    int addLawyerLetterDetail(LawyerLetterDetail lawyerLetterDetail);

    /**
     * 根据律师涵id查询律师函详情
     *
     * @param letterId 律师涵id
     * @return 返回律师涵详情
     */
    List<LawyerLetterDetail> queryLawyerLetterDetails(long letterId);
}
