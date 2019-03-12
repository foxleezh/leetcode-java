package solution;

/**
 *
 * 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 *
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 *
 * 示例 :
 *
 * 给定这个链表：1->2->3->4->5
 *
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 *
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 *
 * 说明 :
 *
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * @author foxleezh
 * @date 2019-03-12.
 */
public class Q25 extends BaseQustion{

    /**
     * 解题思路：这题考虑用三指针法，假设链表是(-1)->1->2->3->4->5, -1是我们新增的， 第一个指针放pre(-1)，第二个指针放cur(1),第三个指针放post（cur的下一个）
     *
     * 先重点关注下翻转，我们先试着把链表整个翻转
     *
     * 那么我们先找到2，将它放pre(-1)后面，顺序变为-1->2->1->3->4->5
     *
     * 再找到3，将它放pre(-1)后面，顺序变为-1->3->2->1->4->5
     *
     * 再找到4，将它放pre(-1)后面，顺序变为-1->4->3->2->1->5
     *
     * 最后找到5，将它放pre(-1)后面，顺序变为-1->5->4->3->2->1
     *
     * 这个过程像个传送带，永远把cur(1)后面的数字抽出来，放到pre(-1)后面，其他顺序保持不变，不断滚动，这过程中pre和cur的值是不变的，post一直在变
     *
     * 然后我们再关注分段的问题，一共是分成num/k段
     *
     * 每段翻转结束后，变更一个pre和cur的值
     */
    ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(-1);
        ListNode pre = dummy;
        ListNode cur = pre;
        ListNode post;
        dummy.next = head;
        int num = 0;
        while ((cur = cur.next)!=null){
            ++num;
        }
        while (num >= k) {
            cur = pre.next;//变更cur的值
            for (int i = 1; i < k; ++i) {
                post = cur.next; //变更post为cur的下一个
                cur.next = post.next;//将cur的下一个链接到post的下一个，相当于抽出post
                post.next = pre.next;//将抽出的post链接到cur
                pre.next = post;//将pre链接到抽出的post
            }
            pre = cur;//变更pre的值
            num -= k;
        }
        return dummy.next;
    }

    @Override
    public void solution() {
        System.out.println(ListNode.nodeToString(reverseKGroup(ListNode.createNode(new int[]{1,2,3,4,5}),4)));
    }
}
