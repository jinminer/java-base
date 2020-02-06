package com.jinm.java.base.collection.unsupportedoperation;

import java.util.*;

public class UnsupportedOperation {

    private static String[] s = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",};

    static List a = Arrays.asList(s);
    static List a2 = Arrays.asList(new String[] { s[3], s[4], s[5] });
    public static void main(String[] args) {
        Collection1.print(a); // Iteration
        System.out.println(
                "a.contains(" + s[0] + ") = " +
                        a.contains(s[0]));
        System.out.println(
                "a.containsAll(a2) = " +
                        a.containsAll(a2));
        System.out.println("a.isEmpty() = " +
                a.isEmpty());
        System.out.println(
                "a.indexOf(" + s[5] + ") = " +
                        a.indexOf(s[5]));
        // Traverse backwards:
        ListIterator lit = a.listIterator(a.size());
        while(lit.hasPrevious()) {
            System.out.print(lit.previous());
        }
        System.out.println();
        // Set the elements to different values:
        for(int i = 0; i < a.size(); i++) {
            a.set(i, "47");
        }

        //Arrays.asList()产生了一个 List（列表），该列表是由一个固定长度的数组后推出来的。因此唯一能够支持的就是那些不改变数组长度的操作。
        //asList()方法返回的 ArrayList 并不是 【java.util.ArrayList】 而是 【java.util.Arrays.ArrayList】，
        //这两个 ArrayList 虽然都继承了 【AbstractList】类，但是后者没有覆写可改变数字长度的方法，所以直接调用是父类的方法，会抛出异常
        System.out.println("------------------ Unsupported Operation ------------------");
        Collection1.print(a);
        // Compiles, but won't run:
        lit.add("X"); // Unsupported operation
        a.clear(); // Unsupported
        a.add("eleven"); // Unsupported
        a.addAll(a2); // Unsupported
        a.retainAll(a2); // Unsupported
        a.remove(s[0]); // Unsupported
        a.removeAll(a2); // Unsupported
    }
}

class Collection1{
    public static void print(Collection collection){
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println();
    }
}
