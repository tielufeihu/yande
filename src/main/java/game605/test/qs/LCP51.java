package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className LCP51
 * @description 烹饪料理
 * @since 2024/7/18 10:17
 */
public class LCP51 {

    int max = -1;
    int n;

    public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
         n = cookbooks.length;
        int[] currM = materials.clone();
        dfs(currM, cookbooks, attribute, limit, 0,0);
        return max;
    }

    public void dfs(int[] currM, int[][] cookbooks, int[][] attribute, int limit, int curr, int index) {
        if(limit<=0){
            max = Math.max(max, curr);
        }
        if(index >= n){
            return;
        }
        int[] clone = currM.clone();
        // 每个节点有选和不选两种
        // 不选
        dfs(clone, cookbooks, attribute, limit, curr, index+1);
        // 选
        for (int i = 0; i < clone.length; i++) {
            clone[i] -= cookbooks[index][i];
            if(clone[i]<0){
                return;
            }
        }
        curr  += attribute[index][0];
        limit -= attribute[index][1];
        // 下一步
        dfs(clone, cookbooks, attribute, limit, curr, index+1);
    }


}
