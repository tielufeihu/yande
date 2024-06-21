package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1812
 * @description 判断国际象棋棋盘中一个格子的颜色
 * @since 2024/6/20 10:24
 */
public class Q1812 {

    public boolean squareIsWhite(String coordinates) {
        return ((coordinates.charAt(0) - 'a') + (coordinates.charAt(1) - '1') % 2) != 0;
    }
}
