package cn.su.study;

/**
 * @author suyulong
 * @date 2018/5/14 16:04
 */
public class publicTest {
    public static void main(String[] args) throws InterruptedException {
        /*Thread t = new Thread(()-> System.out.println(1/0));
        t.start();
        t.join();
*/
        //java中整数默认int，小数默认double
        //fload f = 3.4 //就是错误的，类型不匹配
        //float f = 3.4f;
        //
        //String a = new String("asdf");
        //String b = new String("asdf");
        //String f = "asdf";
        //System.out.println(b == a);
        //System.out.println(f == a);
        //a = a.intern();
        //System.out.println(b == a);
        //System.out.println(f == a);
        //b = b.intern();
        //System.out.println(b == a);

        System.out.println(1 & 2);

        System.out.println(2 & 2);
    }
}
