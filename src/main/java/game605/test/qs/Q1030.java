package game605.test.qs;


import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

/**
 * 给定四个整数 rows ,   cols ,  rCenter 和 cCenter 。有一个 rows x cols 的矩阵，你在单元格上的坐标是 (rCenter, cCenter) 。
 * 返回矩阵中的所有单元格的坐标，并按与 (rCenter, cCenter) 的 距离 从最小到最大的顺序排。你可以按 任何 满足此条件的顺序返回答案。
 * 单元格(r1, c1) 和 (r2, c2) 之间的距离为|r1 - r2| + |c1 - c2|。
 */
public class Q1030 {
    // 先暴力解 过了 时间复杂度妥妥的O方
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        int[] distances  = new int[rows*cols];
        int[][] res = new int[rows*cols][2];
        int k=0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 计算距离
                int distance = Math.abs(i-rCenter) + Math.abs(j-cCenter);
                distances[k] = distance;
                res[k][0] = i;
                res[k][1] = j;
                k++;
            }
        }
        // 排序
        for (int i = 0; i < distances.length-1; i++) {
            for (int j = i+1; j < distances.length; j++) {
                if(distances[i]>distances[j]) {
                    int t = distances[i];
                    distances[i] = distances[j];
                    distances[j] = t;
                    int[] tt = res[i];
                    res[i] = res[j];
                    res[j] = tt;
                }
            }
        }
        return res;
    }

    // 一圈一圈遍历，省下排序(我的解，最终难以去重)
    public int[][] allCellsDistOrder2(int rows, int cols, int rCenter, int cCenter) {
        // 一圈一圈遍历
        int q = 1; // 距离1的一圈
        boolean flag = true;  // 退出的标志
        int[][] res = new int[rows*cols*2][2];
        Set<int[]> set = new HashSet<>();
        int k = 0;
        // 本身肯定是最近的
        res[k++] = new int[]{rCenter,cCenter};
        set.add(res[0]);
        while (flag){
            flag = false;
            for (int i = 0; i <= q; i++) {
                int dr = i; // r距离
                int dc = q-i;  // c距离

                int tr1 = rCenter+dr;
                int tc1 = cCenter+dc;
                int[] tloc1 = new int[]{tr1,tc1};
                if(tr1<rows && tc1<cols && !set.contains(tloc1)){
                    res[k++] = tloc1;
                    set.add(tloc1);
                    flag = true;
                }
                int tr2 = rCenter+dr;
                int tc2 = cCenter-dc;
                int[] tloc2 = new int[]{tr2,tc2};
                if(tr2<rows && tc2<cols && tc2>=0&& !set.contains(tloc2)){
                    res[k++] = tloc2;
                    set.add(tloc2);
                    flag = true;
                }
                int tr3 = rCenter-dr;
                int tc3 = cCenter+dc;
                int[] tloc3 = new int[]{tr3,tc3};
                if(tr3<rows && tc3<cols && tr3>=0&& !set.contains(tloc3)){
                    res[k++] = tloc3;
                    set.add(tloc3);
                    flag = true;
                }
                int tr4 = rCenter-dr;
                int tc4 = cCenter-dc;
                int[] tloc4 = new int[]{tr4,tc4};
                if(tr4<rows && tc4<cols && tc4>=0 && tr4>=0&& !set.contains(tloc4)){
                    res[k++] = tloc4;
                    set.add(tloc4);
                    flag = true;
                }
            }
            q++;
        }
        //int[][] ress = Arrays.stream(res).distinct().toArray();
        return res;
    }

    // 这个解很不错
    public int[][] allCellsDistOrder3(int R, int C, int r0, int c0) {
        return IntStream.range(0, R).boxed()
                .flatMap(r -> IntStream.range(0, C).mapToObj(c -> new int[]{r, c}))
                .sorted(Comparator.comparingInt(pos -> dist(pos[0], pos[1], r0, c0)))
                .toArray(int[][]::new);
    }

    private int dist(int r, int c, int r0, int c0) {
        return Math.abs(r - r0) + Math.abs(c - c0);
    }

    @Test
    public void test1(){
        System.out.println("t 1030");
        int[][] ressss = allCellsDistOrder2(2,2,0,1);
        for (int[] ints : ressss) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
