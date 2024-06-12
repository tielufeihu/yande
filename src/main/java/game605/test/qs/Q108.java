package game605.test.qs;

import java.util.ArrayList;
import java.util.List;

public class Q108 {

    public TreeNode sortedArrayToBST2(int[] nums) {
        return helper2(nums, 0, nums.length - 1);
    }


    public TreeNode helper2(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper2(nums, left, mid - 1);
        root.right = helper2(nums, mid + 1, right);
        return root;
    }

    // 题解
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // 总是选择中间位置右边的数字作为根节点
        int mid = (left + right + 1) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }


    // 尝试从上往下遍历
    public TreeNode sortedArrayToBST1(int[] nums) {
        if (nums.length < 1){
            return null;
        }
        if (nums.length == 1){
            return new TreeNode(nums[0]);
        }
        TreeNode root = new TreeNode(nums[0]);
        List<TreeNode> currentLevel = new ArrayList<>();
        currentLevel.add(root);
        int currNum = 0;
        boolean lFlag = true;
        for (int i = 1; i < nums.length; i++) {
            if(currNum == currentLevel.size()){
                // 读取下一层
                List<TreeNode> treeNodes = new ArrayList<>();
                currentLevel.forEach(e->{
                    treeNodes.add(e.left);
                    treeNodes.add(e.right);
                });
                currentLevel = treeNodes;
                currNum = 0;
            }

            if(lFlag){
                TreeNode t = currentLevel.get(currNum);
                t.left = new TreeNode(nums[i]);
                lFlag = false;
            }else {
                TreeNode t = currentLevel.get(currNum);
                t.right = new TreeNode(nums[i]);
                currNum ++;
                lFlag = true;
            }
        }
        return root;
    }

}
