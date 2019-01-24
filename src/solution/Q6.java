package solution;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p/>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p/>
 * L   C   I   R   <br/>
 * E T O E S I I G <br/>
 * E   D   H   N <br/>
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p/>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p/>
 * string convert(string s, int numRows); <br/>
 * 示例 1: <br/>
 * <p/>
 * 输入: s = "LEETCODEISHIRING", numRows = 3 <br/>
 * 输出: "LCIRETOESIIGEDHN" <br/>
 * 示例 2:
 * <p/>
 * 输入: s = "LEETCODEISHIRING", numRows = 4 <br/>
 * 输出: "LDREOEIIECIHNTSG" <br/>
 * 解释:
 * <p/>
 * L     D     R <br/>
 * E   O E   I I <br/>
 * E C   I H   N <br/>
 * T     S     G <br/>
 *
 * @author foxleezh
 * @date 2019-01-24.
 */
public class Q6 extends BaseQustion {


    /**
     * 解题思路：我们先观察Z 字型排列的一些特征，从上往下，再从下往上，是一个V字型，后续重复这个V字型
     * <p/>
     * V字型每个周期的长度是circleLen=(rows-1)*2
     * <p/>
     * 每个完整周期第一行和最后一行都只有一个字符，其他行都是两个字符
     * <p/>
     * 既然要求是一行一行输出，那我们就一行行来, 把V字型的每一个字符在原始字符串中的位置用公式表示出来
     *  <p/>
     * i-当前行,j-当前周期,c-周期长度=8
     * <p/>
     * 0============0*8+0                                    <br/>
     * 1===7========0*8+1====0*8+(8-1)                       <br/>
     * 2==6=========0*8+2===0*8+(8-2)                        <br/>
     * 3=5==========0*8+3==0*8+(8-3)                         <br/>
     * 4============0*8+4                                    <br/>
     * <p/>
     * 可以总结出，只要是V字型的第一列，在原字符串中的位置为j*c + i    <br/>
     * 只要是V字型的第二列，在原字符串中的位置为j*c+(c-i)
     * <p/>
     * 这是完整周期的情况，而当不完整周期时，相应位置就没有字符
     * 
     */
    public String convert(String s, int numRows) {
        int len = s.length();
        if (len <= numRows || numRows == 1) {
            return s;
        }
        char[] newString = new char[len];
        int circleLen = (numRows - 1) * 2;
        int start = 0;
        for (int i = 0; i < numRows; i++) {
            //V字型第一列应当满足j+i<len，这里每次j+=circleLen,所以j*c+i变成了j+i
            for (int j = 0; j + i < len; j += circleLen) {
                newString[start++] = s.charAt(j + i);
                //同理V字型第二列应当满足j + (circleLen - i)< len,而且第一行和最后一行没有第二列
                if (i != 0 && i != numRows - 1 && j + circleLen - i < len) {
                    newString[start++] = s.charAt(j + circleLen - i);
                }
            }
        }
        return new String(newString);
    }

    @Override
    public void solution() {
        System.out.println(convert("AB", 3));
        System.out.println(convert("ABC", 3));
        System.out.println(convert("A", 3));
        System.out.println(convert("ABCD", 3));
        System.out.println(convert("ABCDEF", 3));
        System.out.println(convert("ABCDEFG", 3));
        System.out.println(convert("ABCDEFGH", 3));
        System.out.println(convert("PAYPALISHIRING", 3));
    }
}
