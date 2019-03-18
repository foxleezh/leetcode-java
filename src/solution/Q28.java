package solution;

/**
 * 实现 strStr() 函数。
 * <p>
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * <p>
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * <p>
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 * @author foxleezh
 * @date 2019-03-14.
 */
public class Q28 extends BaseQustion {


    /**
     * 解题思路：这题可以用暴力法，两层for循环逐一比较每个字符，遇到不同就break，直到有满足条件的
     *
     * 暴力法思路简单，但并不高效，这题可以用经典的KMP(三个发现者的名字简称)算法
     *
     * KMP算法比较复杂，三言两语讲不清楚，我只讲个大概意思
     *
     * 我觉得KMP算法是基于暴力法的，只是在循环的时候i(外层)不回溯，j(内层)有条件回溯，从而有效的利用每一次比较，减少重复比较次数
     *
     * 它“利用已经部分匹配的有效信息，保持i指针不回溯，通过修改j指针，让模式串尽量地移动到有效的位置”
     *
     * 这里面的难点在于确定j指针在不匹配情况下，应该回溯的位置，这也是KMP算法的核心
     *
     * 假设needle(需要寻找的)字符串是ABCABD,当我们去匹配ABCABCABD时，第一次匹配结果是
     *
     *      i
     * ABCABCABD
     * ABCABD
     *      j
     * i停留在C的位置，j停留在D的位置，如果我们保持i不动，j应当回到哪个位置呢？如果回0
     *
     *      i
     * ABCABCABD
     *      ABCABD
     *      j
     *
     * 肯定会错过结果，明显应该回到2
     *
     *      i
     * ABCABCABD
     *    ABCABD
     *      j
     *
     * 这样就能正确匹配，也不会错过那个A，那这里的关键就是怎么寻找j正确的回溯点
     *
     * 这里的规律就是，当我们j在D的时候，它前面的两个字符AB正好是跟字符串开头的AB相同的，这样当我们j回到2的时候，自然能够匹配上i的前两个字符
     *
     * 这样就充分利用了第一次匹配的结果，因为第一次匹配已经知道i的前两个是AB
     *
     * 所以我们的重点就是找出j在每一个位置不匹配时应该回溯的点，然后记录到一个next数组里面
     *
     * 详细对于KMP算法的介绍请参考https://www.cnblogs.com/yjiyjige/p/3263858.html
     */
    public int strStr(String haystack, String needle) {
        if("".equals(haystack)||"".equals(needle)){
            return 0;
        }
        char[] t = haystack.toCharArray();
        char[] p = needle.toCharArray();
        int i = 0; // 主串的位置
        int j = 0; // 模式串的位置
        int[] next = getNext(needle);//获取next数组
        while (i < t.length && j < p.length) {
            if (j == -1 || t[i] == p[j]) { // 当j为-1时，要移动的是i，当然j也要归0
                j++;
                i++;
            } else {
                j = next[j]; // j回到指定位置
            }
        }
        if (j == p.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    /**
     * 获取next数组
     *
     * 这个思路是用双指针法，用j记录字符串遍历下标，用k记录与字符串开头匹配的个数
     *
     * 每当j与k对应的字符串相同了，就将k个数加1，如果不同了，那么k的值还原
     *
     */
    public int[] getNext(String ps) {
        char[] p = ps.toCharArray();
        int[] next = new int[p.length];
        next[0] = -1;
        int j = 0;
        int k = -1;
        while (j < p.length - 1) {
            if (k == -1 || p[j] == p[k]) {//j和k对应字符相同
                if (p[++j] == p[++k]) { // 当两个字符相等时要跳过
                    next[j] = next[k];
                } else {
                    next[j] = k;
                }
            } else {
                k = next[k];//还原k的值，大多数时候是-1
            }
        }
        return next;
    }

    @Override
    public void solution() {
        System.out.println(strStr("acdbaacaaad","aacaaad"));
    }
}
