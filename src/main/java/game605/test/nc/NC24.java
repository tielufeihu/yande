package game605.test.nc;

import java.util.TreeSet;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC24
 * @description TODO
 * @since 2024/8/27 11:08
 */
public class NC24 {

    /**
     * 先不写了
     * @param head
     * @return
     */
    public ListNode deleteDuplicates (ListNode head) {
        // write code here
        if (head == null) {
            return null;
        }
        TreeSet<Integer> set = new TreeSet<>();
        while (head != null) {
            set.add(head.val);
            head = head.next;
        }
        ListNode p = new ListNode(0);
        for (Integer i : set) {
            p.next = new ListNode(i);
            p = p.next;
        }
        return p.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3};
        NC24 nc24 = new NC24();
        ListNode listNode = nc24.deleteDuplicates(null);
        System.out.println(listNode);
    }

}
