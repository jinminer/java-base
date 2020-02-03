package com.jinm.java.base.innerclass;

public class InheritInner extends WithInner.Inner{

//Won't compile
//    InheritInner(){
//
//    }

    /**
     * without this construct：
     * No enclosing instance of type 'com.jinm.java.base.innerclass.WithInner' is in scope
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

class WithInner{

    class Inner{

    }

}
