# Java 传统垃圾回收器

## 内容提要

* 内存管理
* 垃圾回收概念
* 传统垃圾回收器概览
* 串行垃圾回收器
* 并行垃圾回收器
* 并行压缩垃圾回收器
* 并发标记和清除垃圾回收器
* 总结



## 内存管理

Memory management is the process of recognizing when allocated objects are no longer needed, deallocating(freeing) the memory used by such objects, and making it available for subsequent allocations

* 显示内存管理（Explicit）：手动内存管理（Manual），比如：C 语言 malloc 与 free 函数
* 自动内存管理（Automatic）：比如：垃圾收集（Garbage Collection）

### 自动内存管理（Automatic）

#### 算法一：引用计数（Reference Counting）

* C++ 共享指针（shared points）

  ![gc-algorithm-reference-counting](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/1.0-gc-algorithm-reference-counting.png)

* 独立循环（detached cycle）

  ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/1.1-gc-algorithm-reference-counting.png)

#### 算法二：标记与清扫（Mark and Sweep）

* 垃圾收集根对象（Garbage Collection Roots）
  * 局部变量（Local variables）
  * 活动线程（Active threads）
  * 静态域（Static fields）
  * `JNI`引用（`JNI` references）

* `JVM` 利用标记与清扫算法，跟踪所有可达的对象，并且确保从不可达对象中是否的内存空间能够再次被使用，该算法包含两个步骤：
  * **标记**（Marking）：从 `GC` 根集合开始遍历所有可达的对象，并且在本地内存中保持对象的分类
  * **清扫**（Sweeping）：确保那些被不再可达的对象占据的内存能够下次分配复用

### 标记与清扫（Mark and Sweep）

![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/1.3-reachable-objects.png)



## 垃圾回收概念

### 垃圾收集（Garbage Collection）

Garbage Collection(GC) is a form of automatic memory management. The garbage collector, or just collector, attempts to reclaim garbage, or memory occupied by objects that are no longer in use by the program. Garbage collection was invented by John McCarthy around 1959 to simplify manual memory management in Lisp.

### 垃圾收集器（Garbage Collector）的职责

* 分配内存（allocating memory）
* 确保被引用的对象保留在内存（ensuring that any referenced objects remain in memory）
* 恢复那些引用不再可达的对象锁占据的内存（recovering memory used by objects that are no longer reachable from references in executing code）

### 垃圾收集器（Garbage Collector）的特征

* 安全和全面（safe and comprehensive）
* 执行效率（operate efficiently）
* 限制内存碎片（the limitation of fragmentation）
* 伸缩性（Scalability）

### 垃圾收集器（Garbage Collector）的性能指标

* 吞吐量（Throughput）
* 垃圾收集开销（Garbage collection overhead）
* 停顿时间（Pause time）
* 收集频率（Frequency of collection）
* 内存印记（Footprint）
* 敏捷（Promptness）

### HotSpot 内存池（Memory Pools）

* < Java 8

  ![memory-pools](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/2.0-memory-pools.png)

### HotSpot 分代收集（Generational Collection）

![generational-collection](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/3.0-generational-collection.png)

### HotSpot 新生代（Young Generation）

* Young/New Generation = Eden + 2 Survivor spaces

* 参数：-Xmn

  ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/3.1-young-generation.png)

#### Eden 区域

* 初始化分配大多数对象（少量大对象可能直接在老年代分配）

* 可划分为一个或多个 Thread Local Allocation Buffer（TLAB）

  ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/4.0-eden.png)

##### Thread Local Allocation Buffer（TLAB）

For multithreaded applications, allocation operations need to be mutithread-safe. If global locks were used to ensure this, then allocation into a generation would become a bottleneck and degrade performance. Instead, the HotSpot JVM has adopted a technique called Thread-Local Allocation Buffers(TLABs). This improves multithreaded allocation throughput by giving each thread its own buffer(i.e., a small portion of the generation) from which to allocate. Since only **one thread can be allocating into each TLAB**, allocation can take place quickly by utilizing the bump-the-pointer technique, **without requiring any locking**.

#### Survivor 区域

* 由 from 和 to 两个空间组成，同一时间总有一个空间为空

* 存放至少经历过一次 YGC 的对象，被认为足够“老”的对象将会提升（“promote”）至老年代

  ![survivor](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/5.0-survivor.png)

* 当“年老”的对象 GC 超过提升阈值（tenuring threshold），就会被提升到老年代区域，而不会再从 from 复制到 to 区域。
* 参数：-XX:+MaxTenuringThreshod，提升阈值，默认值为 15 （GC 次数）

