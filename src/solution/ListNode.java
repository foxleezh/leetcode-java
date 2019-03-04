package solution;

/**
 * @author foxleezh
 * @date 2019-03-04.
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    static ListNode createNode(int[] nums) {
        ListNode node = new ListNode(0);
        ListNode origin = node;
        for (int i = 0; i < nums.length; i++) {
            ListNode next = new ListNode(nums[i]);
            node.next = next;
            node = node.next;
        }
        return origin.next;
    }

    /**
     * 输出ListNode中的值
     */
    static String nodeToString(ListNode node) {
        StringBuilder re = new StringBuilder();
        while (node.next != null) {
            re.append(node.val);
            re.append("->");
            node = node.next;
        }
        re.append(node.val);
        return re.toString();
    }
}
