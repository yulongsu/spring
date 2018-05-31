package cn.su.study.algorithm.dp;

/**
 * 切绳子问题
 *
 * 题目一：给你一根长度为n的绳子，请把绳子剪成m段 (m和n都是整数，n>1并且m>1)每段绳子的长度记为k[0],k[1],…,k[m].
 * 请问k[0]k[1]…*k[m]可能的最大乘积是多少？
 * 例如，当绳子的长度为8时，我们把它剪成长度分别为2,3,3的三段，此时得到的最大乘积是18.
 *
 * @author suyulong
 * @date 2018/5/31 14:51
 */
public class cutRope {
    /**
     * 绳子长度
     */
    int n;

    /**
     * 用来保存每一个子问题的结果
     */
    int r[];

    cutRope(int n) {
        this.n = n;
        r = new int[n + 1];
        r[0] = 0;
        r[1] = 1;
        r[2] = 2;
        r[3] = 3;
    }

    /**
     * 返回长度为n(n为正整数)的绳子切割后乘积的最大值
     *
     * @return
     */
    int maxCut() {
        /**
         * 这个很关键，如果绳子长度是1，2，3的时候，因为必须要切一刀，所以长度都固定了
         * 比如1，是无法切的，所以是0
         * 2，只能切成1，1，所以是1
         * 3，只能切成1，2，所以是2
         *
         * 但要和初始化r[0,1,2,3]要区别开
         * 因为假如是绳子长度>4,切割成了2，那r[2]的最大长度就是2，就没有必要再切一刀了
         */
        if(n <= 1)
            return 0;
        if(n == 2)
            return 1;
        if(n == 3)
            return 2;

        for (int i = 4; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 1; j <= i / 2; j++) {
                int mul = r[j] * r[i - j];
                if (max < mul) {
                    max = mul;
                }
            }
            r[i] = max;
        }
        return r[n];
    }

    public static void main(String[] args) {
        cutRope cr = new cutRope(8);
        System.out.println(cr.maxCut());
    }

}
