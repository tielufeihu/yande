package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;

/**
 * 最大回文子序列
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/16 0:27
 **/
public class Q516 {

    // 思路还是没错的，但是不够清晰
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        // 初始状态， 所有单个字符 都是长度为1的回文数
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                dp[i][j] = 1;
            }
        }
        char[] charArray = s.toCharArray();
        return 0;
    }

    @Test
    public void t1(){
        System.out.println(longestPalindromeSubseq("aa"));
    }

}
