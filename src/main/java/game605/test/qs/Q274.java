package game605.test.qs;

import org.junit.Test;

import java.util.Arrays;

/**
 * H 指数
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/11 14:00
 **/
public class Q274 {

    /**
     * 根据维基百科上 h 指数的定义：h 代表“高引用次数” ，
     * 一名科研人员的 h 指数 是指他（她）至少发表了 h 篇论文，
     * 并且每篇论文 至少 被引用 h 次。
     * 如果 h 有多种可能的值，h 指数 是其中最大的那个。
     */
    // 根据 H指数 的定义 使用暴力解法解题尝试
    public int hIndex(int[] citations) {
        // 排序 挨个试
        Arrays.sort(citations);
        int len = citations.length;
        int currH = 0;
        for (int i = len-1; i >= 0; i--) {
            int number = len - i;
            if(number >= citations[i]){
                currH = Math.max(currH, citations[i]);
            }else if(number < citations[i]){
                currH = Math.max(currH, number);
            }
        }
        return currH;
    }

    @Test
    public void t1(){
        // 用例
        // citations = [3,0,6,1,5]
        // out: 3
        System.out.println(hIndex(new int[]{1,3,1}));
    }

}
