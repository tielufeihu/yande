package game605.test.qs;

/**
 * 错误的版本之
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/8 15:04
 **/
public class Q278 {
    boolean isBadVersion(int version){
        return true;
    }

    // 二分的 01 模型， 实质是用二分在一串 (0000001111111111) 找到第一个1
   //public int firstBadVersion(int n) {
   //    int l=1, r = n;
   //    while (l != n){
   //        int mid = (l+r)/2;
   //        if(isBadVersion(mid) == false){

   //        }
   //    }
   //}

    // 分块吧
    public int firstBadVersion2(int n){
        int blockSize = (int)Math.sqrt(n);  // blockSize块大小
        int blockNum = 0; // 当前移动的块数量
        int ret = 1;
        while (blockNum < blockSize){
            int temp = n - blockSize*blockNum;
            if(!isBadVersion(temp)){
                // 如果这个对了，就从此往下挨个找
                temp++;
                while (!isBadVersion(temp)){
                    temp++;
                }
                return temp;
            }
            blockNum++;
        }
        while (!isBadVersion(ret)){
            ret++;
        }
        return ret;
    }

}
