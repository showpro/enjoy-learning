package shujujiegou;

import java.util.Arrays;
import java.util.Random;

/**
 * @Description 基数随机选择版本快速排序(不稳定)
 * @Author zhanzhan
 * @Date 2021/3/2 13:26
 */
public class RandomQuickSort {
    Random random = new Random();

    public void randomQuickSort(int[] arrs) {
        randomQuickSort(arrs, 0, arrs.length - 1);
    }

    private void randomQuickSort(int[] arrs, int low, int high) {
        int p;
        if (low < high) {
            p = randomPartition(arrs, low, high);
            //小于区 递归
            randomQuickSort(arrs, low, p - 1);
            //大于区 递归
            randomQuickSort(arrs, p + 1, high);
        }
    }

    /**
     * 划分基准数
     * 随机在数组范围中找一个“基准值”，并将其与数组最左元素交换作为基准。
     */
    private int randomPartition(int[] arrs, int left, int right) {
        int i, j, p;
        i = left;
        j = right;
        // 随机选择基准数索引位置
        int ranInt = random.nextInt(right-left+1);
        int w = left + ranInt;
        // p中存的就是基准数
        p = arrs[w];
        //以数组第一个数为“基准”。将随机在数组取的数p，与第一个数做交换。
        swap(arrs, left, w);

        while (i != j) {
            // 顺序很重要，要先从右边开始找，否则i指针有可能停留在比p大的数上，导致归位交换出错
            // 直到比基准值小，就交换 i , j 位置的值
            while (i < j && arrs[j] >= p)
                j--;
            // 再找左边的, 直到比基准值大，就交换 i , j 位置的值
            while (i < j && arrs[i] <= p)
                i++;
            // 交换两个数在数组中的位置
            if (i < j) {
                swap(arrs, i, j);
            }
        }
        // i=j时，最终将基准数归位
        arrs[left] = arrs[i];
        arrs[i] = p;
        System.out.println("基准数: " + p + " " + "第一轮排序结果：" + Arrays.toString(arrs));
        return i;
    }

    /**
     * 交换指定数组的left和right索引的值
     */
    private void swap(int[] arrs, int left, int right){
        int temp = arrs[left];
        arrs[left] = arrs[right];
        arrs[right] = temp;
    }

    public static void main(String[] args) {
        RandomQuickSort rqs = new RandomQuickSort();
        int[] arrs = { 49, 38, 65, 97, 76, 13, 27, 49, 5, 83 };
        rqs.randomQuickSort(arrs);
        for (int i = 0; i < arrs.length; i++) {
            System.out.println(arrs[i]);
        }
    }

}
