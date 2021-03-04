package shujujiegou;

import java.util.Arrays;

/**
 * @Description 选择排序    与冒泡思路相反
 * 思想是两两比较，交换顺序  O(N^2)
 * 0~N-1 范围内 N个数 找一个最小数的放在0的位置上
 * 1~N-1 范围内 N-1个数 找一个最小数的放在1的位置上
 * ... 总共加起来是个等差数列
 * 所以时间复杂度O(N^2)
 *
 * @Author zhanzhan
 * @Date 2021/3/1 10:09
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] a = {20, 5, 9, 1, 7, 11, 10};
        selecionSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 选择排序
     * @param a
     */
    public static void selecionSort(int[] a) {
        int temp = 0;

        for (int i = 0; i < a.length - 1; i++) {
            //记录最小数的下标，初始从i=0开始
            int minIndex = i;
            for (int j = i + 1; j < a.length ; j++) {
                //如果后面发现更小的，改变minIndex,否则不变
                minIndex = a[j] < a[minIndex] ? j : minIndex;
            }
            //最小的数和 i 位置上交换
            temp = a[i];
            a[i] = a[minIndex];
            a[minIndex] = temp;
        }
    }

    /**
     * 冒泡排序
     *
     * @param a
     */
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
