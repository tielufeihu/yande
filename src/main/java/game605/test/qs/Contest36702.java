package game605.test.qs;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Contest36702 {

    // 这种题应该就是贪心吧
    public String shortestBeautifulSubstring(String s, int k) {
        List<Integer> idxList = new ArrayList<>();  // 用来存是 1 位置上的索引

        List<String> retList = new ArrayList<>();

        // 两次遍历第一次生成 idxList 数组
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '1'){
                idxList.add(i);
            }
        }

        // 第二次直接得到结果  (前开后闭区间)
        int begin=0, end=k-1;
        int oneCount = idxList.size();
        // 判断边界
        if(oneCount < k)
            return "";

        int range = idxList.get(end) - idxList.get(begin);
        retList.add(s.substring(idxList.get(begin), idxList.get(end)+1));

        for (int i = 1; i+k <= oneCount; i++) {
            int tRange = idxList.get(i+k-1) - idxList.get(i);

            if(tRange == range){
                retList.add(s.substring(idxList.get(i), idxList.get(i+k-1)+1));
            }

            if(tRange < range){
                retList = new ArrayList<>();
                range = tRange;
                begin = i;
                end = i+k-1;
                retList.add(s.substring(idxList.get(begin), idxList.get(end)+1));
            }
        }

        // 拿到结果
        return retList.stream().sorted().limit(1).collect(Collectors.toList()).get(0);
    }


    @Test
    public void t1(){
        //"001110101101101111"
        //10
        System.out.println(shortestBeautifulSubstring("001110101101101111", 10));
    }

}
