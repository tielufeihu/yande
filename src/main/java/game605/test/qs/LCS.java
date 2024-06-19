package game605.test.qs;

import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className LCS
 * @description 最长公共子序列
 * @since 2024/6/17 16:29
 */
public class LCS {

    public static void main(String[] args) {
        LCS lcs = new LCS();
        System.out.println(lcs.longestCommonSubsequence("abc", "abc"));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        char[] c1 = text1.toCharArray();
        char[] c2 = text2.toCharArray();
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        // 填充0
        for (int i = 0; i < text1.length(); i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i < text2.length(); i++) {
            dp[0][i] = 0;
        }

        for (int i = 1; i < text1.length()+1; i++) {
            for (int j = 1; j < text2.length()+1; j++) {
                // text1[i] == text2[j]  则dp[i][j] = dp[i-1][j-1] + 1
                if(c1[i-1] == c2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

}
