package game605.test.qs;

/**
 * @author Koyou
 * @version 1.0.0
 * @className Q2181
 * @description TODO
 * @since 2024/9/9 11:29
 */
public class Q2181 {

    // 不要求原地算法就比较好做
    public ListNode mergeNodes(ListNode head) {
        // 哑结点
        ListNode root = new ListNode(0);
        ListNode item = root;

        ListNode cur = head;
        int temp = 0;
        while (cur != null) {
            if(cur.val == 0){
                if(temp != 0){
                    ListNode node = new ListNode(temp);
                    item.next = node;
                    item = item.next;
                    temp = 0;
                }
            }else {
                temp += cur.val;
            }
            cur = cur.next;
        }
        return root.next;
    }

}
