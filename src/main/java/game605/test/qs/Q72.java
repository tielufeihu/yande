package game605.test.qs;

import org.junit.Test;

/**
 * 编辑距离
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/19 16:38
 **/
public class Q72 {

    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        // 特判
        if (len1 == 0 && len2==0)
            return 0;
        if (len1 == 0)
            return len2;
        if (len2 == 0)
            return len1;
        char[] word1Chars = word1.toCharArray();
        char[] word2Chars = word2.toCharArray();
        // dp[i][j] 表示 word1前i个到word2前j个的操作次数子问题
        int dp[][] = new int[len1][len2];
        // 定义初始状态
        dp[0][0] = word1Chars[0] == word2Chars[0]?0:1;
        // （ 问题就出在这里！ ）
        for (int i = 1; i < len1; i++) {
            dp[i][0] = dp[i-1][0]+1;
        }
        for (int i = 1; i < len2; i++) {
            dp[0][i] = dp[0][i-1]+1;
        }
        // 开始dp
        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                int tempMin = Integer.MAX_VALUE;
                if(word1Chars[i] == word2Chars[j]){
                    // 这个字符相等怎么说 TODO
                    tempMin = Math.min(dp[i-1][j], dp[i][j-1])+1;
                    tempMin = Math.min(tempMin, dp[i-1][j-1]);
                }else {
                    // 这个字符不相等
                    tempMin = Math.min(dp[i-1][j], dp[i][j-1])+1;
                    tempMin = Math.min(tempMin, dp[i-1][j-1]+1);
                }
                dp[i][j] = tempMin;
            }
        }
        return dp[len1-1][len2-1];
    }

    // 官解
    public int minDistance2(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }

    @Test
    public void t1(){
        System.out.println(minDistance2("sea","eat"));
    }

}
