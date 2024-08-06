package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q461
 * @description TODO
 * @since 2024/7/31 15:39
 */
public class Q461 {
    public int hammingDistance(int x, int y) {
        int r = x ^ y;
        int ans = 0;
        while (r != 0) {
            if ((r & 1) == 1) {
                ans++;
            }
            r >>= 1;
        }
        return ans;
    }
}
