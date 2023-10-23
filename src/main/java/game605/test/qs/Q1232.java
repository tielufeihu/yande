package game605.test.qs;

import org.junit.Test;

/**
 * 缀点成线
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/23 11:06
 **/
public class Q1232 {

    // 两点即可确定一条直线， 接下来计算后面的点在不在线上即可
    //
    public boolean checkStraightLine(int[][] coordinates) {
        // 输入coordinates.len > 2
        int[] point1 = coordinates[0];
        int[] point2 = coordinates[1];
        // 直线方程
        // 通过推导
        double k;
        if(point2[0]-point1[0] == 0){
            // TODO 说明是一条竖线
            // 直接判断 x是不是全是0
            int x = point1[0];
            for (int i = 2; i < coordinates.length; i++) {
                int[] tempPoint = coordinates[i];
                if(tempPoint[0] != x){
                    return false;
                }
            }
            return true;
        }else {
            k = (double)(point2[1]-point1[1]) / (point2[0]-point1[0]);
        }
        // 有了k， 则b很容易求得
        double b = point1[1] - (k*point1[0]);
        for (int i = 2; i < coordinates.length; i++) {
            int[] tempPoint = coordinates[i];
            if(tempPoint[1] != k * tempPoint[0] + b){
                return false;
            }
        }
        return true;
    }

    @Test
    public void t1(){
        // [1,2],[2,3],[3,4],[4,5],[5,6],[6,7]
        System.out.println(checkStraightLine(new int[][]{{2,4},{2,5},{2,8}}));
    }

}
