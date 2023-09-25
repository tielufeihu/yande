package game605.test.qs;

import org.junit.Test;

/**
 * 加油站
 *在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，
 * 从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以<b>按顺序</b>绕环路行驶一周，
 * 则返回出发时加油站的编号，否则返回 -1 。如果存在解，则 保证 它是 唯一 的。
 */
public class Q134 {
    // 先暴力解  超时了
    public int canCompleteCircuit(int[] gas, int[] cost) {
        for (int i = 0; i < gas.length; i++) {
            // 出发
            if(gas[i] < cost[i])
                continue;
            int currentGas = gas[i] - cost[i];
            int j = (i+1) % gas.length;
            while (j != i){
                currentGas += gas[j] - cost[j];
                if(currentGas < 0){
                    break;
                }
                j = (j+1) % gas.length;
            }
            if(j == i){
                return i;
            }
        }
        return -1;
    }

    // 必须贪心
    public int canCompleteCircuit2(int[] gas, int[] cost) {

        return 0;
    }

    @Test
    public void test1(){
        System.out.println("t1");
        int res = canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3});
        System.out.println(res);
    }
}
