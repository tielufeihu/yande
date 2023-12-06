package game605.test.qs;

import java.util.Arrays;

/**
 * 咒语和药水数量
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/11/10 2:14
 **/
public class Q2300 {

    // TODO 数学优化
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int len = spells.length;
        int potionCount = potions.length;
        int[] ret = new int[len];
        // potion 排序 单一排序无法通过
        // 所以应该是双排序操作
        Arrays.sort(potions);  // 降序
        Arrays.sort(spells);

        int cursorPotion = 0;
        int count = potionCount;

        for (int i = len-1; i >= 0; i--) {
            int temp = potionCount;
            int spell = spells[i];
            for (int potion : potions) {
                if((long) spell * potion<success)
                    temp--;
                else
                    break;
            }
            ret[i] = temp;
        }
        return ret;
    }

}
