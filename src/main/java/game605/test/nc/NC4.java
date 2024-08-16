package game605.test.nc;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC4
 * @description TODO
 * @since 2024/8/16 16:15
 */
public class NC4 {

    public boolean hasCycle(ListNode head) {
        // 特判
        if (head == null) {
            return false;
        }
        // 定义快慢指针
        ListNode slow = head;
        ListNode fast = head;
        boolean flag = false;
        while (slow!=null && fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                flag = true;
                break;
            }
        }
        return flag;
    }

}
