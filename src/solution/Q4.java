package solution;

/**
 * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
 * <p/>
 * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * <p/>
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * <p/>
 * 示例 1:
 * <p/>
 * nums1 = [1, 3] <br/>
 * nums2 = [2]<br/>
 * <p/>
 * 则中位数是 2.0<br/>
 * 示例 2:<br/>
 * <p/>
 * nums1 = [1, 2]<br/>
 * nums2 = [3, 4]<br/>
 * <p/>
 * 则中位数是 (2 + 3)/2 = 2.5<br/>
 *
 * @author foxleezh
 * @date 2019-01-23.
 */
public class Q4 extends BaseQustion {

    /**
     * 解题思路：初看这题可以理解为，把两个数组合并后排序，再找到(m+n)/2的数即中位数 <p/>
     * 而有个要求是时间复杂度是log(m+n),我们知道所有排序算法时间复杂最低也只能是nlogn，所以用排序来解决是不行的 <p/>
     * 既然题目是要求找中位数，我们可以从中位数的概念入手，中位数是指在数组中有一半数比它小，一半数比它大 <p/>
     * 由于这两个数组都是有序数组，我们假设为A数组和B数组，如果我们从下标为i的地方把A数组分为两半，左边的数肯定比A[i]小，右边比A[i]大 <p/>
     * 同理把B数组从下标为j的地方分为两半 <br/>
     * left_part          |        right_part<br/>
     * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]<br/>
     * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]<br/>
     * 那只要满足两个条件，我们就可以顺利找到中位数 <br/>
     * 1.len(left_part)==len(right_part) 相当于 i+j=m-i+n-j<br/>
     * 2.min(right_part)>=max(left_part) 相当于 B[j]>=A[i-1] && A[i]>=B[j-1] <br/>
     * 对于第一个条件:<br/>
     * 我们可以推出j=(m+n)/2 - i,也就是i的值确定了，j的值就确定了，也就是说我们只要把i的值确定下来满足第二个条件就行 <br/>
     * 对于第二个条件： <br/>
     * 我们需要满足 B[j]>=A[i-1] && A[i]>=B[j-1]，不满足的条件有两种： <br/>
     * 一是B[j] < A[i-1],说明i的值太大了，你想想A[i]的值肯定大于A[i-1]，如果i继续加大，条件永远无法满足，所以只能减小i <br/>
     * 二是A[i] < B[j-1],说明i的值太小了，需要加大i的值 <br/>
     * 明白了这些，我们只需要根据条件去增加或缩小i的值就可以了，由于是有序数组，我们可以用二分法变化i的值 <p/>
     * 最后我们再看看满足条件B[j]>=A[i-1] && A[i]>=B[j-1]时怎么得到中位数 <br/>
     * max(left_part)=max(A[i-1],B[j-1]); <br/>
     * min(right_part)=min(A[i],B[j]); <br/>
     * 如果数组之和是偶数，中位数=(max(left_part)+min(right_part))/2 <br/>
     * 如果数组之和是奇数，中位数=max(left_part)或者min(right_part)，那取哪个呢，哪边多一个就取哪边 <br/>
     * 我们可以将j=(m+n)/2 - i改为j=(m+n+1)/2 - i来保证j的值大些，从而保证len(left_part)>left(right_part) <br/>
     * 也可以将j=(m+n)/2 - i改为j=(m+n-1)/2 - i来保证j的值小些, 从而保证len(right_part)>left(left_part) <br/>
     * 最后就是一些特殊情况了： <br/>
     * 首先由于j是由i决定的，j=(m+n)/2 - i, i的取值范围是0到m,也就是说j可能的值是(n-m)/2 到(m+n)/2，由于j不能为负，那么反推n>=m <br/>
     * 如果i==0或者i==m时，A数组有一边是没有值的，得特殊处理 <br/>
     * 同理j==0或者j==m时，B数组有一边也是没有值的，得特殊处理 <br/>
     *
     * @return
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        // 保证 m<=n
        if (m > n) {
            int[] temp = A;
            A = B;
            B = temp;
            int tmp = m;
            m = n;
            n = tmp;
        }
        int iMin = 0, iMax = m;
        //保证len(left_part)>len(right_part)
        int halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            // i 的值太大，这里注意i的值不断减小有可能i变为0,j变为n,必须保证大于0,j也必须小于n
            if (i > iMin && B[j] < A[i - 1]) {
                iMax = i - 1;
            }// i 的值太小
            else if (i < iMax && A[i] < B[j - 1]) {
                iMin = i + 1;
            }// i的值刚好
            else {
                int maxLeft = 0;
                //特殊情况i==0，数组A的left_part为空，只能取B的left_part的最大值
                if (i == 0) {
                    maxLeft = B[j - 1];
                } else if (j == 0) {
                    maxLeft = A[i - 1];
                } else {
                    maxLeft = Math.max(A[i - 1], B[j - 1]);
                }
                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = B[j];
                } else if (j == n) {
                    minRight = A[i];
                } else {
                    minRight = Math.min(B[j], A[i]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    @Override
    public void solution() {
        int[] a = new int[]{1, 3, 5, 7};
        int[] b = new int[]{1, 2, 3, 4, 8};
        System.out.println(findMedianSortedArrays(a, b));
    }
}
