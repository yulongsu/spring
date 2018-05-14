package cn.su.study.thread;

/**
 * @author suyulong
 * @date 2018/5/13 23:41
 */
public class InterruptThread {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println("i am alive");
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupt!");
                    //抛出中断异常后，标志位就恢复成了false
                    System.out.println(Thread.currentThread().getName() + " " + Thread.currentThread().isInterrupted());
                    //Thread.currentThread().interrupt();
                }
            }
        });

        t.start();
        t.interrupt();
        System.out.println(t.isInterrupted());
        System.out.println(t.isInterrupted());
    }
}
