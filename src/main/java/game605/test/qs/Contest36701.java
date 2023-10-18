package game605.test.qs;

import java.util.Arrays;

public class Contest36701 {

    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {
        int[] ret = new int[]{-1,-1};
        int len=nums.length;
        for (int i = 0; i < len-indexDifference; i++) {
            for (int j = i+indexDifference; j < len; j++) {
                // 对后面的数排序
                int[] tnums = Arrays.copyOfRange(nums, j, len);
                Arrays.sort(tnums);
                for (int k = tnums.length-1; k > 0; k--) {
                    if(Math.abs(nums[i]-nums[k]) >= valueDifference){
                        ret[0] = i;
                        ret[1] = k;
                        return ret;
                    }
                }
            }
        }
        return ret;
    }

}
