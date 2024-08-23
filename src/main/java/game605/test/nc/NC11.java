package game605.test.nc;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC111
 * @description TODO
 * @since 2024/8/23 15:06
 */
public class NC11 {

    public TreeNode sortedArrayToBST (int[] nums) {
        return createTree(nums, 0, nums.length - 1);
    }

    private TreeNode createTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        // 获取中点
        int mid = (start + end + 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = createTree(nums, start, mid - 1);
        root.right = createTree(nums, mid + 1, end);
        return root;
    }


}
