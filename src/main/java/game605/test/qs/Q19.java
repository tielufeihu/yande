package game605.test.qs;

/**
 * 删除链表的倒数第N个元素  未解决
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/26 17:30
 **/
public class Q19 {

    // 是不是可以用快慢指针法
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return null;
        if(head.next == null)
            return null;
        ListNode slowP = head.next;
        ListNode fastP = head.next.next;
        return head;
    }

    // 递归
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if(n == 0){
            // 删除这个节点
            head.next = head.next.next;
        }
        removeNthFromEnd2(head.next,0);
        return null;
    }

}
