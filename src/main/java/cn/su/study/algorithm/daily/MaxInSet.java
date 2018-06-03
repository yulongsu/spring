package cn.su.study.algorithm.daily;

import cn.su.study.algorithm.sort.HeapSort;

/**
 * 区间数字在[0,100] 区间中最小值 * 区间中所有数之和 输出最大值
 *
 * @author suyulong
 * @date 2018/5/31 18:12
 */
public class MaxInSet {

    //存储结果集
    private static int[] x;

    //回溯法去查找所有的子集
    //比较子集的

    private static void backtrace(int i, int max) {
        if (i > max - 1) {
            output(x);
        } else {
            for (int m = 0; m < 2; m++) {
                x[i] = m;
                backtrace(i + 1, max);
            }
        }
    }

    private static void output(int[] x) {
        for (int i = 0; i < x.length; i++) {
            if (x[i] == 1) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //第一种思路：遍历所有区间，然后比较，类似穷举法
        //final int n = 3;
        //int[] s = new int[n];
        //MaxInSet.x = new int[n];
        //MaxInSet.backtrace(0, 3);

        //第二种思路：区间排序，从小到大，那么最大值就只需要类比n次就可以了
        //堆排序，大根堆，从小到大
        //比如 1，2，3，4，5，6   区间中最小值 * 区间中所有数之和
        //如果区间中包含1，那么最小值一定是1，乘积的最大值一定是从1+2+3+4+5+6
        //如果区间中不包含1，只包含2，那最小值一定是2，乘积的最大值一定是从2+3+4+5+6这个区间
        //.. 同理，只需要比较到 6*6 ， 总共只需要找这6个比较就可以了

        int[] a = {6, 2, 1};
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }

        HeapSort.heapSort(a);

        int max = a[0] * sum;

        for (int i = 1; i < a.length; i++) {
            sum -= a[i - 1];
            int tmp = a[i] * sum;
            max = (tmp > max) ? tmp : max;
        }

        System.out.println(max);
    }
}
