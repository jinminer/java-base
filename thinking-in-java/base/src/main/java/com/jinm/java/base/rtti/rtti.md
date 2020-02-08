# 运行时类型鉴定

## 运行时类型鉴定(Runtime Type Identification)

* 在运行期，对象的类型会得到鉴定识别。

* 利用`RTTI` 可根据一个匿名的基础类句柄调查出类型信息。

## Class 对象



### 类标记

* 类标记：采用`Class.forName("XXX")`来产生 Class 对象的句柄

* 这样做不仅更加简单，而且更安全，因为它会在编译期间得到检查。由于它取消了对方法调用的需要，所以
  执行的效率也会更高。

* 类标记不仅可以应用于普通类，也可以应用于接口、数组以及基本数据类型。

* 针对每种基本数据类型的封装器类，它还存在一个名为TYPE 的标准字段。TYPE 字段的作用是为相关的基本数据类型产生 Class对象的一个句柄，如下所示：

  | class对象       | 封装类           |
  | --------------- | ---------------- |
  | `boolean.class` | `Boolean.TYPE`   |
  | `char.class`    | `Character.TYPE` |
  | `byte.class`    | `Byte.TYPE`      |
  | `short.class`   | `Short.TYPE`     |
  | `int.class`     | `Integer.TYPE`   |
  | `long.class`    | `Long.TYPE`      |
  | `float.class`   | `Float.TYPE`     |
  | `double.class`  | `Double.TYPE`    |
  | `void.class`    | `Void.TYPE`      |
  |                 |                  |



#### 造型前的检查

* `RTTI`
  * (1) 经典造型，如下溯造型，它用 `RTTI` 确保造型的正确性，并在遇到一个失败的造型后产生一个
    `ClassCastException` 违例。
    * 在进行下溯造型之前，先要执行类型检查，这通常叫作“类型安全”的下溯造型。
  * (2) 代表对象类型的Class 对象。可查询Class 对象，获取有用的运行期资料。

* `instanceof`

  * 静态调用

  * object是否是class的实例

    ```java
    object instanceof class
    ```

  * `RTTI` 在Java 中存在三种形式。关键字 `instanceof` 告诉我们对象是不是一个特定类型的实例（Instance 即“实例”）。

  * `instanceof` 有一个比较小的限制：只可将其与一个已命名的类型比较，不能同Class 对
    象作对比。

  * `instanceof`在Java的编译状态和运行状态是有区别的：

    * 在编译状态中，class可以是object对象的父类，自身类，子类。在这三种情况下Java编译时不会报错。

    * 在运行状态中，class可以是object对象的父类，自身类，不能是子类。在前两种情况下result的结果为true，最后一种为false。

*  `isInstance()` 
  * Java 1.1 为Class 类添加了 `isInstance` 方法。利用它可以动态调用`instanceof` 运算符。而在Java 1.0
    中，只能静态(`object instanceof class`)地调用它。
  * 在运行时检查对象的类，必须使用`isInstance()`方法。
  * `object.isInstance(class)`

## 反射

* `RTTI`限制
  * 如果不知道一个对象的准确类型，`RTTI` 会帮助我们调查。但却有一个限制：类型必须是在编译期间已知的，否则就不能用`RTTI` 调查它，进而无法展开下一步的工作。换言之，编译器必须明确知道 `RTTI` 要处理的所有类。
* `newInstance()`限制
  * `newInstance()`创建的类必须有一个默认构建器。没有办法用 `newInstance()`创建拥有非默认构建器的对象

* `RMI` 
  * 定义
    * 在运行期查询类信息的另一个原动力是通过网络创建与执行位于远程系统上的对象。这就叫作“远程方法调用”（`RMI`），它允许 Java 程序（版本 1.1 以上）使用由多台机器发布或分布的对象。
* 反射的意义
  * 匿名对象的类信息可在运行期被完整的揭露出来，而在编译期间不需要知道任何东西。
  * 通过“反射”同一个未知类型的对象打交道。
  * 反射：`Class.forName()`产生的结果不能在编译期间获知，所以所有方法签名信息都会在运行期间提取。
* `RTTI` 和“反射”的区别
  * 通过“反射”同一个未知类型的对象打交道时，`JVM` 只是简单地检查那个对象，并调查它从属于哪个特定的类（就象以前的 `RTTI` 那样）。
  * `RTTI` 和“反射”之间唯一的区别就是对 `RTTI` 来说，编译器会在编译期打开和检查.class 文件。换句话说，我们可以用“普通”方式调用一个对象的所有方法；
  * 但对“反射”来说，.class 文件在编译期间是不可使用的，而是由运行期环境打开和检查。

























