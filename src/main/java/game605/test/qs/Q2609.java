package game605.test.qs;

import org.junit.Test;

/**
 * 最长平衡子字符串
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/11/9 15:19
 **/
public class Q2609 {

    public int findTheLongestBalancedSubstring(String s) {
        if(s.length()<1) return 0;
        int len = s.length();
        int ret=0;
        int count0=1;
        int count1=0;
        // 找到第一个0
        int startIdx = 0;
        for (int i = 0; i < len; i++) {
            char curr = s.charAt(i);
            if(curr == '0'){
                startIdx=i;
                break;
            }
        }
        if(startIdx==0) return 0;
        char prev = s.charAt(startIdx);
        for (int i = startIdx+1; i < len; i++) {
            char curr = s.charAt(i);
            if(prev=='1' && curr=='0'){
                // 如果是 1-0则结算并清空历史状态
                ret = Math.max(ret, Math.min(count0,count1));
                count0=1;
                count1=0;
            }else if(prev=='0' && curr=='0'){
                count0++;
            }else if(prev=='0' && curr=='1'){
                count1++;
            }else if(prev=='1' && curr=='1'){
                count1++;
            }
            prev = curr;
        }
        ret = Math.max(ret, Math.min(count0,count1));
        return ret*2;
    }

    @Test
    public void t1(){
        System.out.println(findTheLongestBalancedSubstring("111"));
    }

}
