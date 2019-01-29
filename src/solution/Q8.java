package solution;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * <p>
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * <p>
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * <p>
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * <p>
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * <p>
 * 说明：
 * <p>
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，qing返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−231) 。
 *
 * @author foxleezh
 * @date 2019-01-29.
 */
public class Q8 extends BaseQustion {

    /**
     * 判断是否为数字
     */
    boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    /**
     * 解题思路：字符转数字，首先要考虑一些特殊字符，比如空格，加号，减号，数字，不满足条件的要剔除掉
     *
     * 其次要区分正负数，根据加号和减号来判别
     *
     * 然后就是单个字符怎么转换为数字，其实ASCII码都是换顺序排列的，0~9挨到一起，只要字符减去‘0’就变成数字了
     *
     * 在转换过程中如果超过最大最小值，特殊处理
     */
    public int myAtoi(String str) {
        int len = str.length();
        int re = 0;
        int start = 0;
        boolean isNegative = false;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            //剔除空格
            if (c == ' ') {
                continue;
            } else {
                if (c == '-') {
                    isNegative = true;
                    start = i + 1;
                } else if (c == '+') {
                    start = i + 1;
                } else if (isDigit(c)) {
                    start = i;
                } else { //剔除其他字符
                    return 0;
                }
                break;
            }
        }
        for (int i = start; i < len; i++) {
            char c = str.charAt(i);
            //不是数字直接返回
            if (!isDigit(c)) {
                break;
            }
            //字符转为数字
            int pop = (c - '0');
            //超过最大最小值时，返回最大最小值
            if (re > Integer.MAX_VALUE / 10 || (re == Integer.MAX_VALUE / 10 && pop % 10 > 7)) {
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            re = re * 10 + pop;
        }
        if (isNegative) {
            re = ~re + 1;
        }
        return re;
    }

    @Override
    public void solution() {
        System.out.println(myAtoi("2147483648"));
        System.out.println(myAtoi("-2147483648"));
        System.out.println(myAtoi("-42"));
        System.out.println(myAtoi("sdfs -42"));
        System.out.println(myAtoi("42 sdfsdf"));
        System.out.println(myAtoi("845454545489898"));
        System.out.println(myAtoi("-845454545489898"));
    }
}
