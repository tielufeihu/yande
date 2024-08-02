package game605.test.qs;

public class Q2320 {

    int[] cache = new int[1002];
    int MOD = (int) 1e9+7;

    // dp
    public int countHousePlacements(int n) {
        int[] dp = new int[n+2];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (dp[i-1] + dp[i-2])%MOD;
        }
        return (int) (((long)dp[n] * dp[n]) % MOD);
    }

    // 记忆化搜索dfs
    public int countHousePlacements2(int n) {
        if(n == 1) return 4;
        int num = dfs(n-1) + dfs(n-2);
        return (int) ((long) num * num % MOD);
    }

    private int dfs(int n){
        if(n == 0) return 1;
        if(n == 1) return 2;
        if(cache[n] != 0){
            return cache[n];
        }
        cache[n] = (dfs(n-1) + dfs(n-2)) % MOD;
        return cache[n];
    }

    public static void main(String[] args) {

    }



}
