package cn.su.study.shift;

/**
 * @author suyulong
 * @date 2019/9/30 12:58 PM
 */
public class BitMap {
    /**
     * 标明数组中一个元素存放几位，a[i]存放32位
     */
    private static final int BITSPERWORD = 32;

    /**
     * 因为一个元素中放32位，对应的就是2^5，对应移位就是5位
     */
    private static final int SHIFT = 5;

    /**
     * 5位，所以mask就是11111，就是0x1F，也可以理解为32-1
     */
    private static final int MASK = 0x1F;

    /**
     * 正整数的个数
     */
    private static final int N = 100;

    /**
     * 数组a的元素个数，需要用N/32 +1
     */
    private static int[] a = new int[N / BITSPERWORD + 1];


    private static void set(int i) {
        //i>>shift 右移shift位 == 除以32，找到i对应的数组对应的下标
        //i&mask， 除以31，取余，找到数组中对应的bit位
        //1<<i&mask  1表示该位存在，将对应的bit位置为1，其余位置都是0
        //|= 该数组中bit位置为1，其他位置都保持不变，原|0 = 1还是1，0还是0
        a[i >> SHIFT] |= (1 << (i & MASK));
    }

    private static void clr(int i) {
        //~按位取反
        //~(1 << (i & MASK))  将对应的bit位置为0，其余位置都是1
        //&=  将数组中对应的bit位置为0，同时其他的位置保持不变，原&1 = 1还是1，0还是0
        a[i >> SHIFT] &= ~(1 << (i & MASK));
    }

    private static int test(int i) {
        //(1 << (i & MASK))  找到对应的bit位，其余位置都是0
        //a[i >> SHIFT] & (1 << (i & MASK))，除了bit位之外，其余的会是0，这个bit位如果是1，那最后就返回>0的，否则就返回0
        return a[i >> SHIFT] & (1 << (i & MASK));
    }

    public static void main(String[] args) {
        set(30);
        //for (int i : a){
        //    System.out.println(Integer.toBinaryString(i));
        //}
        System.out.println(test(30));
        clr(30);
        System.out.println(test(30));
        set(34);
        //for (int i : a){
        //    System.out.println(Integer.toBinaryString(i));
        //}
    }
}
