package game605.test.qs;


/**
 * 统计能整除数字的位数
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/26 3:22
 **/
public class Q2520 {

    public int countDigits(int num) {
        int temp = num;
        int ret = 0;
        while (temp!=0){
            int t = temp%10;
            temp/=10;
            if(t==0) continue;
            if(num%t==0) ret++;
        }
        return ret;
    }

}
