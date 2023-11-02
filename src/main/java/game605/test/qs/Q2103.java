package game605.test.qs;


import org.junit.Test;

import java.util.Arrays;

/**
 * 球和杆
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/11/2 1:43
 **/
public class Q2103 {

    public int countPoints(String rings) {
        int n = rings.length()/2;
        int[] rCount = new int[10];
        int[] gCount = new int[10];
        int[] bCount = new int[10];
        Arrays.fill(rCount,0);
        Arrays.fill(gCount,0);
        Arrays.fill(bCount,0);
        for (int i = 0; i < rings.length(); i+=2) {
            char colour = rings.charAt(i);
            int target = rings.charAt(i+1)-48;
            if (colour == 'R')
                rCount[target]++;
            else if (colour == 'G')
                gCount[target]++;
            else if (colour == 'B')
                bCount[target]++;
        }
        int ret = 0;
        for (int i = 0; i < 10; i++) {
            if(rCount[i]>0 && gCount[i]>0 && bCount[i]>0) ret++;
        }
        return ret;
    }

    @Test
    public void t1(){
        countPoints("B0B6G0R6R0R6G9");
    }

}
