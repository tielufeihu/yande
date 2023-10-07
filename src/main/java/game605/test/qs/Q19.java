package game605.test.qs;

/**
 * 删除链表的倒数第N个元素  未解决
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/9/26 17:30
 **/
public class Q19 {

    // 是不是可以用快慢指针法 TODO
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null)
            return null;
        if(head.next == null)
            return null;
        ListNode slowP = head.next;
        ListNode fastP = head.next.next;
        return head;
    }

    // 递归  TODO
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        if(n == 0){
            // 删除这个节点
            head.next = head.next.next;
        }
        removeNthFromEnd2(head.next,0);
        return null;
    }

    // 暴力解得了
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        // 获取长度
        int len = 0;
        ListNode p = head;
        while (p!=null){
            len++;
            p = p.next;
        }
        int delIdx = len-n+1;
        // 判断是不是第一个
        if(delIdx == 1){
            return head.next;  // 如果删除的是第一个节点，直接返回第二个指针即可
        }

        int idx = 1;
        ListNode prep = head;
        p = head.next;
        while (p!=null){
            idx++;
            if(idx == delIdx){
                // 删除这个节点
                prep.next = p.next;
                break;
            }
            prep = p;
            p = p.next;
        }
        return head;
    }

}
