package com.jinm.deepinjava.objectoriented.design;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumClassDemo {

    public static void main(String[] args) {

        /**
         * 思考：
         *      Q1：THREE 到底是第几个定义的？
         *      Q2：能否输出所有成员？
         *
         */
        //自定义枚举
        println(Counting.ONE);
        println(Counting.FIVE);

        //枚举
        //Q3：为什么枚举会输出成员的名称
        //      Java 虚拟机帮助提升的操作。
        println(CountingEnum.ONE);
        println(CountingEnum.FIVE);



        //
        printEnumMeta(CountingEnum.FIVE);
        printEnumMeta(CountingEnum.THREE);

        System.out.println("_________________________couting members___________________________________");
        //自定义实现
        printCountingMembers();
        System.out.println("------");
        //Java 枚举字节码提升 values()方法
        printCountingEnumMembers();

    }

    private static void println(Counting counting){
        System.out.println(counting.toString());
    }

    private static void println(CountingEnum counting){
        System.out.println(counting.toString());
    }

    private static void printCountingEnumMembers(){
        Stream.of(CountingEnum.values())
                .forEach(System.out::println);
    }

    private static void printCountingMembers(){
        Stream.of(Counting.values())
                .forEach(System.out::println);
    }

    private static void printEnumMeta(Enum enumeration){

        //任何枚举都扩展了 java.lang.Enum
        System.out.println("Enumeration type: " + enumeration.getClass());
        System.out.println("Enumeration type: " + (enumeration instanceof Enum));

        //java.lang.Enum#name() -> 成员名称
        System.out.println("member: " + enumeration.name());

        //java.lang.Enum#ordinal() -> 成员定义位置
        System.out.println("ordinal: " + enumeration.ordinal());

        // values()方法是 Java 编译器给枚举提升的方法（可以认为是字节码提升）

    }

}

enum CountingEnum{

    //
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private int value;
    CountingEnum(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Counting{" +
                "value=" + value +
                '}';
    }

}

/**
 * 枚举类：计数
 * 缺陷：
 *      强类型约束（相对于常量，常量不能自我表述）
 * 定义：
 *      用常量定义本类，并用数据状态（字段）表示本类
 *
 */
final class Counting{

    public static final Counting ONE = new Counting(1);
    public static final Counting TWO = new Counting(2);
    public static final Counting THREE = new Counting(3);
    public static final Counting FOUR = new Counting(4);
    public static final Counting FIVE = new Counting(5);

    private int value;
    private Counting(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Counting{" +
                "value=" + value +
                '}';
    }

    public static Counting[] values(){

        Field[] fields = Counting.class.getDeclaredFields();

        //Fields -> filter -> public static final fields -> get

        return Stream.of(fields).filter(field -> {
            int modifiers = field.getModifiers();
            return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
        }).map(field -> {
            //Field -> Counting
            try {
                return field.get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList()).toArray(new Counting[0]);

    }

}
