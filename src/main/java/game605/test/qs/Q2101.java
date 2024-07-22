package game605.test.qs;

import java.util.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2101
 * @description c
 * @since 2024/7/22 15:52
 */
public class Q2101 {

    // 引爆炸弹的数量
    public int maximumDetonation(int[][] bombs) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = bombs.length;
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if(i!=j && isBang(bombs[i], bombs[j])){
                    list.add(j);
                }
            }
            map.put(i, list);
        }

        int res = 0;
        for (int i = 0; i < n; ++i) {
            // 遍历每个炸弹，广度优先搜索计算该炸弹可引爆的数量，并维护最大值
            boolean[] visited = new boolean[n];
            int cnt = 1;
            Queue<Integer> queue = new ArrayDeque<>();
            queue.offer(i);
            visited[i] = true;
            while (!queue.isEmpty()) {
                int cidx = queue.poll();
                for (int nidx : map.getOrDefault(cidx, new ArrayList<>())) {
                    if (visited[nidx]) {
                        continue;
                    }
                    ++cnt;
                    queue.offer(nidx);
                    visited[nidx] = true;
                }
            }
            res = Math.max(res, cnt);
        }
        return res;
    }

    // 能引爆
    public boolean isBang(int[] a, int[] b){
        long dx = a[0] - b[0];
        long dy = a[1] - b[1];
        return (long) a[2] * b[2] >= dx * dx + dy * dy;
    }

}
