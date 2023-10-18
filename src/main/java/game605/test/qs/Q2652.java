package game605.test.qs;

/**
 * 倍数求和
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/17 11:42
 **/
public class Q2652 {

    // 枚举
    public int sumOfMultiples1(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            if(i%3==0 || i%5==0 || i%7==0){
                sum += i;
            }
        }
        return sum;
    }

    // 我意识到，是等差数列了，但是忘了一个数只能被加一次
    public int sumOfMultiples(int n) {
        int sum = 0;
        sum = n*3;
        int diff = sum % 7;
        sum -= diff+(diff/5)*5+(diff/3)*3;
        return sum;
    }

}