### HotSpot 老年代（Old Generation）

* 存储：经历多次
* GC：实现复杂，次数较少，垃圾概率小，标记所有通过 GC roots 可达的对象，移除所有不可达对象
* 空间：较大，最大值 = -Xmx 减去 -Xmn

### HotSpot 永久代（Permanent Generation）

* 存储：元数据（metadata），如 Class 信息，`String.intern()` 对象
* GC：FGC 将会回收该区域
* 空间：非区域，-XX：MaxPermSize 参数调整最大值
* 异常：`java.lang.OutOfMemoryError: Permgen space` 
* 场景：Java 8 开始移除该区域，使用 Metaspace 代替

### HotSpot 元数据区（Metaspace）

* 存储：元数据（metadata），如 Class 信息，`String.intern()` 对象
* GC：FGC 将会回收该区域
* 空间：非区域，本地内存，默认去看，仅受限于 Java 进程可用的本地内存 -XX:MaxMetaspaceSize 参数调整最大值
* 场景：Java 8 开始引入，代替永久代（Permanent Generation）

### 垃圾收集事件（Garbage Collection Events）

* Minor GC：次要 GC（小规模 GC），清理 Young/New Generation
* Major GC：主要 GC（大规模 GC），清理 Old Generation
* Full GC：完全 GC，清理整个堆（Heap）



## 传统垃圾回收器概览

### HotSpot 垃圾收集器实现

* **Serial Collector**
* **Parallel Collector**
* **Parallel Compacting Collector**
* **Concurrent Mark-Sweep（CMS）Collector**
* G1 Collector
* Shenandoah Collector

### 垃圾收集器

![garbage-collector](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/6.0-garbage-collector.png)

* Serial GC 能够处理新生代和老年代
* Parallel GC 能够处理新生代和老年代
* Parallel New 仅处理新生代
* Concurrent Mark and Sweep（CMS）仅处理老年代
* G1 比较特殊不区分新生代和老年代



## 串行垃圾回收器

### 串行垃圾回收器（Serial Collector）

With the serial collector, both young and old collections are done serially(using a single CPU), in a **stop-the-world** fashion. That is, application execution is halted while collection is taking place.

#### 收集场景

* 新生代收集（Young Generation Collection）
* 老年代收集（Old Generation Collection）

### 新生代收集（Young Generation Collection）

* 算法：**mark-copy**
* Eden区域：YGC 后的存活对象复制到 To Survivor 区域
  * **足够“老”**的存活对象直接复制到老年代（Old）
* From 区域：**相对“年轻”**的存活对象也复制到 To 区域
  * **相对“老”**的存活对象直接复制到老年代（Old）
* To 区域：当空间满后，Eden 和 From 将不会复制对象
  * 至此，无论在这两个区域存活对象经历多少次 YGC

* 当 Eden 和 From 区域中的存活对象被复制后，垃圾收集器将标记它们为**待回收对象**（图中X表示），不再检测它们的可达性（Reachable）

![serial-gc](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/7.0-serial-gc.png)

* 当 YGC 完成后，Eden 和 To 区域将被清空，From区域将存放仍存活的对象，也就说 From 区域和 To 区域的角色互换

  ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/7.1-serial-gc.png)

### 老年代收集（Old Generation Collection）

* 算法：mark-sweep-compact

* 阶段：

  * 标记（mark）阶段：鉴别哪些对象是否存活
  * 清扫（sweep）阶段：清扫分代信息，识别垃圾
  * 压缩（compact）阶段：a.k.a 滑动（Sliding-Compaction），将存活对象滑动到老年代空间的起始区域，使得空间区域连续

  ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/8.0-serial-gc-old.png)

#### 使用场景

* 客户端级别应用（Client）
  * 无需低停顿时间（Low pause time）
  * 低资源设备
    * 64 MB 堆空间
    * 单 CPU
* 自动选择
  * 非服务器级别机器
    * 客户端 JVM（JVM 命令行参数：-client）
* 手动选择
  * JVM 命令行参数：-XX:+UseSerialGC

## 并行垃圾回收器

### 并行垃圾回收器（Parallel Collector）

These days,l many Java applications run on machines with a lot of physical memory and multiple CPUs. The parallel collector, also known as the **throughput collector**, was developed in order to **take advantage of available CPUs** rather than leaving most of them idle while only one does garbage collection work.

* 收集场景
  * 新生代收集（Young Generation Collection）
  * 老年代收集（Old Generation Collection） -  与 Serial Collector 相同

### 新生代收集（Young Generation Collection）

* 算法：mark-copy（来源于 Serial Controller）

