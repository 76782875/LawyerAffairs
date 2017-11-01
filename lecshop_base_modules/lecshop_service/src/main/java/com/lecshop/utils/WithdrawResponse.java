package com.lecshop.utils;

import lombok.Data;

/**
 * Created by dujinkai on 17/7/26.
 * 提现返回实体
 */
@Data
public class WithdrawResponse {

    private WithdrawResponse() {

    }

    /**
     * 返回code
     */
    private String code;

    /**
     * 业务code
     */
    private String subCode;

    /**
     * 构造提现返回实体
     *
     * @param code    返回code
     * @param subCode 业务返回code
     * @return 返回提现返回实体
     */
    public static WithdrawResponse build(String code, String subCode) {
        WithdrawResponse withdrawResponse = new WithdrawResponse();
        withdrawResponse.code = code;
        withdrawResponse.subCode = subCode;
        return withdrawResponse;
    }

    /**
     * 构造系统异常实体
     *
     * @return 返回提现相应实体
     */
    public static WithdrawResponse buildSystemError() {
        WithdrawResponse withdrawResponse = new WithdrawResponse();
        withdrawResponse.code = "-10001";
        return withdrawResponse;
    }

    /**
     * 是否提现成功
     *
     * @return 成功返回true
     */
    public boolean isSuccess() {
        return "10000".equals(this.code);
    }
}
