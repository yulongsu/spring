package cn.su.study.algorithm.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author suyulong
 * @date 2018/6/4 11:42
 */
public class QuickSort {

    /**
     * 快速的分割，以第一个元素为标志位，分别将待排元素分开
     * 注意一定要先从右边走
     *
     * 关注点在相遇的时候，从左侧先走相遇的时候可以保证左边的都是小于标准值的，
     * 但是相遇时候的值是要大于标准值（或者已经走到最右边都没有大于标准值的），
     * 从右侧走正好是右边都是大于标准值的，但是相遇的时候值是小于标准值（或者走到 最左边都没有小于标准值）。
     * 所以要从基数的对面开始。两种情况，就看你从哪开始扫描。不一定非要从右边开始哦！也可以从左边的，那标准值就选右边的。
     *
     * @param arr  arr数组
     * @param low  数组的前下标
     * @param high 数组的后下标
     * @return  key的下标index，也就是分片的间隔点
     */
    private static int partition(int[] arr, int low, int high) {
        int key = arr[low];
        while (low < high) {
            while (arr[high] >= key && low < high) {
                high--;
            }
            arr[low] = arr[high];

            while (arr[low] <= key && low < high) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[high] = key;
        return high;
    }

    public static void sort(int[] arr, int low, int high) {
        if (low >= high) { return; }
        int index = partition(arr, low, high);
        sort(arr, low, index - 1);
        sort(arr, index + 1, high);
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 4, 3, 7, 8};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
