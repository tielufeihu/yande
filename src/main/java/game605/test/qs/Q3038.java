package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3038
 * @description TODO
 * @since 2024/6/7 15:51
 */
public class Q3038 {

    public int maxOperations(int[] nums) {
        int point = nums[0] + nums[1];
        int ret = 1;
        for (int i = 2; i < nums.length-1; i+=2) {
            if(point == nums[i] + nums[i+1]){
                ret++;
            }else{
                break;
            }
        }
        return ret;
    }

}
