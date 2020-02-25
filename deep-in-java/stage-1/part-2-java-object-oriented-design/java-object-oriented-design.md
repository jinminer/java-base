# Java 面向对象设计

## 内容

* Java 接口设计
* Java 枚举设计
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

### 具体类设计

#### 常见场景

* 功能组件
* 接口/抽象类实现
* 数据对象
* 工具辅助









