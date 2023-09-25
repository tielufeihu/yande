package game605.test.qs;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 回文链表
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/25 15:56
 **/

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Q234 {
    // 暂时不知道怎么递归，先遍历解决 速度6%
    public boolean isPalindrome(ListNode head) {
        Deque<Integer> deque = new ArrayDeque<>();  // 双端队列
        forLinkList(deque, head);
        while (deque.size()>1){
            int l = deque.getFirst();
            int r = deque.getLast();
            deque.removeFirst();
            deque.removeLast();
            if(l != r)
                return false;
        }
        return true;
    }

    public void forLinkList(Deque<Integer> deque, ListNode node){
        if(node==null)
            return;
        deque.add(node.val);
        forLinkList(deque, node.next);
    }

    // 链表快慢指针法 寻找中间节点
    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

}
