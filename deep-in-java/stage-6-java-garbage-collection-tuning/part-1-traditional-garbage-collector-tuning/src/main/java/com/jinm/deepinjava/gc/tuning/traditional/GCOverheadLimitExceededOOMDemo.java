package com.jinm.deepinjava.gc.tuning.traditional;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Random;

public class GCOverheadLimitExceededOOMDemo {

    public static void main(String[] args) {

        // Map: HashMap TreeMap IdentityHashMap
        Map<String,String> map = new HashMap<>();
        Random r = new Random();

        while (true){
            map.put(String.valueOf(r.nextInt()), "true");
        }

    }

}
