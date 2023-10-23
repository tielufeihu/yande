package game605.test.qs;

/**
 * 根据规则将箱子分类
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/20 1:18
 **/
public class Q2525 {

    public String categorizeBox(int length, int width, int height, int mass) {
        String ret;
        long volume = ((long) length)  * width * height;
        int maxDims = (Math.max(Math.max(length,width), height));
        boolean bulkyFlag = false;
        boolean heavyFlag = false;
        if(volume>=1000000000L || maxDims>=10000){
            bulkyFlag = true;
        }
        if(mass>=100){
            heavyFlag = true;
        }
        if(heavyFlag && bulkyFlag)
            ret = "Both";
        else if(!heavyFlag && bulkyFlag){
            ret = "Bulky";
        }else if(heavyFlag){
            ret = "Heavy";
        }else {
            ret = "Neither";
        }
        return ret;
    }

}
