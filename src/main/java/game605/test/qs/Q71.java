package game605.test.qs;

import org.junit.Test;
import org.python.antlr.ast.Str;

import java.util.*;

public class Q71 {
    /**
     * 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
     * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；
     * 两者都可以是复杂相对路径的组成部分。任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。
     * 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
     * 请注意，返回的 规范路径 必须遵循下述格式：
     *     始终以斜杠 '/' 开头。
     *     两个目录名之间必须只有一个斜杠 '/' 。
     *     最后一个目录名（如果存在）不能 以 '/' 结尾。
     *     此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
     * 返回简化后得到的 规范路径 。
     */
    public String simplifyPath(String path) {
        // 数据完整性
        if(path.charAt(0) != '/'){
            path = '/' + path;
        }
        // . 本目录
        // .. 上一级
        // / 进入
        int currentLevel = 1;
        int currentIndex;
        Stack<String> wordStack = new Stack<>();
        StringBuilder sb = new StringBuilder(path);
        // 一次读取若干个连续字符
        for (currentIndex = 0;  currentIndex< sb.length(); currentIndex++) {
            // 读取一次
            char ct = 0;
            int cNum = 0;
            do {
                ct = sb.charAt(currentIndex);
                cNum ++;
                currentIndex ++;
            }while (sb.charAt(currentIndex) == ct);

            // 判断
            if(ct == '.'){
                if(cNum == 1){
                    // 还在当前目录
                }else if(cNum > 1){
                    // 回到上一级
                    currentLevel --;
                    if(currentLevel < 1){
                        return "/";
                    }
                    wordStack.pop();
                }
            }else if(ct == '/'){
                // 读取斜杠后面的单词
                String word = "";
                while (currentIndex<sb.length() && sb.charAt(currentIndex)!='.' && sb.charAt(currentIndex)!='/'){
                    word += sb.charAt(currentIndex);
                    currentIndex++;
                }
                if(word.length() >= 1){
                    wordStack.push(word);
                    currentLevel++; // 层级+1
                }
            }
        }
        String reStr = "";
        if(!wordStack.isEmpty()){
            reStr = "/" + wordStack.pop() + reStr;
        }
        return reStr;
    }


    public String simplifyPath2(String path) {
        Deque<String> stack = new LinkedList<>();
        for (String item : path.split("/")) {
            if (item.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!item.isEmpty() && !item.equals(".")) stack.push(item);
        }
        String res = "";
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("123", "321");
        treeMap.get("123");
        for (String d : stack) res = "/" + d + res;
        return res.isEmpty() ? "/" : res;
    }

    @Test
    public void q71test(){
        System.out.println("q71");
        String res = simplifyPath2("/home//foo/");
        System.out.println(res);
    }

}
