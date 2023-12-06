package game605.test.qs;

import java.util.ArrayList;
import java.util.List;

/**
 * 将整数减少到0的最少操作数
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/11/5 23:10
 **/
public class Q2571 {

    public int minOperations(int n) {
        // 1 <= n <= 10 5
        // 实现记录下 10五次方以内
        List<Integer> power2s = new ArrayList<>();
        int len = power2s.size();
        int temp = 1;
        while (temp<100000){
            power2s.add(temp);
            temp *= 2;
        }
        power2s.add(temp);

        int curr = n;
        int ret = 0;
        while (curr!=0){
            // 找到作差最接近0的那个数
            // 二分
            int l = 0;
            int r = len-1;
            while (l <= r) { // 当 left==right，区间 [left, right] 依然有效，所以用 <=
                int mid = l + ((r - l) / 2);
                if (power2s.get(mid)-curr > 0) {
                    r = mid - 1; // target 在左区间，所以更新为 [left, mid - 1]
                } else if (power2s.get(mid)-curr < 0) {
                    l = mid + 1; // target 在右区间，所以更新为 [mid + 1, right]
                } else {
                    return ret+1;
                }
            }
        }
        return ret;
    }

}
