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
//        Solution solution = new RemoveDuplicates().new Solution();
//        int[] nums = {0,0,1,1,1,2,2,3,3,4};
//        int i1 = solution.removeDuplicates(nums);
//        for (int i : nums) {
//            System.out.print(i + " ");
//        }

        Solution1 solution1 = new RemoveDuplicates().new Solution1();
        int[] nums1 = {1,1,1,2,2,3};
        int i2 = solution1.removeDuplicates(nums1);
        for (int i : nums1) {
            System.out.print(i + " ");
        }
    }

    /**
     * 给定数组 nums = [1,1,2],
     * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * 你不需要考虑数组中超出新长度后面的元素。
     */
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

    /**
     * 给定一个增序排列数组 nums ，你需要在 原地 删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * 输入：nums = [1,1,1,2,2,3]
     * 输出：5, nums = [1,1,2,2,3]
     */
    class Solution1 {
        public int removeDuplicates(int[] nums) {
            int i = 0;
            for (int n : nums) {
                if (i < 2 || n > nums[i-2]) {
                    nums[i++] = n;
                }
            }
            return i;
        }
    }
}
