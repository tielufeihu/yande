package game605.test.qs;

import org.junit.Test;
import org.python.antlr.ast.Str;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 单词拆分
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/12 15:48
 **/
public class Q139 {

    // replace的方法不能解决，打不完的补丁
    public boolean wordBreak(String s, List<String> wordDict) {
        String st = s;
        wordDict = wordDict.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.println(wordDict);
        String prev = null;
        for (String s1 : wordDict) {
            if(prev!=null && s1.contains(prev)){
                List<String> newDict = new ArrayList<>(wordDict);
                newDict.remove(prev);
                boolean t = wordBreak(s.replace(s1,""), newDict);
                if(t)
                    return t;
            }
            st = st.replace(s1, "");
            prev = s1;
        }
        return st.length()==0;
    }

    // 题解基本看懂，这是个背包问题
    public boolean wordBreak2(String s, List<String> wordDict){
        int len = s.length();
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[len];
        // 初始状态
        dp[0] = true;
        for (int i = 1; i < len; i++) {
            dp[i] = false;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if(dp[j] && wordSet.contains(s.substring(j,i))){
                    dp[i] = true;
                }
            }
        }
        return dp[len-1];
    }

    @Test
    public void t1(){
        List<String> strings = new ArrayList<>();
        strings.add("car");
        strings.add("ca");
        strings.add("rs");
        System.out.println(wordBreak("cars", strings));
    }

}
