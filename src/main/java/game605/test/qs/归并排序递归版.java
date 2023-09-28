package game605.test.qs;

import org.junit.Test;
import org.python.antlr.ast.Str;

import java.util.Arrays;

/**
 * 归并排序递归版本
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/26 17:44
 **/
public class 归并排序递归版 {

    public String 字符串1;


    // 归并排序就是把大的数组拆分成两个，再执行 merge 操作
    public int[] mergeList(int[] a, int[] b){
        if(a.length == 0){
            return b;
        }
        if(b.length == 0){
            return a;
        }
        int[] c = new int[a.length+b.length];
        int i=0,j=0,k=0;
        while (k<c.length){
            if((i<a.length && j<b.length) && a[i]<=b[j]){
                c[k] = a[i];
                i++;
            }else if(i<a.length && j<b.length){
                c[k] = b[j];
                j++;
            }else if(i<a.length){
                c[k] = a[i];
                i++;
            }else if(j<b.length){
                c[k] = b[j];
                j++;
            }
            k++;
        }
        return c;
    }


    public int[] mergeSort(int[] list){
        if (list == null || list.length == 0){
            return new int[]{};
        }
        if(list.length == 1){
            return list;
        }
        if(list.length == 2){
            if(list[0]>list[1]){
                int t = list[0];
                list[0] = list[1];
                list[1] = t;
            }
            return list;
        }
        int mid = list.length / 2;
        return mergeList(mergeSort(Arrays.copyOfRange(list, 0, mid)),mergeSort(Arrays.copyOfRange(list, mid+1, list.length)));
    }

    @Test
    public void tst1(){
        //System.out.println(Arrays.toString(mergeList(new int[]{1, 4, 6, 9}, new int[]{3, 5, 7})));

        int[] sortedArr = mergeSort(new int[]{4,9,2,6,1,3,7});

        System.out.println(Arrays.toString(sortedArr));

    }

}
