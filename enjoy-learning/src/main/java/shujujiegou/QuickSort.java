package shujujiegou;

import java.util.Arrays;
import java.util.Random;

/**
 * 快排：挖坑填数 + 分治法
 * 对挖坑填数进行总结:
 * 1．i =L; j = R; 将基准数挖出形成第一个坑a[i]。
 * 2．j--由后向前找比它小的数，找到后挖出此数填前一个坑a[i]中。
 * 3．i++由前向后找比它大的数，找到后也挖出此数填到前一个坑a[j]中。
 * 4．再重复执行2，3二步，直到i==j，将基准数填入a[i]中。
 * @author zhanzhan
 * @date 2020/8/24 21:46
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] s = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        int i1 = partition(s, 0, 9);
        quick_sort1(s, 0, 9);

        //quick_sort(s, 0 , 9);

        System.out.println(Arrays.toString(s));
    }

    /**
     *  0 1  2  3  4  5  6  7  8  9
     * 72 6 57 88 60 42 83 73 48 85
     * 取区间第一个数为基准数
     * 初始时，i = 0;  j = 9;   X = a[i] = 72
     * 由于已经将 a[0] 中的数保存到 X 中，可以理解成在数组 a[0] 上挖了个坑，可以将其它数据填充到这来。
     * 从j开始向前找一个比X小或等于X的数。当j=8，符合条件，将a[8]挖出再填到上一个坑a[0]中。a[0]=a[8]; i++;
     * 这样一个坑a[0]就被搞定了，但又形成了一个新坑a[8]，这怎么办了？
     * 简单，再找数字来填a[8]这个坑。这次从i开始向后找一个大于X的数，当i=3，符合条件，将a[3]挖出再填到上一个坑中a[8]=a[3]; j--;
     *
     * 实现挖坑填数的代码
     */
    //划分函数
    //从子数组a[l...r]中选择任意一个元素x作为主元，调整子数组的元素使得左边的元素都小于等于它，右边的元素都大于等于它，x的最终位置就是q
    //保证 a[l⋯q−1] 中的每个元素小于等于 a[q]，且 a[q] 小于等于a[q+1⋯r] 中的每个元素
    public static int partition(int s[], int l, int r) { //返回调整后基准数的位置
        int i = l, j = r;
        // s[l]即s[i]就是第一个坑, 即以第一个位置为基准
        int x = s[l];
        while (i < j) {
            // 从右向左找小于x的数来填s[i]
            while(i < j && s[j] >= x)
                j--;
            if(i < j) {
                //将s[j]填到s[i]中，s[j]就形成了一个新的坑
                s[i] = s[j];
                i++;
            }

            // 从左向右找大于或等于x的数来填s[j]
            while(i < j && s[i] < x)
                i++;
            if(i < j) {
                //将s[i]填到s[j]中，s[i]就形成了一个新的坑
                s[j] = s[i];
                j--;
            }
        }
        //退出时，i等于j。将x填到这个坑中。
        s[i] = x;

        //返回最终索引位置
        return i;
    }

    /**
     * 分治法的代码：
     */
    public static void quick_sort1(int s[], int l, int r) {
        if (l < r) {
            //先成挖坑填数法调整s[]
            int i = partition(s, l, r);
            // 递归调用
            quick_sort1(s, l, i - 1);
            quick_sort1(s, i + 1, r);
        }
    }

    /**
     * 对以上两个方法组合整理：快速排序
     *
     * @param s
     * @param l
     * @param r
     */
    public static void quick_sort(int s[], int l, int r) {
        if (l < r) {
            //Swap(s[l], s[(l + r) / 2]); //将中间的这个数和第一个数交换 参见注1
            int i = l, j = r, x = s[l];
            while (i < j) {
                while(i < j && s[j] >= x) // 从右向左找第一个小于x的数
                    j--;
                if(i < j)
                    s[i++] = s[j]; // 相当于 先s[i]=s[j], 再i++;

                while(i < j && s[i] < x) // 从左向右找第一个大于等于x的数
                    i++;
                if(i < j)
                    s[j--] = s[i];
            }
            //退出时，i等于j。将x填到这个坑中。
            s[i] = x;
            quick_sort(s, l, i - 1); // 递归调用
            quick_sort(s, i + 1, r);
        }
    }
}
