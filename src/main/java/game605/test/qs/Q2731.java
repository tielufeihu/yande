package game605.test.qs;

import java.util.Arrays;

/**
 * 移动机器人
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/10 16:10
 **/
public class Q2731 {

    // 感觉只能模拟（先试试）
    public int sumDistance(int[] nums, String s, int d) {
        Arrays.sort(nums); // 排序后能够始终保持有序
        int len = nums.length;
        int[] changes = new int[len];
        // 初始化 changes 数组
        for (int i = 0; i < len; i++) {
            if(s.charAt(i) == 'R')
                changes[i] = 1;
            else
                changes[i] = -1;
        }

        // 开始模拟
        for (int di = 0; di < d; di++) {
            for (int ni = 0; ni < len; ni++) {
                nums[ni] += changes[ni];
                // 如果迎面撞上前面的机器人
                if(ni+1<len && nums[ni] == nums[ni+1] && changes[ni] == 1 && changes[ni+1] == -1){
                    nums[ni] -= 2;
                }
            }
        }

        return 0;

    }

}
