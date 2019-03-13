package solution;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * <p>
 * 给定数组 nums = [1,1,2],
 * <p>
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * <p>
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 说明:
 * <p>
 * 为什么返回数值是整数，但输出的答案是数组呢?
 * <p>
 * 请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 * <p>
 * 你可以想象内部操作如下:
 * <p>
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 * <p>
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
 * for (int i = 0; i < len; i++) {
 * print(nums[i]);
 * }
 *
 * @author foxleezh
 * @date 2019-03-13.
 */
public class Q26 extends BaseQustion {


    /**
     * 解题思路：这题用双指针法，一个指针index记录新数组的下标，一个指针i记录当前遍历的下标
     *
     * 如果index记录的下标对应的值与i记录的下标对应的值不相等，那么就把i这个值替换index+1下标对应的值，然后index加1
     *
     * 最后返回index+1,作为新数组的个数
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int index=0;
        for(int i=1;i<nums.length;i++){
            if(nums[index]!=nums[i]){
                nums[++index]=nums[i];
            }
        }
        return index+1;
    }

    @Override
    public void solution() {
        int[] nums=new int[]{0,1,1,2,3,4,4,4};
        int num=removeDuplicates(nums);
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(nums[i]);
            stringBuilder.append(',');
        }
        System.out.println(stringBuilder.toString());
    }
}
