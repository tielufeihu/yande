package game605.test.qs;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 每日一题
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/19 10:51
 **/
public class Q1726 {

    // 暴力枚举四重for显然超时
    public int tupleSameProduct(int[] nums) {
        int len = nums.length;
        int ret = 0;
        // 记录两两乘积
        List<Integer> tewMut = new ArrayList<>();
        for (int i = 0; i < len-1; i++) {
            for (int j = i+1; j < len; j++) {
                tewMut.add(nums[i]*nums[j]);
            }
        }
        // 判断上面list中有多少重复元素
        Map<Integer, Integer> countMap = new HashMap<>();
        for (Integer num : tewMut) {
            countMap.put(num, countMap.getOrDefault(num,0)+1);
        }
        for (Integer count : countMap.values()) {
            if(count>1){
                // 全排列 就是阶乘
                // ret += (jc(count)*4);  阶乘错了
                ret += ret*(ret-1)*4;
            }
        }
        return ret;
    }


    public int jc(int k){
        int ret = 1;
        for (int i = 2; i <= k; i++) {
            ret *= i;
        }
        return ret;
    }

    @Test
    public void t1(){
        System.out.println(tupleSameProduct(new int[]{1,2,4,5,10}));
    }

}
