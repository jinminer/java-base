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



## `FilterInputStream`

* `DataInputStream`
  * 功能
    * 与 `DataOutputStream` 联合使用，使自己能以机动方式读取一个流中的基本数据类型
      （int，char，long 等等） 
  * 构造参数
    * `InputStream`
  * 使用
    * 包含了一个完整的接口，以便读取基本数据类型
* `BufferedInputStream` 
  * 功能
    * 避免每次想要更多数据时都进行物理性的读取，告诉它“请先在缓冲区里找”
  * 构造参数
    * `InputStream`，没有可选的缓冲区大小
  * 使用
    * 本身并不能提供一个接口，只是发出使用缓冲区的要求。要求同一个接口对象连接到一起
* `LineNumberInputStream` 
  * 功能
    * 跟踪输入流中的行号；可调用 `getLineNumber()`以及`setLineNumber(int)` 只是添加
      对数据行编号的能力，所以可能需要同一个真正的接口对象连接
* `PushbackInputStream` 
  * 功能
    * 有一个字节的后推缓冲区，以便后推读入的上一个字符 
  * 构造参数
    * `InputStream`
  * 使用
    * 通常由编译器在扫描器中使用，因为 Java 编译器需要它。一般不在自己的代码中使用

## `FilterOutputStream`

* `DataOutputStream` 
  * 功能
    * 与 `DataInputStream` 配合使用，以便采用方便的形式将基本数据类型（int，char，long
      等）写入一个数据流
  * 构造参数
    * `OutputStream`
  * 使用
    * 包含了完整接口，以便我们写入基本数据类型
* `PrintStream` 
  * 功能
    * 用于产生格式化输出。`DataOutputStream` 控制的是数据的“存储”，而 `PrintStream` 控制的是
      “显示” 
  * 构造参数
    * `OutputStream`，可选一个布尔参数，指示缓冲区是否与每个新行一同刷新
  * 使用
    * 对于自己的`OutputStream` 对象，应该用“final”将其封闭在内。可能经常都要用到它

* `BufferedOutputStream` 
  * 功能
    * 用它避免每次发出数据的时候都要进行物理性的写入，要求它“请先在缓冲区里
      找”。可调用flush()，对缓冲区进行刷新
  * 构造参数
    *  `OutputStream`，可选缓冲区大小
  * 使用
    * 本身并不能提供一个接口，只是发出使用缓冲区的要求。需要同一个接口对象连接到一起



# File

* 代表一个特定文件的名字，也代表目录内一系列文件的名字。

* 若代表一个文件集，便可用list()方法查询这个集，返回的是一个字串数组。之所以要返回一个数组，而非某个灵活的集合类，是因为元素的数量是固定的。
* 而且若想得到一个不同的目录列表，只需创建一个不同的File 对象即可。可以将其理解为“`FilePath`”（文件路径）。



# `StreamTokenizer` 

* `StreamTokenizer` 并不是从 `InputStream` 或 `OutputStream` 衍生的，它只随同`InputStream` 工作。

* `StreamTokenizer` 类用于将任何 `InputStream` 分割为一系列“记号”（Token）。这些记号实际是一些断续的文本块，中间用我们选择的任何东西分隔。例如，我们的记号可以是单词，中间用空白（空格）以及标点符
  号分隔。

## `StringTokenizer`

* `StringTokenizer` 的作用是每次返回字串内的一个记号。这些记号是一些由制表站、空格以及新行分隔的连
  续字符。



# 对象序列化

`Object Serialization` 

* 面向那些实现了`Serializable` 接口的对象，可将它们转换成一系列字节，并可在以后完全恢复回原来的样子。
* 序列化机制能自动补偿操作系统间的差异

## 远程方法调用（`RMI`）

对象序列化为`RMI`提供了支持

## 对象序列化

