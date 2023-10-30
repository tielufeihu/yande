package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 公平的糖果交换
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/27 19:48
 **/
public class Q888 {

    // 两个哈希表,变相两数之和
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        Map<Integer, Integer> aliceMap = new HashMap<>();
        Map<Integer, Integer> bobMap = new HashMap<>();
        int aliceSum = 0;
        int bobSum = 0;
        int diff = Integer.MIN_VALUE;
        // 分别初始化两个表
        for (int s : aliceSizes) {
            aliceMap.put(s,aliceMap.getOrDefault(s,0)+1);
            aliceSum+=s;
        }
        for (int s : bobSizes) {
            bobMap.put(s,bobMap.getOrDefault(s,0)+1);
            bobSum+=s;
        }
        diff = (aliceSum-bobSum)/2;
        int[] ret = new int[2];
        for (Integer s : aliceMap.keySet()) {

            if(bobMap.containsKey(s-diff)){
                ret[0] = s;
                ret[1] = s-diff;
                break;
            }
        }
        return ret;
    }

    @Test
    public void t1(){
        //aliceSizes =
        //[1,1]
        //bobSizes =
        //[2,2]
        System.out.println(Arrays.toString(fairCandySwap(new int[]{1, 1}, new int[]{2, 2})));
    }

}
