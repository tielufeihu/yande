package game605.test.qs;

import java.util.*;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q99
 * @description 恢复二叉树
 * @since 2024/6/13 9:41
 */
public class Q99 {
    /**
     * 题目： 一颗搜索二叉树有两个节点被置换了
     * 解题： 因为搜索二叉树的中序遍历是有序的，所以可以使用中序遍历获得一个有序数组，再找到两个需要交换的位置
     *       ，然后再遍历一遍交换两个值
     */


    /**
     * 解法一： 显式中序遍历
     * @param root
     */
    public void recoverTree(TreeNode root) {
        List<Integer> nums = new ArrayList<>();
        // 获取有序数组
        record(root,nums);
        // 找到需要交换的两个位置
        int[] swap = findSwap(nums);
        // 遍历交换
        swapXY(root,2,swap[0],swap[1]);
    }

    public void record(TreeNode root,List<Integer> nums){
        if(root == null){
            return;
        }
        record(root.left,nums);
        nums.add(root.val);
        record(root.right,nums);
    }

    public int[] findSwap(List<Integer> nums){
        int idx1=-1,idx2=-1;
        for(int i=0;i<nums.size()-1;i++){
            if(nums.get(i) > nums.get(i+1)){
                idx1 = i+1;
                if(idx2 == -1){
                    idx2 = i;
                }else {
                    break;
                }
            }
        }
        return new int[]{nums.get(idx1),nums.get(idx2)};
    }

    public void swapXY(TreeNode root,int count, int x,int y){
        if(root == null){
            return;
        }
        if(root.val == x || root.val == y){
            root.val = root.val == x ? y : x;
            count --;
            if(count == 0){
                return;
            }
        }
        swapXY(root.left,count,x,y);
        swapXY(root.right,count,x,y);
    }


    /**
     * 解法二： 隐式中序遍历（辅助栈）
     * @param root
     */
    public void recoverTree2(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode pred = null, x = null, y = null;
        while (!stack.isEmpty() || root!=null){
            // 把左子树压入栈
            while (root!=null){
                stack.push(root);
                root = root.right;
            }
            // 弹出栈顶元素
            root = stack.pop();
            if(root != null && root.val < pred.val){
                // 记录位置
                y = root;
                if (x == null) {
                    x = pred;
                } else {
                    break;
                }
            }
            pred = root;
            root = root.left;
        }
        // 交换
        TreeNode temp = null;
        temp = x;
        x = y;
        y = temp;
    }
}