* 对象序列化特别“聪明”的一个地方是它不仅保存了对象的“全景图”，而且能追踪对象内包含的所有句柄
  并保存那些对象；接着又能对每个对象内包含的句柄进行追踪；
* 以此类推。我们有时将这种情况称为“对象网”，单个对象可与之建立连接。而且它还包含了对象的句柄数组以及成员对象。
* 若必须自行操纵一套对象序列化机制，那么在代码里追踪所有这些链接时可能会显得非常麻烦。
* 在另一方面，由于Java 对象的序列化似乎找不出什么缺点，所以请尽量不要自己动手，让它用优化的算法自动维护整个对象网。



## `Externalizable`

* 不希望对象的某一部分序列化；或者某一个子对象完全不必序列化

## `transient`

* 要注意的另一个问题是安全，因为序列化处理也会将private 数据保存下来。若有需要保密的字段，应将其
  标记成 transient。
* 控制序列化过程时，可能有一个特定的子对象不愿让Java 的序列化机制自动保存与恢复。
* 一般地，若那个子对象包含了不想序列化的敏感信息（如密码），就会面临这种情况。
* 即使那种信息在对象中具有“private”（私有）属性，但一旦经序列化处理，人们就可以通过读取一个文件，或者拦截网络传输得到它。
* 为防止对象的敏感部分被序列化，一个办法是将自己的类实现为`Externalizable`，就象前面展示的那样。这
  样一来，没有任何东西可以自动序列化，只能在`writeExternal()`明确序列化那些需要的部分。然而，若操作的是一个 `Serializable` 对象，所有序列化操作都会自动进行。
* 为解决这个问题，可以用`transient`（临时）逐个字段地关闭序列化，它的意思是“不要麻烦你（指自动机制）保存或恢复它了——我会自己处理的”。

* 被`transient`修饰的字段一旦对象恢复成原来的样子，字段值就会变成null。
* 由于`Externalizable` 对象默认时不保存它的任何字段，所以transient 关键字只能伴随`Serializable` 使用。



## `Externalizable` 的替代方法
* 若不是特别在意要实现 `Externalizable` 接口，还有另一种方法可供选用。我们可以实现 `Serializable` 接
  口，并添加（注意是“添加”，而非“覆盖”或者“实现”）名为`writeObject()`和 `readObject()`的方法。
  一旦对象被序列化或者重新装配，就会分别调用那两个方法。也就是说，只要提供了这两个方法，就会优先
  使用它们，而不考虑默认的序列化机制。
  这些方法必须含有下列准确的签名：

  ```java
  private void writeObject(ObjectOutputStream stream) throws IOException;
  private void readObject(ObjectInputStream stream)throws IOException, ClassNotFoundException
  ```

* 从设计的角度出发，情况变得有些扑朔迷离。首先，大家可能认为这些方法不属于基础类或者`Serializable`
  接口的一部分，它们应该在自己的接口中得到定义。但请注意它们被定义成“private”，这意味着它们只能
  由这个类的其他成员调用。然而，我们实际并不从这个类的其他成员中调用它们，而是由`ObjectOutputStream` 和`ObjectInputStream` 的 `writeObject()`及 `readObject()`方法来调用我们对象的
  `writeObject()`和`readObject()`方法（注意我在这里用了很大的抑制力来避免使用相同的方法名——因为怕混淆）。大家可能奇怪 `ObjectOutputStream` 和`ObjectInputStream` 如何有权访问我们的类的 private方法——只能认为这是序列化机制玩的一个把戏。

* `o.writeObject(sc)`其中，`writeObject()`方法必须核查`sc`，判断它是否有自己的 `writeObject()`方法（不是检查它的接口——它根本就没有，也不是检查类的类型，而是利用反射方法实际搜索方法）。类似的情况也会在 `readObject()`上发生。
* 只要将所有东西都序列化到单独一个数据流里，就能恢复获得与以前写入时完全一样的对象网(相同的内存地址)，不会不慎造成对象的重复。





























