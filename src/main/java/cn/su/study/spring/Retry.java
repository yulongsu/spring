package cn.su.study.spring;

import cn.su.exam.BizException;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Collections;
import java.util.Random;

/**
 * 重试
 *
 * @author suyulong
 * @date 2018/11/15 7:39 PM
 */
public class Retry {
    public static Boolean vpmsRetryCoupon(final String userId) {
        // 构建重试模板实例
        RetryTemplate retryTemplate = new RetryTemplate();
        // 设置重试策略，主要设置重试次数
        SimpleRetryPolicy policy = new SimpleRetryPolicy(10, Collections.<Class<? extends Throwable>, Boolean>singletonMap(
            BizException.class, true));
        // 设置重试回退操作策略，主要设置重试间隔时间
        FixedBackOffPolicy fixedBackOffPolicy = new FixedBackOffPolicy();
        fixedBackOffPolicy.setBackOffPeriod(100);
        retryTemplate.setRetryPolicy(policy);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);


        long beginTime = System.currentTimeMillis();
        // 通过RetryCallback 重试回调实例包装正常逻辑逻辑，第一次执行和重试执行执行的都是这段逻辑
        final RetryCallback<Object, BizException> retryCallback = new RetryCallback<Object, BizException>() {
            //RetryContext 重试操作上下文约定，统一spring-try包装
            @Override
            public Object doWithRetry(RetryContext context) throws BizException {
                if (System.currentTimeMillis() - beginTime > 4 * 100) {
                    throw new BizException("123","456");
                }
                return pushCouponByVpmsaa(userId,beginTime);
                //if (!result) {
                //    throw new RuntimeException();//这个点特别注意，重试的根源通过Exception返回
                //}
                //return true;
            }
        };
        // 通过RecoveryCallback 重试流程正常结束或者达到重试上限后的退出恢复操作实例
        //当重试超过最大重试时间或最大重试次数后可以调用RecoveryCallback进行恢复，比如返回假数据或托底数据。
        final RecoveryCallback<Object> recoveryCallback = new RecoveryCallback<Object>() {
            @Override
            public Object recover(RetryContext context) throws Exception {
                //                logger.info("正在重试发券::::::::::::"+userId);
                System.out.println("recovery");
                throw new RuntimeException(context.getLastThrowable());
            }
        };
        try {
            retryTemplate.execute(retryCallback, recoveryCallback);
            // 由retryTemplate 执行execute方法开始逻辑执行
        } catch (Exception e) {
            //            logger.info("发券错误异常========"+e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        vpmsRetryCoupon("43333");
    }


    public static Boolean pushCouponByVpmsaa(String userId, long begingTime) {

        Random random = new Random();
        int a = 9;
        //int a = random.nextInt(10);
        System.out.println("a是" + a);
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (a == 8 /*&& System.currentTimeMillis() - begingTime < 3000*/) {
            return true;
        } else {
            //throw new RuntimeException();
            throw new BizException("","");
        }
    }
}
