# Java 传统垃圾回收器调优

## 内容提要

* Java 垃圾收集器概述
* 选择垃圾收集器
* 并行垃圾收集器（Parallel Collector）调优
* 总结



## Java 垃圾收集器概述

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



## 选择垃圾收集器

### 指导原则

* 如果应用属于小规模数据应用（内存资源大概在 100 MB 左右）的话，那么串行收集器时一种不错的选择（`-XX:+UseSerialGc`）
* 如果应用运行在单处理器并堆停顿时间不敏感的话，那么它可用考虑串行收集器（`-XX:+UseSerialGC`）
* 如果应用属于性能敏感但停顿时间要求不高（如停顿一秒以上）的话，那么它可用选择并行收集器（`-XX:+UseParallelGC`）
* 如果应用认为响应时间比吞吐量和停顿时间更为重要的话，那么它可用考虑 CMS 或 G1 收集器



## 并行垃圾收集器（Parallel Collector）调优

### Parallel Collector 线程数量调优

* 并行收集器线程函数（NP > 8 ? 5/8 : NP）
  * 当机器处理器数量 > 8 时，并行收集器线程数量大约为机器处理器数量的 5/8
  * 当机器处理器数量 <= 8 时，并行收集线程数量等于机器处理器数量
* 设置并行收集线程数量
  * JVM 参数：`-XX:ParallelGCThreads=<N>`

* 注意事项
  * 当应用在单处理器中运行时，Parallel Collector 不会比 Serial Collector 表现更好，因为并行执行反而需要额外的开销（如同步和线程切换等）
  * 当应用在双处理器中运行时，即使 Heap 达到中型或大型的空间，Serial Collector 可能时最合适的选择

### Parallel Collector 覆盖分代

![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-6-java-garbage-collection-tuning/part-1-traditional-garbage-collector-tuning/1.0-parallel-generation.png)

### Parallel Collector 目标调整

#### 最大垃圾收集停顿时间（Pause Times）

* 默认不限制最大时间
* 通过 JVM 参数 `-XX:MaxGCPauseMillis=<N>` 设置最大 GC 停顿时间（单位：毫秒）
  * JVM 尽可能地控制停顿时间在指定地参数值之内，然而实际情况，有可能存在出入
  * 调整后，应用地总体吞吐量可能会较少

#### 吞吐量（Throughput）

* 决定于垃圾收集与其他应用执行时间地占比
* 通过 JVM 参数 `-XX:GCTimeRatio=<N>` 调整 GC 时间与非 GC 时间地比重
  * GC 停顿时间等于 1/N+1（如 N = 9时，GC 时间占应用总体执行时间的 1/10）
  * N 默认值为 99，意味着 GC 时间栈 1/100 的应用执行时间

#### 内存足迹（Footprint）

* 最大 Heap 空间：`-Xmx<N>` 
* 初始化 Heap 空间：`-Xms<N>` 
* 新生代空间：`-Xmn<N>` 

### Parallel Collector 目标优先次序

* 停顿时间目标（Pause-Time Goal）
* 吞吐量目标（Throughput Goal）
* 内存足迹（Footprint Goal）

### Parallel Collector 分代内存空间调整

* 每次垃圾收集过后，收集器将会保存并更新相关的统计信息，如平均的停顿时间。
* 同时，分代空间的扩容或缩容也将被固定增量比率完成，扩容和缩容的比率是存在差别的。
* 默认情况，扩容的比率为 20%，而缩容的比率则是 5%。
* 增量比率能够分别在新生代和老生代通过 `-XX:YoungGenerationSizeIncrement=<Y>` 以及 `-XX:TenuredGenerationSizeIncrement=<T>` 调整。
* 而缩容比率则通过 `-XX:=AdaptiveSizeDecrementScaleFactor=<D>` 。
* 假设扩容比率为 10%，当 `-XX:AdaptiveSizeDecrementScaleFactor=2` 时，那么缩容比率为 10/2 % = 5% 

### Parallel Collector Heap 默认空间大小

* 默认值：当 JVM 进程为设置初始化以及最大 Heap 空间大小时，两者的空间将根据当前主机的内存情况做出计算。最大和初始化 Heap 空间大小默认分别非物理内存的 1/4 和 1/16，新生代空间最大值为 Heap 空间总大小的 1/3.
* 设置值：初始值参数 `-Xms`，最大值参数 `Xmx` 
* 校验：`-XX:+PrintFlagsFinal` 中的 `-XX:MaxHeapSize` 选项

### Parallel Collector 案例分析

* 案例一：`java.lang.OutOfMemoryError: Java heap space`
* 案例一：`java.lang.OutOfMemoryError: GC overhead limit exceeded`
* 案例一：`java.lang.OutOfMemoryError: Unable to create new native thread`

































