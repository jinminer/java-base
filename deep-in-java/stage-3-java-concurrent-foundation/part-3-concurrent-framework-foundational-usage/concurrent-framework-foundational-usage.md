# Java 并发框架基础运用

## 内容提要

* Java 并发锁
* Java 原子操作
* Java 并发限制
* Java 线程池
* 热点问题解析



## Java 并发锁

* 重进入锁 - ReentrantLock
  * Synchronized 关键字的API实现
* 重进入读写锁 - ReentrantReadWriteLock
  * 在 重进入锁(ReentrantLock)的基础上增加了读锁和写锁
  * 读锁和写锁可以认为是数据库事务中的读和写
    * 读锁 - 共享锁
    * 写锁 - 独占锁
* 邮票锁 - StampedLock
  * 即乐观锁，基于版本的概念

### 重进入锁 - ReentrantLock

A reentrant mutual exclusion Lock with the same basic behavior and semantics as the implicit monitor lock accessed using synchronized methods and statements, but with extended capabilities.

#### 最佳编程实践

```java
 class X {
   private final ReentrantLock lock = new ReentrantLock();
   // ...

   public void m() { 
     lock.lock();  // block until condition holds
     try {
       // ... method body
     } finally {
       lock.unlock()
     }
   }
 }
```

#### 与 synchronized 的类似点

* 互斥（Mutual Exclusion）
* 重进入（Reentrancy）
* 隐性 Monitor 机制

#### 与 synchronized 的不同点

* 获得顺序（公平和非公平）
* 限时锁定（tryLock）
* 条件对象支持（Condition Support）

* 运维方法

### 重进入读写锁 - ReentrantReadWriteLock

#### 继承 ReentrantLock 的特性

* 互斥（Mutual Exclusion）
* 重进入（Reentrancy）
* 获得顺序（公平和公平锁）

* 中断（Interruption）

* 条件对象支持（Condition Support）

#### 超越 ReentrantLock 的特性

* 共享 - 互斥（Shared - Exclusive）
  * 读锁 - 共享
  * 写锁 - 互斥
* 锁降级（Lock downgrading）

#### 其他语言实现

* POSIX - pthread_rwlock_t
* C++17 - std::shared_mutex

### 游标锁 - StampedLock

A capability-based lock with three modes for controlling read/write access. The state of a StampedLock consists of a version and mode. Lock acquisition methods return a stamp that represents and controls access with respect to a lock state; "try" versions of these methods may instead return the special value zero to represent failure to acquire access. Lock release and conversion methods require stamps as arguments, and fail if they do not match the state of the lock.

#### 三种锁

* 写（Writing）
* 读（Reading）
* 优化读（Optimistic Reading）

#### 类似实现

* Seqlock - https://en.wikipedia.org/wiki/Seqlock

#### 参考资料

* 《Effective synchronisation on Linux systems》
* 《Can Seqlocks Get Along With Programming Language Memory Models?》



## Java 原子操作

* java.util.concurrent.atomic.Atomic* 类
  * AtomicBoolean
  * AtomicInteger 与 AtomicIntegerArray
  * AtomicLong 与 AtomicLongArray
  * AtomicReference 与 AtomicReferenceArray
  * AtomicMarkableReference 与 AtomicStampedReference

* java.util.concurrent.atomic.*Adder 类
  * java.util.concurrent.atomic.Striped64
    * DoubleAccumulator
    * DoubleAdder
    * LongAccumulator
    * LongAdder



## Java 并发限制

* CountDownLatch
* CyclicBarrier
* Semaphore





















