package cn.su.study.algorithm.sort;

import java.util.Arrays;

/**
 * 冒泡排序的优点：每进行一趟排序，就会少比较一次，因为每进行一趟排序都会找出一个较大值。
 * 如上例：第一趟比较之后，排在最后的一个数一定是最大的一个数，
 * 第二趟排序的时候，只需要比较除了最后一个数以外的其他的数，
 * 同样也能找出一个最大的数排在参与第二趟比较的数后面，
 * 第三趟比较的时候，只需要比较除了最后两个数以外的其他的数，
 * 以此类推……也就是说，没进行一趟比较，每一趟少比较一次，一定程度上减少了算法的量。
 *
 * 用时间复杂度来说：
 *
 * 　　1.如果我们的数据正序，只需要走一趟即可完成排序。
 * 所需的比较次数C和记录移动次数M均达到最小值，即：Cmin=n-1;Mmin=0;所以，冒泡排序最好的时间复杂度为O(n)。
 *
 * 　　2.如果很不幸我们的数据是反序的，则需要进行n-1趟排序。
 * 每趟排序要进行n-i次比较(1≤i≤n-1)，且每次比较都必须移动记录三次来达到交换记录位置。
 *
 * Cmax = n(n-1)/2 = O(n^2)   比较次数
 * Mmax = 3n(n-1)/2 = O(n^2)  移动次数
 *
 * 在这种情况下，比较和移动次数均达到最大值：冒泡排序的最坏时间复杂度为：O(n2) 。
 *
 * 综上所述：冒泡排序总的平均时间复杂度为：O(n2) 。
 *
 *
 * @author suyulong
 * @date 2018/6/4 14:27
 */
public class BubbleSort {
    public static void sort(int[] arr) {
        //外层循环控制排序趟数
        for (int i = 0; i < arr.length - 1; i++) {
            //内层循环控制每一趟排序多少次
            //-1为了防止溢出
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {4, 2, 4, 1, 5, 9, 8};
        sort(a);
        System.out.println(Arrays.toString(a));
    }

}
