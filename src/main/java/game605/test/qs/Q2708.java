package game605.test.qs;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2708
 * @description TODO
    1 <= nums.length <= 13
    -9 <= nums[i] <= 9
 * @since 2024/9/3 9:36
 */
public class Q2708 {

    public long maxStrength(int[] nums) {
        if(nums.length == 1){
            return nums[0];
        }
        long ret = 1;
        int version = 0;
        List<Integer> negativeList = new ArrayList<>();
        for (int num : nums) {
            if(num>0){
                ret *= num;
                version++;
            }else if(num<0){
                negativeList.add(-num);
            }
        }
        if(negativeList.size()>1){
            // 降序排
            negativeList.sort((o1, o2) -> o2-o1);
            int count = negativeList.size()%2==1?negativeList.size()-1:negativeList.size();
            for (int i = 0; i < count; i++) {
                ret *= negativeList.get(i);
                version++;
            }
        }
        return version==0?0:ret;
    }

    public static void main(String[] args) {
        Q2708 q2708 = new Q2708();
        System.out.println(q2708.maxStrength(new int[]{-4,-5,-4}));
    }

}
