package com.jinm.java.base.interfacedata.interfacefieldaccess;

import com.jinm.java.base.interfacedata.ClassFieldData;
import com.jinm.java.base.interfacedata.InterfaceData;

public class InterfaceFieldAccess {

    public static void main(String[] args) {

//        InterfaceData.A  default: public static final
//        字段并不是接口的一部分，而是保存于那个接口的 static 存储区域中
        System.out.println(InterfaceData.A);
        ClassFieldData classFieldData = new ClassFieldData();

//        'B' is not public in 'com.jinm.java.base.interfacedata.ClassFieldData'. Cannot be accessed from outside package
//        System.out.println(classFieldData.B);
    }

}
