package com.jinm.deepinjava.colletionsframework.algorithm;

import java.util.Arrays;

public class InsertionSort1<T extends Comparable<T>> implements Sort<T> {

    public static void main(String[] args) {

        Sort<Integer> insertionSort = new InsertionSort1<>();
        Integer[] values = Sort.of(4,5,1,3,2);
        insertionSort.sort(values);
    }

    @Override
    public void sort(T[] values) {

        for (int i = 1; i < values.length; i++) {
            for (int j = i; j > 0 ; j--) {

                if (values[j].compareTo(values[j-1]) < 0){
                    T temp = values[j];
                    values[j] = values[j-1];
                    values[j-1] = temp;
                }

            }
        }
        System.out.println(Arrays.toString(values));

    }

}
