package cn.su.exam;

import cn.su.study.annotation.NotNull;

import java.io.Serializable;

/**
 * 转账请求
 * 省略了get/set
 *
 * @author suyulong
 */
public class TransferDTO implements Serializable {
    /**
     * 接入方token
     * 验证接口调用方的身份有效性
     * 必填
     */
    private String accessToken;

    /**
     * 转账的请求时间
     * 格式"yyyy-MM-dd HH:mm:ss"
     * 必填
     */
    private String timestamp;

    /**
     * 调用接口的版本号
     * 必填
     */
    private String version;

    /**
     * 转账流水号
     * 必填
     * 用来幂等性校验
     */
    private String transferNum;

    /**
     * 付款人账号
     * 必填
     */
    private String payerAccount;

    /**
     * 收款人账号
     * 必填
     */
    private String PayeeAccount;

    /**
     * 转账金额
     * 单位：元。2位小数。
     * 必填
     */
    private String amount;

    /**
     * 转账备注（最大不超过100字符）
     * 选填
     */
    private String remark;
}
