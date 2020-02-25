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
    
  * Java 是否是完全面向对象的语言？
  
    * 答案：否，因为 Java 存在原生类型(基本类型)
  
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

#### 知识点

* 异常信息打印时使用 `java.lang.Throwable#printStackTrace()` 方法会降低程序性能
  * `printStackTrace()` 方法会导致异常堆栈信息输出到标准错误输出流中
  * 而标准错误输出流属于系统级别的公共资源，可能会因为其他程序线程抢占资源，而使当前输入流程序排队等待的情况，所以会降低程序的性能
  * 可以调用其重载方法 `java.lang.Throwable#printStackTrace(java.io.PrintStream)` 指定输出流，以因为避免抢占资源，而出现的程序阻塞等待情况



### 泛型设计

* Java 泛型属于编译时处理，运行时擦写
  * 在运行时只读类信息，而不读类的泛型信息
  * 方法签名时，进行运行时擦写，类型化参数(泛型参数)等同于 Object
    * `List` 等同于 `List<String>`

#### 知识点

* 利用泛型进行程序设计时，如何区分 T 和 ? ，以及它们的使用场景
  * T 有一种类型的概念，通常 T 可以用在类型和方法，而 ? 仅用于方法
  * T 存在类型继承性，而 ? 不存在



## Java 函数式基础

### 面向函数编程(Since Java 8)

* Lambda 表达式
* 默认方法
* 方法引用

### 匿名内置类

* 使用场景

  > ​	Java 作为一门面向对象的静态语言，其封装性能够屏蔽数据结构的细节，从而更加关注模块的功能性。其静态性也确保了 Java 强类型的特性。
  >
  > ​	随着模块功能的提升，伴随而来的是复杂度的增加，代码的语义清晰度依赖于开发人员抽象和命名类活方法的能力。
  >
  > ​	尽管编程思想和设计模式能够促使编程风格趋于统一，然而大多数业务系统属于面向过程的方式，这与面向对象编程在一定程度上存在一些冲突。Java 编程语言为了解决这个问题，引入了匿名内置类的方案。
  >
  > * 注：
  >   * (1) Java 是一种面向对象的语言（不是完全面向对象，因为有基本类型）
  >   * (2) Java 是一种静态语言
  >     * 静态语言：编译后执行（类文件 ---> 字节码 ---> 机器码）
  >     * 动态语言：在运行时，解释执行
  >     * `jsp`  ---> 静态，`JavaScript` ---> 动态
  >   * (3) 封装性

* 典型场景
  * Java Event / Listener
  * Java Concurrent
  * Spring Template

* 基本特性
  * 无名称类，有类型
    * 类名称(表面(代码结构)上看没有，其实是有的)，从编译后的class文件可以看出
  * 声明位置(执行模块)
    * static block
    * 实例 block
    * 方法(类/实例)
    * 构造器
  * 并非特殊的类结构
    * 类全名称：`${package}.${declared_class}.${num}` 
* 基本特点
  * 基于多态(多数基于接口编程)
  * 实现类无需名称
  * 允许多个抽象方法
* 编程局限
  * 代码臃肿
  * 强类型约束
  * 接口方法升级

### Lambda 表达式

* 使用场景
  * 处理函数变为方法参数
  * 将代码看作数据处理
* 基本特点
  * 流程编排清晰
    * 数据处理流程是一段一段进行，处理流程编排结构明朗
  * 函数类型编程
    * 不再原先的强类型编程，即不需要强绑定在某个类型上面
    * 数据处理流程的参数不在是某个具体的类型的方法，而是一个函数类型
  * 改善代码臃肿
    * 匿名内置代码实现啰嗦
  * 兼容接口升级
    * Java 8 中 接口中可以声明默认的 default 方法实现
* 实现手段
  * `@FunctionalInterface` 接口
  * Lambda 语法
  * 方法引用
  * 接口 default 方法实现
* 编程局限
  * 单一抽象方法
  * Lambda 调试困难
  * `Stream API` 操作能力有限

### 默认方法

* 使用场景
  * 当接口升级时，添加了新的抽象方法，此时基于老接口的实现类必然会遇到编译问题。
  * 默认方法的出现能够解决以上问题，同时也能为实现类提供默认或样板实现，减少实现类的负担，无需再使用 Adapter 实现。
* 提示
  * 默认方法不列入 `@FunctionalInterface` 方法计算

### 函数式接口

* 基本特性
  * 所有的函数式接口都引用一段执行代码
  * 函数式接口没有固定的类型
  * 只有固定模式 
    * `SCFP(Supplier + Consumer + Function + Predicate) + Action` 
  * 利用方法引用来实现模式匹配/关联
    * 即所谓固定模式是通过方法引用来实现的



## Java 模块化基础

### Java 9 模块化

* 动机
  * 强封装的实施与精确的模块依赖声明使得大型应用和框架更好的维护
  * 安全提升
  * 增快应用模块中类型检测的速度，提升应用性能
  * 瘦身 `JDK` 已经 `SE` 的体积，有利于在小型计算机设备使用和云端部署

* 收益
  * 提升平台伸缩性
  * 提升平台完整性
  * 提升性能

### 定义模块

* 模块声明

  > A module's self-description is expressed in its module declaration, a new construct of the Java programing language

* 模块依赖

  > One or more requires clauses can be added to declare that the module depends, by name, upon some other modules, at both compile time and run time.

* 模块导出

  >exports clauses can be added to declare that the module makes all, and only, the public types in specific packages available for use by other modules.

### 模块化

* 强封装性
  * 基本特性
    * 并非所有的 `public` Class 都可以被运用，需要 `exports` 来配合
    * `exports` 所配置的 `package` 下必须要有 Class
  * 负面问题
    * 对开发人员的要求较高（对 Class 透明化）
      * 必须了解相关 `module-info.java` 的语义
      * 需要了解某些类的依赖
      * 需要了解某些类的职责
  * 思考
    * 收益不大，代价不小
    * 对开发团队要求较高
    * 区别
      * Java 9 之前：采用 jar 文件管理
      * Java 9 模块化之后，变成了 `module-info.java` 管理
      * 还需要考虑与 `Maven` 等依赖管理组件如何配合



### 模块结构

* 模块描述文件
  *  `module-info.java` 
  * 模块名称类似于 Maven 中的 `artifactId` 
  * 区别
    * Maven 中是默认传递依赖
    * Java 9 模块化中是非传递依赖（相当于 Maven 中的 `optional = true`）
* 平台模块
* 模块 artifacts



## 总结



