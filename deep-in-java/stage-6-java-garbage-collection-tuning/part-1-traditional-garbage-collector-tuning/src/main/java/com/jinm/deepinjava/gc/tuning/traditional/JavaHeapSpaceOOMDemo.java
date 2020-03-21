package com.jinm.deepinjava.gc.tuning.traditional;

/**
 *
 * java.lang.OutOfMemoryError: Java heap space 案例
 */
public class JavaHeapSpaceOOMDemo {

    public static void main(String[] args) {

        // 2M
        int size = 2 * 1024 * 1024;

        // -Xmx10m -> 10 * 1024 * 1024
        // int = 32bit = 4 Byte
        // size(2MB) * 4 = 8MB
        // [OK] -Xmx16m
        int[] array = new int[size];

        /*
         * Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
	     * at com.jinm.deepinjava.gc.tuning.traditional.JavaHeapSpaceOOMDemo.main(JavaHeapSpaceOOMDemo.java:17)
         */

    }

}
