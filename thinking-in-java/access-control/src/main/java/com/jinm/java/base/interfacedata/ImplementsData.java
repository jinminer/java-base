package com.jinm.java.base.interfacedata;

public class ImplementsData implements InterfaceData {

    public void test() {

    }

    public static void main(String[] args) {
        ImplementsData implementsData = new ImplementsData();

//        接口中定义的字段会自动具有 static 和final 属性。
//        Cannot assign a value to final variable 'A'
//        ImplementsData.A = 2;
    }

}
