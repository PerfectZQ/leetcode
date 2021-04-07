package com.sort;

/**
 * 快速排序
 * <p>
 * 每次随机取一个基准元素（通常选数组第一个），找到一个位置，左边的元素都比基准元素小，右边的元素都比基准元素大。
 * 然后递归排序基准元素两边的序列，直到序列长度为 1
 *
 * @author zhangqiang
 * @since 2021/4/7 12:48 上午
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 3, 7, 8, 9};
        quickSort(arr, 0, arr.length - 1, 0);
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void quickSort(int arr[], int start, int end, int depth) {
        if (start < end) {
            // 选取左边第一个元素作为基准元素
            int mid = start;
            int left = start;
            int right = end;
            while (left < right) {
                // 先从右面开始(因为选取左边第一个元素为基准元素，基准元素的左边是空的)，找到第一个小于等于参考值的数的位置
                while (arr[right] > arr[mid] && left < right) {
                    right--;
                }
                // 交换两个数的位置，保证小的在基准元素的左边，大的在右边
                swap(arr, mid, right);
                mid = right;
                // 然后从左边开始，找到第一个大于等于参考值的数的位置
                while (arr[left] < arr[mid] && left < right) {
                    left++;
                }
                // 交换两个数的位置，保证小的在基准元素的左边，大的在右边
                swap(arr, left, mid);
                mid = left;
            }
            System.out.printf("depth = %s, mid = %s, midNum = %s%n", ++depth, mid, arr[mid]);
            // 输出当前的序列
            printArr(arr);
            // 递归排序左边的序列
            quickSort(arr, start, mid - 1, depth);
            // 递归排序右边的序列
            quickSort(arr, mid + 1, end, depth);
        }
    }

    private static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
