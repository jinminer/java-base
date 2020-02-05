package com.jinm.java.base.collection.crash;

import java.util.Stack;
import java.util.Vector;

public class CrashJava {

    @Override
    public String toString() {

        //递归调用：栈溢出
        //字符拼接中 this 即 this.toString()，会发生递归循环调用
        //return "CrashJava address: " + this + "\n";
        return "CrashJava address: " + super.toString() + "\n";
    }

    public static void main(String[] args) {

        Vector v = new Vector();
        for (int i = 0; i < 5; i++) {
            v.addElement(new CrashJava());
        }

        System.out.println(v);

    }

}
