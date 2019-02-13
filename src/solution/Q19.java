package solution;

import java.util.ArrayList;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 *
 * @author foxleezh
 * @date 2019-02-13.
 */
public class Q19 extends BaseQustion {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     *  解题思路：这题我们可以用双指针法，先让first指针走n步，然后second指针开始走，当first走到尾部时，second指向的就是倒数第n个
     *
     *  然后再将指向的这个node删除即可
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode result=new ListNode(0);
        result.next=head;
        ListNode first=result;
        ListNode second=result;
        int i=1;
        while (first.next!=null){
            first=first.next;
            //当first走了n步后，second开始走
            if(i>n){
                second=second.next;
            }
            i++;
        }
        //删除倒数第n个node
        second.next=second.next.next;
        return result.next;
    }

    @Override
    public void solution() {
        int[] nums=new int[]{1,2,3,4,5};
        ListNode node=new ListNode(nums[0]);
        ListNode origin=node;
        for (int i = 1; i < nums.length; i++) {
            node.next=new ListNode(nums[i]);
            node=node.next;
        }
        ListNode res=removeNthFromEnd(origin,2);
        ArrayList<Integer> int_res=new ArrayList<>();
        while (res.next!=null){
            int_res.add(res.val);
            res=res.next;
        }
        int_res.add(res.val);
        System.out.println(int_res.toString());
    }
}
