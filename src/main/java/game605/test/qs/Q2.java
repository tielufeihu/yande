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
            if(num>=10){
                flag = 1;
            }else {
                flag = 0;
            }
            if(dummy!=null){
                dummy.val = num%10;
            }else {
                dummy = new ListNode(num%10);
            }
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


}
