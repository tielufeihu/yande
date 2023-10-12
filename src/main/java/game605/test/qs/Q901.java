package game605.test.qs;

import org.junit.Test;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * 股票价格跨度， 每日一题
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/7 21:21
 **/
public abstract class Q901 {

    // 一定是先挑选数据结构
    // 先试试 最直观的双向链表 击败 5.05%使用 Java 的用户  存在很大的优化空间，比如可以记录昨天的跨度和位置
    LinkedList<Integer> prices;
    int prevSpan = 1;
    //ListIterator<Integer> prevIter;

    public abstract int funcA();

    public Q901() {
        prices = new LinkedList<>();
    }

    // 我的不优雅的模拟+优化  190ms
    public int next(int price) {
        // 判断是否是第一条
        if(prices.size()==0){
            prices.add(price);
            return 1;
        }

        // 判断是否比昨天价格高
        if(price >= prices.getLast()){

            if(prevSpan == 1){
                int ret = 1;
                // 从尾部往前找
                ListIterator<Integer> it = prices.listIterator(prices.size());
                while (it.hasPrevious()){
                    Integer tp = it.previous();
                    if(tp<=price)
                        ret++;
                    else
                        break;
                }
                prevSpan = ret;

            }else {
                // 从昨天的位置开始找
                ListIterator<Integer> it = prices.listIterator(prices.size()-prevSpan+1);
                while (it.hasPrevious()){
                    Integer tp = it.previous();
                    if(tp<=price)
                        prevSpan++;
                    else
                        break;
                }
            }
            prices.add(price);
            return prevSpan;
        }else {
            prevSpan = 1;
            prices.add(price);
            return 1;
        }
    }


    // 20ms左右  next 操作的均摊复杂度为 O(1)
    /**
     *     官方解 单调栈
     *
     *     Deque<int[]> stack;
     *     int idx;
     *
     *     public StockSpanner() {
     *         stack = new ArrayDeque<int[]>();
     *         stack.push(new int[]{-1, Integer.MAX_VALUE});
     *         idx = -1;
     *     }
     *
     *     public int next(int price) {
     *         idx++;
     *         while (price >= stack.peek()[1]) {
     *             stack.pop();
     *         }
     *         int ret = idx - stack.peek()[0];
     *         stack.push(new int[]{idx, price});
     *         return ret;
     *     }
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/online-stock-span/solutions/1906765/gu-piao-jie-ge-kua-du-by-leetcode-soluti-5cm7/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     */


    @Test
    public void t1(){
        //[[],[1007],[1464],[6977],[453],[5739]]
        int d1 = next(1007);
        System.out.println(d1);

        int d2 = next(1464);
        System.out.println(d2);

        int d3 = next(6977);
        System.out.println(d3);

        int d4 = next(453);
        System.out.println(d4);

        int d5 = next(5739);
        System.out.println(d5);

        //int d6 = next(75);
        //System.out.println(d6);

        //int d7 = next(85);
        //System.out.println(d7);


    }

}
