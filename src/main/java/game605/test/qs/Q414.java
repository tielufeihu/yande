package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;

/**
 * 第三大的数
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/20 16:58
 **/
public class Q414 {

    // stream 流两行
    public int thirdMax(int[] nums) {
        Arrays.sort(nums);
        int[] newnum = Arrays.stream(nums).distinct().sorted().toArray();
        return newnum.length<3?newnum[newnum.length-1]:newnum[newnum.length-3];
    }

    // 通过维护三个变量实现o(n)时间复杂度
    public int thirdMax2(int[] nums) {
        int firstMaxNum = Integer.MIN_VALUE;
        int secondMaxNum = Integer.MIN_VALUE;
        int thirdMaxNum = Integer.MIN_VALUE;
        for (int num : nums) {
            if(num == firstMaxNum || num == secondMaxNum || num == thirdMaxNum)
                continue;
            if(num>firstMaxNum){
                thirdMaxNum = secondMaxNum;
                secondMaxNum = firstMaxNum;
                firstMaxNum = num;
            }else if(num>secondMaxNum){
                thirdMaxNum = secondMaxNum;
                secondMaxNum = num;
            }else if(num>thirdMaxNum){
                thirdMaxNum = num;
            }
        }
        if(thirdMaxNum!=Integer.MIN_VALUE && thirdMaxNum!=secondMaxNum && secondMaxNum!=firstMaxNum)
            return thirdMaxNum;
        return firstMaxNum;
    }

    @Test
    public void t1(){
        System.out.println(thirdMax2(new int[]{1, 2}));
    }

}