* 区别：Parallel Collector 是并行执行，非串行

  ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/9.1-parallel-gc.png)

  ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/9.0-parallel-gc.png)

### 老年代收集（Old Generation Collection）

* 算法：mark-sweep-compact（与 Serial collector 一样）
* 注意：Parallel Collector 使用的 Serial Controller 串行算法，而**非并行**。换言之，Parallel Collector 仅收集新生代（Young Generation）：
  * Parallel Collector -> Young Generation Collection
  * Serial Controller  -> Old Generation Collection 

### 使用场景

* 服务器级别应用（Server）
  * 多处理器（Multiple-Processors）
  * 批处理
  * 账单计算
  * 科学计算
* 自动选择
  * 服务器级别机器（JVM 命令行参数：-client）
* 手动选择
  * JVM 命令行参数：-XX:+UseSerialGC

## 并行压缩垃圾回收器

### 并行压缩垃圾回收器（Parallel Compacting Collector）

The parallel compacting collector was introduced in **J2SE 5.0 update 6.** The difference between it and the parallel collector is that it **uses a new algorithm for old generation garbage collection**. Note: Eventually, the parallel compacting collector will replace the parallel collector.

* 收集场景
  * 新生代收集（Young Generation Collection）- **与 Parallel Collector 相同**
  * 老年代收集（Old Generation Collection）

### 新生代收集（Young Generation Collection）

* 算法：mark-copy（来源于 Serial Collector）
* 注意：与 Parallel Collector

### 老年代收集（Old Generation Collection）

* 算法：mark-summary-compact（不同于 Parallel/Serial Collector **mark-sweep-compact**）
* 阶段
  * 标记（mark）阶段：
    * 首先，每个分代被逻辑划分为固定大小的区域（Regions）。
    * 从应用代码中直接可达的存活对象所组成的初始化集合被分配到并行的 GC 线程上，所有的存活对象随后被并行地标记。
    * 一旦对象被识别为存活状态，它所在的区域的空间大小及位置信息将被更新。
  * 总结（summary）阶段：
    * 该阶段操作的是区域（Regions），而非对象（Objects）。
    * 由于前一次垃圾收集，导致堆的分代空间左端（开始端）的某些部分变得密集（存放大多数存活对象）。
    * 这些密集（Dense）的区域并不值得再次压缩（Compact），所以本阶段首要的工作是从最左端开始检测区域的密集性，直到达到一个临界点，其右边部分视作值得压缩区域。
    * 这点称之为“密集前缀”，不再有对象移动到这些区域。
    * 临界点右方的区域将被压缩，删除所有无效空间。本阶段将为每个被压缩区域计算并且存储存活对象首字节的新位置
  * 压缩（compact）阶段：
    * GC 线程将使用总结阶段的数据，以识别那些需要被填充的区域。每个线程能够独立的复制数据到这些区域。本阶段生成一个堆，它将密集地塞入区域地一端，另一端则紧随着单个大且空地模块。

* 使用场景
  * 多处理器（Multiple-Processors）
* JVM 命令行参数
  * 设置并行线程数量：-XX:ParallelGCThreads
  * 激活：-XX:+UseParallelOldGC

## 并发标记和清除垃圾回收器

Young generation collections do not typically cause long pauses. However, old generation collections, though infrequent, can impose long pauses, especially when large heaps are involved. To address this issue, the HotSpot JVM includes a colector called the concurrent mark-sweep(CMS) collector, also known as the **low-latency collector**.

* 收集场景
  * 新生代收集（Young Generation Collection）- **与 Parallel Collector 相同**
  * 老年代收集（Old Generation Collection）

### 老年代收集（Old Generation Collection）

#### 阶段

* 初始化标记（initial mark）阶段：

  * （短暂停顿）识别应用代码中直接可达地存活对象所组成地初始化集合

* 并发标记（concurrent mark）阶段：

  * 标记初始化集合中所有传递可达的存活对象

* 重标记（remark）阶段：

  * （短暂停顿）通过重新访问在并发标记阶段中修改的对象，确认标记，确保堆中的所有存活对象已标记

* 并发清扫（concurrent sweep）阶段：

  * 清除所有被识别的垃圾

  ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/10.1-concurrent-mark-sweep-gc.png)

  ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/10.0-concurrent-mark-sweep-gc.png)

#### 使用场景

* 多处理器（Multiple-Processors）
* 短暂停顿，快速响应（Response Time），如 Web 站点
* 长期存活的数据，如 JVM 缓存

#### JVM 命令行参数

* 激活：-XX:+UseConcMarkSweepGC





















