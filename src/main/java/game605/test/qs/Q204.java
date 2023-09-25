package game605.test.qs;

/**
 * 反转链表
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/25 16:32
 **/
public class Q204 {

    // 我的
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

}
