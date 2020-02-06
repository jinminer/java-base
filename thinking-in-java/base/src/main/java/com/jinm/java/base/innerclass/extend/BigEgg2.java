package com.jinm.java.base.innerclass.extend;

public class BigEgg2 extends Egg2 {

    /**
     * 类加载过程：
     * 在子类装载过程中，装载程序注意它有一个基础类（即 extends 关键字要表达的意思），所以随之将其载入。
     * 无论是否准备生成那个基础类的一个对象，这个过程都会发生。
     * 若基础类含有另一个基础类，则另一个基础类随即也会载入，以此类推。
     * 接下来，会在根基础类执行 static 初始化，再在下一个衍生类执行，以此类推。保证这个顺序是非常关键的，因为衍生类
     * 的初始化可能要依赖于对基础类成员的正确初始化。
     * 此时，必要的类已全部装载完毕，所以能够创建对象。
     * 首先，这个对象中的所有基本数据类型都会设成它们的默认值，而将对象句柄设为 null。
     * 随后会调用基础类构建器。在这种情况下，调用是自动进行的。但也完全可以用super 来自行指定构建器调用（就象在Beetle()构建器中的第一个操作一样）。
     * 基础类的构建采用与衍生类构建器完全相同的处理过程。基础顺构建器完成以后，实例变量会按本来的顺序得以初始化。
     * 最后，执行构建器剩余的主体部分。
     */
    public class Yolk extends Egg2.Yolk {

        //step 5
        public Yolk() {
            System.out.println("BigEgg2.Yolk()");
        }

        //step 7
        @Override
        public void f() {
            System.out.println("BigEgg2.Yolk.f()");
        }
    }

    //step 3
    public BigEgg2() {
        insertYolk(new Yolk());
    }

    public static void main(String[] args) {
        Egg2 e2 = new BigEgg2();
        e2.g();
    }
}

/**
 * Proper inheritance of an inner class
 */
class Egg2 {

    protected class Yolk {

        //step 4
        //step 2
        public Yolk() {
            System.out.println("Egg2.Yolk()");
        }

        public void f() {
            System.out.println("Egg2.Yolk.f()");
        }
    }

    //step 1
    private Yolk y = new Yolk();

    //step 3
    public Egg2() {
        System.out.println("New Egg2()");
    }

    public void insertYolk(Yolk yy) {
        y = yy;
    }

    //step 6
    public void g() {
        y.f();
    }

}
