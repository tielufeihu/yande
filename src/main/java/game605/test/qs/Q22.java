package game605.test.qs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 括号生成
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/7 16:26
 **/
public class Q22 {

    // bfs
    public List<String> generateParenthesis(int n) {
        List<String> retList = new ArrayList<>();
        if(n == 0){
            return retList;
        }
        retList.add("()");
        if (n == 1){
            return retList;
        }

        List<String> currentLevel = new ArrayList<>();
        currentLevel.add("()");
        for (int i = 1; i < n; i++) {
            currentLevel = getNextLevel(currentLevel);
        }
        retList = currentLevel;
        return retList;
    }

    // 计算下一层的节点
    private List<String> getNextLevel(List<String> currentLevel){
        Set<String> retSet = new HashSet<>();
        currentLevel.forEach(e->{
            // 首先头部尾部可以直接插入一对括号
            retSet.add(e+"()");
            retSet.add("()"+e);

            int elen = e.length();
            for (int i = 1; i < elen; i++) {
                // 只要每次都是成对插入，则一直保持有效
                StringBuilder sb = new StringBuilder(e);
                sb.insert(i, "()");
                retSet.add(sb.toString());
            }
        });
        return new ArrayList<>(retSet);
    }


    // 力扣大神解
    List<String> res = new ArrayList<>();
    public List<String> generateParenthesis2(int n) {
        if(n <= 0){
            return res;
        }
        getParenthesis("",n,n);
        return res;
    }

    private void getParenthesis(String str,int left, int right) {
        if(left == 0 && right == 0 ){
            res.add(str);
            return;
        }
        if(left == right){
            //剩余左右括号数相等，下一个只能用左括号
            getParenthesis(str+"(",left-1,right);
        }else if(left < right){
            //剩余左括号小于右括号，下一个可以用左括号也可以用右括号
            if(left > 0){
                getParenthesis(str+"(",left-1,right);
            }
            getParenthesis(str+")",left,right-1);
        }
    }

}
