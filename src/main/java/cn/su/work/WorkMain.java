package cn.su.work;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author suyulong
 * @date 2020/3/24 2:41 PM
 */
public class WorkMain {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>(400);
        Set<String> set = new HashSet<>(400);
        ThreadPool threadPool = new ThreadPool();
        //等待线程池完成
        CountDownLatch latch = new CountDownLatch(5);
        ThreadPoolTaskExecutor executor = threadPool.getPool();
        executor.execute(new Task(list, set, latch));
        executor.execute(new Task(list, set, latch));
        executor.execute(new Task(list, set, latch));
        executor.execute(new Task(list, set, latch));
        executor.execute(new Task(list, set, latch));
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
        System.out.println(set.size());
    }
}
