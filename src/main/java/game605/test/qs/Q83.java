package game605.test.qs;

import java.util.HashSet;
import java.util.Set;

/**
 * 删除排序链表中的重复元素
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/7 11:50
 **/
public class Q83 {

    // 不就去重吗，直接set去重
    public ListNode deleteDuplicates(ListNode head) {
        // 边界条件
        if(head==null || head.next==null){
            return head;
        }
        ListNode p = head;
        Set<Integer> set = new HashSet<>();
        set.add(p.val);
        while (p.next != null){
            if(!set.contains(p.next.val)){
                p = p.next;
                set.add(p.next.val);
                continue;
            }
            // 删除这个 head.next元素
            if(p.next.next!=null)
                p.next = p.next.next;
            else
                p.next = null;
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode pre = head;  //pre指向cur的前一个节点
        ListNode cur = head.next;  //cur指向pre的下一个节点
        while(cur != null){
            if(pre.val == cur.val){ //相邻节点值相等，则修改pre和cur的指向
                cur = cur.next;
                pre.next = cur;
                continue;
            }
            //不相等 pre和cur分别向后移动
            pre = cur;
            cur = cur.next;
        }
        return head;
    }

}
