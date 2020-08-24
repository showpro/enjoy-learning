package leetcode.array;

import java.util.Random;

/**
 * 冒泡排序
 *
 * Author zhanzhan
 * Date：2020/8/23
 * Time: 22:51
 */

public class BubbleSort {
    public static void main(String[] args) {
        Random random = new Random(5);
        int[] score = new int[5];
        for (int i = 0; i < 5; i++) {
            score[i] = random.nextInt(100);
        }
        for (int i = 0; i < score.length-1; i++) {
            for (int j = 0; j < score.length-1-i; j++) {
                if(score[j] > score[j+1]){
                    int temp = score[j];
                    score[j] = score[j+1];
                    score[j+1] = temp;
                }
            }
        }

        for (int i = 0; i < score.length; i++) {
            System.out.println(score[i]);
        }

    }
}
