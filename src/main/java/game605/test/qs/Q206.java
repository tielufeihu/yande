package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q206
 * @description 旋转链表
 * @since 2024/9/20 16:41
 */
public class Q206 {

    /**
     * 反转链表（原地算法 ******）
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

}
