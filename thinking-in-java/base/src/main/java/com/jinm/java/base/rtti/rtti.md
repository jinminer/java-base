# 运行时类型鉴定

## 运行时类型鉴定(Runtime Type Identification)

在运行期，对象的类型会得到鉴定识别。



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
    中，只能静态(`source instanceof class`)地调用它。
  * `object.isInstance(class)`

  



























