package com.jinm.deepinjava.language.foundation.methodsignature;

import java.sql.DataTruncation;
import java.sql.SQLException;
import java.util.Set;
import java.util.SortedSet;

public class JavaMethodSignature {

    /**
     * 1.重写方法的签名规则（JLS Java 语法规范）
     * 2.Accessibility 子类大于或者等于父类
     * 3.返回类型：如果是原生类型，子类与父类保持一直
     *         如果是对象类型，子类的返回类型可以是父类的派生类
     * 4.方法参数：子类要保证和父类参数顺序、数量、类型相同
     * 5.异常：如果是 checked 异常，子类方法的异常允许是父类方法异常的子类
     *                           子类的异常类别可以是父类的子集
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }

}

class A{

    public Set set() throws SQLException, SecurityException {
        return null;
    }

}

class B extends A{

    // DataTruncation 是 SQLException 的子类，同时是父类异常集合的子集
    @Override
    public SortedSet set() throws DataTruncation {
        return null;
    }
}
















