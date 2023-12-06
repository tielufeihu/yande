package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 数位和相等数对的最大和
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/11/18 13:13
 **/
public class Q2342 {

    // 完全理解错题意了
    public int maximumSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        // 从右边往左滑动窗口
        int right = len-1;
        int left = right-1;
        int currNumLen = computeLen(nums[right]);
        while (left>=0){
            int tempNum = nums[left];
            int tempLen = computeLen(nums[left]);
            if(currNumLen != tempLen){
                // 右指针移动到左指针位置
                right = left;
                left -= 1;
                currNumLen = computeLen(nums[right]);
                continue;
            }
            // 遍历窗口内的元素
            for (int i = right; i > left; i--) {
                int tempSum = computeSum(tempNum,nums[i]);
                if(tempSum!=-1) return tempSum;
            }
            left -= 1;  // 窗口左移动
        }
        return -1;
    }

    private int computeSum(int a,int b){
        Set<Integer> numSet = new HashSet<>();
        int numTemp = a;
        while (numTemp!=0){
            int bitT = numTemp%10;
            if(numSet.contains(bitT)) return -1;
            numSet.add(bitT);
            numTemp/=10;
        }
        numTemp = b;
        while (numTemp!=0){
            int bitT = numTemp%10;
            if(numSet.contains(bitT)) return -1;
            numSet.add(bitT);
            numTemp/=10;
        }
        return a+b;
    }

    private int computeLen(int n){
        int ret = 0;
        while (n!=0){
            ret++;
            n/=10;
        }
        return ret;
    }

    @Test
    public void t1(){
        /**
         * input: nums = [18,43,36,13,7]
         * except: 54
         */
        System.out.println(maximumSum(new int[]{18,43,36,13,7}));
    }

}
