package game605.test.qs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3115
 * @description 质数的最大距离
 * @since 2024/7/2 10:43
 */
public class Q3115 {

    // 获取素数列表
    List<Integer> ssList = Arrays.stream(new int[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97})
            .boxed().collect(Collectors.toList());


    // 1 <= nums[i] <= 100
    public int maximumPrimeDifference(int[] nums) {
        int firstInx = -1;
        int lastInx = -1;
        for (int i = 0; i < nums.length; i++){
            if(ssList.contains(nums[i])){
                if(firstInx == -1){
                    firstInx = i;
                }else {
                    lastInx = i;
                }
            }
        }
        if(firstInx!=-1 && lastInx!=-1){
            return lastInx - firstInx;
        }else {
            return 0;
        }
    }

}
