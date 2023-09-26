package game605.test.qs;

/**
 * 宝石与石头
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/26 15:33
 **/
public class Q771 {

    /**
     * 示例 1：
     * 输入：jewels = "aA", stones = "aAAbbbb"
     * 输出：3
     */


    // 我的， 第一时间想到用replace置换后，统计置换后字符的个数
    public int numJewelsInStones1(String jewels, String stones) {
        for (int i = 0; i < jewels.length(); i++) {
            stones = stones.replace(jewels.charAt(i), '*');
        }
        int ret = 0;
        for (int i = 0; i < stones.length(); i++) {
            if(stones.charAt(i) == '*'){
                ret++;
            }
        }
        return ret;
    }

    // 看到题解中有，replace成空串后长度相减的
    public int numJewelsInStones(String jewels, String stones) {
        String ts = stones;
        for (int i = 0; i < jewels.length(); i++) {
            ts = ts.replace(String.valueOf(jewels.charAt(i)), "");
        }
        return stones.length()-ts.length();
    }

}
