package solution;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 *
 * @author foxleezh
 * @date 2019-02-24.
 */
public class Q20 extends BaseQustion {


    /**
     * 解题思路：这种对称性问题可以用栈来解决，先往栈里加入一个元素，比如‘(’，如果下一次加入的是‘）’，我们就把栈里的取出来，
     * 如果不是，那么就继续加，直到最后，看栈里是不是空的，如果不是空的说明就不匹配
     * <p>
     * 不过这样必须全部遍历完才知道结果
     * <p>
     * 优化方案是检查‘）’，‘]’,'}'，这些反的符号，如果栈不为空且前一个元素是它对应的符号，那么继续，如果栈为空或不符合，即返回false
     * <p>
     * 由于这里检查的符号就三个，直接用if else来写了，如果符号比较多，可以换作HashMap来写
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            } else if (c == ']') {
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            } else if (c == '}') {
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }


    @Override
    public void solution() {
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("{()[]}"));
        System.out.println(isValid("{(){]}"));
        System.out.println(isValid("(){"));
    }
}
