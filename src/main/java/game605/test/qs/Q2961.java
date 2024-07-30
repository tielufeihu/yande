package game605.test.qs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2961
 * @description 双模幂运算
 * @since 2024/7/30 10:12
 */
public class Q2961 {

    public List<Integer> getGoodIndices(int[][] variables, int target) {
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < variables.length; i++) {
            int[] variable = variables[i];
            if(compute(variable[0],variable[1],variable[2],variable[3],target)){
                ret.add(i);
            }
        }
        return ret;
    }

    public boolean compute(int a, int b, int c, int d, int target) {
        if(d <= target) {
            return false;
        }
        int result = powMod(a,b,10);
        result = powMod(result,c,d);
        return result == target;
    }

    // 暴力循环计算
    public int powMod(int num, int times, int mod) {
        int multiplier = num;
        for (int i = 1; i < times; i++) {
            num %= mod;
            num *= multiplier;
        }
        return num % mod;
    }

    /**
     * 快速幂解法
     * 本题 mod 很小，即使平方也不会超过 int 范围，所以不需要用 long
     * @param num 数
     * @param times 幂
     * @param mod 模
     * @return
     */
    public int powMod2(int num, int times, int mod) {
        int res = 1;
        while (times > 0) {
            if (times % 2 > 0) {
                res = res * num % mod;
            }
            num = num * num % mod;
            times /= 2;
        }
        return res;
    }


    public static void main(String[] args) {
        Q2961 q2961 = new Q2961();
        int[][] variables = {{2,3,3,10},{3,3,3,1},{6,1,1,4}};
        List<Integer> goodIndices = q2961.getGoodIndices(variables, 2);
        System.out.println(goodIndices);
    }

}
