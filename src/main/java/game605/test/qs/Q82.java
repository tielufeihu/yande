package game605.test.qs;

import java.util.Map;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q82
 * @description 删除重复元素
 * @since 2024/7/24 17:59
 */
public class Q82 {

    // 一次遍历
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null){
            return null;
        }
        ListNode headT = head;
        ListNode prev,next;
        prev = head;
        while (prev.next != null){
            next = prev.next;
            if(prev.val == next.val){
                // 删除后面的元素
                prev.next = next.next;
            }
            // 指针后移
            prev = prev.next;
        }
        return headT;
    }

    // 计数
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null){
            return null;
        }
        int[] map = new int[201];
        while (head != null){
            map[head.val + 100]++;
            head = head.next;
        }
        ListNode ret = new ListNode();
        ListNode item = ret;
        for (int i = 0; i < map.length; i++) {
            if(map[i] == 1){
                item.next = new ListNode(i - 100);
                item = item.next;
            }
        }
        return ret.next;
    }

}
