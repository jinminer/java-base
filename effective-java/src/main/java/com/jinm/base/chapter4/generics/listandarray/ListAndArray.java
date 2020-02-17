package com.jinm.base.chapter4.generics.listandarray;

import java.util.List;

public class ListAndArray {

    public static void main(String[] args) {

        // Fails at runtime!
        Object[] objectArray = new Long[1];
        objectArray[0] =
                "I don't fit in"; // Throws ArrayStoreException


    }

}
