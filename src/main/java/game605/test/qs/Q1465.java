package game605.test.qs;


import org.junit.Test;

import java.util.Arrays;

/**
 * 切割后面积最大的蛋糕
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/27 0:01
 **/
public class Q1465 {

    // 分别找横竖最大的
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        return (int)(getMax(horizontalCuts,h) * getMax(verticalCuts, w) % 1000_000_007);
    }

    public long getMax(int[] cuts, int range) {
        Arrays.sort(cuts);
        int maxCut = Integer.MIN_VALUE;
        int prevCut = 0;
        for (int cut : cuts) {
            int currCut = cut-prevCut;
            if (currCut > maxCut){
                maxCut = currCut;
            }
            prevCut = cut;
        }
        if(range-prevCut>maxCut)
            maxCut = range-prevCut;
        return maxCut;
    }

    @Test
    public void t1(){
        //h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
        System.out.println(maxArea(5,4,new int[]{1,2,4}, new int[]{1,3}));
    }

}
