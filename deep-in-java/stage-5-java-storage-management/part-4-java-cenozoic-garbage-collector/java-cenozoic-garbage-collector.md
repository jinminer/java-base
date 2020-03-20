# Java 新型垃圾回收器

## 内容提要

* 回顾传统垃圾收集器
* Garbage First(G1) 垃圾收集器介绍
* G1 垃圾收集器基本概念
* G1 垃圾收集器内部细节
* G1 垃圾收集器 V.S. 传统垃圾收集器



## 回顾传统垃圾收集器

### HotSpot 垃圾收集器实现

* Serial Collector
* Parallel Collector
* Parallel Compacting Collector
* Concurrent Mark-Sweep(CMS) Collector

### 垃圾收集器

* Serial GC 能够处理新生代和老年代
* Parallel GC 能够处理新生代和老年代
* Parallel New 仅处理新生代
* Concurrent Mark and Sweep（CMS）仅处理老年代

#### 串行垃圾收集器（Serial Collector）

With the serial collector, both young and old collections are done serially(using **a single CPU**), in a **stop-the-world** fashion, That is, application execution is halted while collection is taking place.

* 收集场景
  * 新生代收集（Young Generation Collection）
  * 老年代收集（Old Generation Collection）

#### 并行垃圾收集器（Parallel Collector）

These days, many Java applications run on machines with a lot of physical memory and multiple CPUs. The parallel collector, also known as **the throughput collector**,was developed in order to **take advantage of available CPUs** rather than leaving most of them idle while only one does garbage collection work.

* 收集场景
  * 新生代收集（Young Generation Collection）
  * 老年代收集（Old Generation Collection）- **与 Serial Collector 相同** 

### 并行压缩垃圾收集器（Parallel Compacting Collector）

The parallel compacting collector was introduced in **J2SE 5.0 update 6**. The difference between it and the parallel collector is that it **uses a new algorithm for old generation garbage collection**. Note: Eventually, the parallel compacting collector will replace the parallel collector.

* 收集场景
  * 新生代收集（Young Generation Collection）- **与 Parallel Collector 相同** 
  * 老年代收集（Old Generation Collection）

### 并发标记和清扫垃圾收集器（Concurrent Mark-Sweep Collector）

Young generation collections do not typically cause long pauses. However, old generation collections, though infrequent, can impose long pauses, especially when large heaps are involved. To address this issue, the HotSpot JVM includes a collector called the concurrent mark-sweep(CMS) collector, also known as the **low-latency collector**.

* 收集场景
  * 新生代收集（Young Generation Collection）- **与 Parallel Collector 相同** 
  * 老年代收集（Old Generation Collection）



## Garage First(G1) 垃圾收集器介绍

The Garbage-First(G1) garbage collector is fully supported in Oralce JDK 7 update 4 and later releases. The G1 collector is a server-style garbage collector, targeted for **multiprocessor machines** with a **large amount of memory**. It attempts to meet garbage collection **pause-time** goals with high probability while achieving **high throughput** with little need for configuration. 

Whole-heap operations, such as global marking, are performed concurrently with the application threads. This prevents interruptions proportional to heap or live-data size.

G1 aims to provide the best balance between latency and throughput using current target applications and environments.

### G1 垃圾收集器使用场景

G1 is planned as the long term replacement for the Concurrent Mark-Sweep Collector(CMS)

* Heap sizes up to ten of GBs or larger, with more than 50% of the Java heap occupied with live data.
* Rates of object allocation and promotion that can vary significantly over time.
* A significant amount of fragmentation in the heap.
* Predictable pause-time target goals that aren't longer than a few hundred milliseconds, avoiding long garbage collection pauses.



## G1 垃圾收集器基本概念

### G1 垃圾收集器属性

* Future CMS Replacement
* Server "Style" Garbage Collector
* Parallel
* Concurrent
* Generational

* Good Throughput
* **Compacting** - G1 与 CMS 的主要不同之处
* **Improved case-of-use** - G1 与 CMS 的主要不同之处
* **Predictable（though not hard real-time）** - G1 与 CMS 的主要不同之处

### G1 垃圾收集器 Heap 布局

