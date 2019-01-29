package solution;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 *
 * @author foxleezh
 * @date 2019-01-29.
 */
public class Q7 extends BaseQustion {

    /**
     * 解题思路： 整数反转可以理解为先进后出的栈结构，我们可以先pop一位出来，然后push到另一个变量中
     *
     * pop的话就是%10再/10
     *
     * push的话就是*10再+pop
     *
     * 在push的时候可能会遇到超出位数的情况，我们需要比较一下int的最大最小值
     *
     */
    public int reverse(int x) {
        int result = 0;
        int pop = 0;
        while (x != 0) {
            //pop操作
            pop = x % 10;
            x = x / 10;
            //与最大最小值比较
            if (result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return 0;
            }
            //push操作
            result = result * 10 + pop;
        }
        return result;
    }

    @Override
    public void solution() {
        System.out.println(Integer.MAX_VALUE / 10);
        System.out.println(reverse(-123));
        System.out.println(reverse(120));
        System.out.println(reverse(-2147483641));
        System.out.println(reverse(2147483647));
    }
}
