package game605.test.qs;

/**
 * 猜数大小
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/24 11:12
 **/
public class Q374 {

    public int guess(int num){
        return 0;
    }

    public int guessNumber(int n) {
        // 二分就行了
        int begin = 1;
        int end = n;
        while (begin <= end){
            int mid = begin+(-begin+end)/2;
            int t = guess(mid);
            if(t>0){
                // 区间向右边移动
                begin = mid+1;
            }else if(t==0){
                return mid;
            }else {
                // 区间向左移动
                end = mid-1;
            }
        }
        return begin;
    }

}
