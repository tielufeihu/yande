package game605.test.qs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 树的层平均值
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/15 23:49
 **/
public class Q637 {

    public List<Double> averageOfLevels(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        deque.add(root);
        List<Double> ret = new ArrayList<>();
        while (!deque.isEmpty()){
            Deque<TreeNode> tDeque = new LinkedList<>();
            int count = 0;
            int sum = 0;
            Double avg = 0.0;
            while (!deque.isEmpty()){
                TreeNode node = deque.poll();
                count++;
                sum += node.val;
                if(node.left!=null)
                    tDeque.add(node.left);
                if(node.right!=null)
                    tDeque.add(node.right);
            }
            // 计算这一层的平均值
            avg = ((double) sum) / count;
            ret.add(avg);
            // 切换下一层
            deque = tDeque;
        }
        return ret;
    }

}
