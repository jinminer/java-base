package com.jinm.deepinjava.language.foundation;

import java.util.Collections;
import java.util.List;

public class GenericTypeDemo {

    /**
     *
     * @param <S> 来源类型
     * @param <T> 转换类型
     */
    private interface Converter<S, T>{

        //类型擦写：在运行时并不知道具体的类型
        T convert(S source);
    }

    public static void main(String[] args) {

        //编译后，用 javap -v 指令查看 .class 文件，发现泛型化参数还在
        new Converter<String, Integer>(){

            public Integer convert(String source) {
                return null;
            }
        };

        new Converter<Integer, String>() {
            public String convert(Integer source) {
                return null;
            }
        };

        //编译器认为 data1 和 data2 是等同的
        List<String> data1 = Collections.emptyList();
        List data2 = Collections.emptyList();
        data1 = data2;

    }

    /**
     * 编译错误：方法签名冲突
     *      编译器认为 List<String> 和 List 是同等类型，不能作为方法签名的元素来区别对待
     * 方法签名：#doConvert(List)
     *
     * private static void doConvert(List<String> value){
     *
     * }
     *
     */

    //方法签名：#doConvert(List)
    private static void doConvert(List value){

    }

    //重载
    private static void doConvert(List<String> value, int data){

    }

}
