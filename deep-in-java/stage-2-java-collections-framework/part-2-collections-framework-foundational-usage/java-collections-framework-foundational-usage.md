# Java 集合框架基础运用

## 内容提要

* Java 集合框架总览
* Java 集合框架内建实现
* Java 集合框架抽象实现
* Java 集合框架面试题
* 总结



## Java 集合框架总览

### 基本介绍

> ​	A coollections framework is a unified architecture for representing and manipulating collections, enabling collections to be manipulated independently of implementation details.
>
> ​	集合框架是一个用于展现和操作集合的统一架构，其实现细节能够让集合独立地操作。

* 官方文档：https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html

### 主要优势

* **Reduces programming effort**（减少编程地负担）
* **Increases performance**（提升性能）
* **Provides interoperability between unrelated APIs**（提供无关 API 之间地互用性）
* **Reduces the effort required to learn APIs**（减少学习 API 的负担）
* **Reduces the effort required to design and implement APIs**（减少设计与实现 API 的负担）
* **Fosters software reuse**（促进软件重用）

### 基本组成

* Collection interfaces（集合接口）
* Infrastructure（基础设施）
* General-purpose implementations（通用实现）
* Abstract implementations（抽象实现）
* Legacy implementations（遗留实现）

* Convenience implementations（便利实现）
* Wrapper implementations（包装实现）
* Special-purpose implementations（特殊实现）
* Array Utilities（数组工具类）

### 集合接口

#### 接口分类

* 基于 java.util.Collection 接口
* 基于 java.util..Map 接口或其他接口

#### java.util.Collection 接口

* 通用接口
  * java.util.List
  * java.util.Set
  * java.util.SortedSet
  * java.util.NavigableSet（since Java 1.6）
  * java.util.Queue（since Java 1.5）
  * java.util.Deque（since Java 1.6）

* 并发接口
  * java.util.concurrent.BlockingQueue（since Java 1.5）
  * java.util.concurrent.BlockingDeque（since Java 1.6）
  * java.util.concurrent.TransferQueue（since Java 1.7）

#### java.util.Map 接口

* 通用接口
  * java.util.SortedMap
  * java.util.NavigableMap （since Java 1.6）
* 并发接口
  * java.util.concurrent.ConcurrentMap（since Java 1.6）
  * java.util.concurrent.ConcurrentNavigableMap（since Java 1.6）

### 资源推荐

* 书籍
  * 《Java Generics and Collections》[Maurice Naftalin](https://book.douban.com/search/Maurice Naftalin) / [Philip Wadler](https://book.douban.com/search/Philip Wadler)，O'Reilly Media，2006-10-24
* Cheat Sheet
  * https://www.jrebel.com/blog/java-collections-cheat-sheet



## Java集合框架内建实现

### 集合实现

#### 遗留实现

* java.util.Vector
* java.util.Stack
* java.util.Hashtable
* java.util.Enumeration
* java.util.BitSet

#### 通用实现

![collection-general-implementation](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-2-java-collections-framework/part-2-java-collection-foundational-usage/1.0-collection-general-implementation.png)

#### 并发接口

* java.util.concurrent.BlockingQueue
* java.util.concurrent.TransferQueue
* java.util.concurrent.BlockingDeque
* java.util.concurrent.ConcurrentMap
* java.util.concurrent.ConcurrentNavigableMap

#### 并发实现

* java.util.concurrent.LinkedBlockingQueue
* java.util.concurrent.ArrayBlockingQueue
* java.util.concurrent.PriorityBlockingQueue
* java.util.concurrent.DelayQueue
* java.util.concurrent.SynchronousQueue
* java.util.concurrent.LinkedBlockingDeque
* java.util.concurrent.LinkedTransferQueue
* java.util.concurrent.CopyOnWriteArrayList
* java.util.concurrent.CopyOnWriteArraySet
* java.util.concurrent.ConcurrentSkipListSet
* java.util.concurrent.ConcurrentHashMap
* java.util.concurrent.ConcurrentSkipListMap



## Java 集合框架抽象实现

### 抽象实现

#### 基于 java.util.Collection 接口

* java.util.AbstractCollection
  * java.util.AbstractList
  * java.util.AbstractSet
  * java.util.AbstarctQueue (since Java 1.5)

#### 基于 java.util.Map 接口

* java.util.AbstractMap





## Java 集合框架面试题





## 总结