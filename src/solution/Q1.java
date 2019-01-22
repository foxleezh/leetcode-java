package solution;

import java.util.HashMap;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * <p/>
 * 示例:
 * <p/>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p/>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author foxleezh
 * @date 2019-01-22.
 */
public class Q1 extends BaseQustion {

    /**
     * 解决思路是：
     * <p/>
     * 1.遍历数组，每拿到一个值就让 和数减去它得到余数
     * <p/>
     * 2.在Hash表中找是否有这个余数，有就返回下标， 没有就将这个值和对应下标存入Hash表
     */
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length < 2) {
            return res;
        }
        //最好算一下初始容量，提高效率
        int initialCapacity = (int) ((float) nums.length / 0.75F + 1.0F);
        HashMap<Integer, Integer> map = new HashMap<>(initialCapacity);
        for (int i = 0; i < nums.length; i++) {
            //在map中寻找余数
            if (!map.containsKey(target - nums[i])) {
                map.put(nums[i], i);
            } else {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
                break;
            }
        }
        return res;
    }

    @Override
    public void solution() {
        int[] nums = new int[]{2, 7, 3, 6};
        int[] re = twoSum(nums, 9);
        System.out.println("index=" + re[0] + "," + re[1]);
    }
}
