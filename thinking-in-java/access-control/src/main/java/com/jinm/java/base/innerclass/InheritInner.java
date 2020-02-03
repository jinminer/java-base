package com.jinm.java.base.innerclass;

public class InheritInner extends WithInner.Inner{

//Won't compile
//    InheritInner(){
//
//    }

    /**
     * without this construct：
     * No enclosing instance of type 'com.jinm.java.base.innerclass.WithInner' is in scope
     * 由于内部类构建器必须同封装类对象的一个句柄联系到一起，所以从一个内部类继承的时候，情况会稍微变
     * 得有些复杂。[这儿的问题是封装类的“秘密”句柄必须获得初始化]，而且在衍生类中不再有一个默认的对象
     * 可以连接。
     * @param wi
     */
    InheritInner(WithInner wi){
        wi.super();
    }

    public static void main(String[] args) {

        //InheritInner 只对内部类进行了扩展，没有扩展外部类。但在需要创建一个构建器的时候，
        //默认对象已经没有意义，我们不能只是传递封装对象的一个句柄。此外，必须在构建器中采用下述语法：
        //enclosingClassHandle.super();
        //它提供了必要的句柄，以便程序正确编译。
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);

    }

}

/**
 * 由于每个类都会生成一个.class 文件，用于容纳与如何创建这个类型的对象有关的所有信息（这种信息产生
 * 了一个名为 Class 对象的元类），所以大家或许会猜到内部类也必须生成相应的.class 文件，用来容纳与它
 * 们的Class 对象有关的信息。这些文件或类的名字遵守一种严格的形式：先是封装类的名字，再跟随一个$，
 * 再跟随内部类的名字。
 * 如果内部类是匿名的，那么编译器会简单地生成数字，把它们作为内部类标识符使用。若内部类嵌套于其他
 * 内部类中，则它们的名字简单地追加在一个$以及外部类标识符的后面。
 */
class WithInner{

    class Inner{

    }

}
