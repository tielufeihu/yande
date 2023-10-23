package game605.test.qs;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Arrays;

/**
 * 做菜顺序
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/22 16:20
 **/


public class Q1402 {

    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        // 从后往前， 系数
        int factor = 0;
        int len = satisfaction.length;
        boolean[] selected = new boolean[len];
        Arrays.fill(selected, false);
        for (int i = len-1; i >= 0; i--) {
            if(satisfaction[i]>=0){
                // 大于等于0的直接选取
                factor+=satisfaction[i];
                selected[i] = true;
            }else {
                // 小于0的计算后确定是否选取
                int curr = satisfaction[i];
                if(-curr < factor){
                    selected[i] = true;
                    factor += curr;
                }
            }
        }
        int k = 1;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if(selected[i]){
                sum += satisfaction[i]*k;
                k++;
            }
        }
        return sum;
    }

    @Test
    public void t1(){
        System.out.println(maxSatisfaction(new int[]{-1,-4,-5}));
    }

}
