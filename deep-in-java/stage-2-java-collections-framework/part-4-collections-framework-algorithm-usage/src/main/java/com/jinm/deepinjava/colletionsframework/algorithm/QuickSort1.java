package com.jinm.deepinjava.colletionsframework.algorithm;

import java.util.Arrays;

public class QuickSort1<T extends Comparable<T>> implements Sort<T> {

    @Override
    public void sort(T[] values) {

        int low = 0;
        int high = values.length -1;

        //分区排序
        sort(values, low, high);


    }

    private void sort(T[] values, int low, int high) {

        //递归终止
        if (low > high){
            return;
        }

        int partitionIndex = partition(values, low, high);

        //低分区递归处理
        sort(values, low, partitionIndex - 1);

        //高分区递归处理
        sort(values, partitionIndex + 1, high);

    }

    private int partition(T[] values, int low, int high) {

        //单独声明 i 指针，保护 low 指针不被修改，
        // i 和 j 是不同步的，
        //      j 指针用于对当前传入的整个数组，按照 low 到 high 的范围进行遍历
        //      i 指针用于记录 分区 界限值的索引位置
        int i = low;
        for (int j = low; j < high; j++) {
            if (values[j].compareTo(values[high]) < 1){
                T temp = values[j];
                values[j] = values[i];
                values[i] = temp;
                i++;
            }
        }

        // 将 分区阈值 pivot 放到 分区指针 partition index 所指向的位置
        T temp = values[i];
        values[i] = values[high];
        values[high] = temp;

        return i;
    }


    public static void main(String[] args) {
        Integer[] values = Sort.of(2, 5, 6, 7, 8, 8, 9, 2, 1, 6, 7, 5, 6, 11, 23);
//        Integer[] values = Sort.of(3, 1, 2, 5, 6, 0, 4, 4);
        Sort<Integer> sort = new QuickSort1<>(); // Java 7 Diamond 语法
        sort.sort(values);
        System.out.println(Arrays.asList(values));
    }
}
