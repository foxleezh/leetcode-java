package solution;

import java.util.ArrayList;

/**
 * 给定一个字符串 (s) 和一个字符pattern (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * @author foxleezh
 * @date 2019-01-29.
 */

public class Q10 extends BaseQustion {

    /**
     * 解题思路：当pattern中的下一个字符不是”*”时：
     *  1、 如果字符串当前字符和pattern中的当前字符相匹配，或者pattern字符是”.”,那么字符串指针和pattern指针都后移一个字符
     *  2、 如果字符串当前字符和pattern中当前字符不匹配，直接返回false。
     *
     * 当pattern中的下一个是”*”时：
     * 1、如果字符串当前字符跟pattern当前字符匹配，可以有3种匹配方式：
     *     a、pattern指针后移2字符，相当于x*被忽略；
     *     b、字符串指针后移1字符，pattern指针后移2字符, 相当于x*匹配一位后忽略
     *     c、字符串指针后移1字符，pattern不变，即继续匹配字符下一位，相当于x*一直匹配到最后
     * 2、如果字符串当前字符跟pattern当前字符不匹配，则pattern指针后移2个字符，继续匹配。
     *
     * 其实这题的关键点在于*的处理，因为它可以匹配0到多个，那就要有N多种匹配的可能，所以用递归处理比较好
     *
     * 递归的结束点就是下标同时结束，返回true，如果字符下标先结束，返回false,如果pattern下标先结束，返回false(全匹配)，返回true(部分匹配)
     */
    public boolean isMatch0(String s, String p) {
        if (".*".equals(p)){
            return true;
        }
        // 如果字符串或者pattern是空的，那么肯定没有匹配的
        if (s == null || p == null){
            return false;
        }
        int i = 0, j = 0;
        return matchChar(s, i, p, j);
    }



    public boolean matchChar(String str, int i, String pattern, int j){
        // 字符串和pattern同时都到末尾,完全匹配
        if (i == str.length() && j == pattern.length()){
            return true;
        }
        // pattern先到末尾，这里返回false，表示全匹配
        if (i != str.length() && j == pattern.length()){
            return false;
        }
        // 下一个字符是*
        if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*'){
            //当前字符匹配，则有三种情况
            if ((i != str.length() && str.charAt(i) == pattern.charAt(j)) ||
                    (i != str.length() && pattern.charAt(j) == '.') ){
                return matchChar(str, i, pattern, j + 2) ||
                        matchChar(str, i + 1, pattern, j + 2) ||
                        matchChar(str, i + 1, pattern, j);
            }else {
                //当前字符不匹配，pattern下标移动2位
                return matchChar(str, i, pattern, j + 2);
            }
        }else if ((i!=str.length() && str.charAt(i) == pattern.charAt(j)) ||
                (i != str.length() && pattern.charAt(j) == '.')){
            // 下一个字符不是*，当前字符匹配，两个下标同时移动两位
            return matchChar(str, i + 1, pattern, j + 1);
        }
        //其他情况不满足条件
        return false;
    }

    /**
     * 解题思路：这题除了用递归法，还可以用动态归划的思想来解决
     *
     * 参考 https://leetcode.com/problems/regular-expression-matching/discuss/5684/9-lines-16ms-c-dp-solutions-with-explanations
     *
     * 我们定义 dp[i][j] 二维数组，如果 s[0..i)和p[0..j) 每一位都匹配那么就完成正则匹配. 其中有任意一个不满足，则不匹配
     *
     * 思路跟之前递归一样
     *
     * 二维数组 dp[i][j] 中各项为true的条件是：
     * 1、pattern中字符为‘*’时：
     *    a、dp[i][j] = dp[i][j - 2]   ,即‘*’匹配了0次，p直接右移两位
     *    b、dp[i][j] = dp[i - 1][j] && (s[i - 1] == p[j - 2] || p[j - 2] == '.')  ，即‘*’匹配了1次及以上，p不动，s向右移动一位
     * 2、pattern中字符不为‘*’时：
     *   dp[i][j] = dp[i - 1][j - 1] && (s[i - 1] == p[j - 1] || p[j - 1] == '.'); 即s和p同时向右移动一位
     *
     * 当最后dp[m][n]的值为true时(m,n为s和p的字符长度)，说明两者完全匹配
     *
     */
    public boolean isMatch1(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    //如果是“*”，则判断是否重复0次还是重复多次（如果重复多次的话，还保证p[0:j]和s[0:i - 1]也能够匹配）
                    dp[i][j] = dp[i][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[i - 1][j]);
                } else {
                    //如果不是“*”，则直接看对应的是否匹配, i = 0 是初始状态
                    dp[i][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                            && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }


    /**
     * 动态归划缩减版，只用两个数组来回交替即可，增加一定时间，缩小极大空间
     */
    public boolean isMatch2(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[2][n + 1];
        dp[0][0] = true;
        int cur = 0, pre = 0;
        for (int i = 0; i <= m; i++) {
            //两个数组来回交替
            cur = i % 2;
            pre = (i + 1) % 2;
            if (i > 1) {
                for (int j = 0; j <= n; j++) {
                    dp[cur][j] = false;
                }
            }
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    dp[cur][j] = dp[cur][j - 2] || (i > 0 && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') && dp[pre][j]);
                } else {
                    dp[cur][j] = i > 0 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')
                            && dp[pre][j - 1];
                }
            }
        }
        return dp[cur][n];
    }

    /**
     * 动态归划缩减版，只用一个数组前后交替即可
     */
    public boolean isMatch3(String s, String p) {
        boolean[] match = new boolean[s.length()+1];
        match[s.length()] = true;
        for(int i=p.length()-1; i>=0; i--){
            if(p.charAt(i)=='*'){
                for(int j=s.length()-1; j>=0; j--){
                    match[j] = match[j]||match[j+1]&&(p.charAt(i-1)=='.'||p.charAt(i-1)==s.charAt(j));
                }
                i--;
            }else {
                for(int j=0; j<s.length(); j++){
                    match[j] = match[j+1]&&(p.charAt(i)=='.'||p.charAt(i)==s.charAt(j));
                }
                match[s.length()] = false;
            }
        }
        return match[0];
    }

    /**
     * 动态归划缩减版，只用一个数组和一些temp变量
     */
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[] cur=new boolean[n+1];
        for (int i = 0; i <= m; i++) {
            boolean pre = cur[0];
            cur[0] = i==0;
            for (int j = 1; j <= n; j++) {
                boolean temp = cur[j];
                if (p.charAt(j - 1) == '*') {
                    cur[j] = cur[j - 2] || (i>0 && cur[j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'));
                } else {
                    cur[j] = i>0 && pre && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
                }
                pre = temp;
            }
        }
        return cur[n];
    }

    @Override
    public void solution() {
        System.out.println(isMatch("aa", "a"));
        System.out.println(isMatch("aa", "a.*.*"));
        System.out.println(isMatch("aab", ".*"));
        System.out.println(isMatch("aab", "c*a*b"));
        System.out.println(isMatch("mississippi", "mis*is*p*."));

    }
}
