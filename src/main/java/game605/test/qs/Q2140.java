package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;

/**
 * 解决智力问题
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/20 9:25
 **/
public class Q2140 {

    public long mostPoints(int[][] questions) {
        int len = questions.length;
        int[] dp = new int[len];
        // dp[i] 表示包括第i个问题在内的，最大分数
        // 初始状态自然是dp[0] = q[0][0]
        dp[0] = questions[0][0];
        int maxRet = 0;
        for (int i = 1; i < len; i++) {
            questions[i][1] += i;
            dp[i] = maxFromArr(dp,questions,i) + questions[i][0];
            if (dp[i]>maxRet)
                maxRet = dp[i];
        }
        return maxRet;
    }

    public int maxFromArr(int[] dp, int[][] questions, int end){
        int ret = 0;
        for (int i = 0; i < end; i++) {
            if(dp[i]>ret && questions[i][1] < end){
                ret = dp[i];
            }
        }
        return ret;
    }

    @Test
    public void t1(){
        // [3,2],[4,3],[4,4],[2,5]
        System.out.println(mostPoints(new int[][]{{1,1},{2,2},{3,3},{4,4},{5,5}}));
    }

}
