package game605.test.qs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q77
 * @description 组合
 * @since 2024/7/8 11:00
 */
public class Q77 {

    // 不递归应该也能做
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            ret.add(list);
        }
        k--;
        while (k>0){
            List<List<Integer>> temp = new ArrayList<>();
            for (List<Integer> list : ret) {
                int last = list.get(list.size() - 1);
                // 剪枝，判断后面的数还够不够
                if(n-last+1<k){
                    continue;
                }
                for (int i = last+1; i <= n; i++) {
                    List<Integer> newList = new ArrayList<>(list);
                    newList.add(i);
                    temp.add(newList);
                }
            }
            ret = temp;
            k--;
        }
        return ret;
    }

    // 递归回溯
    public List<List<Integer>> combine2(int n, int k) {
        List<List<Integer>> ret = new ArrayList<>();
        backtrack(ret, new ArrayList<Integer>(), 1, n, k);
        return ret;
    }

    private void backtrack(List<List<Integer>> ret, ArrayList<Integer> list, int i, int n, int k) {
        // 是一个结果
        if (list.size() == k) {
            ret.add(new ArrayList<>(list));
            return;
        }
        for (int j = i; j <= n; j++) {
            list.add(j);
            backtrack(ret, list, j + 1, n, k);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        Q77 q77 = new Q77();
        System.out.println(q77.combine(4,2));
    }

}
