package game605.test.qs;

import org.junit.Test;

import java.util.HashMap;

/**
 * 猜数字游戏
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/27 16:54
 **/
public class Q299 {

    public String getHint(String secret, String guess) {
        // 先判断有多少个公牛数
        int len = secret.length();  // 两个字符串等长
        int bullsCount = 0;
        int cowsCount = 0;
        StringBuilder ssb = new StringBuilder(secret);
        StringBuilder gsb = new StringBuilder(guess);
        // 第一遍遍历先找出公牛数
        HashMap<Character, Integer> sMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char sNum = ssb.charAt(i);
            char gNum = gsb.charAt(i);
            if(sNum == gNum){
                bullsCount++;
                ssb.setCharAt(i, '#');
                gsb.setCharAt(i, '#');
            }else
                sMap.put(sNum, sMap.getOrDefault(sNum, 0)+1);
        }
        // 第二遍遍历找出奶牛数
        for (char c : gsb.toString().toCharArray()) {
            if (c=='#') continue;
            if (sMap.containsKey(c) && sMap.get(c)>0){
                sMap.put(c, sMap.get(c)-1);
                cowsCount++;
            }
        }
        return bullsCount+"A"+cowsCount+"B";
    }

    @Test
    public void t1(){
        //secret = "1807", guess = "7810"
        System.out.println(getHint("1807", "7810"));
    }

}
