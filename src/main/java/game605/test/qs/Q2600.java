package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2600
 * @description TODO
 * @since 2024/8/21 11:23
 */
public class Q2600 {

    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (k <= numOnes) {
            return k;
        } else if (k <= numOnes + numZeros) {
            return numOnes;
        } else {
            return numOnes - (k - numOnes - numZeros);
        }
    }

}
