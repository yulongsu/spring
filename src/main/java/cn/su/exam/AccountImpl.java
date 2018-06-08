/*
package cn.su.exam;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;

import java.math.BigDecimal;

*/
/**
 * @author suyulong
 *//*

public class AccountImpl implements Account {
    private static Logger LOGGER = LoggerFactory.getLogger(AccountImpl.class);

    */
/**
     * 转账接口实现
     *
     * @param transferDTO
     * @return
     *//*

    @Override
    public Result transfer(TransferDTO transferDTO) {
        //LOGGER.info("转账请求入参：{}", transferDTO);

        //校验参数的有效性
        //token是否合法，字符串是否为空，长度是否过长，金额字符型数组是否规范等
        checkParam(transferDTO);

        //调用方转账流水记录入库保存
        try {
            //转账流水号（transferNum）作为主键去落库保存
            //生成对应的内部订单号，外部流水号和内部订单号一一对应
            String orderId = saveTransferNum(transferDTO);

        } catch (DuplicateKeyException e) {
            //如果重复请求会报主键冲突
            LOGGER.warn("重复请求，转账流水号：{}", transferDTO.getTransferNum());

            //幂等提交
            //去查询该流水号对应转账订单结果返回（SUCCESS,FAIL等）
            return idempotentSubmit(transferDTO);
        } catch (Exception e) {
            LOGGER.error("系统异常：{}", e);
            return new Result("FAIL", "系统异常");
        }

        try {
            //单笔账户转账
            return singleTransferAmount(orderId, transferDTO);
        } catch (BizException e) {
            LOGGER.error("业务异常：{}", e);
            return Result("FAIL", e.getMessage());
        } catch (Exception e) {
            LOGGER.error("系统异常：{}", e);
            return Result("FAIL", 系统异常);
        }
    }

    */
/**
     * 单笔账户转账 事务处理
     *
     * @param orderId     转账订单号
     * @param transferDTO 转账请求
     * @return
     *//*

    @Transactional
    private Result singleTransferAmount(String orderId, TransferDTO transferDTO) {

        LOGGER.info("单笔转账请求：orderId={}，payerAccount={},payeeAccount={},amount={},remark={}",
            orderId, payerAccount, payeeAccount, amount, remark);

        //付款方A账户状态,收款方B账户是否正常
        if (!checkAvaiable(payerAccount) || !checkAvaiable(payeeAccount)) {
            //throw new BizException("账户状态异常")
        }

        //付款方A账户余额检查，for update把对应的A账户金额锁住
        if (!checkBalance(payerAccount, amount)) {
            //throw new BizException("账户余额不足")
        }

        //转出方
        //A数据库：落库保存转出账户的记录
        insertOrder(ADbInstance, orderId, payerAccount, payeeAccount, amount, remark);

        //转出账户A,A账户-amount
        updateAccount(ADbInstance, payerAccount, amount.negate());

        //收款方
        //B数据库：落库保存转入账户的记录
        insertOrder(BDbInstance, orderId, payerAccount, payeeAccount, amount, remark);

        //转入账户B,B账户+amount
        updateAccount(BDbInstance, payeeAccount, amount);

        return new Result("SUCCESS");
    }

    */
/**
     * 入参合法性校验
     *
     * @param transferDTO
     * @throws BizException
     *//*

    private void checkParam(TransferDTO transferDTO) throws BizException {
        //TODO
    }

    */
/**
     * 幂等提交
     *//*

    private Result idempotentSubmit(TransferDTO transferDTO) {
        //TODO
        //用调用方的转账流水对应的内部订单号去查询实际的转账订单表，查看转账结果返回
        return null;
    }
}
*/
