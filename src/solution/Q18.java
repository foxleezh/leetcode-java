package solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * <p>
 * 注意：
 * <p>
 * 答案中不可以包含重复的四元组。
 * <p>
 * 示例：
 * <p>
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * <p>
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 *
 * @author foxleezh
 * @date 2019-02-11.
 */
public class Q18 extends BaseQustion {

    int len = 0;

    /**
     * 解题思路：之前有做过两数之和，三数之和，现在是四数之和，其实我们可以抽象出N数之和的解法
     *
     * 1.首先对数组排序，这个是必须的，保证元组不重复
     *
     * 2.我们在解三数之和的时候，将其简化为两数之和，双指针左右往中间走，那么N数之和也可以这样
     *
     * 3.在N-2次循环之前，用递归不断进行遍历，直到循环到2时用两数之和来解
     *
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        len = nums.length;
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }


    private ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (index >= len) {
            return res;
        }
        if (k == 2) {
            int i = index, j = len - 1;
            while (i < j) {
                //寻找两数之和
                if (target - nums[i] == nums[j]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(target - nums[i]);
                    res.add(temp);
                    //重复值跳过
                    while (i < j && nums[i] == nums[i + 1]) {
                        i++;
                    }
                    while (i < j && nums[j - 1] == nums[j]) {
                        j--;
                    }
                    i++;
                    j--;
                } else if (target - nums[i] > nums[j]) {
                    i++;
                } else {
                    j--;
                }
            }
        } else {
            //递归+遍历
            for (int i = index; i < len - k + 1; i++) {
                ArrayList<List<Integer>> temp = kSum(nums, target - nums[i], k - 1, i + 1);
                if (temp != null) {
                    for (List<Integer> t : temp) {
                        t.add(0, nums[i]);
                    }
                    res.addAll(temp);
                }
                while (i < len - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }
        return res;
    }

    @Override
    public void solution() {
        int[] nums = new int[]{1, 1, 1, 1, 2, 3, 2, -1, 0};
        List<List<Integer>> lists = fourSum(nums, 4);
        System.out.println(lists.toString());
    }
}
