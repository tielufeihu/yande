package game605.test.qs;

import java.util.Arrays;

/**
 * @author Koyou
 * @version 1.0.0
 * @className QuickSort
 * @description 快排
 * 基本思路： 选择一个数作为基准(base)， 把比基准小的放左边，把比基准大的放右边、再递归的遍历左右两部分
 * @since 2024/6/5 12:50
 */
public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        int[] arr = {1, 3, 2, 4, 5, 6, 7, 8, 9};
        quickSort.quickSort(arr, 0, arr.length - 1);
        System.out.printf("arr = " + Arrays.toString(arr));
    }

    public int[] quickSort(int[] arr, int left, int right) {
        // 使用i，j作为左指针和右指针
        int i = left, j = right;
        while (i < j){
            int base = arr[left];
            while (i < j){
                while (arr[j] >= base && i < j){
                    j--;
                }
                arr[i] = arr[j];
                while (arr[i] <= base && i < j){
                    i++;
                }
                arr[j] = arr[i];
            }
            // 此时左右指针相遇，将基准数放入相遇位置
            arr[i] = base;
            quickSort(arr, left, i-1);
            quickSort(arr, i+1, right);
        }
        return arr;
    }

}
