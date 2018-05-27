package cn.su.study.algorithm;

/**
 * 名人问题
 *
 * @author suyulong
 * @date 2018/5/28 00:13
 */
public class famousPerson {
    public static void main(String[] args) {

    }

    /**
     * 名人问题
     *
     * 1)取第一个做为candidate
     *
     * 2) 遍历剩余的人，如果 candidate knows  i, 排除当前candidate，换成i；否则排除i, candidate不变
     *
     * 3）不变式：已经考察过的人，要么被排除是名人的可能性，要么在candidate里
     *
     * 4）循环结束时，只有剩下的candidate没有确定，单独验证之。
     *
     * @param n
     * @return
     */
    private static int celebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            if (know(candidate, i)) {
                candidate = i;
            }
        }

        //第一遍筛出来的candidate，还不能确认是否是名人，
        //循环结束的条件：有可能是i已经遍历完了；或者只能认为candidate不认识其他人
        //但不确认其他人是否认识他，所以需要再验证。
        for (int i = 0; i < n; i++) {
            if (i != candidate && (know(candidate, i) || !know(i, candidate))) {
                return -1;
            }
        }
        return candidate;

    }

    /**
     * a 是否认识 b
     *
     * @param a
     * @param b
     * @return
     */
    private static boolean know(int a, int b) {
        return false;
    }
}
