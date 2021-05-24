##ThreadStatus线程运行状态

```shell
Microsoft Windows [版本 10.0.19042.985]
(c) Microsoft Corporation。保留所有权利。
D:\repository\repo-learning\project-parent>jps
6948 ThreadStatus
4104 RemoteMavenServer36
7628 Jps
8636

D:\repository\repo-learning\project-parent>jstack 6948
2021-05-25 00:58:58
Full thread dump Java HotSpot(TM) Client VM (25.91-b15 mixed mode):

"DestroyJavaVM" #15 prio=5 os_prio=0 tid=0x0089c800 nid=0x21b0 waiting on condition [0x00000000]
   java.lang.Thread.State: RUNNABLE

"BlockDemo-02" #14 prio=5 os_prio=0 tid=0x14dadc00 nid=0x1c78 waiting for monitor entry [0x1554f000]
   java.lang.Thread.State: BLOCKED (on object monitor)
        at com.jinm.concurrent.phase1.ThreadStatus$BlockedDemo.run(ThreadStatus.java:43)
        - waiting to lock <0x04b68e50> (a java.lang.Class for com.jinm.concurrent.phase1.ThreadStatus$BlockedDemo)
        at java.lang.Thread.run(Thread.java:745)

"BlockDemo-01" #12 prio=5 os_prio=0 tid=0x14daac00 nid=0x598 waiting on condition [0x154bf000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at java.lang.Thread.sleep(Thread.java:340)
        at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
        at com.jinm.concurrent.phase1.ThreadStatus$BlockedDemo.run(ThreadStatus.java:43)
        - locked <0x04b68e50> (a java.lang.Class for com.jinm.concurrent.phase1.ThreadStatus$BlockedDemo)
        at java.lang.Thread.run(Thread.java:745)

"Waiting" #10 prio=5 os_prio=0 tid=0x14daa400 nid=0x2ad8 in Object.wait() [0x1542f000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x04946140> (a java.lang.Class for com.jinm.concurrent.phase1.ThreadStatus)
        at java.lang.Object.wait(Object.java:502)
        at com.jinm.concurrent.phase1.ThreadStatus.lambda$main$1(ThreadStatus.java:25)
        - locked <0x04946140> (a java.lang.Class for com.jinm.concurrent.phase1.ThreadStatus)
        at com.jinm.concurrent.phase1.ThreadStatus$$Lambda$2/23237446.run(Unknown Source)
        at java.lang.Thread.run(Thread.java:745)

"timewaiting" #9 prio=5 os_prio=0 tid=0x14da9c00 nid=0x2ae8 waiting on condition [0x1539f000]
   java.lang.Thread.State: TIMED_WAITING (sleeping)
        at java.lang.Thread.sleep(Native Method)
        at java.lang.Thread.sleep(Thread.java:340)
        at java.util.concurrent.TimeUnit.sleep(TimeUnit.java:386)
        at com.jinm.concurrent.phase1.ThreadStatus.lambda$main$0(ThreadStatus.java:13)
        at com.jinm.concurrent.phase1.ThreadStatus$$Lambda$1/24037599.run(Unknown Source)
        at java.lang.Thread.run(Thread.java:745)

"Service Thread" #8 daemon prio=9 os_prio=0 tid=0x14cfd400 nid=0x2aa0 runnable [0x00000000]
   java.lang.Thread.State: RUNNABLE

"C1 CompilerThread0" #7 daemon prio=9 os_prio=2 tid=0x14cf9800 nid=0x2bbc waiting on condition [0x00000000]
   java.lang.Thread.State: RUNNABLE

"Monitor Ctrl-Break" #6 daemon prio=5 os_prio=0 tid=0x14cf8c00 nid=0x2ac8 runnable [0x1515f000]
   java.lang.Thread.State: RUNNABLE
        at java.net.SocketInputStream.socketRead0(Native Method)
        at java.net.SocketInputStream.socketRead(SocketInputStream.java:116)
        at java.net.SocketInputStream.read(SocketInputStream.java:170)
        at java.net.SocketInputStream.read(SocketInputStream.java:141)
        at sun.nio.cs.StreamDecoder.readBytes(StreamDecoder.java:284)
        at sun.nio.cs.StreamDecoder.implRead(StreamDecoder.java:326)
        at sun.nio.cs.StreamDecoder.read(StreamDecoder.java:178)
        - locked <0x04996828> (a java.io.InputStreamReader)
        at java.io.InputStreamReader.read(InputStreamReader.java:184)
        at java.io.BufferedReader.fill(BufferedReader.java:161)
        at java.io.BufferedReader.readLine(BufferedReader.java:324)
        - locked <0x04996828> (a java.io.InputStreamReader)
        at java.io.BufferedReader.readLine(BufferedReader.java:389)
        at com.intellij.rt.execution.application.AppMainV2$1.run(AppMainV2.java:47)

"Attach Listener" #5 daemon prio=5 os_prio=2 tid=0x14c98000 nid=0x1f04 waiting on condition [0x00000000]
   java.lang.Thread.State: RUNNABLE

"Signal Dispatcher" #4 daemon prio=9 os_prio=2 tid=0x14c89000 nid=0x20b8 runnable [0x00000000]
   java.lang.Thread.State: RUNNABLE

"Finalizer" #3 daemon prio=8 os_prio=1 tid=0x14c75800 nid=0x1d90 in Object.wait() [0x14f3f000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x04807ee8> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:143)
        - locked <0x04807ee8> (a java.lang.ref.ReferenceQueue$Lock)
        at java.lang.ref.ReferenceQueue.remove(ReferenceQueue.java:164)
        at java.lang.ref.Finalizer$FinalizerThread.run(Finalizer.java:209)

"Reference Handler" #2 daemon prio=10 os_prio=2 tid=0x14c63400 nid=0xfc4 in Object.wait() [0x14eaf000]
   java.lang.Thread.State: WAITING (on object monitor)
        at java.lang.Object.wait(Native Method)
        - waiting on <0x04805f50> (a java.lang.ref.Reference$Lock)
        at java.lang.Object.wait(Object.java:502)
        at java.lang.ref.Reference.tryHandlePending(Reference.java:191)
        - locked <0x04805f50> (a java.lang.ref.Reference$Lock)
        at java.lang.ref.Reference$ReferenceHandler.run(Reference.java:153)

"VM Thread" os_prio=2 tid=0x0268c800 nid=0x1bec runnable

"VM Periodic Task Thread" os_prio=2 tid=0x14d00000 nid=0x1f48 waiting on condition

JNI global references: 319

```





