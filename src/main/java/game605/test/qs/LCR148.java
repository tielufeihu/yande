package game605.test.qs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Koyou
 * @version 1.0.0
 * @className LCR148
 * @description TODO
 * @since 2024/6/14 11:17
 */
public class LCR148 {

    public static void main(String[] args) {
        LCR148 lcr148 = new LCR148();
        int[] putIn = {1,0};
        int[] takeOut = {1,0};
        System.out.println(lcr148.validateBookSequences(putIn,takeOut));
    }

    public boolean validateBookSequences(int[] putIn, int[] takeOut) {
        // 特判
        if(putIn.length == 0){
            return true;
        }
        int p1=1,p2=0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(putIn[0]);
        while (p1<putIn.length && p2<takeOut.length){
            if(!stack.isEmpty() && stack.peek() == takeOut[p2]){
                stack.pop();
                p2++;
            }else {
                stack.push(putIn[p1++]);
            }
        }
        while (p2<takeOut.length){
            if(!stack.isEmpty() && stack.peek() == takeOut[p2]){
                stack.pop();
                p2++;
            }else {
                break;
            }
        }
        return p1==putIn.length && p2==takeOut.length;
    }

}
