package game605.test.qs;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/7 15:29
 **/
public class Q141 {

    // 判断给定链表中是否存在环，如果有环则返回ture否则false
    // AC 但是不满足进阶要求（o1 的空间复杂度，即不使用额外的空间）
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head !=null){
            if(!set.contains(head)){
                set.add(head);
            }
            else
                return true;
        }
        return false;
    }

    // 官方解，使用快慢指针的方法， 实现了 o1 的空间复杂度。
    // 很好理解：
    /**
     * 快慢指针就是一个指针快，一个指针慢。 快指针一次移动两个，慢指针一次移动一个。
     * 如果到头了，两指针都没用相遇则没有环。  如果相遇了，就说明有环、
     * 因为，陷入环内后， 快指针每次都多走 一个距离， 一定会追上后进入环慢指针。 所以一定会相遇
     */
    public boolean hasCycle2(ListNode head) {
        if(head == null || head.next == null)
            return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                // 相遇了
                return true;
            }
        }
        return false;
    }

}
