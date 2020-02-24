# 方法

## 检查参数有效性



## 必要时进行防御性拷贝



## 仔细设计方法签名



## 明智审慎地使用重载

* 重载（overloaded）方法之间的选择是静态的，而重写（overridden）方法之间的选择是动态的
* 一个安全和保守的策略是永远不要导出两个具有相同参数数量的重载。



## 明智审慎地使用可变参数

* 定义
  * 可变参数机制首先创建一个数组，其大小是在调用位置传递的参数数量，然后将参数值放入数组中，最
    后将数组传递给方法。



## 返回空的数组或集合，不要返回 null

* 永远不要返回 null 来代替空数组或集合



## 明智审慎地返回 Optional



## 为所有已公开的 `API` 元素编写文档注释




























