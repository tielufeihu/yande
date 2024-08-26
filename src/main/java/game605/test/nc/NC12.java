package game605.test.nc;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC12
 * @description TODO
 * @since 2024/8/26 9:49
 */
public class NC12 {

    // 根据前序遍历和中序遍历重建二叉树
    public TreeNode reConstructBinaryTree (int[] preOrder, int[] vinOrder) {
        // write code here
        // 首先前序遍历第一个节点一定是根节点
        if (preOrder.length == 0) {
            return null;
        }
        return dfs(preOrder, vinOrder, 0, preOrder.length - 1, 0, vinOrder.length - 1);
    }

    public TreeNode dfs (int[] preOrder, int[] vinOrder, int preStart, int preEnd, int vinStart, int vinEnd) {
        TreeNode root = new TreeNode(preOrder[preStart]);
        // 构建左子树右子树
        // 找到根节点在中序遍历中的位置
        int idx = vinStart;
        for (int i = idx; i <= vinEnd; i++) {
            if(vinOrder[i] == preOrder[preStart]) {
                idx = i;
                break;
            }
        }
        // 该位置的左边是左子树，右边是右子树
        // 构建左子树
        if (idx > vinStart) {
            root.left = dfs(preOrder, vinOrder, preStart + 1, preStart + idx - vinStart, vinStart, idx - 1);
        }
        // 构建右子树
        if (idx < vinEnd) {
            root.right = dfs(preOrder, vinOrder, preStart + idx - vinStart + 1, preEnd, idx + 1, vinEnd);
        }
        return root;
    }

}
