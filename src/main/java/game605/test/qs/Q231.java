package game605.test.qs;

/**
 * 2的幂
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/25 15:37
 **/
public class Q231 {

    // 递归爆栈（有个递归的标签，我给他当成递归做了）
    public boolean isPowerOfTwo1(int n) {
        if(n == 1)
            return true;
        if(n%2 != 0)
            return false;
        return isPowerOfTwo1(n/2);
    }

    // 优质位运算 O(1)
    public boolean isPowerOfTwo2(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }


}
