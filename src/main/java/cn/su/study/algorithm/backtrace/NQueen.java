package cn.su.study.algorithm.backtrace;

/**
 * 八（N）皇后问题
 *
 * @author suyulong
 * @date 2018/5/28 20:10
 */
public class NQueen {
    /**
     * v[i] = j, 表示第i行，第j列是皇后
     */
    int[] v;

    /**
     * 棋盘行长、列长、皇后个数
     */
    int num;

    /**
     * 可行的方案个数
     */
    int sum;

    /**
     * 保存sum种的可行方案
     */
    int[][] result;

    NQueen(int num) {
        this.num = num;
        v = new int[num + 1];
        sum = 0;
        result = new int[100][num + 1];
    }

    /**
     * 待选皇后放到第t行v[t],和之前1～t-1行的是否冲突
     *
     * @param t
     * @return
     */
    private boolean isOK(int t) {
        //已经就在不同行了，所以只需要判断是否在不同列，已经是否在同一斜线上
        for (int i = 1; i < t; i++) {
            //用斜率判断是否在同一斜线上
            if (v[t] == v[i] || Math.abs(t - i) == Math.abs(v[t] - v[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 递归解决n皇后问题
     *
     * @param t 从第1行开始。
     */
    public void recurse_solution(int t) {
        if (t > num) {
            for (int i = 1; i <= num; i++) {
                result[sum][i] = v[i];
            }
            sum++;
        } else {
            for (int i = 1; i <= num; i++) {
                v[t] = i;
                if (isOK(t)) {
                    recurse_solution(t + 1);
                }
            }
        }
    }

    /**
     * 迭代求解n皇后问题
     */
    public void iterate_solution() {
        //从第一行开始，初始化
        int t = 1;
        v[t] = 0;

        //这一个很精髓，这个t<0就是退出循环的条件
        while (t > 0) {
            //t行从第1列开始尝试
            v[t] += 1;

            //尝试在棋盘的范围内（<=num）种找到符合条件的位置（isOK）
            while (!isOK(t) && v[t] <= num) {
                v[t] += 1;
            }

            if (v[t] <= num) {
                if (t == num) {
                    //找到了
                    for (int i = 1; i <= num; i++) {
                        result[sum][i] = v[i];
                    }
                    sum++;
                } else {
                    //局部找到了，剩下的继续找
                    //下一行，初始化为0
                    t++;
                    v[t] = 0;
                }
            } else {
                //t行里没有一个合适的，回溯至上一行，重新尝试
                t--;
            }
        }
    }

    public void output() {
        System.out.println("解法有：" + sum + "种");
        for (int i = 0; i < sum; i++) {
            for (int j = 1; j <= num; j++) {
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
    }

    public void reset() {
        sum = 0;
        for (int i = 0; i < result.length; i++) {
            for (int j = 1; j <= num; j++) {
                result[i][j] = 0;
            }
        }
    }

    public static void main(String[] args) {
        NQueen nQueen = new NQueen(8);
        nQueen.recurse_solution(1);
        nQueen.output();

        nQueen.reset();
        nQueen.iterate_solution();
        nQueen.output();
    }

}
