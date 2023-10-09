package game605.test.qs;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 股价跨度， 分块的解法
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/8 14:42
 **/
public class Q901B {

    // 使用分块的策略，把每100天的价格作为一个块，开另一个数组记录每一块最大的数
    ArrayList<Integer> prices;
    ArrayList<Integer> region;  // 100天分为一块

    int count = 0;

    public Q901B(){
        prices = new ArrayList<>();
        region = new ArrayList<>();
    }

    public int next(int price){
        count++;
        //if(region.size()==0 || region.get(0))

        return 1;
    }

}
