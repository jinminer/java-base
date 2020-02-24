# Java 语言基础

## Java 面向过程编程

### 核心要素

* 数据结构：原生类型、对象类型、数组类型（对象类型）、集合类型（对象类型）
* 方法调用：访问性、返回类型、方法参数、异常等
* 执行流程：赋值、逻辑、迭代（循环）、递归等

### 基础要点

* `java` 基本类型：

  * boolean(1位)、byte(8位)、short(16位)、char(16位)、int(32位)、long(64位)、float(32位)、double(64位)

  * long和double的线程安全问题

    > * Java虚拟机规范定义的许多规则中的一条：**所有对基本类型的操作，除了某些对long类型和double类型的操作之外，都是原子级的。**
    > * 目前的`JVM`都是将32位作为原子操作，并非64位。当线程把主存中的 long/double类型的值读到线程内存中时，可能是两次32位值的写操作，显而易见，如果几个线程同时操作，那么就可能会出现高低2个32位值出错的情况发生。
    > * 要在线程间共享long与double字段是，必须在synchronized中操作，或是声明为volatile。

* 访问控制
  * Java 9 之前
    * public ：all
    * protect ：继承/同包
    * (default) ：同包
    * private ：当前类/内部类
  * Java 9 之后
    * 新增的模块化方式，增加了封装性
* Java 中引用
  * 强引用、弱引用、软引用、幻象引用、最终引用(类对象被回收时)



## Java 面向对象基础

### 核心要素

* 面向对象特性
  * 封装性
  * 派生性
  * 多态性

* 面向对象设计模式
  * `GoF 23` ：构建、结构、行为
  * 方法设计：名称、访问性、参数、返回类型、异常
  * 泛型设计：类级别、方法级别
  * 异常设计：层次性、传播性

### 方法设计

* 单元：一个类或者一组类（组件）
  * 类命名：采用名词结构
    * 动词过去式 + 名词
      * `ContextRefreshedEvent`
    * 动词ing + 名词
      * `InitializingBean`
    * 形容词 + 名词
      * `ConfigurableApplicationContext` 
* 执行：某个方法
  * 方法命名：动词
    * execute
    * callback
    * run
  * 参数命名：名词
  * 异常：
    * 根（顶层）异常
      * `Throwable`
        * checked 类型：`Exception`
        * unchecked 类型：`RuntimeException`
        * 不常见：`Error` 
    * Java 1.4 `java.lang.StackTraceElement` 
      * 添加异常原因（cause）
        * 反模式：吞掉某个异常
        * 性能：注意 `java.lang.Throwable#fillInStackTrace()` 方法的开销，避免异常栈调用深度
          * 方法一：`JVM`参数控制栈深度（物理屏蔽）
          * 方法二：`logback` 日志框架控制堆栈输出深度（逻辑屏蔽）



### 泛型设计

* Java 泛型属于编译时处理，运行时擦写
  * 在运行时只读类信息，而不读类的泛型信息
  * 方法签名时，进行运行时擦写，类型化参数(泛型参数)等同于 Object
    * `List` 等同于 `List<String>`





## Java 函数式基础



## Java 模块化基础



## 总结



