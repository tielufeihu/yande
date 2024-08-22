package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1266
 * @description TODO
 * @since 2024/8/21 17:32
 */
public class Q1266 {

    // 题目要求逐个访问
    public int minTimeToVisitAllPoints(int[][] points) {
        int res = 0;
        for (int i = 0; i < points.length - 1; i++) {
            res += Math.max(Math.abs(points[i][0] - points[i + 1][0]),
                    Math.abs(points[i][1] - points[i + 1][1]));
        }
        return res;
    }

}
