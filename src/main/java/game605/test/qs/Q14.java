package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q14
 * @description 最长公共前缀
 * @since 2024/6/17 17:58
 */
public class Q14 {

    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0 || strs[0].isEmpty()){
            return "";
        }
        int ret = 0;
        int pos = 0;
        boolean flag = true;
        while (flag){
            if(pos>=strs[0].length()){
                break;
            }
            char target = strs[0].charAt(pos);
            for (int i = 1; i < strs.length; i++) {
                if(pos < strs[i].length() && strs[i].charAt(pos) != target){
                    flag = false;
                    break;
                }
            }
            pos++;
            if(flag) ret++;
        }
        return strs[0].substring(0,ret);
    }

}
