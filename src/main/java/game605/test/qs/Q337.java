package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q337
 * @description 打家劫舍3
 * @since 2024/7/12 10:57
 */
public class Q337 {

    // dfs暴搜，每个节点有要于不要两种状态
    public int rob(TreeNode root) {
        return Math.max(dfs(root,0,true), dfs(root,0,false));
    }

    /**
     *
     * @param root
     * @param flag 代表要与不要
     * @return
     */
    public int dfs(TreeNode root, int ret, boolean flag) {
        if(root == null){
            return 0;
        }
        if(flag){
            // 如果不要，那子节点就要
            ret += Math.max(dfs(root.right,ret,true), dfs(root.left,ret,true));
        }else {
            ret += root.val;
            // 如果要，那子节点就不要
            ret += Math.max(dfs(root.right,ret,false), dfs(root.left,ret,false));
        }
        return ret;
    }

}
