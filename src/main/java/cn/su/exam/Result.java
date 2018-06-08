package cn.su.exam;

import java.io.Serializable;

/**
 * 转账返回结果
 * 省略了get/set
 *
 * @author suyulong
 */
public class Result implements Serializable {

    private static final long serialVersionUID = 0xf83b913d008a251fL;

    /**
     * 返回码（SUCCESS,FAIL..）
     */
    private String resultCode;

    /**
     * 结果描述
     */
    private String resultMsg;

    /**
     * 被调用方签名
     * 验证接口被调用方的身份有效性
     */
    private String sign;

    /**
     * 对应入参时的调用方传递的转账流水号
     */
    private String transferNum;

    /**
     * 转账成功的订单号（被调用方）
     * resultCode==SUCCESS返回
     */
    private String orderId;

    /**
     * 转账时间
     * resultCode==SUCCESS返回
     */
    private String orderTime;

}
