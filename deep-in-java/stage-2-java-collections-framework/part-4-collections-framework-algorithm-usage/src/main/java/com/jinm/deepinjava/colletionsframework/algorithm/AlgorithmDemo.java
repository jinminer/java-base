package com.jinm.deepinjava.colletionsframework.algorithm;

import java.util.Arrays;

public class AlgorithmDemo {

    public static void main(String[] args) {

        Integer[] arr = {3, 1, 2, 4, 7, 6, 6, 5};
//        bubbleSort(arr);
//        insertionSort(arr);
        quickSort(arr);

    }

    private static void integerTest(Integer test) {
        test += 2;
    }

    private static void bubbleSort(Integer[] arr){

        // [3, 1, 2, 4, 7, 6, 5]

        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = 0; j < arr.length - i -1; j++) {

                if (arr[j].compareTo(arr[j+1]) > 0) {
                    arr[j] = arr[j] + arr[j+1];
                    arr[j+1] = arr[j] - arr[j+1];
                    arr[j] = arr[j] - arr[j+1];
//                    swap(arr[j], arr[j+1]);
                }

            }
            
        }

        System.out.println(Arrays.toString(arr));

    }

    private static void insertionSort(Integer[] arr){

        // [3, 1, 2, 4, 7, 6, 5]

        for (int i = 0; i < arr.length - 1; i++) {

            for (int j = i; j > 0; j--) {

                if (arr[j].compareTo(arr[j+1]) > 0) {
                    arr[j] = arr[j] + arr[j+1];
                    arr[j+1] = arr[j] - arr[j+1];
                    arr[j] = arr[j] - arr[j+1];
//                    swap(arr[j], arr[j+1]);
                }

            }

        }

        System.out.println(Arrays.toString(arr));

    }

    private static void quickSort(Integer[] arr){

        int low = 0;
        int high = arr.length - 1;

        quickSort(arr, low, high);
        System.out.println(Arrays.toString(arr));

    }

    private static void quickSort(Integer[] arr, int low, int high){

        if (low > high){
            return;
        }

        int partitionIndex = quickSortPartition(arr, low, high);
        quickSort(arr, partitionIndex + 1, high);
        quickSort(arr, low, partitionIndex - 1);

    }

    private static int quickSortPartition(Integer[] arr, int low, int high){

        //{3, 1, 2, 4, 7, 6, 6, 5}
        // 分区指针
        int i = low;
        // 分区阈值
        Integer pivot = arr[high];
        for (int j = low; j < high; j++) {
            if (arr[j].compareTo(pivot) < 1){
                Integer temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
            }
        }

        Integer temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;

        return i;
    }

    // java 值传递和传地址的问题
    //  基本数据类型传值，对形参的修改不会影响实参；
    //  引用类型传引用，形参和实参指向同一个内存地址（同一个对象），所以对参数的修改会影响到实际的对象。
    //  String, Integer, Double等immutable的类型特殊处理，可以理解为传值，最后的操作不会修改实参对象。
    private static void swap(Integer low, Integer high){

        low = high + low;
        high = low - high;
        low = low - high;

    }


}
