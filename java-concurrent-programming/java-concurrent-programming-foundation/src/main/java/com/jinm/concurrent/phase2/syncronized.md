# 同步锁
## 原子性

```shell
javap -v AtomicDemo.class
Classfile /D:/repository/repo-learning/java-base/java-concurrent-programming/java-concurrent-programming-foundation/target/classes/com/jinm/concurrent/phase2/syncronized/AtomicDemo.class
  Last modified 2021-5-25; size 1881 bytes
  MD5 checksum 6c98d694f10c92e8775f20b6c93430c8
  Compiled from "AtomicDemo.java"
public class com.jinm.concurrent.phase2.syncronized.AtomicDemo
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #15.#45        // java/lang/Object."<init>":()V
   #2 = Fieldref           #3.#46         // com/jinm/concurrent/phase2/syncronized/AtomicDemo.num:I
   #3 = Class              #47            // com/jinm/concurrent/phase2/syncronized/AtomicDemo
   #4 = Methodref          #3.#45         // com/jinm/concurrent/phase2/syncronized/AtomicDemo."<init>":()V
   #5 = Class              #48            // java/lang/Thread
   #6 = InvokeDynamic      #0:#53         // #0:run:(Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;)Ljava/lang/Runnable;
   #7 = Methodref          #5.#54         // java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
   #8 = Methodref          #5.#55         // java/lang/Thread.start:()V
   #9 = Methodref          #5.#56         // java/lang/Thread.join:()V
  #10 = Class              #57            // java/lang/InterruptedException
  #11 = Methodref          #10.#58        // java/lang/InterruptedException.printStackTrace:()V
  #12 = Fieldref           #59.#60        // java/lang/System.out:Ljava/io/PrintStream;
  #13 = Methodref          #61.#62        // java/io/PrintStream.println:(I)V
  #14 = Methodref          #3.#63         // com/jinm/concurrent/phase2/syncronized/AtomicDemo.incr:()V
  #15 = Class              #64            // java/lang/Object
  #16 = Utf8               num
  #17 = Utf8               I
  #18 = Utf8               <init>
  #19 = Utf8               ()V
  #20 = Utf8               Code
  #21 = Utf8               LineNumberTable
  #22 = Utf8               LocalVariableTable
  #23 = Utf8               this
  #24 = Utf8               Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;
  #25 = Utf8               incr
  #26 = Utf8               main
  #27 = Utf8               ([Ljava/lang/String;)V
  #28 = Utf8               i
  #29 = Utf8               e
  #30 = Utf8               Ljava/lang/InterruptedException;
  #31 = Utf8               args
  #32 = Utf8               [Ljava/lang/String;
  #33 = Utf8               demo
  #34 = Utf8               threads
  #35 = Utf8               [Ljava/lang/Thread;
  #36 = Utf8               StackMapTable
  #37 = Class              #47            // com/jinm/concurrent/phase2/syncronized/AtomicDemo
  #38 = Class              #35            // "[Ljava/lang/Thread;"
  #39 = Class              #57            // java/lang/InterruptedException
  #40 = Utf8               lambda$main$0
  #41 = Utf8               (Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;)V
  #42 = Utf8               j
  #43 = Utf8               SourceFile
  #44 = Utf8               AtomicDemo.java
  #45 = NameAndType        #18:#19        // "<init>":()V
  #46 = NameAndType        #16:#17        // num:I
  #47 = Utf8               com/jinm/concurrent/phase2/syncronized/AtomicDemo
  #48 = Utf8               java/lang/Thread
  #49 = Utf8               BootstrapMethods
  #50 = MethodHandle       #6:#65         // invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/
invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #51 = MethodType         #19            //  ()V
  #52 = MethodHandle       #6:#66         // invokestatic com/jinm/concurrent/phase2/syncronized/AtomicDemo.lambda$main$0:(Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;)V
  #53 = NameAndType        #67:#68        // run:(Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;)Ljava/lang/Runnable;
  #54 = NameAndType        #18:#69        // "<init>":(Ljava/lang/Runnable;)V
  #55 = NameAndType        #70:#19        // start:()V
  #56 = NameAndType        #71:#19        // join:()V
  #57 = Utf8               java/lang/InterruptedException
  #58 = NameAndType        #72:#19        // printStackTrace:()V
  #59 = Class              #73            // java/lang/System
  #60 = NameAndType        #74:#75        // out:Ljava/io/PrintStream;
  #61 = Class              #76            // java/io/PrintStream
  #62 = NameAndType        #77:#78        // println:(I)V
  #63 = NameAndType        #25:#19        // incr:()V
  #64 = Utf8               java/lang/Object
  #65 = Methodref          #79.#80        // java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/Method
Handle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #66 = Methodref          #3.#81         // com/jinm/concurrent/phase2/syncronized/AtomicDemo.lambda$main$0:(Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;)V
  #67 = Utf8               run
  #68 = Utf8               (Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;)Ljava/lang/Runnable;
  #69 = Utf8               (Ljava/lang/Runnable;)V
  #70 = Utf8               start
  #71 = Utf8               join
  #72 = Utf8               printStackTrace
  #73 = Utf8               java/lang/System
  #74 = Utf8               out
  #75 = Utf8               Ljava/io/PrintStream;
  #76 = Utf8               java/io/PrintStream
  #77 = Utf8               println
  #78 = Utf8               (I)V
  #79 = Class              #82            // java/lang/invoke/LambdaMetafactory
  #80 = NameAndType        #83:#87        // metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType
;)Ljava/lang/invoke/CallSite;
  #81 = NameAndType        #40:#41        // lambda$main$0:(Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;)V
  #82 = Utf8               java/lang/invoke/LambdaMetafactory
  #83 = Utf8               metafactory
  #84 = Class              #89            // java/lang/invoke/MethodHandles$Lookup
  #85 = Utf8               Lookup
  #86 = Utf8               InnerClasses
  #87 = Utf8               (Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;
  #88 = Class              #90            // java/lang/invoke/MethodHandles
  #89 = Utf8               java/lang/invoke/MethodHandles$Lookup
  #90 = Utf8               java/lang/invoke/MethodHandles
{
  public com.jinm.concurrent.phase2.syncronized.AtomicDemo();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;

  public void incr();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0
         1: dup
         2: getfield      #2                  // Field num:I
         5: iconst_1
         6: iadd
         7: putfield      #2                  // Field num:I
        10: return
      LineNumberTable:
        line 8: 0
        line 9: 10
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      11     0  this   Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=5, locals=4, args_size=1
         0: new           #3                  // class com/jinm/concurrent/phase2/syncronized/AtomicDemo
         3: dup
         4: invokespecial #4                  // Method "<init>":()V
         7: astore_1
         8: iconst_2
         9: anewarray     #5                  // class java/lang/Thread
        12: astore_2
        13: iconst_0
        14: istore_3
        15: iload_3
        16: aload_2
        17: arraylength
        18: if_icmpge     49
        21: aload_2
        22: iload_3
        23: new           #5                  // class java/lang/Thread
        26: dup
        27: aload_1
        28: invokedynamic #6,  0              // InvokeDynamic #0:run:(Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;)Ljava/lang/Runnable;
        33: invokespecial #7                  // Method java/lang/Thread."<init>":(Ljava/lang/Runnable;)V
        36: aastore
        37: aload_2
        38: iload_3
        39: aaload
        40: invokevirtual #8                  // Method java/lang/Thread.start:()V
        43: iinc          3, 1
        46: goto          15
        49: aload_2
        50: iconst_0
        51: aaload
        52: invokevirtual #9                  // Method java/lang/Thread.join:()V
        55: aload_2
        56: iconst_1
        57: aaload
        58: invokevirtual #9                  // Method java/lang/Thread.join:()V
        61: goto          69
        64: astore_3
        65: aload_3
        66: invokevirtual #11                 // Method java/lang/InterruptedException.printStackTrace:()V
        69: getstatic     #12                 // Field java/lang/System.out:Ljava/io/PrintStream;
        72: aload_1
        73: getfield      #2                  // Field num:I
        76: invokevirtual #13                 // Method java/io/PrintStream.println:(I)V
        79: return
      Exception table:
         from    to  target type
            49    61    64   Class java/lang/InterruptedException
      LineNumberTable:
        line 13: 0
        line 14: 8
        line 16: 13
        line 17: 21
        line 22: 37
        line 16: 43
        line 26: 49
        line 27: 55
        line 30: 61
        line 28: 64
        line 29: 65
        line 32: 69
        line 34: 79
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
           15      34     3     i   I
           65       4     3     e   Ljava/lang/InterruptedException;
            0      80     0  args   [Ljava/lang/String;
            8      72     1  demo   Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;
           13      67     2 threads   [Ljava/lang/Thread;
      StackMapTable: number_of_entries = 4
        frame_type = 254 /* append */
          offset_delta = 15
          locals = [ class com/jinm/concurrent/phase2/syncronized/AtomicDemo, class "[Ljava/lang/Thread;", int ]
        frame_type = 250 /* chop */
          offset_delta = 33
        frame_type = 78 /* same_locals_1_stack_item */
          stack = [ class java/lang/InterruptedException ]
        frame_type = 4 /* same */
}
SourceFile: "AtomicDemo.java"
InnerClasses:
     public static final #85= #84 of #88; //Lookup=class java/lang/invoke/MethodHandles$Lookup of class java/lang/invoke/MethodHandles
BootstrapMethods:
  0: #50 invokestatic java/lang/invoke/LambdaMetafactory.metafactory:(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodType;Ljava/lang/invoke/MethodHandle;Ljava/lang/invok
e/MethodType;)Ljava/lang/invoke/CallSite;
    Method arguments:
      #51 ()V
      #52 invokestatic com/jinm/concurrent/phase2/syncronized/AtomicDemo.lambda$main$0:(Lcom/jinm/concurrent/phase2/syncronized/AtomicDemo;)V
      #51 ()V

```
* `i++` 这行java代码在cpu层面是三条指令
    ```shell
    5: iconst_1
    6: iadd
    7: putfield      #2                  // Field num:I
    ```
