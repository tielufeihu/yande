package game605.test.qs;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 分发饼干
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/24 13:48
 **/
public class Q455 {

    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int ret = 0;
        int sp = 0;
        for (int appetite : g) {
            for (; sp < s.length; sp++) {
                if(s[sp]>=appetite){
                    // 使这个孩子满足
                    ret ++;
                    sp++;
                    break;
                }
            }
        }
        return ret;
    }

    @Test
    public void t1(){
        // g = [1,2,3], s = [1,1]
        // g = [1,2], s = [1,2,3]
        System.out.println(findContentChildren(new int[]{1,2,3}, new int[]{3}));
    }

}
