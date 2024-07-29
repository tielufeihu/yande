package game605.test.qs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q165
 * @description 比较版本号
 * @since 2024/7/29 14:33
 */
public class Q165 {

    public int compareVersion(String version1, String version2) {
        // 点分
        List<String> v1s = Arrays.asList(version1.split("\\."));
        List<String> v2s = Arrays.asList(version2.split("\\."));
        v1s = new ArrayList<>(v1s);
        v2s = new ArrayList<>(v2s);
        // 补0
        if(v1s.size() != v2s.size()){
            int len = Math.max(v1s.size(), v2s.size());
            if(v1s.size() < len){
                for(int i = v1s.size(); i < len; i++){
                    v1s.add("0");
                }
            }else{
                for(int i = v2s.size(); i < len; i++){
                    v2s.add("0");
                }
            }
        }
        // 从左往右
        for(int i = 0; i < v1s.size(); i++){
            if(Integer.parseInt(v1s.get(i)) > Integer.parseInt(v2s.get(i))){
                return 1;
            }else if(Integer.parseInt(v1s.get(i)) < Integer.parseInt(v2s.get(i))){
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Q165 q165 = new Q165();
        System.out.println(q165.compareVersion("1.2", "1.10"));
    }

}
