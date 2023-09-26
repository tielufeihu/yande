package game605.test.qs;

import java.util.ArrayList;
import java.util.List;



/**
 * 二叉树的层序遍历
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/26 11:18
 **/
public class Q102 {

    // 层序遍历一次过，简单迭代即可
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 边界判断
        if(root == null)
            return new ArrayList<>();
        List<List<Integer>> resListList = new ArrayList<>();  // 记录结果
        List<TreeNode> currLevelNode = new ArrayList<>(); // 记录每一层的 node
        currLevelNode.add(root); // 录入第一层的node
        while (!currLevelNode.isEmpty()){
            // 记录层的值
            List<Integer> currLevel = new ArrayList<>();
            // 记录下层的node
            List<TreeNode> tns = new ArrayList<>();
            currLevelNode.forEach(e->{
                currLevel.add(e.val);
                if(e.left!=null)
                    tns.add(e.left);
                if(e.right!=null)
                    tns.add(e.right);
            });
            resListList.add(currLevel);
            currLevelNode = tns;
        }
        return resListList;
    }


    // 递归的解法
    public List<List<Integer>> levelOrder2(TreeNode root) {
        if(root==null) {
            return new ArrayList<>();
        }
        //用来存放最终结果
        List<List<Integer>> res = new ArrayList<>();
        dfs(1,root,res);
        return res;
    }

    void dfs(int index,TreeNode root, List<List<Integer>> res) {
        //假设res是[ [1],[2,3] ]， index是3，就再插入一个空list放到res中
        if(res.size()<index) {
            res.add(new ArrayList<>());
        }
        //将当前节点的值加入到res中，index代表当前层，假设index是3，节点值是99
        //res是[ [1],[2,3] [4] ]，加入后res就变为 [ [1],[2,3] [4,99] ]
        res.get(index-1).add(root.val);
        //递归的处理左子树，右子树，同时将层数index+1
        if(root.left!=null) {
            dfs(index+1, root.left, res);
        }
        if(root.right!=null) {
            dfs(index+1, root.right, res);
        }
    }

}
