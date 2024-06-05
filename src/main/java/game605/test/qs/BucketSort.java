package game605.test.qs;

import org.python.antlr.op.In;

import java.util.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className BucketSort
 * @description 桶排序
 * @since 2024/6/5 11:04
 */
public class BucketSort {

    public static void main(String[] args) {
        BucketSort t = new BucketSort();
        int[] arr = {1,44,3,24,32,14,7,8,24,34};
        System.out.println("原始数组：" + Arrays.toString(arr));
        int[] sortedArr = t.bucketSort2(arr);
        System.out.println("排序后数组：" + Arrays.toString(sortedArr));

    }

    // 固定桶数
    public int[] bucketSort(int[] arr){
        // 创建桶
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        // 固定桶数5个 ，固定区间0-49
        int bucketSize = 5;
        for (int i = 0; i < bucketSize; i++) {
            buckets.add(new ArrayList<>());
        }

        // 分配桶
        for (int i = 0; i < arr.length; i++) {
            buckets.get(arr[i]/10).add(arr[i]);
        }
        // 排序桶
        for (int i = 0; i < bucketSize; i++){
            Collections.sort(buckets.get(i));
        }
        // 组合桶
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < bucketSize; i++) {
            ret.addAll(buckets.get(i));
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

    // 不定桶数
    public int[] bucketSort2(int[] arr){
        // 创建桶
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        // 获取最大最小值
        int max = 0, min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>max){
                max = arr[i];
            }
            if(arr[i]<min){
                min = arr[i];
            }
        }
        // 计算桶数量
        int bucketSize = (max-min)/arr.length+1;
        for (int i = 0; i < bucketSize; i++) {
            buckets.add(new ArrayList<>());
        }
        // 分配桶
        for (int i = 0; i < arr.length; i++){
            buckets.get((arr[i]-min)/arr.length).add(arr[i]);
        }
        // 排序桶
        for (int i = 0; i < bucketSize; i++){
            Collections.sort(buckets.get(i));
        }
        // 组合桶
        List<Integer> ret = new ArrayList<>();
        for (int i = 0; i < bucketSize; i++) {
            ret.addAll(buckets.get(i));
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();
    }

}
