# Java 面向对象设计

## 内容

* Java 接口设计
* Java 枚举设计
* Java 泛型设计
* Java 方法设计
* 总结



## Java 接口设计

### 内容

* 类设计
* 抽象类设计
* 内置类设计
* 接口

### 通用设计 - 类/接口名

* 模式：(形容词) + 名词
* 举例
  * 单名词：java.lang.String
  * 双名词：java.util.ArrayList
  * 形容词 + 名词：java.util.LinkedList

### 通用设计 - 可访问性

#### 四种修饰符

* public
* (default)
* protected：不能用于修饰最外层 class
* private：不能用于修饰最外层 class

#### 使用场景

* public：开放 API 使用场景
  * 举例：java.lang.String
* (默认)：仅在当前 package 下使用，属于私有 API
  * 举例：java.io.FileSystem

### 通用设计 - 可继承性

* final：`final` 不具备继承性，仅用于实现类，不能与 `abstract` 关键字同时修饰类
  * 举例：java.lang.String
* 非 final：最常见/默认的设计手段，可继承性依赖于可访问性
  * 举例：java.io.FileSystem

#### jdk详解

* java.lang.String 不能被继承，不可变类
  * 被 `final` 关键字修饰
    * String 类中有的方法不希望被子类覆盖，所以声明为 `final`
    * 反例：将`final`下沉到具体的不可变方法上。
      * 不可变方法较多，不适合下沉
      * 而且在不同的 jdk 版本， String 类会添加一些新的不可变方法，用`final`修饰类，使得类不可变，就不用每次新增加方法都添加`final`关键字
* String 类并不是真正意义上的不可变类
  * 从 java 1.5 开始**对象属性**可以通过反射修改（不是类属性）
  * 所以 String 不是真正意义上的不可变类，因为从 java 1.5 以后，它的值可以通过反射被修改

### 具体类设计

#### 常见场景

* 功能组件
  * HashMap
* 接口/抽象类实现
  * HashMap <- AbstractMap <- Map
* 数据对象
  * POJO
* 工具辅助
  * *Utils
  * ViewHelper
  * *Helper

#### 命名模式

* 前缀：”Default“、”Generic“、”Common“、”Basic“
* 后缀：”Impl“

### 抽象类设计

#### 常见场景

* 接口通用实现（模板模式）
  * AbstractMap
  * AbstractSet
  * AbstractList
* 状态/行为继承
* 工具类

#### 常见模式

* 抽象程度介于类与接口之间（Java 8+ 可完全由接口代替）
* 以”Abstract“或”Base“类名前缀
  * java.util.AbstractCollection
  * javax.sql.rowset.BaseRowSet

### 接口设计

#### 常见场景

* 上下游系统（组件）通讯契约
  * API
  * RPC
* 常量定义
* 标记接口
  * `Serializable`
  * `Cloneable`
  * `AutoCloseable`
  * `EventListener` 
* 常见模式
  * 无状态（Stateless）
  * 完全抽象（ < Java 8）
  * 局部抽象（ Java 8+）
  * 单一抽象（ Java 8 函数式接口）

### 内置类设计

#### 常见场景

* 临时数据存储类：java.lang.ThreadLocal.ThreadLocalMap
* 特殊用途的 API 实现：java.util.Collections.UnmodifiableCollection
* Builder 模式（接口）：java.util.Stream.Builder



## Java 枚举设计

### 内容

* ”枚举类“
* 基本特性
* 成员设计
* 构造器设计
* 方法设计

### 枚举类

#### 场景

* Java 枚举(enum)引入之前(Java 5之前)的模拟枚举实现类

#### 模式

* 成员用常量表示，并且类型为当前类型
* 常用关键字 final 修饰类
* 非 public 构造器

#### 基本特性

* 类结构（强类型）
* 继承 java.lang.Enum
* 不可显式的继承和被继承

#### 结论

* 根据枚举类编译后的字节码文件，得出如下结论：
  * 枚举(enum)实际是 final class
  * 枚举(enum)成员修饰符为 public static final
  * `values()` 是 Java 编译器做的字节码提升
* 枚举中可以定义抽象方法
  * 打破了 `final` 修饰的类不能拥有抽象方法的事实

#### “枚举类” V.S Java 枚举

| 特性            | “枚举类”         | 枚举 |
| --------------- | ---------------- | ---- |
| 类成员结构      | ✔                | ✔    |
| 必须具体类      | ✖ (可以是抽象类) | ✔    |
| 必须 `final` 类 | ✖                | ✔    |
| 支持继承类      | ✔                | ✖    |
| 支持实现接口    | ✔                | ✔    |
| 可被继承        | ✔                | ✖    |
| 抽象方法        | ✔                | ✔    |
| 可序列化        | ✔                | ✔    |



## Java 泛型设计

### 内容

* 泛型类设计
* 泛型接口设计
* 泛型方法设计

### 泛型使用场景

* 编译时强类型检查
* 避免类型强转
* 实现通用算法

### 泛型类型

> A generic type is a generic class or interface that is parameterized over types.

* 调用泛型类型
* 实例化泛型
* Java 7 Diamond 语法
* 类型参数命名约定

### 类型参数命名约定

* E：表示集合元素（Element）
* V：表示数值（Value）
* K：表示键（Key）
* T：表示类型
* R：函数返回值

### 泛型有界类型参数

* 单界限
* 多界限
* 泛型方法和有界类型参数

### 泛型通配类型

* 上界通配类型
* 下界通配类型

### 泛型类型擦写

> Generics were introduced to the Java language to provide tighter type checks at compile time and to support generic programming. To implement generics, the Java compiler applies type erasure to:
>
> * Replace all type parameters in generic types with their bounds or 'Object' if the type parameters are unbounded. The produced bytecode, therefore, contains only ordinary classes, interfaces, and methods.
> * Insert type casts if necessary to preserve type safety.
> * Generate bridge methods to preserve polymorphism in extended generic types.
>
> Type erasure ensures that no new classes are created for parameterrized types; consequently, generics incur no runtime overhead.



## Java 方法设计

### 内容

* 方法命名设计
* 方法返回类型设计
* 方法参数类型设计
* 方法参数名称设计
* 方法参数数量设计





## 问题要点

* HashMap是否是线程安全的？
  * 1.在只读情况下，线程安全
  * 2.在读写操作共存时，会因索引构建不一致而出现线程不安全的情况

