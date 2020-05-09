package cn.su.leetcode;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1:
 *
 * 给定数组 nums = [1,1,2],
 *
 * 函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
 *
 * 你不需要考虑数组中超出新长度后面的元素。
 *
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 *
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 *
 *
 *
 * 执行用时 : 72 ms, 在所有 Java 提交中击败了9.83%的用户
 * 内存消耗 : 39.7 MB, 在所有 Java 提交中击败了95.69%的用户
 *
 * @author suyulong
 * @date 2019/9/23 10:50 AM
 */
public class NO26 {
    /**
     * 自己方法
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length < 1) {
            return 0;
        }
        int remain = nums[0];
        int begin = 0;
        int length = nums.length;
        for (int i = begin + 1; i < length; i++) {
            if (remain == nums[i]) {
                if (i == length - 1) {
                    length = begin + 1;
                    break;
                } else {
                    continue;
                }
            }

            int same = i - begin - 1;
            length -= same;

            remain = nums[i];
            for (int j = i; j < nums.length; j++) {
                nums[j - same] = nums[j];
            }
            i = begin++;
        }

        return length;
    }

    /**
     * java双指针法
     *
     * @param nums
     * @return
     */
    public static int removeDuplicatesV2(int[] nums) {
        if (nums == null || nums.length < 1) {
            return 0;
        }
        //i 对应的就是数组的下标
        int i = 0, j = 1;
        while (j < nums.length) {
            if(nums[i] == nums[j]){
                j++;
            }else{
                i++;
                nums[i] = nums[j];
                j++;
            }
        }
        //最终长度就是下标+1
        return i+1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 1, 1};
        System.out.println(removeDuplicatesV2(nums1));
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println(removeDuplicatesV2(nums2));
    }

}
