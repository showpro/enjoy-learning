package shujujiegou;


/**
 * @Description 快速排序随机选取基准值
 *
 * 随机在数组范围中找一个“基准值”，并将其与数组最右元素交换作为基准。
 * 与此“基准值”进行比较就可以得到三个区域：小于，等于，大于
 *
 * @Author zhanzhan
 * @Date 2021/3/2 11:02
 */
public class RandomQuickSort1 {

    public  void quickSort(int[] arr){
        if (arr == null || arr.length < 2){
            return;
        }

        randomQuickSort(arr,0,arr.length-1);
    }

    public  void randomQuickSort(int[] arr, int left, int right){
        //不越界
        if (left < right){

            int random = (int) (Math.random() * (right - left + 1));
            //以数组最后一个数为“基准”。随机在数组取一个数，与最后一个数做交换。
            swap(arr,left + random, right);

            //分层 <区 =区 >区
            int[] p = partition(arr, left, right);

            //递归，小于区递归，大于区递归
            randomQuickSort(arr, left,p[0]-1);     //p[0]是分层后的数组 等于区的左边界 p[0]-1是 小于区的最后一个元素
            randomQuickSort(arr,p[1]+1, right);     //p[1]是分层后的数组 等于区的右边界 p[1]+1是 大于区的第一个元素
        }
    }

    /**
     * 交换指定数组的left和right索引的值
     */
    private  void swap(int[] arrs, int left, int right){
        int temp = arrs[left];
        arrs[left] = arrs[right];
        arrs[right] = temp;
    }

    /**
     * 划分基准数
     * 随机在数组范围中找一个“基准值”，并将其与数组最右元素交换作为基准。
     */
    //荷兰国旗是以target目标值 作为划分
    //快速排序是以数组最右位置上的元素arr[R] 作为划分
    public  int[] partition(int[] arr, int left, int right){
        //小于区的右边界
        int less = left - 1;
        //大于区的左边界
        int more = right;
        //current代表当前值索引
        int current = left;
        while (current < more){
            //当前值 ＜ 基准值
            if (arr[current] < arr[right]){
                swap(arr, ++less, current++);
            }else if (arr[current] > arr[right]){
                swap(arr, --more, current);
            }else
                current++;
        }
        //此时已经分好区，把最后一个元素与＞区第一个元素交换，得到左边：＜基准值的区域，中间=基准值的区域，右边：＞基准值的区域
        swap(arr, more, right);

        int[] res = {less + 1,more};
        return res;
    }

    public static void main(String[] args) {
        RandomQuickSort1 rqs = new RandomQuickSort1();
        int[] arrs = { 49, 38, 65, 97, 76, 13, 27, 49, 5, 83 };
        rqs.quickSort(arrs);
        for (int i = 0; i < arrs.length; i++) {
            System.out.println(arrs[i]);
        }
    }


}
