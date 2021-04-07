package com.leetcode.editor.cn;

//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 示例 3： 
//
// 
//输入：nums1 = [0,0], nums2 = [0,0]
//输出：0.00000
// 
//
// 示例 4： 
//
// 
//输入：nums1 = [], nums2 = [1]
//输出：1.00000
// 
//
// 示例 5： 
//
// 
//输入：nums1 = [2], nums2 = []
//输出：2.00000
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -106 <= nums1[i], nums2[i] <= 106 
// 
//
// 
//
// 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？ 
// Related Topics 数组 二分查找 分治算法 
// 👍 3927 👎 0

public class MedianOfTwoSortedArrays {

    // leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            int totalNum = nums1.length + nums2.length;
            int mid = totalNum / 2;
            boolean isOdd = totalNum % 2 == 1;
            int median1 = 0;
            int median2 = 0;
            int current;
            for (int i = 0, j = 0; i + j < totalNum; ) {
                if (i < nums1.length && j >= nums2.length) {
                    current = nums1[i++];
                } else if (i >= nums1.length && j < nums2.length) {
                    current = nums2[j++];
                } else if (nums1[i] < nums2[j]) {
                    current = nums1[i++];
                } else {
                    current = nums2[j++];
                }
                if (i + j == mid && isOdd) {
                    median1 = current;
                }
                if (i + j == mid + 1 && isOdd) {
                    median2 = current;
                    break;
                }
                if (i + j == mid + 1 && !isOdd) {
                    median1 = current;
                    break;
                }
            }
            return isOdd ? (median1 + median2) / 2.0 : median1;
        }
    }
    // leetcode submit region end(Prohibit modification and deletion)


    public static void main(String[] args) {
        Solution solution = new MedianOfTwoSortedArrays().new Solution();
        System.out.println(solution.findMedianSortedArrays(new int[]{2}, new int[0]));
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
        System.out.println(solution.findMedianSortedArrays(new int[]{1, 3}, new int[]{2}));
    }
}