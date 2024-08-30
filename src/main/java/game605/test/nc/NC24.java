package game605.test.nc;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
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
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<>();
        while (head != null) {
            map.put(head.val, map.getOrDefault(head.val, 0) + 1);
            head = head.next;
        }
        ListNode thead = new ListNode(0);
        ListNode p = thead;
        for (Map.Entry<Integer,Integer> i : map.entrySet()) {
            if(i.getValue() != 1){
                continue;
            }
            p.next = new ListNode(i.getKey());
            p = p.next;
        }
        return thead.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 2, 3, 3};
        NC24 nc24 = new NC24();
        ListNode head = nc24.deleteDuplicates(ListNode.createList(arr));
        ListNode listNode = nc24.deleteDuplicates(head);
        ListNode.printList(listNode);
    }

}
