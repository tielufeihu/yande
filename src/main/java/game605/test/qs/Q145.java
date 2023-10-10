package game605.test.qs;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的后序遍历
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/10 10:14
 **/
public class Q145 {

    List<Integer> ret = new ArrayList<>();

    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null)
            return ret;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        ret.add(root.val);
        return ret;
    }

}
