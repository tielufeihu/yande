package game605.test.qs;


import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q105
 * @description 从前序和中序生成二叉树
 * @since 2024/6/13 11:04
 */
public class Q105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int p1 = 1, p2 = 0;
        TreeNode root = new TreeNode(preorder[0]);
        TreeNode rt = root;
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (p1 < preorder.length || !stack.isEmpty()){
            // 移动p1，直到遇到p2的值
            while (preorder[p1] != inorder[p2]){
                TreeNode temp = new TreeNode(preorder[++p1]);
                rt.left = temp;
                stack.push(rt);
            }
            p2++;
            // 弹出栈，直到遇到p2的值
            TreeNode temp = stack.pop();
            while (temp.val != inorder[p2]){
                temp = stack.pop();
            }
            // 更新root
            temp.right = new TreeNode(preorder[++p1]);
            rt = temp.right;
        }
        return root;
    }

}
