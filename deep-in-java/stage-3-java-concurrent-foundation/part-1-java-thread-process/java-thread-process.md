# Java 线程与进程

## 内容提要

* 进程、线程、协程
* Java 线程状态
* Java 线程生命周期
* Java 线程通讯
* Java 进程管理
* 热点题解析

## 进程、线程、携程

### 进程（Process）

​		指计算机中已运行的程序。进程为曾经是分时系统的基本运作单位。在面向进程设计的系统中，进程是程序的基本执行实体；在面向线程设计的系统中，进程本身不是基本运行单位，而是线程的容器。程序本身只是指令、数据及其组织形式的描述，进程才是程序的真正运行实例。

### 线程（Thread）

​		操作系统能够进行运算调度的最小单位。它被包含在进程之中，是进程中的实际运作单位。一条线程指的是进程中一个单一顺序的控制流，一个进程中可以并发多个线程，每条线程并行执行不同的任务。在 Unix System V 及 SunOS 中也被称为轻量进程，但轻量进程更多指内核线程，而把用户线程称为线程。

* Java 线程
* POSIX　线程

### Java 线程

* Green Thread（绿色线程）
  * Java 1.2 之前的 Java Thread 实现，模拟多线程并发
* Native OS Thread（原生 OS 线程）
  * Java 1.2 之后 Java Thread 实现，基于 OS 线程实现，数量映射为 1:1

### Java 线程编程模型

* < Java 5：Thread、Runnable
* Java 5 +：Executor、Future、Callable
* Java 7 +：ForkJoin
* Java 8 +：CompletionStage、CompletableFuture
* Java 9 +：Flow（Publisher、Subscriber、Subscription、Processor）

### Java 线程池

* < Java 5：自定义 Thread Pool
* Java 5 +：ExecutorService
  * ThreadPoolExecutor
  * ScheduledThreadPoolExecutor
* Java 7 +：ForkJoinPool

### Java 并发框架

* Java 5：Java Util Concurrent
* Java 7：Fork/Join
* Java 8 ：CompletionFuture、RxJava、Reactor
* Java 9 ：Flow API、Reactive Streams

### 编程手段

* 同步
  * 最常见的编程手段，是指任务发起方和执行方在同一线程中完成
* 异步
  * 常见的提升通吐手段，是指任务发起方和执行方在不同线程中完成
* 非阻塞
  * 一直编程模型，由通知状态被动的回调执行，同步或异步执行均可

### POSIX 线程

* POSIX 线程（英语：POSIX Threads，常缩写成为Pthreads）是POSIX 的线程标准，定义了创建和操纵线程的一套 API。

* 实现 POSIX 线程标准的库常被称作 Pthreads，一般用于 Unix-like POSIX 系统，如 Linux、Solaris。但是 Microsoft Windows 上的实现也存在，例如直接使用 Windows API实现的第三方库 pthreads-w32；而利用Windows的 SFU/SUA 子系统，则可以使用微软提供的一部分原生 POSIX API - https://sourceware.org/pthreads-win32/

### 协程（Coroutine）

​	**Coroutines** are [computer program](https://en.wikipedia.org/wiki/Computer_program) components that generalize [subroutines](https://en.wikipedia.org/wiki/Subroutine) for [non-preemptive multitasking](https://en.wikipedia.org/wiki/Non-preemptive_multitasking), by allowing execution to be suspended and resumed. Coroutines are well-suited for implementing familiar program components such as [cooperative tasks](https://en.wikipedia.org/wiki/Cooperative_multitasking), [exceptions](https://en.wikipedia.org/wiki/Exception_handling), [event loops](https://en.wikipedia.org/wiki/Event_loop), [iterators](https://en.wikipedia.org/wiki/Iterator), [infinite lists](https://en.wikipedia.org/wiki/Lazy_evaluation) and [pipes](https://en.wikipedia.org/wiki/Pipeline_(software)).



## Java 线程状态

* API - `java.lang.Thread.State`（since 1.5）
  * NEW：线程已创建，尚未启动
  * RUNNABLE：表示线程处于可运行状态，不代表一定运行
  * BLOCKED：被 Monitor 锁阻塞，表示当前线程在同步锁的场景运作
  * WAITTING：线程处于等待状态，由 Object#wait()、Thread#join() 或 LockSupport#park() 引起
  * TIMED_WAITTING：线程处于规定时间内的等待状态
  * TERMINATED：线程执行结束

### 使用场景

#### 线程堆栈

* 工具  - jstack
* JMX - java.lang.management.ThreadMXBean#dumpAllThreads(boolean,boolean)
* API - java.lang.Thread#dumpStack()



## Java 线程生命周期

### 生命周期方法

* 启动 - java.lang.Thread#start()
* ~~停止 - java.lang.Thread#stop()~~
* ~~暂停 - java.lang.Thread#suspend()~~
* ~~恢复 - java.lang.Thread#resume()~~
* “中止” - java.lang.Thread#interrupt()、java.lang.Thread#isInterrupted()

**为什么弃用 Thread.stop；Thread.suspend 和 Thread.resume 方法？**

* 参考：https://docs.oracle.com/javase/8/docs/technotes/guides/concurrency/threadPrimitiveDeprecation.html

  * ### Why is `Thread.stop` deprecated?

  * ### Why are `Thread.suspend` and `Thread.resume` deprecated?

**如何中止一个线程？**

**怎样理解线程“中止”方法**

* Thread#interrupt()
* Thread#isInterrupted()



## Java 线程通讯



## Java 进程管理

### 管理当前 JVM 进程

* 获取当前 JVM 进程 ID
* 获取当前 JVM 进程启动时间
* 获取当前 JVM 进程线程数量
* 获取当前 JVM 内存使用情况
* 退出当前 JVM 线程

### 管理子进程

* 启动子进程
* 进程 API
* 主子进程 I/O 交互
* 阻塞进程
* 推出进程

### Java 9 进程 API 提升

* 基础模块：java.base
* 核心 API：java.lang.ProcessHandle
* 进程管理
  * 当前进程
  * 子进程



























