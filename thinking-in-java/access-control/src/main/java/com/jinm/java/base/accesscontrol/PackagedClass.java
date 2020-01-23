package com.jinm.java.base.accesscontrol;

public class PackagedClass {
//class PackagedClass {
//    当类的访问控制符为默认（friendly）型时，该类只能由当前包的其他类访问，其他包的类没有访问权限

    public PackagedClass(){
        System.out.println("Creating a packaged class");
    }

}
