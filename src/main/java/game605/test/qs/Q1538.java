package game605.test.qs;

import org.junit.Test;
import org.springframework.scheduling.annotation.Async;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1538
 * @description 这肯定是双指针了
 * @since 2024/9/3 14:05
 */
public class Q1538 {

    @Async
    public CompletableFuture<Integer> numberOfSubstrings(String s) throws InterruptedException {
        System.out.println("111111111111");
        int answer=0;
        //abc 的计数
        int[] count=new int[3];
        //窗口左沿
        int start=0;
        //窗口右沿
        for(int end=0;end<s.length();end++){
            char charAtEnd=s.charAt(end);
            count[charAtEnd-'a']++;
            while(count[0]>=1 && count[1]>=1 && count[2]>=1){
                answer+=s.length()-end;
                char charAtStart=s.charAt(start);
                count[charAtStart-'a']--;
                start++;
            }
        }
        for (int i = 0; i < 10000000; i++) {
            i++;
            int t = i;
            t++;
            t++;
            i = t;
            i--;
            i--;
            i--;
        }
        Thread.sleep(1000);
        System.out.println("111111111111");
        return CompletableFuture.completedFuture(answer);
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Q1538 q1538 = new Q1538();
        Future<Integer> f = q1538.numberOfSubstrings("abcabc");
        System.out.println("2222222222222222");
        System.out.println(f.get());
    }


    @Test
    public void test() throws InterruptedException, ExecutionException {
        Q1538 q1538 = new Q1538();
        Future<Integer> f = q1538.numberOfSubstrings("abcabc");
        System.out.println("2222222222222222");
        System.out.println(f.get());
    }

}
