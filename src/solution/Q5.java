package solution;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 <br/>
 * <p/>
 * 示例 1： <br/>
 * <p/>
 * 输入: "babad" <br/>
 * 输出: "bab" <br/>
 * 注意: "aba" 也是一个有效答案。 <br/>
 * 示例 2： <br/>
 * <p/>
 * 输入: "cbbd" <br/>
 * 输出: "bb" <br/>
 * <p/>
 *
 * @author foxleezh
 * @date 2019-01-24.
 */
public class Q5 extends BaseQustion {

    /**
     * 解题思路：回纹子串就是正着读反着读都一样的字符，比如aba,abba，那我们就可以找到一个下标，比较左右两个字符是否一样 <p/>
     *
     * 如果一样就继续往外找，直到不一样为止，然后算出字串长度<p/>
     *
     * 由于回纹的对称性，我们在可以忽略一些下标，比如 abba 这种，当我们发现b的后面还有个b，这时后面一个b就不用再作为中心继续找了<p/>
     *
     * 另外当我们发现了一个足够长的回纹子串后，比如40长度，那len-19后面的下标就不用找了<p/>
     *
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        int maxLen = 0, start = 0;
        for (int i = 0; i < n;) {
            //跳过一些太短的下标
            if (n - i <= maxLen / 2) {
                break;
            }
            int left = i, right = i;
            while (right < n - 1 && s.charAt(right + 1) == s.charAt(right)){
                ++right;
            }
            //有重复的字符直接跳过
            i = right + 1;
            //寻找回纹，左右字符对比
            while (right < n - 1 && left > 0 && s.charAt(right + 1) == s.charAt(left - 1)) {
                ++right; --left;
            }
            if (maxLen < right - left + 1) {
                maxLen = right - left + 1;
                start = left;
            }
        }
        return s.substring(start,  start + maxLen);
    }


    @Override
    public void solution() {
//        System.out.println(longestPalindrome("bb"));
//        System.out.println(longestPalindrome("b"));
//        System.out.println(longestPalindrome("bbcb"));
        System.out.println(longestPalindrome("bbcbcccsf"));
//        System.out.println(longestPalindrome("abb"));
    }
}
