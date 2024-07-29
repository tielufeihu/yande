package game605.test.qs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2844
 * @description TODO
 * @since 2024/7/24 9:42
 */
public class Q2844 {

    int count = 0;
    List<Integer> ret = new ArrayList<>();

    // 2844. 生成特殊数字的最少操作 dfs回溯
    public int minimumOperations(String num) {
        // 首先被25整数，尾数一定是 0或者5
        ret.add(num.length());
        for (int i = num.length()-1; i>=0 ; i--) {
            if (i == 0) {
                return count;
            }
            if (num.charAt(i) == '0' || num.charAt(i) == '5') {
                break;
            }
            count++;
        }
        // 回溯
        String s = num.substring(0, num.length()-count);
        dfs(s, 0);
        return ret.stream().min(Integer::compareTo).get();
    }

    public void dfs(String num, int index) {
        if (index == num.length()) {
            return;
        }
        if(Integer.parseInt(num) % 25 == 0){
            ret.add(count);
            return;
        }
        // 删
        String delStr = num.substring(0, index) + num.substring(index+1);
        count++;
        dfs(delStr, index+1);
        // 撤销
        count--;
        dfs(num, index+1);
    }


    public static void main(String[] args) {
        Q2844 q2844 = new Q2844();
        System.out.println(q2844.minimumOperations("2908305"));
    }

}
