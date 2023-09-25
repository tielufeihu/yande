package game605.test.rabbitMq;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test02 {

    /*如果在将所有大写字符转换为小写字符、
    并移除所有非字母数字字符之后，
    短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
    字母和数字都属于字母数字字符。*/
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^0-9A-Za-z]", "").toLowerCase();
        String rs = new StringBuilder(s).reverse().toString();
        return s.equals(rs);
    }

    /*给你一个字符串 columnTitle ，表示 Excel 表格中的列名称。返回 该列名称对应的列序号 。*/
    /*A -> 1
      B -> 2
      C -> 3
        ...
        Z -> 26
        AA -> 27
        AB -> 28
        ...*/
    // ok
    public int titleToNumber(String columnTitle) {
        StringBuilder sb = new StringBuilder(columnTitle).reverse();
        int sum = 0;
        int w = 1;
        for (int i = 0; i < sb.length(); i++) {
            char ct = sb.charAt(i);
            sum += (ct-64)*w;
            w *= 26;
        }
        return sum;
    }

    /*给定两个字符串 s 和 t ，判断它们是否是同构的。
    如果 s 中的字符可以按某种映射关系替换得到 t ，那么这两个字符串是同构的。*/
    public boolean isIsomorphic(String s, String t) {
        // 以下map k,v 是 s到t 的映射关系
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            // 取出两个字符串相同位置上的字符
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if(!map.containsKey(cs)){
                // 如果不包括这个映射，把这个映射加入到 map 中
                map.put(cs, ct);
            }else {
                // 包括这个映射， 判断ct 与映射值是否相同
                if(ct != map.get(cs))
                    return false;
            }
        }
        return true;
    }

    /*DNA序列 由一系列核苷酸组成，缩写为 'A', 'C', 'G' 和 'T'.。
        例如，"ACGAATTCCG" 是一个 DNA序列 。
        在研究 DNA 时，识别 DNA 中的重复序列非常有用。
        给定一个表示 DNA序列 的字符串 s ，
        返回所有在 DNA 分子中出现不止一次的 长度为 10 的序列(子字符串)。
        你可以按 任意顺序 返回答案。
    */
    public List<String> findRepeatedDnaSequences(String s) {
        return null;
    }

    @Test
    public void t00(){
        boolean re = isIsomorphic("badc", "baba");
        System.out.println(re);
    }

}
