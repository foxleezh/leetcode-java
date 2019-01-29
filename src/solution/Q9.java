package solution;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *
 * @author foxleezh
 * @date 2019-01-29.
 */
public class Q9 extends BaseQustion {

    /**
     * 解题思路：这题有两种思路，一是转换为字符串，然后比较前后字符是否一致即可，简单方便，不好之处就是转字符串耗时
     *
     * 第二种就是把数字反转，然后比较反转后的数和原数是否一样，但是这样会遇到反转数越界的问题
     *
     * 那我们可以只反转一半，因为回纹数前后肯定一样，怎么知道是一半了呢，我们可以比较前后两数，如果后数比前数大，说明到一半了
     *
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
//        字符串解决方案
//        String s=x+"";
//        int len=s.length();
//        if(len==1){
//            return true;
//        }
//        for (int i = 0; i < len/2; i++) {
//            //比较前后两数
//            if(s.charAt(i)!=s.charAt(len-i-1)){
//                return false;
//            }
//        }
        //尾数为0的肯定不是回纹
        if(x%10==0&&x!=0){
            return false;
        }
        int last=0;
        //当last比x大时，说明到一半了
        while(last<x){
            //取后面的数给last
            last=last*10+x%10;
            x/=10;
        }
        //last==x表示奇数，last/10==x表示偶数
        return last==x||last/10==x;
    }

    @Override
    public void solution() {
        System.out.println(isPalindrome(100));
        System.out.println(isPalindrome(10));
        System.out.println(isPalindrome(1));
        System.out.println(isPalindrome(12021));
        System.out.println(isPalindrome(1221));
        System.out.println(isPalindrome(1211));
    }
}
