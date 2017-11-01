package com.lecshop.total.withdraw.service.impl;

import com.lecshop.admin.profitset.bean.ProfitSet;
import com.lecshop.admin.profitset.service.ProfitSetService;
import com.lecshop.total.transactionrecords.bean.TransactionRecords;
import com.lecshop.total.transactionrecords.service.TransactionRecordsService;
import com.lecshop.total.withdraw.bean.QueryConditions;
import com.lecshop.total.withdraw.bean.Withdraw;
import com.lecshop.total.withdraw.mapper.WithdrawMapper;
import com.lecshop.total.withdraw.service.WithdrawService;
import com.lecshop.total.withdrawset.bean.WithdrawSet;
import com.lecshop.total.withdrawset.service.WithdrawSetService;
import com.lecshop.utils.PageHelper;
import com.lecshop.utils.WithdrawResponse;
import com.lecshop.utils.WithdrawUtils;
import com.lecshop.utils.alipay.config.AliOpenApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dujinkai on 17/7/26.
 * 提现服务接口实现
 */
@Service
public class WithdrawServiceImpl implements WithdrawService {

    /**
     * 调试日志
     */
    private Logger logger = LoggerFactory.getLogger(WithdrawServiceImpl.class);

    /**
     * 注入提现设置
     */
    @Autowired
    private ProfitSetService profitSetService;

    /**
     * 注入律师提现设置接口
     */
    @Autowired
    private WithdrawSetService withdrawSetService;

    /**
     * 注入提现数据库接口
     */
    @Autowired
    private WithdrawMapper withdrawMapper;


    @Autowired
    private AliOpenApi aliOpenApi;

    /**
     * 注入交易记录
     */
    @Autowired
    private TransactionRecordsService transactionRecordsService;

    @Override
    public int addWithdraw(Withdraw withdraw) {

        logger.debug("addWithdraw and withdraw:{}", withdraw);

        if (Objects.isNull(withdraw) || Objects.isNull(withdraw.getMoney())) {
            logger.error("addWithdraw fail due to params is null...");
            return -3;
        }

        // 检查当前日期是否可以提现
        if (!canWithdraw()) {
            logger.error("addWithdraw fail due to time is error....");
            return -1;
        }

        // 检查用户有没有配置提现的支付宝账户
        if (!hasAccount(withdraw.getLawyerId())) {
            logger.error("addWithdraw fail due to has no account...");
            return -2;
        }

        // 检查律师账户是否有充足的余额
        if (!isBalanceEnough(withdraw.getMoney(), withdraw.getLawyerId())) {
            logger.error("addWithdraw fail due to lawyer balance is not enough....");
            return -4;
        }

        // 添加交易记录
        withdraw.setTransId(transactionRecordsService.addTransactionRecords(TransactionRecords.buildForWithdraw(withdraw.getLawyerId(), withdraw.getMoney())));

        return withdrawMapper.addWithdraw(withdraw);
    }

    /**
     * 判断律师账户余额 是否足够
     *
     * @param bigDecimal 用户提现的金额
     * @param lawyerId   律师id
     * @return 足够返回true  ,不足返回false
     */
    private boolean isBalanceEnough(BigDecimal bigDecimal, long lawyerId) {
        return transactionRecordsService.queryLawyerBalance(lawyerId).compareTo(bigDecimal) >= 0;
    }

    @Override
    public PageHelper<Withdraw> queryWithdraws(PageHelper<Withdraw> pageHelper, QueryConditions queryConditions) {
        logger.debug("queryWithdraws and pageHelper:{} r\n queryConditions:{}", pageHelper, queryConditions);
        Map<String, Object> params = queryConditions.getQueryMap();
        return setWithdrawSet(pageHelper.setListDates(withdrawMapper.queryWithdraws(pageHelper.getQueryParams(params, withdrawMapper.queryWithdrawsCount(params)))));
    }

    /**
     * 设置律师的提现设置
     */
    private PageHelper<Withdraw> setWithdrawSet(PageHelper<Withdraw> pageHelper) {
        pageHelper.getList().parallelStream().forEach(withdraw -> withdraw.setWithdrawSet(withdrawSetService.queryByLawyerId(withdraw.getLawyerId())));
        return pageHelper;
    }

    @Override
    public int changeStatus(long id, String status) {
        logger.debug("changeStatus and id :{} , status:{}", id, status);
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("status", status);

        // 如果是拒绝提现 则将账户扣除的钱返回
        if ("2".equals(status)) {
            logger.debug("withdraw refuse and  begin to back moeny");
            transactionRecordsService.deleteById(withdrawMapper.queryById(id).getTransId());
        }

        return withdrawMapper.changeStatus(params);
    }

    @Override
    public WithdrawResponse releaseMoney(long id) {

        logger.debug("releaseMoney and id:{}", id);

        // 查询提现记录
        Withdraw withdraw = withdrawMapper.queryById(id);

        if (Objects.isNull(withdraw)) {
            return WithdrawResponse.buildSystemError();
        }

        // 支付宝提现
        WithdrawResponse withdrawResponse = WithdrawUtils.getInstance().withdraw(aliOpenApi, withdraw, withdrawSetService.queryByLawyerId(withdraw.getLawyerId()));


        // 如果成功则修改状态
        if (withdrawResponse.isSuccess()) {
            logger.debug("withdraw success....");
            changeStatus(id, "3");
        }


        return withdrawResponse;
    }

    /**
     * 判断当天是否可以提现
     *
     * @return 可以返回true  不可以返回false
     */
    private boolean canWithdraw() {
        ProfitSet profitSet = profitSetService.queryProfitSet();

        if (Objects.isNull(profitSet)) {
            return false;
        }
        return LocalDateTime.now().getDayOfMonth() == profitSet.getWithdrawTime();
    }

    /**
     * 用户是否配置了支付宝账户
     *
     * @param lawyerId 律师id
     * @return 配置 返回true  没有配置返回false
     */
    private boolean hasAccount(long lawyerId) {
        WithdrawSet withdrawSet = withdrawSetService.queryByLawyerId(lawyerId);
        return Objects.nonNull(withdrawSet) && !StringUtils.isEmpty(withdrawSet.getAccount()) && !StringUtils.isEmpty(withdrawSet.getName());
    }

}
