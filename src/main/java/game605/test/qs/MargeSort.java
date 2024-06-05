package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className MargerSort
 * @description 重新学习归并排序
 * @since 2024/6/4 14:10
 */
public class MargeSort {

    public static void main(String[] args) {
        int [] arr = {1, 3, 5, 7, 9, 2, 4, 6, 8};
        MargeSort margeSort = new MargeSort();
        int [] result = margeSort.margeSort(arr, 0, arr.length - 1);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }


    public int[] margeSort(int [] arr, int l, int r){
        // 递归结束条件
        if (l == r) {
            return null;
        }
        // 分
        int mid = l + (r - l) / 2;
        // 对左侧分
        margeSort(arr, l, mid);
        // 对右侧分
        margeSort(arr, mid + 1, r);
        // 合并
        return marge(arr, l, mid, r);
    }

    private int[] marge(int[] arr, int l, int mid, int r) {
        if (l == r){
            return new int[0];
        }

        int [] temp = new int[r - l + 1];
        int p1 = l;
        int p2 = mid + 1;
        int idx = 0;
        while (p1 <= mid && p2 <= r){
            if(arr[p1] < arr[p2]){
                temp[idx++] = arr[p1++];
            }else {
                temp[idx++] = arr[p2++];
            }
        }
        while (p1 <= mid){
         temp[idx++] = arr[p1++];
        }
        return temp;
    }


}
