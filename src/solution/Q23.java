package solution;

/**
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * <p>
 * 示例:
 * <p>
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @author foxleezh
 * @date 2019-02-28.
 */
public class Q23 extends BaseQustion {

    /**
     * 解题思路： 可以按照归并排序的原理，两两归并
     * 时间复杂度为mlogk,m为链表中数字的总个数，k为链表个数
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int k = lists.length;
        while (k > 1) {
            for (int i = 0; i < k / 2; i++) {
                //两两合并
                lists[i] = mergeTwoLists(lists[i], lists[i + (k + 1) / 2]);
            }
            k = (k + 1) / 2;
        }
        return lists[0];
    }

    /**
     * 归并排序，两两合并
     */
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode root = new ListNode(0);
        ListNode node = root;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        if (list1 == null) {
            node.next = list2;
        }
        if (list2 == null) {
            node.next = list1;
        }
        return root.next;
    }

    @Override
    public void solution() {
        ListNode node1=ListNode.createNode(new int[]{1,3});
        ListNode node2=ListNode.createNode(new int[]{2,4,7});
        ListNode node3=ListNode.createNode(new int[]{3,5,9});
        ListNode[] nodes=new ListNode[3];
        nodes[0]=node1;
        nodes[1]=node2;
        nodes[2]=node3;
        ListNode node = mergeKLists(nodes);
        System.out.println(ListNode.nodeToString(node));
    }
}