* 要保证 `i++`  操作的原子性，则需要在保证3条 cpu 指令的原子性
* java 关键字 `synchronized` 可以保证`i++` 操作的原子性，使其在多线程环境下串行执行


## MarkWork 对象头
* mark-word：对象标记字段占4个字节，用于存储一些列的标记位，比如：哈希值、轻量级锁的标
  记位，偏向锁标记位、分代年龄等。
  

## 使用JOL查看对象的内存布局
* 无锁
  * 在未加锁之前，对象头中的第一个字节最后三位为 [001], 其中最后两位 [01]表示无锁，第一位[0]也表示无锁
 
    ```shell
    com.jinm.concurrent.phase2.classlayout.Demo object internals:
     OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
          0     4                    (object header)                           09 00 00 00 (00001001 00000000 00000000 00000000) (9)
          4     4                    (object header)                           60 26 a3 14 (01100000 00100110 10100011 00010100) (346236512)
          8     4   java.lang.Object Demo.o                                    (object)
         12     4                    (loss due to the next object alignment)
    Instance size: 16 bytes
    Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
    ```

* 有锁：轻量级锁
  * 对象头中的的高位第一个字节最后三位数为[101]，表示当前为偏向锁状态。
  * 这里的第一个对象和第二个对象的锁状态都是101，是因为偏向锁打开状态下，默认会有配置匿名的对象获得偏向锁。

  ```shell
  com.jinm.concurrent.phase2.classlayout.LightweightLockDemo object internals:
   OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
        0     4                    (object header)                           09 00 00 00 (00001001 00000000 00000000 00000000) (9)
        4     4                    (object header)                           d0 26 23 15 (11010000 00100110 00100011 00010101) (354625232)
        8     4   java.lang.Object LightweightLockDemo.o                     (object)
       12     4                    (loss due to the next object alignment)
  Instance size: 16 bytes
  Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
  
  com.jinm.concurrent.phase2.classlayout.LightweightLockDemo object internals:
   OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
        0     4                    (object header)                           a4 f4 00 01 (10100100 11110100 00000000 00000001) (16839844)
        4     4                    (object header)                           d0 26 23 15 (11010000 00100110 00100011 00010101) (354625232)
        8     4   java.lang.Object LightweightLockDemo.o                     (object)
       12     4                    (loss due to the next object alignment)
  Instance size: 16 bytes
  Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
  
  
  Process finished with exit code 0
  
  ```
* 有锁：重量级锁
  * 在竞争的情况下锁的标记为 [010] ，其中所标记 [10]表示重量级锁
  ```shell
  main locking .....
  com.jinm.concurrent.phase2.classlayout.HeavyweightLockDemo object internals:
   OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        0     4        (object header)                           ae cf 0c 15 (10101110 11001111 00001100 00010101) (353161134)
        4     4        (object header)                           80 28 e3 14 (10000000 00101000 11100011 00010100) (350431360)
  Instance size: 8 bytes
  Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
  
  thread1 locking .....
  com.jinm.concurrent.phase2.classlayout.HeavyweightLockDemo object internals:
   OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
        0     4        (object header)                           ae cf 0c 15 (10101110 11001111 00001100 00010101) (353161134)
        4     4        (object header)                           80 28 e3 14 (10000000 00101000 11100011 00010100) (350431360)
  Instance size: 8 bytes
  Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
  ```


