package game605.test.qs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 最小和分割  ： 每日一题
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/9 15:01
 **/
public class Q2578 {

    public int splitNum(int num) {
        int tnum = num;
        List<Integer> singleNums = new ArrayList<Integer>();
        while (tnum!=0){
            singleNums.add(tnum%10);
            tnum /= 10;
        }
        singleNums = singleNums.stream().sorted().collect(Collectors.toList());
        int num1 = 0;
        int num2 = 0;
        boolean sign = true;
        for (Integer singleNum : singleNums) {
            if (sign){
                num1 *= 10;
                num1 += singleNum;
            }else {
                num2 *= 10;
                num2 += singleNum;
            }
            sign = !sign;
        }
        return num1+num2;
    }

    @Test
    public void t1(){
        System.out.println(splitNum(4325));
    }

}
