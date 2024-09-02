package game605.test.qs;

import java.util.Arrays;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className LCR35
 * @description 最小时间差
 * @since 2024/9/2 16:27
 */
public class LCR35 {

    public int findMinDifference(List<String> timePoints) {
        // 转换为分钟
        int[] minutes = new int[timePoints.size()];
        for (int i = 0; i < timePoints.size(); i++) {
            minutes[i] = Integer.parseInt(timePoints.get(i).substring(0, 2)) * 60
                    + Integer.parseInt(timePoints.get(i).substring(3));
        }
        // 排序
        Arrays.sort(minutes);
        // 求最小差值
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < minutes.length; i++) {
            min = Math.min(min, minutes[i] - minutes[i - 1]);
        }
        // 判断首尾之间的时间差
        min = Math.min(min, minutes[0] + 1440 - minutes[minutes.length - 1]);
        return min;
    }

}
