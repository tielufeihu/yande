package game605.test.qs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 汇总区间
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/20 16:17
 **/
public class Q228 {

    public List<String> summaryRanges(int[] nums) {
        int len = nums.length;
        List<String> ret = new ArrayList<>();
        if(len == 0)
            return ret;
        if(len == 1){
            ret.add(String.valueOf(nums[0]));
            return ret;
        }
        int prev = nums[0];
        int begin = prev;
        for (int i = 1; i < len; i++) {
            if(prev + 1 == nums[i]){
                // 如果是连续的
                prev++;
            }else {
                // 结算之前积攒的区间
                if(begin!=prev)
                    ret.add(begin +"->"+prev);
                else
                    ret.add(String.valueOf(prev));
                begin = nums[i];
                prev = nums[i];
            }
        }
        // 清算剩余的
        if(begin!=prev)
            ret.add(begin +"->"+prev);
        else
            ret.add(String.valueOf(prev));
        return ret;
    }

    @Test
    public void t1(){
        System.out.println(summaryRanges(new int[]{0,2,3,4,6,8,9}));
    }

}
