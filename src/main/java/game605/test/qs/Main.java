package game605.test.qs;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = in.nextInt();
        String s1 = "", s2 = "";
        for (int i = 0; i < count; i++) {
            s1 = in.next();
            s2 = in.next();
        }
        char[] cs1 = s1.toCharArray();
        char[] cs2 = s2.toCharArray();
        // 枚举
        System.out.println(enumTest(cs1,cs2));
    }

    public static int enumTest(char[] cs1, char[] cs2) {
        Set<String> set = new HashSet<>();
        dfs(cs1, cs2, 0, "", set);
        return set.size();
    }

    public static void dfs(char[] cs1, char[] cs2, int currIdx, String currS,
                           Set<String> set) {
        if (currIdx >= cs1.length) {
            if (isReS(currS)) {
                set.add(currS);
            }
            return;
        }
        dfs(cs1, cs2, currIdx + 1, currS + cs1[currIdx], set);
        dfs(cs1, cs2, currIdx + 1, currS + cs2[currIdx], set);
    }

    public static boolean isReS(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            char l = s.charAt(i);
            char r = s.charAt(n - 1 - i);
            if (l != r)
                return false;
        }
        return true;
    }

}
