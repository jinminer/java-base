package com.jinm.deepinjava.concurrent.advanced.usage;

import java.util.HashMap;
import java.util.Map;

public class HashMapDemo {

    public static void main(String[] args) {

        String strValue = "Hello,World";

        Integer intValue = new Integer(strValue.hashCode());

        Map<Object, Object> map = new HashMap<>();

        map.put(strValue, 1);
        map.put(intValue, 2);
    }
}
