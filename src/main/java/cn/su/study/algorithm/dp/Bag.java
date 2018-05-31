package cn.su.study.algorithm.dp;

import java.util.ArrayList;

/**
 * 动态规划背包问题
 * <p>
 * 动态规划的本质在于要保存每一步子问题的解，用于后面的计算。类似穷举法
 *
 * @author suyulong
 * @date 2018/5/28 23:14
 */
public class Bag {
    /**
     * 待放入背包种的物品列表
     */
    ArrayList<Thing> list;

    /**
     * 承受的总重量
     */
    int limitWeight;

    /**
     * 保存最终结果，表示对应物品的数组下标的物品是否会选中，1：选中，0：没有选中
     */
    boolean[] option;

    /**
     * 用来保存dp过程中的运算的结果。
     * f[i][j]:表示前i件物品放入容量为j的背包得到的最大价值
     */
    int[][] f;

    /**
     * 保存空间优化过的dp运算结果
     * f[j]:表示前i件物品放入容量为j的背包得到的最大价值
     * 忽略了i
     */
    int[] f2;

    /**
     * init
     *
     * @param limitWeight
     */
    Bag(int limitWeight) {
        this.list = init();
        option = new boolean[list.size() + 1];
        this.limitWeight = limitWeight;
        f = new int[list.size() + 1][limitWeight + 1];
        f2 = new int[limitWeight + 1];
    }

    /**
     * TODO
     * <p>
     * 递归查找最优解  这个是有问题的！！！
     * <p>
     * 放入了n个物品（从第一个开始放，准备放第n个） 背包的总总量限制是limitWeight（0<=limitWeight<=Max) 返回value的最大值
     *
     * @param n 装入的背包的候选的n件物品，对应的物品是放在list.get(i) 0<=i<n
     * @param w 限制总量
     * @return 返回当前查找的到的最大值
     */
    //int recurse_find(int n, int w) {
    //    int t1 = 0, t2 = 0;
    //    if (n == 0 || w <= 0) {
    //        return 0;
    //    }
    //
    //    if (list.get(n).weight <= w) {
    //        t1 = recurse_find(n - 1, w);
    //        t2 = list.get(n).value +
    //            recurse_find(n - 1, w - list.get(n).weight);
    //        if (t1 > t2) {
    //            option[n] = false;
    //        } else {
    //            option[n] = true;
    //        }
    //        return (t1 > t2) ? t1 : t2;
    //    } else {
    //        option[n] = false;
    //        return recurse_find(n - 1, w);
    //    }
    //}

    /**
     * 0-1 背包问题
     * <p>
     * 此算法的时间复杂度为O(N*V)，空间复杂度也为O(N*V)。其中，N 表示物品个数，V 表示背包容量。 这里，时间复杂度不可以在优化了，但是空间复杂度可以继续优化到O(V)。
     * <p>
     * f(n,w) n件物品恰放入一个容量为w的背包可以获得的最大价值
     * <p>
     * 初始化:f数组全设置为0
     *
     * @param n
     * @param w
     */
    void find(int n, int w) {

        //从第一个物品开始尝试放入背包
        //枚举物品。i为0时无意义，故f[0][j] = 初始值0
        for (int i = 1; i <= n; i++) {
            //枚举背包容量
            for (int j = 0; j <= w; j++) {
                //！！j < weight[i]时，状态仅能来自于f[i - 1][j]
                //没有这一句的话，下一步f[i - 1][j - list.get(i).weight]
                // 这一个会一直是f[i-1][负数<0]，那就报java.lang.ArrayIndexOutOfBoundsException: -10 之类的错误
                //所以就导致j的遍历必须从 [list.get(i).weight，w] 开始，从而使f[][]中少计算了一部分的值。
                f[i][j] = f[i - 1][j];

                if (j >= list.get(i).weight) {
                    f[i][j] = Math.max(f[i - 1][j],
                        f[i - 1][j - list.get(i).weight] + list.get(i).value);
                }
            }
        }
        //最优值
        System.out.println(f[n][w]);

        //查找放入背包的物品
        int j = w;
        for (int i = n; i > 0; i--) {
            if (f[i][j] != f[i - 1][j]) {
                System.out.print("物品编号：" + i + " ");
                j -= list.get(i).weight;
            }
        }
        System.out.println();

    }

    /**
     * 0-1背包问题，优化空间使用
     *
     * @param n
     * @param w
     */
    void find_optimize_space(int n, int w) {

        for (int i = 1; i <= n; i++) {
            //一维数组，防止越界，另外就不需要像二维数组那样再进行初始赋值了,f[i][j] = f[i - 1][j];
            for (int j = w; j >= list.get(i).weight; j--) {
                if (j >= list.get(i).weight) {
                    f2[j] = Math.max(f2[j],
                        f2[j - list.get(i).weight] + list.get(i).value);
                }
            }
        }
        System.out.println(f2[w]);
    }

    /**
     * 0-1 完全背包问题 初始化是精髓
     *
     * @param n
     * @param w
     */
    void find_complete(int n, int w) {
        System.out.println();
        System.out.println("0-1 完全背包问题 f[][]:");

        //除了第一列f[n][0]，全部初始化为 -∞
        for (int i = 0; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                f[i][j] = Integer.MIN_VALUE;
            }
        }
        find(n, w);
    }

    /**
     * 0-1 完全背包问题 初始化是精髓
     *
     * @param n
     * @param w
     */
    void find_complete_v2(int n, int w) {
        System.out.println();
        System.out.println("0-1 完全背包问题 空间优化f[]:");

        //除了第一个元素f2[0]，全部初始化为 -∞
        f2[0] = 0;
        for (int i = 1; i <= w; i++) {
            f2[i] = Integer.MIN_VALUE;
        }
        find_optimize_space(n, w);
    }

    void output() {
        int sum = 0;
        for (int i = 0; i <= list.size(); i++) {
            if (option[i]) {
                sum += list.get(i).value;
                System.out.println("物品序号：" + i + "； 对应重量： " + list.get(i).weight
                    + "; 对应价值：" + list.get(i).value);
            }
        }
        System.out.println("最优总价值：" + sum);
    }

    ArrayList<Thing> init() {
        int w[] = {10, 8, 9, 12, 6};
        int v[] = {100, 90, 120, 120, 80};
        ArrayList<Thing> list = new ArrayList<>(6);
        list.add(null);
        for (int i = 0; i < 5; i++) {
            list.add(new Thing(w[i], v[i], 1));
        }
        return list;
    }

    public static void main(String[] args) {
        Bag bag = new Bag(20);
        //bag.recurse_find(5, 20);
        bag.find(5, 20);
        bag.find_optimize_space(5, 20);
        bag.find_complete(5, 20);
        bag.find_complete_v2(5, 20);
    }
}
