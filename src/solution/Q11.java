package solution;

/**
 * 给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 *
 *
 *
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 *
 *
 * 示例:
 *
 * 输入: [1,8,6,2,5,4,8,3,7]
 * 输出: 49
 *
 * @author foxleezh
 * @date 2019-01-30.
 */
public class Q11 extends BaseQustion{

    /**
     * 解题思路：这题可以用暴力法，两次循环把所有可能找出来，然后一一比较，时间复杂度为O(n^2)
     *
     * 另外可以用双指针法，头尾一起往中间走，我们知道水桶是由短的一根决定，那我们可以比较首尾两根，将较短的一根往中间移动
     *
     * 因为如果移动长的那根，高度不会变，长度在缩短，面积肯定是变小的，只有移动短的那根，才有可能把高度增加，让面积变大
     *
     */
    public int maxArea(int[] height) {
        int len=height.length;
        int max=0;
        int temp;
        for (int i = 0,j=len-1; i < j;) {
            if(height[i]<height[j]){
                temp=(j-i)*height[i++];
            }else {
                temp=(j-i)*height[j--];
            }
            max=temp>max?temp:max;
        }
        return max;
    }

    @Override
    public void solution() {
        int[] a=new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(a));
    }
}
