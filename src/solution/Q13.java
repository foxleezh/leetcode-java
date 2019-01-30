package solution;

/**
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "III"
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: "IV"
 * 输出: 4
 * 示例 3:
 * <p>
 * 输入: "IX"
 * 输出: 9
 * 示例 4:
 * <p>
 * 输入: "LVIII"
 * 输出: 58
 * 解释: L = 50, V= 5, III = 3.
 * 示例 5:
 * <p>
 * 输入: "MCMXCIV"
 * 输出: 1994
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * @author foxleezh
 * @date 2019-01-30.
 */
public class Q13 extends BaseQustion {

    static int[] vals = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    static String[] roms = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * 解题思路：罗马数字的表示方式比较特殊，一般来说个，十，百，千这种十位一个变化比较好处理，罗马却有一，四，五，九四种不同的表示
     * <p>
     * 所以需要把每十位又细分四个档次，分别判断是否大于该档，大于该档就加上对应的字符即可
     */
    public int romanToInt1(String s) {
        int re = 0;
        int len = s.length();
        String temp = null;
        String temp1 = null;
        for (int i = 0, j = 0; i < len; i++) {
            //字符可能有单个如M,D,C，也可能有两个如CM,CD,XC，所以需要比较两种字符
            temp = s.substring(i, i + 1);
            if (i + 1 < len) {
                temp1 = s.substring(i, i + 2);
            }
            while (j < roms.length) {
                //如果是单个字符，直接加上对应值
                if (roms[j].equals(temp)) {
                    re += vals[j];
                    break;
                } else if (roms[j].equals(temp1)) {
                    //如果是多个字符，加上对应值，i的值多加1，由于不可能重复出现多个相同双字符，j加1
                    re += vals[j];
                    i++;
                    j++;
                    temp1 = null;
                    break;
                } else {
                    //不满足，找下一个
                    j++;
                }
            }
        }
        return re;
    }

    /**
     * 另一种更快的解决方案，用字符本身作为下标建立map,省掉字符比较
     *
     * 如果前一个字符比后一个字符大，说明是5，6，7这种数，直接加，反之是4或者9的情况，要减
     *
     */
    public int romanToInt(String s) {
        int out = 0;
        int arr[] = new int[91];
        arr['I'] = 1;
        arr['V'] = 5;
        arr['X'] = 10;
        arr['L'] = 50;
        arr['C'] = 100;
        arr['D'] = 500;
        arr['M'] = 1000;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            int one = arr[s.charAt(i)];
            int two = i+1<len?arr[s.charAt(i + 1)]:0;
            //5，6，7，8，1，2，3
            if (one >= two) {
                out += one;
            } else {
                //4，9
                out -= one;
            }
        }
        return out;
    }

    @Override
    public void solution() {
        System.out.println(romanToInt("III"));
        System.out.println(romanToInt("MCMXCIV"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("DCXXI"));
        System.out.println(romanToInt("MDCXCV"));

    }
}
