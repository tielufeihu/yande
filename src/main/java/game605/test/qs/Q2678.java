package game605.test.qs;

import org.junit.Test;

/**
 * 老人的数目
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/23 10:25
 **/
public class Q2678 {

    public int countSeniors(String[] details) {
        int ret = 0;
        for (String detail : details) {
            String age = detail.substring(11,13);
            if(Integer.parseInt(age)>60)
                ret++;
        }
        return ret;
    }

    @Test
    public void t1(){
        System.out.println(countSeniors(new String[]{"7868190130M7522","5303914400F9211","9273338290F4010"}));
    }



}
