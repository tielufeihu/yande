package game605.test.qs;

import org.python.antlr.runtime.tree.Tree;

public class Q110 {

    // 计算深度，效率较低
    public boolean isBalanced1(TreeNode root) {
        if(root == null)
            return true;
        return Math.abs(death(root.left) - death(root.right)) <= 1 && isBalanced1(root.left) && isBalanced1(root.right);
    }

    public int death(TreeNode node){
        if(node == null)
            return 0;
        return Math.max(death(node.left), death(node.right)) + 1;
    }

    public boolean isBalanced(TreeNode root) {
        return recur(root)!=-1;
    }

    public int recur(TreeNode node){
        if(node == null)
            return 0;
        int left = recur(node.left);
        // 剪枝
        if(left == -1)
            return -1;
        int right = recur(node.right);
        // 剪枝
        if(right == -1)
            return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

}
