package game605.test.qs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的锯齿形层序遍历
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/17 16:05
 **/
public class Q103 {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null)
            return new ArrayList<>();
        Deque<TreeNode> levelDeque = new LinkedList<>();
        boolean lrFlag = true;   // 左右标识，一个左边出一个右边出
        // 初始状态
        levelDeque.add(root);
        List<List<Integer>> ret = new ArrayList<>();
        while (!levelDeque.isEmpty()){
            Deque<TreeNode> nextLevel = new LinkedList<>();
            LinkedList<Integer> currLevel = new LinkedList<>();
            while (!levelDeque.isEmpty()){
                TreeNode node = null;
                node = levelDeque.poll();
                if(lrFlag){
                    currLevel.offerLast(node.val);
                }else
                    currLevel.offerFirst(node.val);
                if(node.left != null){
                    nextLevel.add(node.left);
                }
                if(node.right != null){
                    nextLevel.add(node.right);
                }
            }
            lrFlag = !lrFlag;   // 状态切换
            ret.add(currLevel);  // 结果加入结果集
            levelDeque = nextLevel;  // 切换到下一层
        }
        return ret;
    }

}
