package solution;

/**
 * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author foxleezh
 * @date 2019-02-24.
 */
public class Q21 extends BaseQustion {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 解题思路：我们可以用归并排序的思想解决，也就是说，将两个有序链表的头结点进行比较，较小的放前面，较大的放后面
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 类似归并排序中的合并过程
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        // 任一为空，直接连接另一条链表
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return head.next;
    }

    /**
     * 根据数组创建ListNode
     */
    ListNode createNode(int[] nums){
        if(nums.length==0){
            return null;
        }
        ListNode head=new ListNode(nums[0]);
        ListNode origin=head;
        for (int i = 1; i < nums.length; i++) {
            ListNode next=new ListNode(nums[i]);
            head.next=next;
            head=next;
        }
        return origin;
    }

    /**
     * 输出ListNode中的值
     */
    String nodeToString(ListNode node){
        StringBuilder re=new StringBuilder();
        while (node.next!=null){
            re.append(node.val);
            re.append("->");
            node=node.next;
        }
        re.append(node.val);
        return re.toString();
    }

    @Override
    public void solution() {
        int[] nums1=new int[]{1,3,5};
        int[] nums2=new int[]{2,4,6};
        System.out.println(nodeToString(mergeTwoLists(createNode(nums1),createNode(nums2))));
    }
}
