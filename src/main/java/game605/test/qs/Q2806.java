package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2806
 * @description TODO
 * @since 2024/6/12 14:01
 */
public class Q2806 {

    public int accountBalanceAfterPurchase(int purchaseAmount) {
        int ret = 100;
        int consume = purchaseAmount % 10;
        if(consume >= 5){
            ret -= 10;
        }
        ret -= (purchaseAmount / 10 * 10);
        return ret;
    }

}
