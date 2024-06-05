package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2086
 * @description 喂食仓鼠的最小食物桶数
 * @since 2024/6/5 15:05
 */
public class Q2086 {

    public static void main(String[] args) {
        System.out.println(new Q2086().minimumBuckets(".HH.H.H.H.."));
    }

    public int minimumBuckets(String hamsters) {
        // 如果有联系三个的，或者起始结束有连续则返回+1
        char[] arr = hamsters.toCharArray();
        int count = 1;
        for (char c: arr) {
            if(c == 'H')
                count++;
            if(count>=3)
                return -1;
            if(c == '.')
                count = 0;
        }
        if(count+1 >= 3)
            return -1;
        count = 0;
        // 贪心的插空
        for (int i=0; i<arr.length; i++) {
            if(arr[i] == 'H'){
                // 情况1 .HH.
                if(i+1<arr.length && arr[i+1] == 'H'){
                  count+=2;
                  i += 2;
                  continue;
                }
                // 情况2 .H.HH
                if(i+2<arr.length && arr[i+1] == '.' && arr[i+2] == 'H'){
                    count += 1;
                    i += 2;
                    continue;
                }
                // 情况3 .H..H
                if(i+2<arr.length && arr[i+1] == '.' && arr[i+2] == '.'){
                    count += 1;
                    i += 2;
                    continue;
                }
                // 情况4 .HH
                if(i+2<arr.length && arr[i] == 'H' && arr[i+1] == 'H'){
                    count++;
                    continue;
                }
                // 情况5 .H end
                if(i == arr.length-1 && arr[i] == 'H'){
                    count++;
                    continue;
                }
            }
        }
        return count;
    }

}
