package game605.test.qs;

import java.util.HashSet;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q202
 * @description TODO
 * @since 2024/7/29 17:23
 */
public class Q202 {

    // 快乐数，用hashset检测重复
    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        set.add(n);
        while (n!=1){
            n = getSum(n);
            if (set.contains(n)){
                return false;
            }
            set.add(n);
        }
        return true;
    }

    public int getSum(int n) {
        int sum = 0;
        while (n!=0){
            sum += (n%10)*(n%10);
            n /= 10;
        }
        return sum;
    }



}
