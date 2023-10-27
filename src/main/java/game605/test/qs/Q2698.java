package game605.test.qs;

import org.junit.Test;

/**
 * 求一个数的惩罚数
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/25 11:17
 **/
public class Q2698 {

    public int punishmentNumber(int n) {
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            if(i==9)
                System.out.println(9);
            int num = i*i;
            if(isCFS(String.valueOf(num), i, 0, 0))
                ret+=num;
        }
        return ret;
    }

    public boolean isCFS(String num, int target, int sum, int currIdx){
        // dfs暴力搜
        if(currIdx>=num.length() || sum>target){
            if(target == sum){
                return true;
            }else
                return false;
        }
        for (int i = currIdx+1; i <= num.length(); i++) {
            String subStr = num.substring(currIdx,i);
            if(isCFS(num, target, sum+Integer.parseInt(subStr), i))
                return true;
        }
        return false;
    }

    @Test
    public void t1(){
        System.out.println(punishmentNumber(10));
    }

}
