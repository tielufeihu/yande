package game605.test.qs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3111
 * @description TODO
 * @since 2024/7/31 13:58
 */
public class Q3111 {

    public int minRectanglesToCoverPoints(int[][] points, int w) {
        Set<Integer> idxSet = new HashSet<>();
        // 遍历每个点
        for (int[] point : points) {
            idxSet.add(point[0]);
        }
        // 转数组并排序
        List<Integer> idxList = new ArrayList<>(idxSet).stream().sorted().collect(Collectors.toList());
        int remain = -1;
        int ret = 0;
        for (int i = 0; i < idxList.size(); i++) {
            if(remain == -1){
                // 框一个
                remain = w;
                ret ++;
            }else {
                // 和前一个的差
                int diff = idxList.get(i) - idxList.get(i-1);
                if(diff <= remain){
                    remain -= diff;
                }else {
                    remain = w;
                    ret ++;
                }
            }
        }
        return ret;
    }

    // 灵神的
    public int minRectanglesToCoverPoints2(int[][] points, int w) {
        Arrays.sort(points, (p, q) -> p[0] - q[0]);
        int ans = 0;
        int x2 = -1;
        for (int[] p : points) {
            if (p[0] > x2) {
                ans++;
                x2 = p[0] + w;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] points = {{1,1},{3,3}};
        System.out.println(new Q3111().minRectanglesToCoverPoints(points, 2));
    }

}
