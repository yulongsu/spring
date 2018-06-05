package cn.su.study.algorithm.backtrace;

import java.security.cert.CertPathValidatorSpi;

/**
 * 旅行售货员的问题
 *
 * 这个实际是所有结点的全排列问题，n个结点就有n层，从根结点到每个结点都有一条路径，求路径权值和最小
 *
 * @author suyulong
 * @date 2018/5/28 13:38
 */
public class TravelSaleMan {
    /**
     * 正权图的顶点数
     */
    public int n;

    /**
     * 存储有向图
     */
    public double[][] graph;

    /**
     * 路径结点的顺序，比如 0，1，2，3...
     */
    public int[] x;

    /**
     * 当前路径加权值的和
     */
    public double currentVal;

    /**
     * 最优路径
     */
    public int[] bestX;

    /**
     * 最优路径加权值的和
     */
    public double bestVal;

    public TravelSaleMan(double[][] graph, int n) {
        this.n = n;
        x = new int[n];
        bestX = new int[n];

        //初始化路径顺序，
        for (int i = 0; i < n; i++) {
            x[i] = i;
        }
        bestVal = Double.MAX_VALUE;
        currentVal = 0;
        this.graph = graph;
    }

    private void swap(int[] x, int i, int j) {
        int tmp = x[i];
        x[i] = x[j];
        x[j] = tmp;
    }

    /**
     * @param layer 开始遍历的层数（层数是0,1..n-1），默认从一层开始，最大到n-1，每个结点都遍历一遍
     */
    public void backtrace(int layer) {
        if (layer == n - 1) {
            //条件：结点可达，最后一个结点到结点0也可达，路径的权值和比目前的最优值要好
            if (graph[x[n - 2]][x[n - 1]] != -1 && graph[x[n - 1]][0] != -1
                && (bestVal == Double.MAX_VALUE
                || currentVal + graph[x[n - 2]][x[n - 1]] + graph[x[n - 1]][x[0]] < bestVal)) {
                for (int i = 0; i < n; i++) {
                    bestX[i] = x[i];
                }
                bestVal = currentVal + graph[x[n - 2]][x[n - 1]] + graph[x[n - 1]][x[0]];
            }
        } else {
            //这里面有很隐藏的剪枝操作，x[]候选结点是从layer到n-1，没有经过0到layer,因为这一部分在之前的遍历中已经完成了
            for (int i = layer; i < n; i++) {
                if (graph[x[layer - 1]][x[i]] != -1 && (bestVal == Double.MAX_VALUE
                    || currentVal + graph[x[layer - 1]][x[i]] + graph[x[layer - 1]][x[i]] < bestVal)) {
                    //可以理解为层次遍历的向右扩展：x[],结点数组中，将第layer层的结点依次遍历，看结果是否最小
                    swap(x, layer, i);
                    currentVal += graph[x[layer - 1]][x[layer]];

                    backtrace(layer + 1);

                    //当前层全部遍历完之后再回溯至初始状态，准备进入下一层开始下一次的层次遍历
                    currentVal -= graph[x[layer - 1]][x[layer]];
                    swap(x, i, layer);
                }
            }
        }
    }

    public static void main(String[] args) {
        //结点数是4，结点号分别是0，1，2，3
        final int n = 4;
        double[][] graph = {{-1, 30, 6, 4}, {30, -1, 5, 10}, {6, 5, -1, 20}, {4, 10, 20, -1}};

        TravelSaleMan travelSaleMan = new TravelSaleMan(graph, n);
        //默认是从结点0开始，所以每次遍历是从第一层开始，也就是第二个结点开始。
        travelSaleMan.backtrace(1);

        System.out.println("最短回路长：" + travelSaleMan.bestVal);
        System.out.print("最短回路为：");
        for (int i = 0; i < n; i++) {
            System.out.print(travelSaleMan.bestX[i] + " ");
        }

    }
}
