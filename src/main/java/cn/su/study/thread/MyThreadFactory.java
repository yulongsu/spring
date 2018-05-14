package cn.su.study.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author suyulong
 * @date 2018/5/14 22:13
 */
public class MyThreadFactory {
    public static void main(String[] args) {

        /**
         * 线程资源必须通过线程池提供，不允许在应用中自行显式创建线程。
         * 说明：使用线程池的好处是减少在创建和销毁线程上所花的时间以及系统资源的开销，解决资源不足的问题。
         * 如果不使用线程池，有可能造成系统创建大量同类线程而导致消耗完内存或者“过度切换”的问题。
         */
        
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("su-thread-%d").build();

        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
            0, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(10), threadFactory);

        singleThreadPool.execute(() -> System.out.println(Thread.currentThread().getName()));

        singleThreadPool.shutdown();
    }

}
