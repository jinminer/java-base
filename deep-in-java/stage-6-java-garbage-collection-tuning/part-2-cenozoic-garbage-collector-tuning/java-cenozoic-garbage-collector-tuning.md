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





## 吞吐量 G1 调优







































































