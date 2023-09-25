package game605.test.qs;

/**
 *
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/22 11:19
 **/
public class Q101 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        } else if (p == null || q == null) {
            return false;
        } else if (p.val != q.val) {
            return false;
        } else {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }

}
