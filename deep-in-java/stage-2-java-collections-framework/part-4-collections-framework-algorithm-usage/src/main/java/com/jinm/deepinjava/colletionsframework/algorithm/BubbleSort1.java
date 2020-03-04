package com.jinm.deepinjava.colletionsframework.algorithm;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author keanemer
 */
public class BubbleSort1<T extends Comparable<T> > implements Sort<T>{

    public static void main(String[] args) {
        int[] arr = {4,5,1,3,2};
        bubbleSort(arr);
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {

                if (arr[j] > arr[j + 1]){
                    //int temp = arr[j];
                    //arr[j] = arr[j + 1];
                    //arr[j + 1] = temp;

                    //不需要 临时变量
                    arr[j] = arr[j + 1] + arr[j];
                    arr[j + 1] = arr[j] - arr[j + 1];
                    arr[j] = arr[j] - arr[j + 1];

                }

            }
        }
        System.out.println(Arrays.toString(arr));
    }

    private static void unNamedSort(int[] array){
        array = new int[]{3, 1, 2, 5, 4};
        System.out.println(Arrays.toString(array));
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]){
                    int value = array[i];
                    array[i] = array[j];
                    array[j] = value;

                }
            }
        }
        System.out.println(Arrays.toString(array));
    }

    @Override
    public void sort(T[] values) {

    }

}
