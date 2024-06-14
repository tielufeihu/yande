package game605.test.qs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q1379
 * @description TODO
 * @since 2024/6/12 16:42
 */
public class Q1379 {

    /**
     * 深度优先递归算法
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // 退出条件
        if(cloned == null){
            return null;
        }

        // 判断是否找到
        if(original.equals(target)){
            return cloned;
        }

        // 递归
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if(left != null){
            return left;
        }
        TreeNode right = getTargetCopy(original.right, cloned.right, target);
        return right;
    }

    /**
     * 广度优先算法
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy2(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        // 广搜original
        Queue<TreeNode> queue = new LinkedList<>();
        // 根放入队列
        queue.offer(cloned);
        // 层节点不未空继续循环
        while (!queue.isEmpty()){
            List<TreeNode> nextLevel = new LinkedList<>();
            while (!queue.isEmpty()){
                TreeNode node = queue.poll();
                if(node.val == target.val){
                    return node;
                }
                if(node.left != null){
                    nextLevel.add(node.left);
                }

                if(node.right != null){
                    nextLevel.add(node.right);
                }
            }
            queue.addAll(nextLevel);
        }
        return null;
    }

}
