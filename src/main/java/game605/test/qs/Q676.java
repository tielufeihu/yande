package game605.test.qs;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q676
 * @description 实现一个魔法字典  尝试暴力解法
 * @since 2024/8/12 11:15
 */
class Q676 {

    private List<String> dict;

    public Q676() {

    }

    public void buildDict(String[] dictionary) {
        dict = new ArrayList<>();
        dict.addAll(Arrays.asList(dictionary));
    }


    public boolean search(String searchWord) {
        for(String word : dict){
            if(match(word, searchWord)){
                return true;
            }
        }
        return false;
    }

    public boolean match(String word, String searchWord) {
        if(word.length() != searchWord.length()){
            return false;
        }
        int count = 0;
        for(int i = 0; i < word.length(); i++){
            if(word.charAt(i) != searchWord.charAt(i)){
                count++;
            }
            if(count >= 2){
                return false;
            }
        }
        return count == 1;
    }

}
