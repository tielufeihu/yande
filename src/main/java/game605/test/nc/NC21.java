package game605.test.nc;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC21
 * @description TODO
 * @since 2024/8/27 10:19
 */
public class NC21 {

    public ListNode reverseBetween (ListNode head, int m, int n) {
        if(m == n){
            return head;
        }
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode p1 = null, p2 = null, p = head;
        int idx = 1;
        while (p != null){
            if(idx == m-1){
                p1 = p;
            }else if(idx >= m && idx <= n){
                stack.push(p);
            }
            else if(idx == n+1){
                p2 = p;
                break;
            }
            p = p.next;
            idx ++;
        }
        ListNode reverse = stack.pop();
        ListNode thead = reverse;
        if(p1 != null){
            p1.next = reverse;
        }
        while (!stack.isEmpty()){
            reverse.next = stack.pop();
            reverse = reverse.next;
        }
        reverse.next = p2;
        return m == 1 ? thead : head;
    }

    public static void main(String[] args) {;
        ListNode head = new ListNode(3);
        head.next = new ListNode(5);
        NC21 nc21 = new NC21();
        ListNode listNode = nc21.reverseBetween(head, 1, 2);

        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

}
