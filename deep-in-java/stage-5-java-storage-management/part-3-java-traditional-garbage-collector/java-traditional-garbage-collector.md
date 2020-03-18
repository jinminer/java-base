# Java 传统垃圾回收器

## 内容提要

* 内存管理
* 垃圾回收概念
* 传统垃圾回收器概念
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



## 传统垃圾回收器概念

### HotSpot 垃圾收集器实现

* Serial Collector
* Parallel Collector
* Parallel Compacting Collector
* Concurrent Mark-Sweep（CMS）Collector
* G1 Collector
* Shenandoah Collector

### 垃圾收集器

![garbage-collector](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-3-java-traditional-garbage-collector/6.0-garbage-collector.png)

* Serial GC 能够处理新生代和老年代
* Parallel GC 能够处理新生代和老年代
* Parallel New 仅处理新生代
* Concurrent Mark and Sweep（CMS）仅处理老年代
* G1 比较特殊不区分新生代和老年代























































































































































