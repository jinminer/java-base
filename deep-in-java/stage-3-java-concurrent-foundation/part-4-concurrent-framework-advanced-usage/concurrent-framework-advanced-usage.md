# Java 并发框架高级运用

## 内容提要

* Java 并发集合框架
* Java 7 Fork/Join
* Java 8 CompletableFuture
* Java 9 Flow 框架



## Java 并发集合框架

### CopyOnWrite* 实现

* 并发特征：
  * 读：无锁（volatile）、快速（O(1)）
  * 写：同步（synchronized）、复制（较慢、内存损耗）
* 代表实现
  * CopyOnWriteArrayList
  * CopyOnWriteArraySet（底层实现为 CopyOnWriteArrayList）

### 不同 List 实现比较

![list-implements](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-3-java-concurrent-foundation/part-4-concurrent-framework-advanced-usage/1.0-list-implements.png)

### ConcurrentSkipList* 实现

* 并发特征：无锁
* 数据结构：有序（ConcurrentNavigableMap 实现）、跳跃列表（Skip List）变种
* 时间复杂度：平均 log(n) - containsKey、get、remove 方法
* 代表实现：ConcurrentSkipListMap、ConcurrentSkipListSet（底层实现为 ConcurrentSkipListMap）
* 特征：空间换时间

### 跳跃列表（Skip List）

* 时间复杂度：搜索 O(log(n))、插入O(log(n))

### ConcurrentSkipListMap

* 注意事项
  * size() 方法非 O(C) 操作
  * 批量操作无法保证原子执行，如 putAll、equals、同Array、containsValue 已经 clear 方法
  * Iterator 和 Spliterators：弱一致性、并非 fail-fast
  * 非 null 约束：keys 和 values 均不允许为 null

### ConcurrentHashMap 实现

* 并发特征
  * 1.5 ~ 1.6：读锁（部分），写锁
  * 1.7：读无锁，写锁
  * 1.8：读无锁，写锁

* 数据结构
  * < 1.8：桶（bucket）
  * 1.8：桶（bucket）、平衡树（红黑树）

### BlockingQueue 实现

* 并发特征：读锁、写锁

  ![blocking-queue](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-3-java-concurrent-foundation/part-4-concurrent-framework-advanced-usage/2.0-blocking-queue.png)



* 典型实现：ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue
* 子接口/实现:
  * BlockingDeque - LinkBlockingDeque
  * TransferQueue - LinkedTransferQueue（无锁）



## Java 7 Fork/Join 框架

### Fork/Join 框架

The fork/join framework is an implementation of the `ExecutorService` interface that helps you take advantage of multiple processors. It is designed for work that can be broken into smaller pieces recursively. The goal is to use all the available processing power to enhance the performance of your application.

As with any `ExecutorService` implementation, the fork/join framework distributes tasks to worker threads in a thread pool. The fork/join framework is distinct because it uses a *work-stealing* algorithm. Worker threads that run out of things to do can steal tasks from other threads that are still busy.

### 编程模型

* ExecutorService 扩展 - ForkJoinPool
* Future 扩展 - ForkJoinTask、RecursiveTask、RecursiveAction



## Java 8 CompletableFuture

### CompletableFuture 背景

* Future 的限制
  * 阻塞式结果返回
  * 无法链式多个Future
  * 无法合并多个Future结果
  * 缺少异常处理



## Java 9 Flow 框架

### Reactive Streams

* 核心接口
  * Publisher
  * Subscriber
  * Subscription
  * Processor



























