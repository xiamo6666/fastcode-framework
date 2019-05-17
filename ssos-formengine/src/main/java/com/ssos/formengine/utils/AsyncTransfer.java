package com.ssos.formengine.utils;


import java.util.concurrent.*;

/**
 * @ClassName: AsyncTransfer
 * @Description: 异步调用
 * @Author: xwl
 * @Date: 2019-05-17 14:39
 * @Vsersion: 1.0
 */
public class AsyncTransfer {
    private final static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public final static <T> T invoke(Callable<T> callable) throws ExecutionException, InterruptedException {
        Future<T> submit = executorService.submit(callable);
        return submit.get();

    }

}
