package cn.su.study;

/**
 * @author suyulong
 * @date 2018/5/14 16:04
 */
public class publicTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()-> System.out.println(1/0));
        t.start();
        t.join();
    }
}
