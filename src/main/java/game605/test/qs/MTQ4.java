package game605.test.qs;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class MTQ4 {

    // 直接递归枚举吧
    public static int enumTest(char[] cs1, char[] cs2){
        Set<String> set = new HashSet<>();
        dfs(cs1,cs2,0,"",set);
        return set.size();
    }

    // 直接双指针
    public static int direct(char[] cs1, char[] cs2){
        int len = cs1.length;
        int ret = 1;
        for (int i = 0; i < len/2; i++) {
            char b1 = cs1[i];
            char b2 = cs2[i];
            char e1 = cs1[len-1-1];

        }
        return 0;
    }

    public static void dfs(char[] cs1, char[] cs2, int currIdx, String currS, Set<String> set){
        if(currIdx >= cs1.length){
            if(isReS(currS)){
                set.add(currS);
            }
            return;
        }
        dfs(cs1,cs2,currIdx+1,currS+cs1[currIdx],set);
        if(cs1[currIdx] != cs2[currIdx])
            dfs(cs1,cs2,currIdx+1,currS+cs2[currIdx],set);
    }

    public static boolean isReS(String s){
        int n = s.length();
        for (int i = 0; i < n/2; i++) {
            char l = s.charAt(i);
            char r = s.charAt(n-1-i);
            if(l!=r)
                return false;
        }
        return true;
    }

    @Test
    public void t1(){
        int r = enumTest(new char[]{'a','b','c'}, new char[]{'c','b','a'});
        System.out.println(r);
    }

}
