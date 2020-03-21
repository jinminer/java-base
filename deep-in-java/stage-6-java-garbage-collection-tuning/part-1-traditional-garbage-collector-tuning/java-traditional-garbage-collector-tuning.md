# Java 传统垃圾回收器调优

## 内容提要

* Parallel 并行垃圾收集器调优
  * Java 垃圾收集器概述
  * 选择垃圾收集器
  * 并行垃圾收集器（Parallel Collector）调优
* CMS 并发标记-清除垃圾收集器调优
  * CMS Collector 性能与结构
  * 并发模式失效
  * 极端 GC 时间与 OOM
  * CMS Collector 浮动垃圾
  * CMS Collector 停顿
  * CMs Collector 并发阶段
  * 并发收集周期起始
  * CMS Collector 调度停顿



## Parallel 并行垃圾收集器调优

### Java 垃圾收集器概述

* Serial Collector
  * 能够处理新生代和老生代
* Parallel Collector
  * 能够处理新生代和老生代
* Parallel Compacting Collector
  * 仅处理新生代
* Concurrent Mark-Sweep(CMS) Collector
  * 仅处理老生代
* G1 Collector
  * 比较特殊不区分新生代和老生代



### 选择垃圾收集器

#### 指导原则

* 如果应用属于小规模数据应用（内存资源大概在 100 MB 左右）的话，那么串行收集器时一种不错的选择（`-XX:+UseSerialGc`）
* 如果应用运行在单处理器并堆停顿时间不敏感的话，那么它可用考虑串行收集器（`-XX:+UseSerialGC`）
* 如果应用属于性能敏感但停顿时间要求不高（如停顿一秒以上）的话，那么它可用选择并行收集器（`-XX:+UseParallelGC`）
* 如果应用认为响应时间比吞吐量和停顿时间更为重要的话，那么它可用考虑 CMS 或 G1 收集器



### 并行垃圾收集器（Parallel Collector）调优

#### Parallel Collector 线程数量调优

* 并行收集器线程函数（NP > 8 ? 5/8 : NP）
  * 当机器处理器数量 > 8 时，并行收集器线程数量大约为机器处理器数量的 5/8
  * 当机器处理器数量 <= 8 时，并行收集线程数量等于机器处理器数量
* 设置并行收集线程数量
  * JVM 参数：`-XX:ParallelGCThreads=<N>`

* 注意事项
  * 当应用在单处理器中运行时，Parallel Collector 不会比 Serial Collector 表现更好，因为并行执行反而需要额外的开销（如同步和线程切换等）
  * 当应用在双处理器中运行时，即使 Heap 达到中型或大型的空间，Serial Collector 可能时最合适的选择

#### Parallel Collector 覆盖分代

![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-6-java-garbage-collection-tuning/part-1-traditional-garbage-collector-tuning/1.0-parallel-generation.png)

#### Parallel Collector 目标调整

##### 最大垃圾收集停顿时间（Pause Times）

* 默认不限制最大时间
* 通过 JVM 参数 `-XX:MaxGCPauseMillis=<N>` 设置最大 GC 停顿时间（单位：毫秒）
  * JVM 尽可能地控制停顿时间在指定地参数值之内，然而实际情况，有可能存在出入
  * 调整后，应用地总体吞吐量可能会较少

##### 吞吐量（Throughput）

* 决定于垃圾收集与其他应用执行时间地占比
* 通过 JVM 参数 `-XX:GCTimeRatio=<N>` 调整 GC 时间与非 GC 时间地比重
  * GC 停顿时间等于 1/N+1（如 N = 9时，GC 时间占应用总体执行时间的 1/10）
  * N 默认值为 99，意味着 GC 时间栈 1/100 的应用执行时间

##### 内存足迹（Footprint）

* 最大 Heap 空间：`-Xmx<N>` 
* 初始化 Heap 空间：`-Xms<N>` 
* 新生代空间：`-Xmn<N>` 

#### Parallel Collector 目标优先次序

* 停顿时间目标（Pause-Time Goal）
* 吞吐量目标（Throughput Goal）
* 内存足迹（Footprint Goal）

#### Parallel Collector 分代内存空间调整

* 每次垃圾收集过后，收集器将会保存并更新相关的统计信息，如平均的停顿时间。
* 同时，分代空间的扩容或缩容也将被固定增量比率完成，扩容和缩容的比率是存在差别的。
* 默认情况，扩容的比率为 20%，而缩容的比率则是 5%。
* 增量比率能够分别在新生代和老生代通过 `-XX:YoungGenerationSizeIncrement=<Y>` 以及 `-XX:TenuredGenerationSizeIncrement=<T>` 调整。
* 而缩容比率则通过 `-XX:=AdaptiveSizeDecrementScaleFactor=<D>` 。
* 假设扩容比率为 10%，当 `-XX:AdaptiveSizeDecrementScaleFactor=2` 时，那么缩容比率为 10/2 % = 5% 

#### Parallel Collector Heap 默认空间大小

* 默认值：当 JVM 进程为设置初始化以及最大 Heap 空间大小时，两者的空间将根据当前主机的内存情况做出计算。最大和初始化 Heap 空间大小默认分别非物理内存的 1/4 和 1/16，新生代空间最大值为 Heap 空间总大小的 1/3.
* 设置值：初始值参数 `-Xms`，最大值参数 `Xmx` 
* 校验：`-XX:+PrintFlagsFinal` 中的 `-XX:MaxHeapSize` 选项

