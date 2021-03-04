package shujujiegou;

import java.util.Arrays;

/**
 * @Description 冒泡排序
 * 思想是两两比较，交换顺序  O(N^2)
 * 0~N-1 范围内 N个数 找一个最大的放在N-1的位置上
 * 0~N-2 范围内 N-1个数 找一个最大的放在N-2的位置上
 * ... 总共加起来是个等差数列
 *
 * 所以时间复杂度O(N^2)
 * 见 sort1()
 *
 * @Author zhanzhan
 * @Date 2021/3/1 10:09
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = {20, 5, 9, 1, 7, 11, 10};
        sort1(a);
        System.out.println(Arrays.toString(a));
    }

    public static void sort(int[] a) {
        int temp = 0;
        // 外层循环，它决定一共走几趟
        for (int i = 0; i < a.length - 1; ++i) {
            //内层循环，它决定每趟比较几次
            for (int j = 0; j < a.length - i - 1; ++j) {
                //如果后一个大于前一个
                if (a[j + 1] < a[j]) {
                    //换位
                    temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    public static void sort1(int[] a) {
        int temp = 0;
        // 外层循环，它决定一共走几趟, 最大数放最后一位
        for (int end = a.length - 1; end > 0  ; end--) {
            //内层循环，它决定每趟比较几次
            for (int i = 0; i < end; i++) {
                //如果后一个大于前一个
                if (a[i] > a[i+1]) {
                    //换位
                    temp = a[i];
                    a[i] = a[i + 1];
                    a[i+ 1] = temp;
                }
            }
        }
    }
}
