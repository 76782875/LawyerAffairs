package com.lecshop.total.lawyer.bean;

import com.lecshop.utils.MD5Utils;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 律师会员实体类
 * <p>
 * Created by LecShop on 2017/7/11.
 */
@Data
public class Lawyer {
    /**
     * 主键id
     */
    private long id;

    /**
     * 律师姓名
     */
    private String name;

    /**
     * 律师手机号码
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 执业证号
     */
    private String code;

    /**
     * 律师证地址
     */
    private String lawyerPic;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 律师所在地址
     */
    private String lawyersPlace;

    /**
     * 律师状态 0 启用 1 禁用 2 待审核  3 审核不通过
     */
    private String status;

    /**
     * 律师类型 1 自己的律师 2 外面的律师
     */
    private String type;

    /**
     * qq号码
     */
    private String qq;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 律师头像地址
     */
    private String pic;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 删除标记 0 未删除，1 已删除，默认为0
     */
    private String delFlag;

    /**
     * 是否禁用搜索 0 启用 1 禁用
     */
    private String searchForbid;

    /**
     * 总计收入
     */
    private BigDecimal allIncome;

    /**
     * 今日收入
     */
    private BigDecimal todayIncome;

    /**
     * 校验密码是否正确
     *
     * @param password 密码
     * @return 正确返回true  失败返回false
     */
    public boolean validatePassword(String password) {
        return MD5Utils.getInstance().createMd5(password).equals(this.password);
    }

    /**
     * 清除密码
     */
    public Lawyer clearPassword() {
        this.password = "*******";
        return this;
    }

    /**
     * 校验律师的状态
     *
     * @return 启用返回true  否则返回false
     */
    public boolean validateStatus() {
        return "0".equals(this.status);
    }

    /**
     * 对密码进行md5加密
     *
     * @return 返回当前对象
     */
    public Lawyer encryptionPassword() {
        this.password = MD5Utils.getInstance().createMd5(this.password);
        return this;
    }

    /**
     * 设置律师账户余额
     *
     * @param balance 账户余额
     * @return 返回当前对象
     */
    public Lawyer setLawyerBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }

    /**
     * 设置律师的所有收入
     *
     * @param allIncome 总收入
     * @return 返回当前对象
     */
    public Lawyer setLawyerAllIncome(BigDecimal allIncome) {
        this.allIncome = allIncome;
        return this;
    }

    /**
     * 设置律师的当天收入
     *
     * @param todayIncome 当天收入
     * @return 返回当前对象
     */
    public Lawyer setLawyerTodayIncome(BigDecimal todayIncome) {
        this.todayIncome = todayIncome;
        return this;
    }

    /**
     * 设置添加的时候的默认值
     *
     * @return 返回当前对象
     */
    public Lawyer setDefaultValuesForAdd() {
        this.status = "2";
        this.type = "2";
        this.searchForbid = "0";
        return this;
    }
}
