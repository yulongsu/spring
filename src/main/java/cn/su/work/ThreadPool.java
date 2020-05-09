package cn.su.work;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;


public class ThreadPool {

    public ThreadPoolTaskExecutor getPool() {
        int corePoolSize = 4;
        int maximumPoolSize = 8;
        int keepAliveTime = 100;
        ThreadPoolTaskExecutor executor  = new ThreadPoolTaskExecutor();
        //核心池大小
        executor.setCorePoolSize(corePoolSize);
        //最大线程数
        executor.setMaxPoolSize(maximumPoolSize);
        //队列程度
        executor.setQueueCapacity(keepAliveTime);
        //线程空闲时间
        executor.setKeepAliveSeconds(1000);
        //线程前缀名称
        executor.setThreadNamePrefix("REGSENSOR_");
        //配置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy ());
        // 执行初始化
        executor.initialize();
        return executor;
    }

}
