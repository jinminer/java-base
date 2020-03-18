package com.jinm.deepinjava.garbagecollector.traditional;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryManagerMXBean;
import java.util.Arrays;

public class MemoryPoolDemo {

    /**
     * -XX:+UseConcMarkSweepGC（默认日志）：
     * 当前 MemoryManagerMXBean 名称：CodeCacheManager，以及它关联的内存池名称: [Code Cache]
     * 当前 MemoryManagerMXBean 名称：Metaspace Manager，以及它关联的内存池名称: [Metaspace]
     * 当前 MemoryManagerMXBean 名称：Copy，以及它关联的内存池名称: [Eden Space, Survivor Space]
     * 当前 MemoryManagerMXBean 名称：MarkSweepCompact，以及它关联的内存池名称: [Eden Space, Survivor Space, Tenured Gen]
     *
     * -XX:UseSerialGC：
     * 当前 MemoryManagerMXBean 名称：CodeCacheManager，以及它关联的内存池名称: [Code Cache]
     * 当前 MemoryManagerMXBean 名称：Metaspace Manager，以及它关联的内存池名称: [Metaspace]
     * 当前 MemoryManagerMXBean 名称：Copy，以及它关联的内存池名称: [Eden Space, Survivor Space]
     * 当前 MemoryManagerMXBean 名称：MarkSweepCompact，以及它关联的内存池名称: [Eden Space, Survivor Space, Tenured Gen]
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        // JMX 里面
        ManagementFactory.getMemoryManagerMXBeans()
                .forEach(mxBean -> {
                    System.out.printf("当前 MemoryManagerMXBean 名称：%s，以及它关联的内存池名称: %s\n",
                            mxBean.getName(),
                            Arrays.toString(mxBean.getMemoryPoolNames()));
                });

        System.in.read();

    }

}
