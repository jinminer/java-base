
# 集合

## 对象型集合
> 集合实际容纳的是类型为Object 的一些对象的句柄。
 (1) 将一个对象句柄置入集合时，由于类型信息会被抛弃，所以任何类型的对象都可进入我们的集合——即
 便特别指示它只能容纳特定类型的对象。
 (2) 由于类型信息不复存在，所以集合能肯定的唯一事情就是自己容纳的是指向一个对象的句柄。正式使用
 它之前，必须对其进行造型，使其具有正确的类型。

## 集合对象存储
> 所有集合能容纳的仅有对象句柄，所以不可以使用基本类型



## 集合组件结构

![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/thinking-in-java/collection/1-collection-structural.png)

## 迭代器

`Iterator` 对 `Enumeration` 的提升

> (1) `Iterator`采用了一个历史上默认、而且早在`OOP` 中得到广泛采纳的名字（迭代器）。
> (2)  `Iterator`采用了比`Enumeration` 更短的名字：`hasNext()`代替了 `hasMoreElement()`，而 `next()`代替了`nextElement()`。
> (3) 添加了一个名为`remove()`的新方法，可删除由`Iterator` 生成的上一个元素。所以每次调用 `next()`的时候，只需调用`remove()`一次。