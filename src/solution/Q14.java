package solution;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * @author foxleezh
 * @date 2019-01-31.
 */
public class Q14 extends BaseQustion {


    /**
     * 解题思路：这题方法有很多，但是大多数方法时间复杂度都差不多
     *
     * 这里采用水平比较的方法，挨个对比，只要发现不一样的字符或者哪个字符到了末尾就返回
     *
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    @Override
    public void solution() {
        //String[] strs = new String[]{"flower", "flow", "flight"};
        String[] strs = new String[]{"aa", "a"};
        System.out.println(longestCommonPrefix(strs));
    }
}
