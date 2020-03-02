# Java 集合框架高级运用

## 内容提要

* java 集合便利实现
* Java 集合包装实现
* Java 集合特殊实现
* Java 集合框架面试题
* 总结



## Java 集合便利实现

### 基本接口

> High-performance "mini-implementations" of the collection interface.
>
> 高性能的最小化的实现接口

#### 主要入口

* Java 9 前：java.util.Collections、java.util.Arrays、java.util.BitSet、java.util.EnumSet
* Java 9 +：java.util.List、java.util.Set、java.util.Map

### 接口类型

* 单例集合接口（Collections.singleton*）
* 空集合接口（Collections.empty*）
* 转换集合接口（Collection.*、Arrays.*）
* 列举集合接口（*.of(...)）

### 单例集合接口（Collections.singleton*）

* List：Collections.singletonList(T)
* Set：Collections.singleton(T)
* Map：Collections.singletonMap(K,V)

设计原则：不变集合（Immutable Collection）

### 空集合接口（Collection.empty*）

* 枚举：Collections.emptyEnumeration()
* 迭代器：emptyIterator()、emptyListIterator()
* List：emptyList()

* Set：emptySet()、emptySortedSet()、emptyNabigableSet()
* Map：emptyMap()、emptySortedMap()、emptyNavigableMap()

### 转换集合接口（`Collections.*、Arrays.*`）

* Enumeration: Collections.enumeration(Collections)
* List: Collections.list(Enumeration<T>)、Arrays.asList(T...)
* Set: Collections.newSetFromMap(Map<E, Boolean>)
* Queue: Collections.asLifoQueue(Deque<T>)
* HasCode: Arrays.hashCode(...)
* String: Arrays.toString(...)

### 列举集合接口（*.of(...)）

* java.util.BitSet.valueOf(...)
* java.util.EnumSet.of(...) (Sinece 1.5)
* java.util.Stream.of(...) (Sinece 1.8)
* java.util.List.of(...) (Sinece 9)
* java.util.Set.of(...) (Sinece 9)
* java.util.Map.of(...) (Sinece 9)



## Java 集合包装实现

### 基本介绍

Add functionality, such as synchronization, to other implementations.

功能性添加，比如同步以及其他实现

* 设计原则：Wrapper 模式原则，入参集合类型与返回类型相同或者其子类
* 主要入口
  * java.util.Collections

### 包装接口类型

* 同步包装接口（java.util.Collections.synchronized*）
* 只读包装接口（java.util.Collections.unmodifiable*）
* 类型安全包装接口（java.util.Collections.checked*）



## Java 集合特殊实现

### 基本介绍

Implementations designed for use in special situations. These implementations display nonstandard performance characteristic, usage restrictions, or behavior.

为特殊场景设计实现，这些实现表现出非标注性能特征、使用限制或者行为。

### 示例说明

#### 弱引用 Map

* java.util.WeakHashMap
* java.lang.ThreadLocal.ThreadLocalMap

#### 对象鉴定 Map

* java.util.IdentityHashMap

#### 优先级 Queue

* java.util.PriorityQueue

#### 枚举 Set

* java.util.EnumSet



















































































