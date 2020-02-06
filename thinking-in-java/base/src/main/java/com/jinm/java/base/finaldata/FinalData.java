package com.jinm.java.base.finaldata;

public class FinalData {

    final int[] array = {1,2,3};


    // 含有固定初始化值（即编译期常数）的 fianl static 基本数据类型，它们的名字根据规则要全部采用大写;
    // 编译期间是未知的，在运行期间使用随机生成的数字, 没有大写
    static final int i5 = (int)(Math.random()*20);

    public static void main(String[] args) {

        FinalData finalData = new FinalData();
        finalData.array[1] = 0;
//        finalData.array = new int[]{1, 2, 3};
//        Cannot assign a value to final variable 'array'



    }

}
