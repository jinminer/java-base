package com.jinm.java.base.array;

public class ArrayInitialize {

    public static void main(String[] args) {

        int[] a = new int[3];
        for (int i : a){
            System.out.println(i);
        }
        System.out.println("------------------------------------");
        int[] b = new int[5];
        a = b;
        for (int i : a){
            System.out.println(i);
        }

    }

}
