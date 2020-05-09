package cn.su.daily;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author suyulong
 * @date 2019/11/15 11:35 AM
 */
public class TestABN {

    private static final Object lock = new Object();

    private volatile int index = 1;

    private volatile boolean mutex = false;

    class Printer1 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                synchronized (lock) {
                    while (mutex) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Printer1" + "-" + index);
                    index++;
                    mutex = true;
                    lock.notifyAll();
                }
            }
        }
    }

    class Printer2 implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 50; i++) {
                synchronized (lock) {
                    while (!mutex) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Printer2" + "-" + index);
                    index++;
                    mutex = false;
                    lock.notifyAll();
                }
            }
        }
    }

    private volatile AtomicInteger atomicNum = new AtomicInteger(1);

    class Printer implements Runnable {
        @Override
        public void run() {
            while (atomicNum.get() < 101) {
                synchronized (atomicNum) {
                    String tName = Thread.currentThread().getName();
                    if (atomicNum.get() % 2 == Integer.valueOf(tName) % 2) {
                        System.out.println("Printer" + tName + "-" + atomicNum.get());
                        atomicNum.incrementAndGet();
                        atomicNum.notify();
                    } else {
                        try {
                            atomicNum.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

    // 基本类型都没有wait notify的方法
   /* private volatile int num = 1;

    class Printer3 implements Runnable {
        @Override
        public void run() {
            while (num < 101) {
                synchronized (num) {
                    String tName = Thread.currentThread().getName();
                    if (num % 2 == Integer.valueOf(tName) % 2) {
                        System.out.println("Printer" + tName + "-" + num);
                        num++;
                        num.notify();
                    } else {
                        try {
                            num.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }*/

    public static void main(String[] args) {
        TestABN testABN = new TestABN();
        Thread threadA = new Thread(testABN.new Printer(), "1");
        Thread threadB = new Thread(testABN.new Printer(), "2");
        //Thread threadA = new Thread(testABN.new Printer1(), "1");
        //Thread threadB = new Thread(testABN.new Printer2(), "2");
        threadA.start();
        threadB.start();
    }
}
