package cn.su.study.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 *
 * @author suyulong
 * @date 2018/6/3 11:20
 */
public class HeapSort {
    /**
     * 构建大顶堆
     *
     * @param a   待排序数组
     * @param i   待调整的非叶子结点
     * @param len 待排序数组的长度 （随着堆排序，这个长度是逐渐变短的）
     */
    public static void adjustHeap(int[] a, int i, int len) {
        int j = 2 * i + 1;
        while (j < len) {
            //找到左右子树最大的结点
            if (j + 1 < len && a[j] < a[j + 1]) {
                j++;
            }
            if (a[i] < a[j]) {
                int tmp = a[i];
                a[i] = a[j];
                a[j] = tmp;
            } else {
                break;
            }
            /**
             * 这个就相当于下降到左子树，开始继续调整
             *
             * 这个在用在倒数第二层的调整实际是没有什么用的，再下移一层也就结束了
             * 主要是用在调整后，引起了上一层不平衡（根结点 < 左/右 子数）
             * 故而需要层层调整
             */
            i = j;
            j = 2 * i + 1;
        }
    }

    public static void heapSort(int a[]) {
        /**
         * 构建大顶堆，是从最后一个非叶子结点开始调整
         *
         * 从数组的最大索引节点对应的父节点开始逐个向上调整每一个结点，
         * 使得以该结点为根的子树都能满足堆的要求。这个调整称为向下调整。
         *
         * 设最大的非叶子结点为x, n为待排序个数
         * 没有右子树：2*x+1 = n-1  => x = n/2 - 1
         * 有右子数:  2*x+2 = n-1  => x = n/2 - 3/2
         *
         * 实际仔细想想，如果有右子数的话，那n一定是奇数，奇数/2 一定会有 1/2
         * 所以就变成了 n/2 - 1
         */
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            adjustHeap(a,i,a.length);
        }

        /**
         * 之前已经调整完毕，是一个大顶堆了
         * 是将根结点和最后一个结点调整，
         * 如果不平衡，只可能是跟结点，
         * 所以只需要从第一个结点0开始重新调整下就可以了
         * adjustHeap(a,0,i);
         */
        for(int i = a.length -1 ; i>=0;i--){
            int tmp = a[i];
            a[i]= a[0];
            a[0] = tmp;
            adjustHeap(a,0,i);
        }
    }

    public static void main(String[] args) {
        int[] a = {5,4,3,1,2};
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
