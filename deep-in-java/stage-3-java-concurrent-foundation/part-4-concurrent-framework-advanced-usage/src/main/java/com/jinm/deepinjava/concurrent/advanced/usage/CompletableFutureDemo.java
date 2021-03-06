package com.jinm.deepinjava.concurrent.advanced.usage;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) {
        //  CompletableFuture

        CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(String::valueOf)
                // 异常的方式结束
                .completeExceptionally(new RuntimeException())
        ;
    }
}
