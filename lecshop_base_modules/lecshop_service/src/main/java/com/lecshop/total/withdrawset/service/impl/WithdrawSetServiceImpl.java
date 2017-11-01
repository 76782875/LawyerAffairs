package com.lecshop.total.withdrawset.service.impl;

import com.lecshop.total.withdrawset.bean.WithdrawSet;
import com.lecshop.total.withdrawset.mapper.WithdrawSetMapper;
import com.lecshop.total.withdrawset.service.WithdrawSetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by dujinkai on 17/7/25.
 * 律师提现服务接口实现
 */
@Service
public class WithdrawSetServiceImpl implements WithdrawSetService {

    /***
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(WithdrawSetServiceImpl.class);

    /**
     * 注入提现设置数据库接口
     */
    @Autowired
    private WithdrawSetMapper withdrawSetMapper;

    @Override
    public WithdrawSet queryByLawyerId(long lawyerId) {
        logger.debug("queryByLawyerId and lawyerId:{}", lawyerId);
        return withdrawSetMapper.queryByLawyerId(lawyerId);
    }

    @Override
    public int updateWithdrawSet(WithdrawSet withdrawSet) {
        logger.debug("updateWithdrawSet and withdrawSet:{}", withdrawSet);

        if (Objects.isNull(withdrawSet)) {
            logger.error("updateWithdrawSet due to params is null..");
            return 0;
        }

        withdrawSetMapper.deleteByLawyerId(withdrawSet.getLawyerId());

        return withdrawSetMapper.addWithdrawSet(withdrawSet);
    }
}