#### Parallel Collector 案例分析

* 案例一：`java.lang.OutOfMemoryError: Java heap space`
* 案例一：`java.lang.OutOfMemoryError: GC overhead limit exceeded`
* 案例一：`java.lang.OutOfMemoryError: Unable to create new native thread`



## CMS 并发标记-清除垃圾收集器调优

### CMS Collector 性能与结构

#### 并发标记-清扫垃圾收集器的特征

* 分代收集器
* 并发执行
* 致力于减少停顿时间
* 不压缩分代空间
* STOP-THE-WORLD 停顿

#### 收集过程（图示）



### 并发模式失效

#### 问题描述

* CMS 收集器使用一个或多个 GC 线程并发地在老年代区域执行垃圾收集。
* 正常情况，CMS 多数跟踪和清扫工作在应用运行时同步进行，这种简要地停顿能被应用感知到。

* **如果 CMS 无法在老年代区域填满之前完成清扫不可达对象，或者新的分配无法获取足够的老年代空间时**，应用将被停顿以及应用线程也将被停止直到收集任务的结束。
* 这种无法完成收集的行为称之为“并发模式失效”，同时需要调整 CMS 参数。
* 如果应用显性地调用垃圾收集（如 `System.gc()`）或者被外部工具获取诊断信息时，并发模式将被阻碍并行报告相关信息。

#### GC 日志

* 2019-08-20T15:05:53.957-0800: 59.831: [CMS2019-08-20T15:05:53.975-0800: 59.849: [CMS-concurrent-mark: 0.036/0.037 secs] [Times: user=0.07 sys=0.01, real=0.04 secs]
* (**concurrent mode failure**): 3512732K->3512633K(3512768K), 0.3197625 secs] 4126030K->4073564(4126208K), [Metaspace: 4145K->4145K(1056768K)], 0.3198771 secs] [Times: user=0.33 sys=0.01, real=0.32 secs]

### 极端 GC 时间与 OOM

#### 基本特征

* 设计目的 - 保护应用正常运行
* 异常信息 - `java.lang.OutOfMemoryError: GC overhead limit exceeded` 
* 出发条件 - GC 时间超过整体运行时间 98% 并且 Heap 空间小于 2%
* 关闭保护 - JVM 启动参数 `-XX:UseGCOverheadLimit` 
* 相同策略 - 并行垃圾收集器（Parllel Collector）

### CMS Collector 浮动垃圾

#### 浮动垃圾（Floating Garbage）

* 由于在大规模垃圾收集过程中，应用线程与垃圾收集器线程并行地运行，被垃圾收集器跟踪线程的对象在收集处理结束后可能变得不可达，这些并未被回收的不可达的对象称之为”**浮动垃圾**“。
* 浮动垃圾的数量依赖于并发收集周期的持续时间以及引用对象更新的频率，又称之为”**突变**“。
* 在某个并发收集结束后，Heap 中的浮动垃圾将被下一个收集周期收集。

### CMS Collector 停顿

#### 发生时机

* 一次并行收集周期中，CMS 垃圾收集器将会停顿应用两次
* 首次停顿是在可达的 roots 直接标记存活对象。首次停顿称之为”**初始化标记停顿**“（initial mark pause）
* 第二次停顿出现在并发跟踪阶段的结果，并且寻找那些被并发跟踪错过的对象，由于这些对象的引用更新在 CMS 收集器之后已完成。第二次停顿称之为”**重标记停顿**“（remark pause）

### CMS Collector 并发阶段



### 并发收集周期起始

* 对于串行垃圾收集器而言，大规模收集器发生在老年代区域已满并使得应用线程停止直到收集结束。CMS 垃圾收集器则相反，它在老年代区域变满之前就开始并行收集。否则，由于在并行模式失效的作用下，应用的停顿时间将变得更长。
* 基于最近的历史收集情况，CMS 垃圾收集器将在老年代区域消尽前维护若干剩余时间的评估，并发收集周期这些时间评估。使用这些动态的时间评估后，在老年代区域耗尽前，一个并发收集周期从即将完成的并发收集周期的目的地开始。这些时间评估能够安全地被填充，因为并发模式失效可以非常昂贵。

* 当老年代区域空间使用超过初始化阈值（老年代区域空间百分比）时，一次并行收集同样会启动。这个初始化阈值地默认大约为 92%，不过这个值随着不同地 JVM 发布随之变化。当然，该值可通过命令行参数 `-XX:CMSInitiatingOccupancyFraction<N>` 手动调节，其中 N 时老年代空间地整数百分率（从 0 到 100）

### CMS Collector 调度停顿

#### 描述

* 由于新生代和老年代中地收集执行是独立地，两种收集器不会重叠，尽管一个收集停顿后可能紧跟另一次收集，允许出现单个较长地停顿。
* 为了避免这个问题，在上一次老年代收集和下一次新生代收集之间，CMS 垃圾收集器将尝试调度 remark 停顿。
* 这种调度机制不会被 initial mark 停顿完成，它的停顿时间通常远短于 remark 停顿

























