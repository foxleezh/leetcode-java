package solution;

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p/>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p/>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p/>
 * 示例：
 * <p/>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * <p/>
 * 输出：7 -> 0 -> 8
 * <p/>
 * 原因：342 + 465 = 807
 * <p/>
 *
 * @author foxleezh
 * @date 2019-01-22.
 */
public class Q2 extends BaseQustion {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 解题思路：这题的实质就是加法，加法的话就是涉及到进位的问题，所以我们就用一个变量来保存下进位
     * <p/>
     * 在遍历过程中将每一位的数字对应相加，比如个位加个位，十位加十位，然后再加进位
     * <p/>
     * 当有对不上位的时候，比如一个三位数+四位数这种，三位数的千位则不加
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode result = new ListNode(0);
        ListNode origin = result;
        while (l1 != null || l2 != null || carry != 0) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            ListNode node = new ListNode(carry % 10);
            result.next = node;
            result = node;
            carry /= 10;
        }
        return origin.next;
    }

    @Override
    public void solution() {
        ListNode node1=new ListNode(2);
        ListNode node2=new ListNode(4);
        ListNode node3=new ListNode(3);
        node1.next=node2;
        node2.next=node3;
        ListNode node4=new ListNode(5);
        ListNode node5=new ListNode(6);
        ListNode node6=new ListNode(4);
        node4.next=node5;
        node5.next=node6;
        ListNode re=addTwoNumbers(node1,node4);
        while(re!=null){
            System.out.println(re.val);
            re=re.next;
        }

    }
}
