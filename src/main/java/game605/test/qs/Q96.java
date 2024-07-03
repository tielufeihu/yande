package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q96
 * @description TODO
 * @since 2024/7/3 9:37
 */
public class Q96 {

    /**
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;

        for (int i = 2; i <= n; ++i) {
            for (int j = 1; j <= i; ++j) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

}
