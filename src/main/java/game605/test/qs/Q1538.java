package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1538
 * @description 这肯定是双指针了
 * @since 2024/9/3 14:05
 */
public class Q1538 {

    public int numberOfSubstrings(String s) {
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
        return answer;
    }


    public static void main(String[] args) {
        Q1538 q1538 = new Q1538();
        System.out.println(q1538.numberOfSubstrings("abcabc"));
    }

}
