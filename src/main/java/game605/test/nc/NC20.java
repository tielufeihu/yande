package game605.test.nc;

import java.util.ArrayList;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC20
 * @description TODO
 * @since 2024/8/26 18:03
 */
public class NC20 {

    public ArrayList<String> restoreIpAddresses (String s) {
        // write code here
        ArrayList<String> res = new ArrayList<>();
        dfs(s, "", 0, res, 0);
        return res;
    }

    private void dfs(String s, String curr, int start, ArrayList<String> path, int count) {
        if(count == 4 && start == s.length()){
            path.add(curr.substring(0, curr.length() - 1));
            return;
        }else if(count == 4 || start == s.length()) {
            return;
        }
        // 尝试往后取三位
        for (int i = 1; i + start <= s.length(); i++) {
            // 判断剩下几个数
            if (s.length() - start > 3 * (4 - count)) {
                break;
            }
            String temp = s.substring(start, start + i);
            if (Long.parseLong(temp) > 255) {
                break;
            }
            // 如果下一个是0
            if(s.charAt(start) == '0' && i > 1) {
                continue;
            }
            dfs(s, curr + temp + ".", start + i, path, count + 1);
        }
    }

    public static void main(String[] args) {
        NC20 nc20 = new NC20();
        System.out.println(nc20.restoreIpAddresses("25525511135"));
    }


}
