package game605.test.qs;

/**
 * 两两交换链表中的节点
 * @version 0.1.0
 * @author 孙铁义
 * @since 0.1.0
 * @create 2023/10/11 20:34
 **/
public class Q24 {

    // 理解错题意了， 两两交换我以为是四个一组
    public ListNode swapPairs(ListNode head) {
        if(head==null)
            return head;
        // 应该是每四个一组吧
        ListNode ret = head;
        ListNode p0 = null;
        ListNode p1 = head;
        ListNode p2,p3,p4;
        if (p1.next!=null)
            p2 = p1.next;
        else
            return head;
        if (p2.next!=null)
            p3 = p2.next;
        else
            return head;
        if (p3.next!=null)
            p4 = p3.next;
        else
            return head;
        // 执行两两交换
        while (p1!=null && p2!=null && p3!=null && p4!=null){
            ListNode end = p4.next;
            p2.next = p1;
            p1.next = p4;
            p4.next = p3;
            p3.next = end;
            if (p0!=null){
                p0.next = p2;
                ret = p2;
                p0 = p3;
            }else {
                p0 = p3;
            }
            p1 = p0.next;
            if (p1.next==null)
                return ret;
            p2 = p1.next;
            if (p2.next==null)
                return ret;
            p3 = p2.next;
            if (p3.next==null)
                return ret;
            p4 = p3.next;
        }
        return ret;
    }

    

}
