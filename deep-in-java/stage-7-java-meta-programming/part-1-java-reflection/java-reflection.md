# Java 反射（Reflection）

## 内容提要

* Java 反射基础
* Java 反射 API 运用
* Java 8 反射提升



## Java 反射基础

### Java 反射概念

Reflection is commonly used by programs which require the ability to **examine or modify** the **runtime** behavior of applications running in the Java virtual machine. This is a relatively advanced feature and should be used only by developers who have a strong grasp of the fundamentals of the language. With that caveat in mind, reflection is a powerful technique and can enable applications to perform operations which would otherwise be impossible.

### Java 反射使用场景

* 扩展性特性
  * An application may make use of external, user-defined classes by creating instances of extensibility objects using their fully-qualified names.
* 类浏览器和虚拟开发环境
  * A class browser needs to be able to enumerate the members of classes. Visual development environments can benefit from making use of type information available in reflection to aid the developer in writing correct code.
* 调试与测试工具
  * Debuggers need to be able to examine private members on classes. Test harnesses can make of reflection to systematically call a discoverable set APIs defined on a class, to insure a high level of code coverage in a test suite.

### Java 反射的缺陷

* 性能开销
  *  Because reflection involves types that are dynamically resolved, certain Java virtual machine optimizations can not performed. Consequently, reflective operations have slower performance than their non-reflective counterparts, and should be avoided in sections of code which are called frequently in performance-sensitive applications.
  * `java.lang.Class` 是所有对象的定义类，同时是所有反射的入口
    * 加载类需要时间和空间开销
    * 反射解析也需要时间和空间开销
    * 反射的执行需要校验
* 安全限制
  * Reflection requires a runtime permission which may not be present when running under a security manager.
  * Java 安全框架 - Java security
    * `java.security.Permission`
      * `java.security.BasicPermission`
        * `java.lang.reflect.ReflectPermission` 
    * `java.lang.SecurityManager#checkPermission(java.security.Permission)`

* 暴露内部结构
  * Since reflection allows code to perform operations that would be illegal in non-reflective code.
  * 反射会打破 OOP 对象/类的封装

### Java 反射核心组件

* 类对象（Class Objects）
* 类成员（Class Members）
* 泛型（Generics）
* 动态代理（Dynamic Proxy）
* 数组（Arrays）

### Java 反射 - 类对象（Class Objects）

* 类（Classes）
* 接口（Interfaces）
* 枚举（Enums）
* 注解（Annotations）
* 原生类型（Primitives）
* 集合（Collections）

### Java 反射 - 类成员（Class Members）

* 字段（Fields）
  * 字段（Field）本身是元数据，包含的内容是数据（属性），又称之为状态信息
  * Java 字段类型
    * 类字段
    * 对象字段
* 方法（Methods）
  * 方法是描述对象“行为”的契约（抽象）或者实现（具体）
  * 方法唯一约束是方法签名：
    - 归属（定义所在）
    - 访问修饰符（public）
    - 返回类型（return type）
    - 方法名称（method name）
    - 方法参数（method parameters）
    - 方法异常列表（method throws）
* 构造器（Constructs）
  * 签名：
    - 归属（定义所在）
    - 访问修饰符（public）
    - 构造器名称（Constructor name = Class Name）
    - 构造器参数（method parameters）
    - 构造器异常列表（method throws）
  * 方法与构造器又归类为可执行对象

### Java 反射 - 类成员修饰符/修饰语（Modifiers）

* 访问性（Accessibility）：public、protected、private
* 属性：static、final、volatile、transient
* 方法：static、final、synchronized、native、abstract、strictfp

### Java 反射 - 泛型（Generics）

* 泛型信息（Generics Info）：泛型参数（Parameters）、泛型父类（Super Classes）、泛型接口（Interfaces）
* 泛型签名（Generics Signature）
* 泛型擦写（Generics Erase）

### Java 反射 - 枚举（Enumerations）

* 字段常量（Constants）
* 内建行为（Built-in Behaviors）
* 合成的父类 - `java.lang.Enum` 

### Java 反射 - 数组（Arrays）

* 数组大小（Length）
* 成员类型（Component Type）
* 特殊类型

## Java 反射 API 运用

### Java 反射 API - 类对象（Class Objects）

* 分类
  * 类（Classes）：`java.lang.Object.class` 
  * 接口（Interfaces）：`java.lang.CharSequence.class`
  * 枚举（Enums）：`java.util.concurrent.TimeUnit.class` 
  * 注解（Annotations）：`java.lang.Override.class` 
  * 原生类型（Primitives）：`int.class` 
* Java 中所有类型不一定是对象（Object），然而每种类型都类对象（java.lang.Class）
* Java 类型
  - 对象类型（java.lang.Object 子类）
  - 数组类型（[]）
  - 原生类型（boolean、char、int 等）
  - 特殊类型（void 仅用在方法返回类型中）
* Java 类型对象（java.lang.Class）
  - java.lang.Object.class
  - x[].class
  - x.class
  - void.class
  - java.lang.reflect.Type
    - java.lang.reflect.ParameterizedType - 带有泛型参数的类型
    - java.lang.Class - 普通类型

### Java 反射 API - 类成员（Class Members）

* 成员（Members）：java.lang.reflect.Member
  * 字段（Fields）：java.lang.reflect.Field
  * 可执行（Executable）：java.lang.reflect.Executable
    * 方法（Methods）：java.lang.reflect.Method
    * 构造器（Constructors）：java.lang.reflect.Constructor

### Java 反射 API - 类成员修饰符/修饰语（Modifiers）

* `java.lang.reflect.Modifier` 

  ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-7-java-meta-programming/part-1-java-reflection/1.0-java-reflection-modifiers.png)

  ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-7-java-meta-programming/part-1-java-reflection/1.1-java-reflection-modifiers.png)



### Java 反射 API - 泛型（Generics）

* 泛型信息（Generics Info）：java.lang.Class#getGenericInfo()
* 泛型参数（Parameters）：java.lang.reflect.ParameterizedType
* 泛型父类（Super Classes）：java.lang.Class#getGenericSuperclass()
* 泛型接口（Interfaces）：java.lang.Class#getGenericInterfaces()
* 泛型申明（Generics Declaration）：java.lang.reflect.GenericDeclaration

### Java 反射 API - 枚举（Enumerations）

* 修饰符/修饰语（Modifiers）
  * 类对象层面 - java.lang.Class#isEnum()
  * 字段层面
    * java.lang.reflect.Field#isEnumConstant()
* 继承父类 - java.lang.Enum

### Java 反射 API - 注解（Annotations）

* 类型判断 - java.lang.Class#isAnnotation()
* 继承接口 - java.lang.annotation.Annotation
* 反射接口 - java.lang.reflect.AnnotatedElement
* 标注范围 - java.lang.annotation.ElementType
* 执行实现 - 动态代理

### Java 反射 API - 数组（Arrays）

* 类型判断 - java.lang.Class#isArray()
* 成员类型 - java.lang.Class#getComponentType()
* 继承父类 - java.lang.Object
* 继承接口 - java.lang.Cloneable
* 执行实现 - JVM 合成

## Java 8 反射提升

* Java 方法参数
  * 保留接口方法编译参数：-parameters
  * 反射 API：java.lang.reflect.Parameter
* 可重复注解
  * Repeatable















