package game605.test.nc;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC13
 * @description 二叉树的最大深度
 * @since 2024/8/26 10:29
 */
public class NC13 {

    public int maxDepth (TreeNode root) {
        if(root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }

}
