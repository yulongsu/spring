package cn.su.daily;

/**
 * @author suyulong
 * @date 2019/12/18 9:42 PM
 */
public class Main3 implements Runnable {
    int num = 1;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                notify();
                /*try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                if (num <= 100) {
                    System.out.println(Thread.currentThread().getName() + ":" + num);
                    num++;
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Main3 th = new Main3();
        Thread t1 = new Thread(th);
        Thread t2 = new Thread(th);

        t1.setName("a");
        t2.setName("b");

        t1.start();
        t2.start();
    }
}
