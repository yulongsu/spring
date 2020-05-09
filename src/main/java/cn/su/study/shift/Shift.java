package cn.su.study.shift;

/**
 * @author suyulong
 * @date 2018/5/11 下午2:47
 */
public class Shift {
    public static void main(String[] args) {
        int num = 10;

        //original
        printInfo(num);

        /**
         * >>：带符号右移。正数右移高位补0，负数右移高位补1。 相当于除以2
         * 左移运算符，num << 1,相当于num乘以2
         * >>>：无符号右移。无论是正数还是负数，高位通通补0
         *
         */
        // << 1
        printInfo(num <<= 1);

        // >> 1
        printInfo(num >>= 1);

        // >>> 1
        //左移绝不可能出现符号问题，所以不需要带不带符号。所以是没有<<<操作符的，
        //只取原来符号就行。
        printInfo(num >>>= 1);

        printInfo(1 << 31);
        System.out.println(1 << 31);
        printInfo(2);
        printInfo(~2);
    }

    private static void printInfo(int num) {
        System.out.println(Integer.toBinaryString(num));
    }


}
