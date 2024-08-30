package game605.test.nc;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Koyou
 * @version 1.0.0
 * @className NC3
 * @description TODO
 * @since 2024/8/16 16:00
 */
public class NC3 {

    // 环形检测， 典型的快慢指针题
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        // 特判
        if (pHead == null) {
            return null;
        }
        // 定义快慢指针
        ListNode slow = pHead;
        ListNode fast = pHead;
        boolean flag = false;
        while (slow!=null && fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                flag = true;
                break;
            }
        }
        // 找到环的入口
        if (flag) {
            // 记录出现的节点
            Set<ListNode> set = new HashSet<>();
            ListNode temp = pHead;
            while (temp != null) {
                if(set.contains(temp)) {
                    return temp;
                }
                set.add(temp);
                temp = temp.next;
            }
        }
        return null;
    }

}



class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        ListNode head = new ListNode(arr[0]);
        ListNode temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new ListNode(arr[i]);
            temp = temp.next;
        }
        return head;
    }

    public static void printList(ListNode listNode) {
        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }
        System.out.println();
    }
}
