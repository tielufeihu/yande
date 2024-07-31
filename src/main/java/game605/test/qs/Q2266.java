package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2266
 * @description TODO
 * @since 2024/7/30 16:22
 */
public class Q2266 {

    int[][] map = {{},
            {},
            {0,1,2},
            {4,5,6},
            {7,8,9},
            {10,11,12},
            {13,14,15},
            {16,17,18,1},
            {19,20,21},
            {22,23,24,25}};

    public int countTexts(String pressedKeys) {
        // 首先要分组吧
        char[] chars = pressedKeys.toCharArray();
        int r = 0, l = 1;
        int ret = 1;
        for (int i = 1; i < chars.length; i++,l++) {
            if(chars[i] != chars[i-1]){
                // 结算
                int count = l-r;
                // 判断 count 个数能组成多少种
                ret = (int)((long)ret  * computeCount(chars[r]-'0', count) % 1000000007);
                r = l = i;
            }
        }
        ret = (int) ((long)ret * computeCount(chars[r]-'0', l-r) % 1000000007);
        return ret;
    }

    public int computeCount(int num, int count){
        int[] dp = new int[count+1];
        int kinds = map[num].length;
        // 初始化dp
        for (int i = 1; i <= kinds && i < count + 1; i++) {
            dp[i] = 1;
            for (int j = 1; j <= kinds; j++) {
                if(i-j > 0){
                    dp[i] = dp[i] + dp[i-j];
                }
            }
        }
        for (int i = kinds + 1; i < count + 1; i++) {
            int temp = 0;
            for (int j = 1; j <= kinds; j++) {
                temp = (temp + dp[i-j]) % 1000000007;
            }
            dp[i] = temp;
        }
        return dp[count];
    }

    public static void main(String[] args) {
        Q2266 q2266 = new Q2266();
        System.out.println(q2266.countTexts("222222222222222222222222222222222222"));
    }

}
