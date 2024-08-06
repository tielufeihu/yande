package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q326
 * @description TODO
 * @since 2024/7/31 14:55
 */
public class Q326 {

    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }

}
