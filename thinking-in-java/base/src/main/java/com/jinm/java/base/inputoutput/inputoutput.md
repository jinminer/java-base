# 输入/输出

## `InputStream`
`InputStream` 的作用是标志那些从不同起源地产生输入的类。这些起源地包括（每个都有一个相关的`InputStream` 子类）：

* 字节数组
*  String 对象
* 文件
* “管道”，它的工作原理与现实生活中的管道类似：将一些东西置入一端，它们在另一端出来。 
* 一系列其他流，以便我们将其统一收集到单独一个流内。
*  其他起源地，如Internet 连接等。

### 类型
* `ByteArrayInputStream` 
    * 功能
        * 允许内存中的一个缓冲区作为`InputStream` 使用
    * 构造器参数
        * 从中提取字节的缓冲区
    * 使用
        * 作为一个数据源使用。
        * 通过将其同一个 `FilterInputStream` 对象连接，可提供一个有用的接口
* `StringBufferInputStream`
    * 功能
        *  将一个 String 转换成 `InputStream`。
    * 构造参数
        *  一个String（字串）
        * 基础的实施方案实际采用一个`StringBuffer`（字串缓冲）
    * 作用
        * 作为一个数据源使用。
        * 通过将其同一个 `FilterInputStream` 对象连接，可提供一个有用的接口
* ``FileInputStream`` 
    * 功能
        * 用于从文件读取信息 
    * 构造参数
        * 代表文件名的一个 String，或者一个 File 或`FileDescriptor` 对象
    * 使用
        * 作为一个数据源使用。
        * 通过将其同一个 `FilterInputStream` 对象连接，可提供一个有用的接口
* `PipedInputString`
    * 功能
        * 产生为相关的 `PipedOutputStream` 写的数据。实现了“管道化”的概念
* `PipedOutputStream`
    * 作用
        * 作为一个数据源使用。通过将其同一个 `FilterInputStream` 对象连接，可提供一个有用的接口
* ``SequenceInputStream`` 
    * 功能
        * 将两个或更多的`InputStream` 对象转换成单个`InputStream` 使用 
    * 构造参数
        * 两个`InputStream` 对象或者一个 `Enumeration`，用于 `InputStream` 对象的一个容器
    * 作用
        * 作为一个数据源使用。
        * 通过将其同一个`FilterInputStream` 对象连接，可提供一个有用的接口
* ``FilterInputStream`` 
    * 功能
        * 对作为破坏器接口使用的类进行抽象；那个破坏器为其他 `InputStream` 类提供了有用的功能。

## `OutputStream`
### 类型

* ``ByteArrayOutputStream`` 
    * 功能
        * 在内存中创建一个缓冲区。我们发送给流的所有数据都会置入这个缓冲区。 
    * 构造参数
        * 可选缓冲区的初始大小
    * 作用
        * 用于指出数据的目的地。
        * 若将其同`FilterOutputStream` 对象连接到一起，可提供一个有用的接口
* ``FileOutputStream`` 
    * 功能
        * 将信息发给一个文件 
    * 构造参数
        * 用一个 `String` 代表文件名，或选用一个`File` 或`FileDescriptor` 对象
    * 作用
        * 用于指出数据的目的地。
        * 若将其同`FilterOutputStream` 对象连接到一起，可提供一个有用的接口
* ``PipedOutputStream`` 
    * 功能
        * 我们写给它的任何信息都会自动成为相关的`PipedInputStream` 的输出。
            实现了“管道化”的概念
    * 构造参数
        *  `PipedInputStream`
    * 作用
        * 为多线程处理指出自己数据的目的地
        * 将其同`FilterOutputStream` 对象连接到一起，便可提供一个有用的接口
* ``FilterOutputStream`` 
    * 功能
      * 对作为破坏器接口使用的类进行抽象处理；
      * 那个破坏器为其他 `OutputStream` 类提供了有用的功能。

## 输入/输出架构中的装饰器设计

### 装饰器
   * 定义
        利用层次化对象动态和透明地添加单个对象的能力的做法叫作“装饰器”（Decorator）方案。
   * 规则
        装饰器方案规定封装于初始化对象中的所有对象都拥有相同的接口，以便利用装饰器的“透明”性质——我们将相同的消息发给一个对象，无论它是否已被“装饰”。
   * 场景
           * 子类处理要求大量子类对每种可能的组合提供支持时，便经常会用到装饰器
           * Java IO 库中的“过滤器”（Filter）类：
                抽象的“过滤器”类是所有装饰器的基础类（装饰器必须拥有与它装饰的那个对象相同的接口，但装饰器亦可对接口作出扩展，这种情况见诸于几个特殊的“过滤器”类中）。
        * Java IO 库要求许多不同的特性组合方案，这正是装饰器方案显得特别有用的原因。

   * 缺点
        * 在我们写一个程序的时候，装饰器为我们提供了大得多的灵活性（因为可以方便地混合与匹配属性），但它们也使自己的代码变得更加复杂，由于组合形式太多，造成子类处理变得不切实际。
        * 原因在于Java IO 库操作不便，我们必须创建许多类——“核心”IO 类型加上所有装饰器——才能得到自己希望的单个IO 对象。

* 示例
  * `FilterInputStream` 和`FilterOutputStream` （这两个名字不十分直观）提供了相应的装饰器接口，用于控制一个特定的输入流（`InputStream`）或者输出流（`OutputStream`）。
  * 它们分别是从`InputStream`和`OutputStream`（它们都属于抽象类，在理论上为我们与一个流的不同通信手段都提供了一个通用的接口。）衍生出来的。
  * 事实上，`FilterInputStream` 和 `FilterOutputStream` 只是简单地模仿了自己的基础类，它们是一个装饰器的基本要求。

















