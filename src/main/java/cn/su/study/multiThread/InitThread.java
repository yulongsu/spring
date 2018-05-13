package cn.su.study.multiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author suyulong
 * @date 2018/5/12 22:42
 */
public class InitThread {

    public static void main(String[] args) {
        /**
         * thread 启动的5种方法
         */

        //1 extend thread
        Thread t1 = new _init_func_1();
        t1.start();

        //2 implement runnable
        Thread t2 = new Thread(new _init_func_2());
        t2.start();

        //3 lambda
        Thread t3 = new Thread(() ->
            System.out.println(Thread.currentThread().getName()));
        t3.start();

        //4 匿名内部类
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        t4.start();

        //5 callable && future
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return Thread.currentThread().getName();
            }
        });
        Thread t5 = new Thread(futureTask);
        t5.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

class _init_func_1 extends Thread {
    @Override
    public void run() {
        System.out.println(getName());
    }
}

class _init_func_2 implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
