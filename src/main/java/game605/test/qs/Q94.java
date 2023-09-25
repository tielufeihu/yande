package game605.test.qs;

import java.util.ArrayList;
import java.util.List;
/**
 * 二叉树的中序遍历
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/22 10:37
 **/
public class Q94 {

    public List<Integer> integers = new ArrayList<>();

    // 我记着中序遍历是先遍历左子树再遍历根节点再遍历右子树
    public List<Integer> inorderTraversal(TreeNode root) {
        if(root==null){
            return new ArrayList<>();
        }
        if(root.left!=null)
            inorderTraversal(root.left);

        integers.add(root.val);

        if(root.right!=null)
            inorderTraversal(root.right);
        return integers;
    }

}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
