package game605.test.nc;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC6
 * @description TODO
 * @since 2024/8/16 17:01
 */
public class NC6 {

    int ret = Integer.MIN_VALUE;

    public int maxPathSum (TreeNode root) {
        if(root == null) {
            return 0;
        }
        maxPathSum(root.left);
        maxPathSum(root.right);
        int rMax = root.right == null ? 0 : root.right.val;
        int lMax = root.left == null ? 0 : root.left.val;
        root.val = root.val + Math.max(0, Math.max(rMax, lMax));
        ret = Math.max(ret, rMax+lMax+root.val);
        return ret;
    }

}






class TreeNode {
  int val = 0;
  TreeNode left = null;
  TreeNode right = null;
  public TreeNode(int val) {
    this.val = val;
  }
}
