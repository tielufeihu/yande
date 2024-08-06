package game605.test.qs;

import java.util.Arrays;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q575
 * @description TODO
 * @since 2024/7/31 15:45
 */
public class Q575 {

    public int distributeCandies(int[] candyType) {
        return Math.min(candyType.length / 2, (int) Arrays.stream(candyType).distinct().count());
    }

    public void t1(){

    }

}
