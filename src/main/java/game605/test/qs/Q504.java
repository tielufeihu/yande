package game605.test.qs;

import org.junit.Test;

/**
 * 七进制数
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/24 14:15
 **/
public class Q504 {

    public String convertToBase7(int num) {
        if(num>0 && num <= 6)
            return String.valueOf(num);
        StringBuilder ret = new StringBuilder();
        while (num!=0){
            ret.insert(0, num % 7);
            num/=7;
        }
        if(ret.charAt(0) == '-'){
            String r = ret.toString();
            r = r.replace("-","");
            r = "-" + r;
            return r;
        }
        return ret.toString();
    }

    @Test
    public void t1(){
        System.out.println(convertToBase7(-8));
    }

}
