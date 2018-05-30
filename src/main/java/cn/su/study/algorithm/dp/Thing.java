package cn.su.study.algorithm.dp;

/**
 * 要放入背包的物品
 *
 * @author suyulong
 * @date 2018/5/28 23:16
 */
public class Thing {
    /**
     * 重量
     */
    int weight;
    /**
     * 价值
     */
    int value;
    /**
     * 个数，用来表示可以放到背包里几次，0-1，完全，多重
     */
    int count;

    Thing(int weight, int value, int count) {
        this.weight = weight;
        this.value = value;
        this.count = count;
    }
}
