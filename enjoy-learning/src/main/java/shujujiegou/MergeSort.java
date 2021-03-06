package shujujiegou;

import java.util.Arrays;

/**
 * @author zhanzhan 归并排序
 *
 * 分治的思想
 *
 * 小和问题和逆序列问题
 *
 * @date 2021/3/6 21:18
 */
public class MergeSort {
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //整体排序
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1); // L和R中点的位置 等价于（L+R）/2，这样写下表可能会溢出
        mergeSort(arr, l, mid); // 左部分排序 T(N/2)
        mergeSort(arr, mid + 1, r);// 右部分排序 T(N/2)
        merge(arr, l, mid, r);// 左，右合并有序 O(N)
        // T(N) = 2*T(N/2) + O(N)  —> O(N*logN)
    }

    // 外排并拷贝
    public static void merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];//定义一个辅助数组
        int i = 0;//辅助数组下标
        int p1 = l;//左部分指针l，指向左部分数组第一个数
        int p2 = m + 1;//右部分指针r，指向右部分数组第一个数
        while (p1 <= m && p2 <= r) {//看谁的下标先到各部分末尾
            // p1,p2指针上的数谁小，谁就填入辅助数组
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 以下两个while必有且只有一个越界
        while (p1 <= m) {//p1没越界，p2必越界
            //将P1拷贝到辅助数组
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {//p2没越界，p1必越界
            //将P2拷贝到辅助数组
            help[i++] = arr[p2++];
        }
        //将辅助数组数据拷贝到原数组中进行覆盖
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
    }

    //*********************************** 对数器 ************************//
    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            mergeSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        mergeSort(arr);
        printArray(arr);

    }

}
