package game605.test.qs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 填充每个节点的下一个右侧节点指针 II
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/11/3 16:14
 **/
public class Q117 {

    // 目测层序构建一下即可
    public Node connect(Node root) {
        // 层序
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Node> tempQueue = new LinkedList<>();
            while (!queue.isEmpty()){
                Node node = queue.poll();
                if(node.left!=node) tempQueue.add(node.left);
                if(node.right!=node) tempQueue.add(node.right);
            }
            // 构造next关系
            for (int i = 0; i < tempQueue.size()-1; i++) {
                tempQueue.get(i).next = tempQueue.get(i+1);
            }
            queue.addAll(tempQueue);
        }
        return root;
    }

}


class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
