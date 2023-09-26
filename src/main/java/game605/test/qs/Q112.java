package game605.test.qs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 递归+二叉树 路径总和
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/26 15:49
 **/
class TreeNodeDetail{
    public TreeNode treeNode;
    public int sum;

    public TreeNodeDetail(TreeNode node, int sum){
        this.treeNode = node;
        this.sum = sum;
    }
}

public class Q112 {

    public boolean flag = false;

    // dfs 一点不会
    public boolean hasPathSum(TreeNode root, int targetSum) {
        dfs(root, root.val, targetSum);
        return flag;
    }

    public void dfs(TreeNode root, int currSum, int targetSum){
        if(root.left==null && root.right==null){
            if (currSum == targetSum)
                flag = true;
            return;
        }
        if(root.right!=null){
            dfs(root.right, currSum+root.right.val,targetSum);
        }
        if(root.left!=null){
            dfs(root.left, currSum+root.left.val,targetSum);
        }
    }

    // bfs
    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if(root == null)
            return false;
        Queue<TreeNodeDetail> queue = new LinkedList<>();
        queue.add(new TreeNodeDetail(root, root.val));
        while (!queue.isEmpty()){
            TreeNodeDetail node = queue.poll();
            if(node.treeNode.left == null && node.treeNode.right==null){  // 是叶子
                if(targetSum == node.sum)
                    return true;
            }
            if(node.treeNode.left!= null){
                queue.add(new TreeNodeDetail(node.treeNode.left, node.sum+node.treeNode.left.val));
            }
            if(node.treeNode.right!= null){
                queue.add(new TreeNodeDetail(node.treeNode.right, node.sum+node.treeNode.right.val));
            }
        }
        return false;
    }

}
