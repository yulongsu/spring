package cn.su.work;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @author suyulong
 * @date 2020/3/24 4:04 PM
 */
public class Task implements Runnable {
    List<String> list;
    Set<String> set;
    private CountDownLatch latch;

    public Task(List<String> list, Set<String> set, CountDownLatch latch) {
        this.list = list;
        this.set = set;
        this.latch = latch;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            String id = OrderIdGenerator.INSTANCE.genId();
            synchronized (list) {
                list.add(id);
            }
            synchronized (set) {
                set.add(id);
            }
        }
        latch.countDown();
    }
}
