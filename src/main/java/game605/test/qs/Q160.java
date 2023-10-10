package game605.test.qs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 相交链表
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/10 10:32
 **/
public class Q160 {

    // 链表就线性找呗
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1!=null || p2!=null){
            if (p1!=null){
                if (set.contains(p1))
                    return p1;
                else
                    set.add(p1);
            }
            if (p2!=null){
                if (set.contains(p2))
                    return p2;
                else
                    set.add(p2);
            }
            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        return null;
    }

}
