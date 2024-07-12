package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2
 * @description TODO
 * @since 2024/7/12 11:42
 */
public class Q2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode head = dummy;
        int flag = 0;
        while (l1 != null || l2 != null) {
            int num = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + flag;
            if(num>=10) flag = 1;
            else flag = 0;
            dummy.val = num % 10;
            l1 = l1!=null?l1.next:null;
            l2 = l2!=null?l2.next:null;
            if(l1!=null || l2!=null){
                dummy.next = new ListNode();
                dummy = dummy.next;
            }
        }
        if(flag==1) dummy.next = new ListNode(1);
        return head;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode sum = new ListNode();
        ListNode head = sum;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int num = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
            carry = num / 10;
            sum.next = new ListNode(num % 10);
            sum = sum.next;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }
        return head.next;
    }




}
