# 枚举和注解

## 使用枚举类型替代整型常量

* 特定于常量（constant-specific）的方法实现
  * 在枚举类型中声明一个抽象的方法，并用常量特定的类主体中的每个常量的具体方法重写它。
  * 可以将不同的行为与每个枚举常量关联起来



## 使用实例属性替代序数



## 使用 `EnumMap` 替代序数索引



## 使用接口模拟可扩展的枚举



## 注解优于命名模式

### 命名模式

* 缺点
  * 拼写错误导致失败，但不会提示。
  * 无法确保它们仅用于适当的程序元素。
  * 没有提供将参数值与程序元素相关联的好的方法。



## 始终使用 Override 注解



## 使用标记接口定义类型

### 定义

* 标记接口（marker interface），不包含方法声明，只是指定（或“标记”）一个类实现了具有某些属性的接口。



































