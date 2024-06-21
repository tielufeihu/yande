package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2210
 * @description TODO
 * @since 2024/6/20 14:03
 */
public class Q2210 {

    public static void main(String[] args) {
        Q2210 q = new Q2210();
        System.out.println(q.countHillValley(new int[]{2,4,1,1,6,5}));
    }

    public int countHillValley(int[] nums) {
        int prev = nums[0];
        int curr,next,ret = 0;
        for (int i = 1; i < nums.length-1; i++) {
            curr = nums[i];
            if(curr!=prev){
                next = nums[i+1];
                // curr是不是峰
                if((prev<curr && next<curr) || (prev>curr && next>curr)) ret++;
                if(next != curr) prev = curr;
            }
        }
        return ret;
    }

}
