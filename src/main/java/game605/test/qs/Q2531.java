package game605.test.qs;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 使字符串总不同字符的数目相等
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/9 15:53
 **/
public class Q2531 {

    // 哈希表 + 分类讨论， 逻辑太复杂了， 懒得调了
    public boolean isItPossible(String word1, String word2) {
        // 让长的在前
        if(word1.length() < word2.length()){
            String t = word1;
            word1 = word2;
            word2 = t;
        }
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        // 初始化数据
        int len1 = word1.length();
        int len2 = word2.length();
        for (int i = 0; i < len1; i++) {
            char ct = word1.charAt(i);
            if(map1.containsKey(ct)){
                map1.put(ct, map1.get(ct)+1);
            }else
                map1.put(ct, 1);
        }
        for (int i = 0; i < len2; i++) {
            char ct = word2.charAt(i);
            if(map2.containsKey(ct)){
                map2.put(ct, map2.get(ct)+1);
            }else
                map2.put(ct, 1);
        }

        // 分类讨论
        int diffNum = map1.size() - map2.size();
        if (diffNum == 0){
            // a有c大于等于i个。 b有c大于等于1
            // a有c等于1个， b没有c，但是有c1等于1个，且a没有c1
            //
            boolean flag = false;
            for (Map.Entry<Character, Integer> characterIntegerEntry : map1.entrySet()) {
                if(characterIntegerEntry.getValue() >= 1
                        && map2.containsKey(characterIntegerEntry.getKey())
                        && map2.get(characterIntegerEntry.getKey()) >= 1) {
                    flag = true;
                    break;
                }
                for (Map.Entry<Character, Integer> integerEntry : map2.entrySet()) {
                    char k = integerEntry.getKey();
                    if(!map1.containsKey(k) && map2.get(k) == 1)
                        flag = true;
                    if(characterIntegerEntry.getValue()>1 && integerEntry.getValue()>1)
                        flag = true;
                }
            }
            return flag;
        }else if (diffNum == 1){
            // 1. s1 有且不只有一个的  s2 没有的
            // 2. s1 只有一个的 s2 也有的
            boolean flag = false;
            for (Map.Entry<Character, Integer> characterIntegerEntry : map1.entrySet()) {
                if(characterIntegerEntry.getValue() > 1 && !map2.containsKey(characterIntegerEntry.getKey())){
                    flag = true;
                    break;
                }
                if(characterIntegerEntry.getValue() == 1 && map2.containsKey(characterIntegerEntry.getKey())){
                    flag = true;
                    break;
                }
            }
            return flag;
        }else if (diffNum == 2){
            // s1 有且只有一个的
            // s2 没有的
            boolean flag = false;
            for (Map.Entry<Character, Integer> characterIntegerEntry : map1.entrySet()) {
                if(characterIntegerEntry.getValue() == 1 && !map2.containsKey(characterIntegerEntry.getKey())){
                    flag = true;
                    break;
                }
            }
            return flag;

        }else {
            return false;
        }
    }


    @Test
    public void t1(){
        System.out.println(isItPossible("aa", "bb"));
    }

}
