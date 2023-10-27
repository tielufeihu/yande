package game605.test.qs;

import java.util.ArrayList;
import java.util.List;

/**
 * Fizz Buzz
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/24 11:31
 **/
public class Q412 {

    public List<String> fizzBuzz(int n) {
        List<String> ret = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if(i%3==0 && i%5==0){
                ret.add("FizzBuzz");
            }else if(i%3 == 0) {
                ret.add("Fizz");
            }else if(i%5 == 0){
                ret.add("Buzz");
            }else {
                ret.add(String.valueOf(i));
            }
        }
        return ret;
    }

}
