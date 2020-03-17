# Java 内存分配

## 内容提要

* Java 内存结构
* Java 栈（Stack）
* Java 堆（Heap）
* 总结



## Java 内存结构

### Java 内存结构（图）

![java-memory-structure-diagram](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-2-java-memory-allocation/1.0-java-memory-structure-diagram.png)

### Java 内存区域部分

* Java 堆（Heap）
* 方法区（Method Area）
* JVM 栈（JVM Stack）
* 本地方法栈（Native Method Stack）
* PC 寄存器（PC Registers）

### Java 堆（Heap）

* 内存中共享的运行时数据区域，用于存储 Java 对象。当 JVM 启动时，被实例化
* 为 Java 对象和数组分配内存空间，堆的空间大小可为固定的或动态的，其取决于系统配置

* JVM 提供给用户初始化或改变堆空间大小的控制，当 Java 对象被 new 关键字创建时，其对象分配至空间，而对象的引用则存在于 JVM 栈空间
* 单个 JVM 进程有且仅有一个 Heap 区域

### 方法区（Method Area）

* 属于 Heap 的逻辑区域，在 JVM 启动时创建
* 为类（接口）结构、方法（特殊方法）数据、构造器以及字段数据分配的内存区域，空间大小可为固定的或动态的，其取决于系统配置
* 能够固定空间大小或者根据计算需要扩容，无需连续的空间

### JVM 栈（JVM Stacks）

* 在线程创建时，栈也随之创建。在线程启动后，被用作存储临时结果或部分来源于方法执行结果、以及动态链接库

* 栈的空间大小既能固定，又能动态调整，甚至在创建时独立设置大小

* 内存空间无需连续

  ![java-object-reference](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-2-java-memory-allocation/2.0-java-object-reference.png)

### 本地方法栈（Native Method Stack）

* 又名 C 语言栈，本地方法栈非 Java 编程语言编写，它在其关联的线程创建时分配空间，自然也能固定和动态调整空间大小
* PC 寄存器（PC Registers）
  * Each JVM thread which carries out the task of a specific method has a program counter register associated with it. The non native method has a PC which stores the address of the available JVM instruction whereas in a native method, the value of program counter is undefined.



## Java 栈（Stack）

### 编程语言的栈（Stack）

Since the call stack is organized as a stack, the caller pushes the return address onto the stack, and the called function, when it finishes, pops the return address off the call stack and transfers control to that address. If a called function calls on to yet another function, it will push another return address onto the call stack, and so on, with the information stacking up and unstacking as the program dictates.

In high-level programming languages, the specifics of the call stack are usually hidden from the programmer. They are given access only to a set of functions, and not the memory on the stack iteself.

### Java 语言中的栈（Stack）

> Each Java virtual machine thread has a private *Java virtual machine stack*, created at the same time as the thread.[3](https://docs.oracle.com/javase/specs/jvms/se6/html/Overview.doc.html#30937) A Java virtual machine stack stores **frames** ([§3.6)](https://docs.oracle.com/javase/specs/jvms/se6/html/Overview.doc.html#17257). A Java virtual machine stack **is analogous to** the stack of a conventional language such as C: it holds local variables and partial results, and plays a part in method invocation and return. Because the Java virtual machine stack is never manipulated directly except to push and pop frames, frames may be heap allocated. The memory for a Java virtual machine stack **does not need to be contiguous**.

In the first edition of this specification, the Java virtual machine stack was known as the *Java stack*.

《**The JavaTM Virtual Machine Specification, Java SE 8 Edition**》 -2.5.2 Java Virtual Machine Stacks



## Java 堆（Heap）

The heap is created on virtual machine start-up. Heap storage for objects is reclaimed by an automatic storage management system (known as a *garbage collector*); objects are never explicitly deallocated. The Java Virtual Machine assumes no particular type of automatic storage management system, and the storage management technique may be chosen according to the implementor's system requirements. The heap may be of a fixed size or may be expanded as required by the computation and may be contracted if a larger heap becomes unnecessary. The memory for the heap does not need to be contiguous.

### Java 堆逻辑结构图

![java-storage-management](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-2-java-memory-allocation/3.0-java-heap.png)



### Java 非堆内存（Non-Heap）

* Metaspace
* Code Cache
* Method Area
* Run-time Constant Pool

### V.S Java Stacks

![java-stacks](https://raw.githubusercontent.com/jinminer/docs/master/java-base/deep-in-java/stage-5-java-storage-management/part-2-java-memory-allocation/4.0-java-stacks.png)

























































































