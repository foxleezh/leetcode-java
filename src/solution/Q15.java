package solution;

import java.util.*;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 *
 * @author foxleezh
 * @date 2019-01-31.
 */
public class Q15 extends BaseQustion {

    /**
     * 解题思路：这题可以简化为求两数之和，我们取一个数然后变为它的相反数，如果有另外两个数之和等于这个相反数，那么这三个数之和就为0
     * <p>
     * 但是问题中涉及到不能有相同的元素，比如[0,1,-1]和[0,-1,1]是不能出现的，但是[2,2,-4]是可以的，所以题意要清楚
     * <p>
     * 为了避免相同的组合出现，最好的办法就是先排序，排序后在遍历过程中跳过相同的数，就能避免出现相同结果
     *
     * 由于排过序，我们可以采用两头往中间走的形式，减少二次遍历
     *
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList();
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            //跳过大于0的开始，因为三个正数相加不可能等于0
            if (nums[i] > 0) {
                break;
            }
            //跳过相同的数，避免重复
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //两头往中间走，j在左，k在右
            int j = i + 1;
            int k = len - 1;
            int target = 0 - nums[i];
            while (j < k) {
                if (nums[j] + nums[k] == target) {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    //跳过相同的数
                    while (j < k && nums[j] == nums[j + 1]) {
                        j++;
                    }
                    //跳过相同的数
                    while (j < k && nums[k] == nums[k - 1]) {
                        k--;
                    }
                    j++;
                    k--;
                } else if (nums[j] + nums[k] < target) {
                    j++;
                } else {
                    k--;
                }

            }
        }
        return res;
    }

    @Override
    public void solution() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }
}
