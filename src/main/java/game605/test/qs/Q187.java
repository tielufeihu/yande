package game605.test.qs;

import com.baomidou.mybatisplus.annotation.TableId;
import org.junit.Test;

import java.util.*;

/**
 * 重复的DNA序列
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/11/5 0:50
 **/
public class Q187 {

    // 十个十个往前移动，然后Hash表计数
    public List<String> findRepeatedDnaSequences(String s) {
        int len = s.length();
        if(len<10) return new ArrayList<>();
        Set<String> subStrSet = new HashSet<>();
        Set<String> ans = new HashSet<>();
        // 十个十个前进
        for (int i = 0; i < len-9; i++) {
            String subStr = s.substring(i,i+10);
            if(subStrSet.contains(subStr)){
                ans.add(subStr);
            }else {
                subStrSet.add(subStr);
            }
        }
        return new ArrayList<>(ans);
    }

    @Test
    public void t1(){
        System.out.println(findRepeatedDnaSequences("AAAAAAAAAAA"));
    }

}
