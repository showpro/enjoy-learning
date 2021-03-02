package shujujiegou;

import java.util.Arrays;

/**
 * @Description 冒泡排序
 * 思想是两两比较，交换顺序
 * @Author zhanzhan
 * @Date 2021/3/1 10:09
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] a = {20, 5, 9, 1, 7, 11, 10};
        sort(a);
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
}
