package game605.test.qs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q61
 * @description 旋转链表
 * @since 2024/7/29 17:33
 */
public class Q61 {

    // 借助额外的结构
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null){
            return null;
        }
        if (k == 0){
            return head;
        }
        Deque<Integer> temp = new ArrayDeque<>();
        while (head != null) {
            temp.addLast(head.val);
            head = head.next;
        }
        int len = temp.size();
        k %= len;
        for (int i = 0; i < k; i++) {
            Integer num = temp.pollLast();
            temp.addFirst(num);
        }
        // 生成链表(哑节点)
        ListNode hNode = new ListNode();
        ListNode tempNode = hNode;
        for (int i = 0; i < len; i++) {
            tempNode.next = new ListNode(temp.pollFirst());
            tempNode = tempNode.next;
        }
        return hNode.next;
    }

    // 可不可以不借助呢, 可以 使用二分算法分治
    public ListNode rotateRight2(ListNode head, int k){
        return null;
    }
}
