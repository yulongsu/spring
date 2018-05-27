package cn.su.study.algorithm.backtrace;

import java.util.ArrayList;

/**
 * 当所给问题是从n个元素的集合S中找出S满足的某种性质的子集时，相应的解空间树称为子集树。
 * <p>
 * 例如，0-1背包问题，要求在n个物品的集合S中，选出几个物品， 使物品在背包容积C的限制下，总价值最大（即集合S的满足条件<容积C下价值最大>的某个子集）。
 * <p>
 * 另：子集树是从集合S中选出符合限定条件的子集，故每个集合元素只需判断是否（0,1）入选，因此解空间应是一颗满二叉树
 *
 * @author suyulong
 * @date 2018/5/27 11:46
 */
public class Subset {
    public static void main(String[] args) {
        ArrayList<Node> arrayList = init(4);
        //用回溯法去遍历子集
        subset_backtrace(0, arrayList.size() - 1, arrayList);

        //用二进制的方法，取巧
        subset_binary(arrayList);
    }

    /**
     * 回溯法遍历出集合的所有子集，没有其他的限定条件
     *
     * @param layer 当前遍历层数
     * @param max   最大层数
     * @param c     结果集
     */
    private static void subset_backtrace(int layer, int max, ArrayList<Node> c) {
        ////需要判断每一个元素是否加入子集，所以必须达到叶节点，才可以输出
        if (layer > max) {
            output(c);
        } else {
            //子集树是从集合S中，选出符合限定条件的子集，故每个元素判断是（1）否（0）选入即可（二叉树），
            //因此i定义域为{0,1}
            for (int i = 0; i < 2; i++) {
                //是否加入点集，1表示是，0表示否
                c.get(layer).setChoose(i);
                //constraint(t)和bound(t)分别是约束条件和限定函数
                //对于求子集的问题，没有约束和限定条件的
                //if(constraint(t)&&bound(t))
                if (constraint(c, layer)) {
                    subset_backtrace(layer + 1, max, c);
                }
            }
        }
    }

    /**
     * 例如求4个元素 3 2 1 0 的子集。 那么用二进制的1代表每一位是否选中。 十进制 二进制 0       0000  代表空集 1       0001  代表{0} 2       0010  代表{1} 3
     * 0011  代表{0,1} 4       0100  代表{2} ... 15      1110  代表{3,2,1} 16      1111  代表{3,2,1,0}
     * <p>
     * 如果n很大的话可以用字符串模拟二进制
     *
     * @param c
     */
    private static void subset_binary(ArrayList<Node> c) {
        //1左移n位等价于2^n-1.因为子集个数2^n-1
        for (int i = 0; i < (1 << c.size()); i++) {

            //依次遍历list c的每一个元素，j代表集合中元素的下标（index）
            for (int j = 0; j < c.size(); j++) {
                // i 就是单纯的的排列组合的二进制的  类似 0001  0011 表示输出集合第一个元素，输出集合第1/2个元素
                // 1<<j 就是去和i去&运算，看哪一位是1，就把这个j对应的集合元素输出
                if ((i & 1 << j) != 0) {
                    System.out.print(c.get(j).getData());
                }
            }
            System.out.println();
        }
    }

    private static boolean constraint(ArrayList<Node> c, int layer) {
        return true;
    }

    /**
     * @param c 结果集
     */
    private static void output(ArrayList<Node> c) {
        c.forEach(e -> {
            if (e.getChoose() == 1) {
                System.out.print(e.getData());
            }
        });
        System.out.println();
    }

    /**
     * 初始化num个元素的集合 Node的data元素默认位Integer
     *
     * @param num
     * @return
     */
    private static ArrayList<Node> init(int num) {
        ArrayList<Node> arrayList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            arrayList.add(new Node<>(i + 1));
        }
        return arrayList;
    }

}

/**
 * 结点数据
 *
 * @param <E>
 */
class Node<E> {
    /**
     * 原数据
     */
    private E data;
    /**
     * 是否满足子集的选择条件 1：满足 0：不满足
     */
    private int choose;

    public Node(E data) {
        this.data = data;
        this.choose = 0;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public int getChoose() {
        return choose;
    }

    public void setChoose(int choose) {
        this.choose = choose;
    }
}

enum Strategy {
    None,
    NumLimit
}