package game605.test.qs;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的前序遍历
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/10 9:59
 **/
public class Q144 {

    List<Integer> ret = new ArrayList<>();

    public List<Integer> preorderTraversal(TreeNode root) {
        // 前序遍历是 根 左 右
        if(root == null){
            return ret;
        }
        // 前序遍历是 左 根 右
        ret.add(root.val);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return ret;
    }

}
