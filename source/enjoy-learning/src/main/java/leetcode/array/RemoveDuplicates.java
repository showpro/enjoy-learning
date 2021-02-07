package leetcode.array;

/**
 * @Description 删除排序数组中的重复项
 * 给定数组 nums = [1,1,2],
 *
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。

 * @Author zhanzhan
 * @Date 2021/2/7 13:42
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        Solution solution = new RemoveDuplicates().new Solution();
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        int i1 = solution.removeDuplicates(nums);
        for (int i : nums) {
            System.out.print(i + " ");
        }
    }

    class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) return 0;
            int i = 0;
            for(int j = 1; j < nums.length; j++) {
                if(nums[j] != nums[i]) {
                    i++;
                    nums[i] = nums[j];
                }
            }
            return i+1;
        }
    }
}
