package game605.test.qs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q113
 * @description 路径总和
 * @since 2024/7/29 14:00
 */
public class Q113 {

    List<List<Integer>> ret = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        dfs(new ArrayList<>(), root, targetSum, 0);
        return ret;
    }

    public void dfs(List<Integer> path, TreeNode root, int targetSum, int currSum) {
        // 出口
        if (root == null){
            return;
        }
        // 如果是叶子节点
        if(root.left == null && root.right == null && currSum + root.val == targetSum){
            path.add(root.val);
            ret.add(new ArrayList<>(path));
            return;
        }

        path.add(root.val);
        currSum += root.val;
        dfs(new ArrayList<>(path), root.left, targetSum, currSum);
        dfs(new ArrayList<>(path), root.right, targetSum, currSum);
    }

    public static void main(String[] args) {
        Q113 q113 = new Q113();
        q113.pathSum(null, 0);
    }

}
