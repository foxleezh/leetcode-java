package solution;

import java.util.Arrays;

/**
 *
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 *
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 *
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 *
 * @author foxleezh
 * @date 2019-01-31.
 */
public class Q16 extends BaseQustion {

    /**
     * 解题思路：这个跟上一题三数之和解法差不多，先排序，然后首尾遍历找到最合适的值
     */
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            while (left < right) {
                int threeSum = nums[i] + nums[left] + nums[right];
                //求和后进行比较
                if (threeSum > target) {
                    right--;
                } else if (threeSum < target) {
                    left++;
                } else {
                    return target;
                }
                //比较sum与target的差值，将最小差值保存
                res = Math.abs(threeSum - target) > Math.abs(res - target) ? res : threeSum;
            }
        }
        return res;
    }

    @Override
    public void solution() {
        int nums[]={-1,2,1,-4};
        System.out.println(threeSumClosest(nums,1));
    }
}
