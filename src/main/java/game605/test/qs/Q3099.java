package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q3099
 * @description TODO
 * @since 2024/7/3 9:34
 */
public class Q3099 {

    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = 0;
        int temp = x;
        while (temp != 0){
            sum += temp % 10;
            temp /= 10;
        }
        return x%sum==0?sum:-1;
    }

}
