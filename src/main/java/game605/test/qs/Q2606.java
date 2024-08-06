package game605.test.qs;

public class Q2606 {

    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int[] valMap = new int[26];
        for (int i = 0; i < valMap.length; i++) {
            valMap[i] = i+1;
        }
        for (int i = 0; i < chars.length(); i++) {
            char c = chars.charAt(i);
            valMap[c-'a'] = vals[i];
        }

        if(s.length() == 1){
            return Math.max(valMap[s.charAt(0)-'a'],0);
        }
        // 以为 i 为结尾的最大开销
        char[] sArr = s.toCharArray();
        int[] dp = new int[sArr.length];
        dp[0] = valMap[sArr[0] - 'a'];
        int ret = Math.max(0,dp[0]);
        for (int i = 1; i < sArr.length; i++) {
            int val = valMap[sArr[i] - 'a'];
            dp[i] = Math.max(val, dp[i-1] + val);
            ret = Math.max(dp[i], ret);
        }
        return ret;
    }

}
