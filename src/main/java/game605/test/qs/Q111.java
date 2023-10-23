package game605.test.qs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉树的最小深度
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/26 11:56
 **/
class TreeNodeInfo{
    public TreeNode treeNode;
    public int death;

    public TreeNodeInfo(TreeNode node, int death){
        this.treeNode = node;
        this.death = death;
    }

}

public class Q111 {

    // 我的解（官方解改）
    // 我没解出来的主要原因就是 Integer.MAX_VALUE
    public int minDepth(TreeNode root){
        if(root == null)
            return 0;
        return dfs(root);
    }
    public int dfs(TreeNode root) {
        if(root == null)
            return Integer.MAX_VALUE;
        if(root.left == null && root.right == null)
            return 1;
        return Math.min(dfs(root.left), dfs(root.right))+1;
    }


    // 我的解，bfs, 层序
    public int minDepth2(TreeNode root){
        if(root == null)
            return 0;
        // 直接层序遍历，得到的第一个叶子节点就是了
        Queue<TreeNodeInfo> queue = new LinkedList<>();
        queue.offer(new TreeNodeInfo(root, 1));
        while (!queue.isEmpty()){
            TreeNodeInfo node = queue.poll(); // pop
            if(node.treeNode.left==null && node.treeNode.right==null){
                return node.death;
            }
            if(node.treeNode.left!= null)
                queue.offer(new TreeNodeInfo(node.treeNode.left, node.death+1));
            if(node.treeNode.right!= null)
                queue.offer(new TreeNodeInfo(node.treeNode.right,node.death+1));
        }
        return 0;
    }

}
