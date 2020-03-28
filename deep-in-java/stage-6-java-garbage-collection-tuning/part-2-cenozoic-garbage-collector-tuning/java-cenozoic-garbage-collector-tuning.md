# G1 垃圾收集器调优

## 内容提要

* Garbage First（G1）垃圾收集器
* Garbage First（G1）阶段分代空间
* 提升 Garbage First（G1）性能
* 延迟时间 G1 调优
* 吞吐量 G1 调优
* 总结



## Garbage First（G1）垃圾收集器

### G1 垃圾收集器 Heap 布局

* 灰色区域：空间
* 红色区域：新生代
* 红色区域（S）：新生代 Survivor 区域
* 蓝色区域：老年代区域
* 蓝色区域（H）：大老年代区域（跨多区域）

### G1 垃圾收集器收集周期

G1 垃圾收集器由两个周期轮替，即 Young-only 和 Space-reclamation。Young-only 阶段的垃圾收集是逐渐地将老年代地对象填充到当前可用地内存。Space-reclamation 阶段则是回收老年代地空间。附加处理新生代。随后地收集周期又从 Young-only 开始。

### 初始 Heap 空间占用

* Initiating Heap Occupancy Percent（IHHOP）：Initial Mark 收集出发地阈值，为老年代空间定义 Heap 占用百分比
  * JVM 设置参数：`-XX:InitiatingHeapOccupancyPercent` 
* 默认情况，根据标记时间以及老年代在标记周期中的内存分配，G1 垃圾收集器将自动抉择理想的 IHOP 值。
  * JVM 失效参数：`-XX:-G1UseAdaptiveIHOP` 

### 超大对象（Humongous Objects）

超大对象是指那些空间大小等于或超过 1/2 个区域空间的对象（区域空间大小可通过 JVM 参数 `-XX:G1HeapRegionSize` 调整）。超大对象有时被以下特殊方式处理：

* 每个超大对象在老年代区域中的连续区域分配，对象分配起始于在连续区域中的首个成员。如果连续区域中的最后一个存在剩余空间的话，那么该空间将失去分配的机会，只到其关联的超大对象被完全地回收。
* 超大对象的回收通常仅在 Cleanup 停顿中的 Marking 结束后被、或者在 Full GC时。

* 超大对象的分配可能造成垃圾收集停顿过早的发生。
* 超大对象绝不会发生移动，即使在没有 Full GC 的情况下。



## Garbage First（G1）阶段分代空间

### Young-Only 阶段分区空间

多个新生代区域（红色）组成

* 满足停顿时间
  * `-XX:MaxGCPauseTimeMills`
  * `-XX:PauseTimeIntervalMills` 
* 时间与空间
  * `-XX:G1NewSizePercent`
  * `-XX:G1MaxNewSizePercent`

### Garbage First（G1）Ergonomics默认值

![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-6-java-garbage-collection-tuning/part-2-cenozoic-garbage-collector-tuning/0.0-g1-ergonomics.png)

![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-6-java-garbage-collection-tuning/part-2-cenozoic-garbage-collector-tuning/1.0-g1-ergonomics.png)

![](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-6-java-garbage-collection-tuning/part-2-cenozoic-garbage-collector-tuning/1.1-g1-ergonomics.png)



## 提升 Garbage First（G1）性能



## 延迟时间 G1 调优

### 高延迟通用问题

* 系统时间（System Time）占用过高
* 引用对象处理时间过长
* Young-Only 收集时间过长
* Mixed 收集时间过长
* Remerbered Set 更新和扫描时间过高

### System-Time 和 Real-Time

* 每次收集停顿时，gc+cpu=info 日志将会记录操作系统停顿时间，比如 `User=0.19s Sys=0.01s Real=0.01s` ，其中：
  * User 是用户时间，记录 JVM 代码所执行的时间
  * Sys 是系统时间，包含操作系统所花费的时间
  * Real 是停顿的绝对时间消耗

### 系统时间（System Time）占用过高

#### 场景一

* JVM 从操作系统内存中分配或回收内存可能造成不必要的延迟
* 解决方案 - 将内存操作提早到启动阶段
  * 将 VM 参数 -`Xms` 与 -`Xmx` 设置成相同数值
  * 并配合参数 `-XX:+AlwaysPreTouch` 

#### 场景二

* 在 Linux 中，Transparent Huge Pages（THP）特性将小页面合并成大页面，不仅仅是停顿，它将随机的拖慢进程，对于管理大内存的 JVM，这将是一个较高的风险，因此，关闭 THP 特性，减少操作系统所带来的时间消耗
* 解决方案（CentOS）
  * `echo 'never' > /sys/kernel/mm/transparent_hugepage/enabled` 
  * `echo 'never' > /sys/kernel/mm/transparent_hugepage/defrag` 

#### 场景三

* 不同应用进程向硬盘中写入大量的日志文件，可能造成 I/O 带宽瓶颈，造成一定的延迟
* 解决方案
  * 设置合理的 Buffer 大小
  * 替换文件系统的日志存储方式

### 引用对象处理时间过长

* 引用处理和入队所消耗的时间将显示在 GC 日志信息中
* 在引用处理阶段，G1 将更新引用对象（Reference）中被引用对象（根据它们特性类型）
* 在引用入队阶段，如果发现被引用对象已消亡，G1将入队引用对象（Reference）到它们各自的引用队列
* 如果以上过程处理时间较长，可以考虑增加 VM 参数 `-XX:+ParallelRefProcEnabled` 来处理

### Young-Only 收集时间过长

* 通常，Young 收集所消耗的时间与 Young 生代的空间大小大致上成正比。尤其是，大量的存活对象需要被复制
* 如果 Evacuate Collection Set 阶段执行时间较长的话，可以考虑减少新生代的 Heap 空间占比，即调低 `-XX:G1NewSizePercent` （实验性参数）
* 同样地，调低 `-XX:G1MaxNewSizePercent` 也可以减缓 Young 收集所带来地时间消耗

### Mixed 收集时间过长

* Mixed 收集在老生代回收空间，Mixed 收集包含新生代和老年代的 Regions，如果收集时间过长，首先调优 Young-Only 收集，再调优：
  * `-XX:G1MixedGCCountTarget` 
  * `-XX:G1MixedGCLiveThresholdPercent` 
  * `-XX:G1HeapWastePercent` 



## 吞吐量 G1 调优







































































