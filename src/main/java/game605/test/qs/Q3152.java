package game605.test.qs;

import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3152
 * @description TODO
 * @since 2024/8/14 10:41
 */
public class Q3152 {

    // 暴力超时
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        boolean[] ret = new boolean[queries.length];
        Arrays.fill(ret, true);
        int idx = 0;
        for (int[] query : queries) {
            int prev = -1;
            for (int i = query[0]; i <= query[1]; i++) {
                if(prev == -1){
                    prev = nums[i] % 2;
                }else {
                    if(prev != nums[i] % 2){
                        // 奇偶性不同
                        prev = nums[i] % 2;
                    }else {
                        ret[idx] = false;
                        break;
                    }
                }
            }
            idx++;
        }
        return ret;
    }

    // 前缀和(灵神的解法)
    public boolean[] isArraySpecial2(int[] nums, int[][] queries) {
        int[] preSum = new int[nums.length];
        boolean[] ret = new boolean[queries.length];
        for (int i = 1; i < nums.length; i++) {
            preSum[i] = preSum[i-1] + (nums[i-1] % 2 == nums[i] % 2 ? 1 : 0);
        }
        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            ret[i] = preSum[q[0]] == preSum[q[1]];
        }
        return ret;
    }


    public static void main(String[] args) {
        Q3152 q3152 = new Q3152();
        System.out.println(Arrays.toString(q3152.isArraySpecial2(new int[]{3,4,1,2,6}, new int[][]{{0, 4}, {0, 3}, {1, 2}})));
    }

}
