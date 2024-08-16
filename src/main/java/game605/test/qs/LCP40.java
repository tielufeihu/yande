package game605.test.qs;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class LCP40 extends BaseQ{

    public int maxmiumScore(int[] cards, int cnt) {
        PriorityQueue<Integer> oddList = new PriorityQueue<>();
        PriorityQueue<Integer> evenList = new PriorityQueue<>();
        for (int card : cards) {
            if(card % 2 == 0) evenList.add(card);
            else oddList.add(card);
        }
        // 尽可能多用单数
        if(cnt == 1) return evenList.stream().mapToInt(e->e).sum();

        this.doSomething2();
        return 0;
    }

    public static void main(String[] args) {
    }



}