* G1 将 Heap 划分成固定大小的区域（Regions），G1 中的新生代就是一个区域集合，它无需连续的内存区域。老年代同样如此。每个区域是内存分配和回收的单元，它在某个时间可能空闲，或者被分配到某个分代（新生代或老年代）。当内存请求时，内存管理器将给予空闲的区域，分配他们到某个分代，随后归还应用分配的空间。大多数 GC 操作同一时间在一个区域操作，而非整个 Java Heap 或某个 Heap 分代。

* 区域空间大小依赖于 Heap 空间大小，必须时 2 的倍数，最小 1MB， 最大 32 MB，因此，有六种可能：1、2、4、8、16、以及 32 MB。

  ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-4-java-cenozoic-garbage-collector/1.0-g1-regions.png)

  * 灰色区域：空闲空间
  * 红色区域：新生代 Eden
  * 红色区域（S）：新生代 Survivor 区域
  * 蓝色区域：老年代区域
  * 篮球区域（H）：大老年代区域（跨多区域）

### G1 垃圾收集器收集周期

* G1 垃圾收集器由两个周期轮替，即 Yong-only 和 Space-reclamation。
  * Young-only 阶段的垃圾收集是逐渐地将老年代对象填充到当前可用地内存。

  * Space-reclamation 阶段则是回收老年代地空间，附加处理新生代。随后地收集周期又从 Young-only 开始。

    ![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-4-java-cenozoic-garbage-collector/1.1-g1-gc-cycle.png)

#### Young-only 阶段

* 该阶段始于一些 Yong-only 收集， 即提升对象到老年代。
* Young-only 与 Space-reclamation 地过渡实际始于老年代空间占用到某个阈值，即 Heap 初始化占用阈值。
* 此时，G1 将调度 Initial Mark 的 Young-only 收集，而不是常规的Young-only 收集

#### Initial Mark 收集

* 这类收集始于标记过程，附带一个常规的 Young-only 收集。
* 并发标记决定所有在老年代区域中可达的存活对象是否保留到 Space-reclamation 阶段。
* 当标记多次尚未完成时，它将伴随两个提升的 Stop-The-World 停顿，即 Remark 和 Cleanup。

#### Remark 停顿

* 该停顿将中止标记，执行全局应用处理以及类的卸载。
* Remark 与 Cleanup 间，G1 将并发地计算出一份对象存活性总结报告，它将在 Cleanup 停顿时介绍并用于更新内部地数据结构。

#### Cleanup 停顿

* 该停顿同样将完整地回收空闲区域，并且决定 Space-reclamation 阶段是否将继续跟随。
* 如果继续跟随的话，Young-only 阶段的完成仅为 Young-only 收集。

#### Space-reclamation 阶段

* 这个阶段由多个混合的收集组成，不仅包含新生代区域，同时也撤出老年代区域的存活对象。
* 当 G1 认为撤出更多的老年代区域也无法满足空闲的空间时，G1 将中止本阶段。
* 如果应用消耗完内存时，G1 将执行 Stop-The-World 的全堆压缩（Full GC）



## G1 垃圾收集器内部细节

### 初始化 Heap 空间占用

* Initiating Heap Occupancy Percent（IHOP）：Initial Mark 收集触发的阈值，为老年代空间定义 Heap 占用百分比
  * JVM 设置参数：`-XX:InitiatingHeapOccupancyPercent`
* 默认情况，根据标记时间以及老年代在标记周期中的内存分配，G1 垃圾收集器将自动选择理想的 IHOP 值。
  * JVM 失效参数：`-XX:-G1UseAdaptiveIHOP`

### 超大对象（Humongous Objects）

* 超大对象是指那些空间大小等于或超过 1/2 个区域空间的对象（区域空间大小可通过 JVM 参数 `-XX:G1HeapRegionSize` 调整）。超大对象有时被以下特殊方式处理：
  * 每个超大对象在老年代区域中的连续区域分配。对象分配起始于连续区域中的首个成员。如果连续区域中的最后一个区域存在剩余空间的话，那么该空间将失去分配的机会，直到其关联的超大对象被完全地回收。
  * 超大对象地回收通常仅在 Cleanup 停顿中地 Marking 结束后被，或者在 Full GC 时
  * 超大对象地分配可能造成垃圾收集停顿过早地发生
  * 超大对象绝不会发生移动，即使在没有 Full GC 地情况下

## G1 垃圾收集器 V.S. 传统垃圾收集器



































































































































