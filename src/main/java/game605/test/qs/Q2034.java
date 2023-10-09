package game605.test.qs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * 股票价格波动
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/8 16:29
 **/
public class Q2034 {

    // 使用一个TreeMap 记录不同时间戳的股价 <时间戳，价格>, 按照时间戳排序
    TreeMap<Integer, Integer> priceMap;

    // 用一个双向链表， 维护一个价格序列(存key: 即时间戳)  这都能超时
    // 其实思路没错，只是选取的数据结构太慢了，一个链表
    //LinkedList<Integer> priceOdIdx;


    // 第一时间没有想到可以用  TreeMap<price, count> 的方法解决price多次出现的问题
    TreeMap<Integer, Integer> priceCountMap;  // 使用这个数据结构过了，但是只击败15 并不快

    // 官方解中有优先队列的解法，显然优先队列在有序插入上更具优势。


    public Q2034() {
        priceMap = new TreeMap<>();
        //priceOdIdx = new LinkedList<>();
        priceCountMap = new TreeMap<>();
    }

    public void update(int timestamp, int price) {
        if(priceMap.containsKey(timestamp)) {
            // 删除 价格排序索引 相应的项
            // priceOdIdx.remove(new Integer(timestamp));
            // 原价格map  -1
            int oldP = priceMap.get(timestamp);
            int t = priceCountMap.get(oldP) - 1;
            if(t==0)
                priceCountMap.remove(oldP);
            else
                priceCountMap.put(oldP,t);
        }
        priceMap.put(timestamp, price);

        if(priceCountMap.containsKey(price)){
            priceCountMap.put(price,priceCountMap.get(price)+1);
        }else {
            priceCountMap.put(price,1);
        }

        //if(priceOdIdx.isEmpty()){
        //    priceOdIdx.add(timestamp);
        //}else {
        //    // 有序插入索引
        //    // 获取迭代器 从头开始
        //    ListIterator<Integer> it = priceOdIdx.listIterator();
        //    int currIdx = 0;
        //    while (it.hasNext() && price > priceMap.get(it.next())){
        //        currIdx++;
        //    }
        //    priceOdIdx.add(currIdx, timestamp);
        //}

    }

    public int current() {
        return priceMap.lastEntry().getValue();
    }

    public int maximum() {
        //return priceMap.get(priceOdIdx.getLast());
        return priceCountMap.lastKey();
    }

    public int minimum() {
        //return priceMap.get(priceOdIdx.getFirst());
        return priceCountMap.firstKey();
    }

}

