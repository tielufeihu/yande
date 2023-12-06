package game605.test.qs;


import java.util.ArrayList;
import java.util.List;

/**
 * 11.13 每日一题
 * 区域和检索 - 数组可修改
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/11/13 23:52
 **/
public class Q307 {

    // 我的前缀和解，只能应对不变数组求区间，不能应对可变数组

    // 用一个List存储数据
    private List<Integer> numsList = new ArrayList<>();
    // 维护一个从前往后做sum操作的数组
    private List<Integer> leftSumDP = new ArrayList<>();
    public Q307(int[] nums) {
        int tempSum = 0;
        // 初始化和维护积数组
        for (int i = 0; i < nums.length; i++) {
            numsList.add(nums[i]);
            tempSum += nums[i];
            leftSumDP.add(tempSum);
        }
    }

    public void update(int index, int val) {
        // 提示中 index 在 range 内
        // 更新数组
        int diff = numsList.get(index) - val;
        numsList.set(index, val);  // 设置值
        for (int i = index; i < numsList.size(); i++) {
            leftSumDP.set(i, leftSumDP.get(i)-diff);
        }
    }

    public int sumRange(int left, int right) {
        // 提示中 left和right 在 range 内
        return leftSumDP.get(right) - (left-1>=0?leftSumDP.get(left-1):0);
    }


    public static void main(String[] args) {
        Q307 q = new Q307(new int[]{1,3,5});
        System.out.println(q.sumRange(0,2));
        q.update(1,2);
        System.out.println(q.sumRange(0,2));
    }

}
