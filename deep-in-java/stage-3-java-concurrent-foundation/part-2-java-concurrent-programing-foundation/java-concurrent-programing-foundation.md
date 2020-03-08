# Java 并发编程基础

## 内容提要

* Java 并发理论基础

* Java 同步原语
* Java 线程 Liveness
* Java 并发经典模型
* 热点问题解析



## Java 并发理论基础

### 线程安全

**Thread safety** is a [computer programming](https://en.wikipedia.org/wiki/Computer_programming) concept applicable to [multi-threaded](https://en.wikipedia.org/wiki/Thread_(computing)) code. Thread-safe code only manipulates shared data structures in a manner that ensures that all threads behave properly and fulfill their design specifications without unintended interaction. There are various strategies for making thread-safe data structures.[[1\]](https://en.wikipedia.org/wiki/Thread_safety#cite_note-1)[[2\]](https://en.wikipedia.org/wiki/Thread_safety#cite_note-2)

A program may execute code in several threads simultaneously in a shared [address space](https://en.wikipedia.org/wiki/Address_space) where each of those threads has access to virtually all of the [memory](https://en.wikipedia.org/wiki/Computer_storage) of every other thread. Thread safety is a property that allows code to run in multithreaded environments by re-establishing some of the correspondences between the actual flow of control and the text of the program, by means of [synchronization](https://en.wikipedia.org/wiki/Synchronization_(computer_science)).

### 线程安全层次

- **Thread safe**: Implementation is guaranteed to be free of [race conditions](https://en.wikipedia.org/wiki/Race_condition#Computing) when accessed by multiple threads simultaneously.
- **Conditionally safe**: Different threads can access different objects simultaneously, and access to shared data is protected from race conditions.
- **Not thread safe**: Data structures should not be accessed simultaneously by different threads.

### 线程安全实现手段

* Re-entrancy（重进入）

Writing code in such a way that it can be partially executed by a thread, reexecuted by the same thread or simultaneously executed by another thread and still correctly complete the original execution. This requires the saving of [state](https://wikimili.com/en/State_(computer_science)) information in variables local to each execution, usually on a stack, instead of in [static](https://wikimili.com/en/Static_variable) or [global](https://wikimili.com/en/Global_variable) variables or other non-local state. All non-local state must be accessed through atomic operations and the data-structures must also be reentrant.

* Thread-local storage（线程本地存储）

Variables are localized so that each thread has its own private copy. These variables retain their values across [subroutine](https://wikimili.com/en/Subroutine) and other code boundaries, and are thread-safe since they are local to each thread, even though the code which accesses them might be executed simultaneously by another thread.

![computer-memory-model](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-3-java-concurrent-foundation/part-2-java-concurrent-programing-foundation/1.0-computer-memory-model.png)

* Immutable objects（不可变对象）

The state of an object cannot be changed after construction. This implies both that only read-only data is shared and that inherent thread safety is attained. Mutable (non-const) operations can then be implemented in such a way that they create new objects instead of modifying existing ones. 

* Mutual exclusion（互斥）

  Access to shared data is *serialized* using mechanisms that ensure only one thread reads or writes to the shared data at any time. Incorporation of mutual exclusion needs to be well thought out, since improper usage can lead to side-effects like [deadlocks](https://wikimili.com/en/Deadlock), livelocks, and resource starvation.

* Atomic operations（原子操作）

  Shared data is accessed by using atomic operations which cannot be interrupted by other threads. This usually requires using special machine language instructions, which might be available in a [runtime library](https://wikimili.com/en/Runtime_library). Since the operations are atomic, the shared data is always kept in a valid state, no matter how other threads access it. Atomic operations form the basis of many thread locking mechanisms, and are used to implement mutual exclusion primitives.

### 同步（synchronization）

**Synchronization** refers to one of two distinct but related concepts: synchronization of processes, and synchronization of data. *Process synchronization* refers to the idea that multiple processes are to join up or [handshake](https://wikimili.com/en/Handshaking) at a certain point, in order to reach an agreement or commit to a certain sequence of action. *[Data synchronization](https://wikimili.com/en/Data_synchronization)* refers to the idea of keeping multiple copies of a dataset in coherence with one another, or to maintain [data integrity](https://wikimili.com/en/Data_integrity). Process synchronization primitives are commonly used to implement data synchronization.

#### 同步引入的问题

* 死锁（Dead Lock）
* 饥饿（Starvation）
* 优先级倒转（Priority Inversion）
* 繁忙等待（Busy Waiting）

#### 同步实现

* 信号量（Semaphores）：LInux、Solaris
* 屏障（Barriers）：Linux、Pthreads
* 互斥（Mutex）：Linux、Pthreads
* 条件变量（Condition Variables）：Solaris、Pthreads

* 自旋锁（Spinlock）：Windows、Linux、Pthreads
* 读-写锁（Reader-Writer Lock）：Linux、Solaris、Pthreads

### 临界区（Critical Section）

Concurrent accesses to shared resources can lead to unexpected or erroneous behavior, so parts of the program where the shared resource is accessed need to be protected in ways that avoid the concurrent access. This protected section is the **critical section** or **critical region.** 

#### 锁（Lock）

A **lock** or **mutex** (from **mutual exclusion**) is a synchronization mechanism for **enforcing** limits on access to a resource in an environment where there are many threads of **execution**. **A lock is designed to enforce a mutual exclusion concurrency control policy**.



































